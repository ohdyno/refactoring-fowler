package VideoStore

/**
 * VideoStore
 * Responsibility:
 */

class Movie(val title: String, priceCode: Int) {
    companion object {
        val CHILDREN = 2
        val REGULAR = 0
        val NEW_RELEASE = 1
    }

    private lateinit var price: Price

    var priceCode: Int
        get() = price.code
        set(value) {
            price = when (value) {
                REGULAR -> RegularPrice()
                CHILDREN -> ChildrenPrice()
                NEW_RELEASE -> NewReleasePrice()
                else -> throw IllegalArgumentException("Incorrect VideoStore.Price Code")
            }
        }

    init {
        this.priceCode = priceCode
    }

    fun getCharge(daysRented: Int): Double = price.getCharge(daysRented)

    fun getFrequentRenterPoints(daysRented: Int): Int = price.getFrequentRenterPoints(daysRented)
}