package basic.lesson_11

class Rabbit(
    title: String,
    amountPaws: Int,
    status: basic.lesson_11.Status,
) : basic.lesson_11.Animal(title, amountPaws, status, isDomesticated = true) {

    override fun goAhead() {
        super.goAhead()
        println("so fast from wolf...")
    }

    override fun eat() {
        println("$title is eating something")
    }

    override fun jump() {
        println("$title is jumping somewhere")
    }

}