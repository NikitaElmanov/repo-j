import basic.lesson_11.Rabbit
import basic.lesson_11.Status
import basic.lesson_11.Wolf

fun main() {

    val rabbit = basic.lesson_11.Rabbit(
        "Vasy",
        4,
        basic.lesson_11.Status.RELEASE
    )

    val wolf = basic.lesson_11.Wolf(
        "Ignat",
        4,
        basic.lesson_11.Status.CURED
    )

    wolf.goAhead()
    rabbit.goAhead()

    rabbit.jump()
    wolf.eat()

    println(rabbit.speed)
    println(rabbit.status())

}
