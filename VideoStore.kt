import java.util.*

/**
 * VideoStore
 * Responsibility:
 */

public data class Movie(val title: String, var priceCode: Int) {
    companion object {
        val CHILDRENS = 2
        val REGULAR = 0
        val NEW_RELEASE = 1
    }
}

public data class Rental(val movie: Movie, val daysRented: Int)

public data class Customer(val name: String) {
    private val rentals: MutableList<Rental> = ArrayList()

    fun addRental(rental: Rental) {
        rentals.add(rental)
    }

    fun statement(): String {
        var totalAmount: Double = 0.0
        var frequentRenterPoints: Int = 0
        val rentals = rentals.iterator()
        var result = "Rental Record for $name \n"

        while (rentals.hasNext()) {
            var thisAmount: Double = 0.0
            val each: Rental = rentals.next()

            // determine amounts for each line
            when (each.movie.priceCode) {
                Movie.REGULAR -> {
                    thisAmount += 2
                    if (each.daysRented > 2) {
                        thisAmount += (each.daysRented - 2) * 1.5
                    }
                }
                Movie.NEW_RELEASE -> {
                    thisAmount += each.daysRented * 3
                }
                Movie.CHILDRENS -> {
                    thisAmount += 1.5
                    if (each.daysRented > 3) {
                        thisAmount += (each.daysRented - 3) * 1.5
                    }
                }
            }

            // add frequent renter points
            frequentRenterPoints++
            // add bonus for a two day new release rental
            if ((each.movie.priceCode == Movie.NEW_RELEASE) && each.daysRented > 1) {
                frequentRenterPoints++
            }

            // show figures for this rental
            result += "\t${each.movie.title}\t${thisAmount}\n"
            totalAmount += thisAmount
        }

        //add footer lines
        result += "Amount owed is ${totalAmount}\n"
        result += "You earned ${frequentRenterPoints} frequent renter points"
        return result
    }
}