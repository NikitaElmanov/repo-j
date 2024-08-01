import basic.lesson_12.ConsoleWriter
import basic.lesson_12.WebWriter

fun main() {

    val consoleWriter =
        basic.lesson_12.ConsoleWriter(
            "Title 1",
            """With chicories drink coffee.
            |Protons malfunction from moons like evasive suns.""".trimMargin()
        )
    val webWriter = basic.lesson_12.WebWriter(
        "Title 2",
        """Sons stutter on amnesty at norman island!
            |PRemember: toasted quinoa tastes best when cut in a jar blended with dill.""".trimMargin()
    )

    println(consoleWriter.write())
    println(webWriter.write())

}