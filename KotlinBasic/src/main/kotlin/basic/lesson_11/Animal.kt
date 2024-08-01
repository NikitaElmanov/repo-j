package basic.lesson_11

abstract class Animal(
    protected val title: String,
    protected val amountPaws: Int,
    protected val status: basic.lesson_11.Status,
    protected val isDomesticated: Boolean = false,
) : basic.lesson_11.Movable {

    var speed = 100
        get() = field
        set(value) {
            field = value * 2
        }

    fun status() = status

    abstract fun eat()

}