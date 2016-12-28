package VideoStore

abstract class Price {
    abstract val code: Int
    abstract fun getCharge(daysRented: Int): Double
    open fun getFrequentRenterPoints(daysRented: Int): Int = 1
}