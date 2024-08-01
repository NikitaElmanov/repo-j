import java.io.File

val File.nameWithoutExtension: String
    get() = name.substringBeforeLast(".")

fun main() {

    val file = File("fileName.txt")
    println(file.nameWithoutExtension)

}