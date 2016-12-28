package VideoStore

public data class Rental(val movie: Movie, val daysRented: Int) {
    public val charge: Double
        get() = movie.getCharge(daysRented)
    public val frequentRenterPoints: Int
        get() = movie.getFrequentRenterPoints(daysRented)
}