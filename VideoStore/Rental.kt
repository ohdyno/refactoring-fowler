package VideoStore

data class Rental(val movie: Movie, val daysRented: Int) {
    val charge: Double
        get() = movie.getCharge(daysRented)
    val frequentRenterPoints: Int
        get() = movie.getFrequentRenterPoints(daysRented)
}