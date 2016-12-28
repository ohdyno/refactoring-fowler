package VideoStore

class RegularPrice : Price() {
    override val code: Int = Movie.REGULAR

    override fun getCharge(daysRented: Int): Double {
        var amount: Double = 2.0
        if (daysRented > 2) {
            amount += (daysRented - 2) * 1.5
        }
        return amount
    }
}