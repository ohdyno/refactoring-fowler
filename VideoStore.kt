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

    fun getFrequentRenterPoints(): Int {
        return if ((movie.priceCode == Movie.NEW_RELEASE) && daysRented > 1) 2 else 1
    }
}

public data class Customer(val name: String) {
    private val rentals: MutableList<Rental> = ArrayList()

    fun addRental(rental: Rental) {
        rentals.add(rental)
    }

    fun statement(): String {
        var totalAmount: Double = 0.0
        val rentals = rentals.iterator()
        var result = "Rental Record for $name \n"
        var frequentRenterPoints: Int = 0

        while (rentals.hasNext()) {
            val each: Rental = rentals.next()
            frequentRenterPoints += each.getFrequentRenterPoints()

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