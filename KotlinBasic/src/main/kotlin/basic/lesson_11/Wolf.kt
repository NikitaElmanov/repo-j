package basic.lesson_11

class Wolf(
    title: String,
    amountPaws: Int,
    status: basic.lesson_11.Status,
) : basic.lesson_11.Animal(title, amountPaws, status) {

    override fun goAhead() {
        super.goAhead()
        println("just fast to rabbit...")
    }

    override fun jump() {
        println("$title is jumping somewhere")
    }

    override fun eat() {
        println("$title eats delicious meat...")
    }

}