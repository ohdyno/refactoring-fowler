import java.util.*

/**
 * VideoStore
 * Responsibility:
 */

public class Movie(val title: String, priceCode: Int) {
    companion object {
        val CHILDRENS = 2
        val REGULAR = 0
        val NEW_RELEASE = 1
    }

    private lateinit var price: Price

    public var priceCode: Int
        get() = price.priceCode
        set(value) {
            price = when (value) {
                REGULAR -> RegularPrice()
                CHILDRENS -> ChildrenPrice()
                NEW_RELEASE -> NewReleasePrice()
                else -> throw IllegalArgumentException("Incorrect Price Code")
            }
        }

    init {
        this.priceCode = priceCode
    }

    public fun getCharge(daysRented: Int): Double = price.getCharge(daysRented)


    public fun getFrequentRenterPoints(daysRented: Int): Int {
        return if ((priceCode == Movie.NEW_RELEASE) && daysRented > 1) 2 else 1
    }
}

abstract class Price {
    abstract val priceCode: Int

    fun getCharge(daysRented: Int): Double =
        when (priceCode) {
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

class ChildrenPrice : Price() {
    override val priceCode: Int = Movie.CHILDRENS
}

class NewReleasePrice : Price() {
    override val priceCode: Int = Movie.NEW_RELEASE
}

class RegularPrice : Price() {
    override val priceCode: Int = Movie.REGULAR
}

public data class Rental(val movie: Movie, val daysRented: Int) {
    public val charge: Double
        get() = movie.getCharge(daysRented)
    public val frequentRenterPoints: Int
        get() = movie.getFrequentRenterPoints(daysRented)
}

public data class Customer(val name: String) {
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
        var result = "Rental Record for $name \n"

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
        var result = "<h1>Rental Record for <em>$name</em></h1>\n"

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