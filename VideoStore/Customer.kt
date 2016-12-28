package VideoStore
import java.util.*

data class Customer(val name: String) {
    private val rentals: MutableList<Rental> = ArrayList()

    private val totalCharge: Double
        get() {
            var totalAmount: Double = 0.0
            val iterator = rentals.iterator()
            while (iterator.hasNext()) {
                val each: Rental = iterator.next()
                totalAmount = each.charge
            }

            return totalAmount
        }

    private val totalFrequentRenterPoints: Int
        get() {
            var totalFrequentRenterPoints: Int = 0
            val iterator = rentals.iterator()
            while (iterator.hasNext()) {
                val each: Rental = iterator.next()
                totalFrequentRenterPoints += each.frequentRenterPoints
            }
            return totalFrequentRenterPoints
        }

    fun addRental(rental: Rental) {
        rentals.add(rental)
    }

    fun statement(): String {
        val rentals = rentals.iterator()
        var result = "VideoStore.Rental Record for $name \n"

        while (rentals.hasNext()) {
            val each: Rental = rentals.next()
            // show figures for this rental
            result += "\t${each.movie.title}\t${each.charge}\n"
        }

        //add footer lines
        result += "Amount owed is ${totalCharge}\n"
        result += "You earned ${totalFrequentRenterPoints} frequent renter points"
        return result
    }

    fun htmlStatement(): String {
        val rentals = rentals.iterator()
        var result = "<h1>VideoStore.Rental Record for <em>$name</em></h1>\n"

        result += "<p>"
        while (rentals.hasNext()) {
            val each: Rental = rentals.next()
            // show figures for this rental
            result += "\t${each.movie.title}\t${each.charge}</br>\n"
        }
        result += "</p>"

        //add footer lines
        result += "<p>Amount owed is <em>${totalCharge}</em></p>\n"
        result += "<p>You earned <em>${totalFrequentRenterPoints}</em> frequent renter points</p>"
        return result
    }
}