package VideoStore

class ChildrenPrice : Price() {
    override val code: Int = Movie.CHILDREN

    override fun getCharge(daysRented: Int): Double {
        var amount: Double = 1.5
        if (daysRented > 3) {
            amount += (daysRented - 3) * 1.5
        }
        return amount
    }
}