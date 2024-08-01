package basic.lesson_12

class WebWriter(
    private val title: String,
    private val text: String,
) : basic.lesson_12.Writer() {

    override fun write(): String {
        return "<head>$title</head>\n<div>$text</div>"
    }

}