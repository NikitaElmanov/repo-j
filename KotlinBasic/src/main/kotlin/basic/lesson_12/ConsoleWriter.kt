package basic.lesson_12

class ConsoleWriter(
    private val title: String,
    private val text: String,
) : basic.lesson_12.Writer() {

    override fun write(): String {
        return "$title\n$text"
    }

}