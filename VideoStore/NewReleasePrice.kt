package VideoStore

class NewReleasePrice : Price() {
    override val code: Int = Movie.NEW_RELEASE

    override fun getCharge(daysRented: Int): Double = daysRented * 3.0

    override fun getFrequentRenterPoints(daysRented: Int): Int = if (daysRented > 1) 2 else 1
}