package VideoStore

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
        get() = price.code
        set(value) {
            price = when (value) {
                REGULAR -> RegularPrice()
                CHILDRENS -> ChildrenPrice()
                NEW_RELEASE -> NewReleasePrice()
                else -> throw IllegalArgumentException("Incorrect VideoStore.Price Code")
            }
        }

    init {
        this.priceCode = priceCode
    }

    public fun getCharge(daysRented: Int): Double = price.getCharge(daysRented)

    public fun getFrequentRenterPoints(daysRented: Int): Int = price.getFrequentRenterPoints(daysRented)
}