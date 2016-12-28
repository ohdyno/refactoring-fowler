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

public data class Rental(val movie: Movie, val daysRented: Int) {
    public fun getCharge(): Double =
            when (movie.priceCode) {
                Movie.REGULAR -> {
                    var amount: Double = 2.0
                    if (daysRented > 2) {
                        amount += (daysRented - 2) * 1.5
                    }
                    amount
                }
                Movie.NEW_RELEASE -> {
                    daysRented * 3.0
                }
                Movie.CHILDRENS -> {
                    var amount: Double = 1.5
                    if (daysRented > 3) {
                        amount += (daysRented - 3) * 1.5
                    }
                    amount
                }
                else -> 0.0
            }
}

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
            val each: Rental = rentals.next()

            // add frequent renter points
            frequentRenterPoints++
            // add bonus for a two day new release rental
            if ((each.movie.priceCode == Movie.NEW_RELEASE) && each.daysRented > 1) {
                frequentRenterPoints++
            }

            // show figures for this rental
            result += "\t${each.movie.title}\t${each.getCharge()}\n"
            totalAmount += each.getCharge()
        }

        //add footer lines
        result += "Amount owed is ${totalAmount}\n"
        result += "You earned ${frequentRenterPoints} frequent renter points"
        return result
    }
}