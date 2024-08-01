fun main() {

    println("Input some text:")

    val stringValue: String = readln()
    val stringValue2: String? = null

    println(stringValue.length)
    println(stringValue2?.length)

    val lengthOfString: Int = stringValue2?.length ?: 0
    // val lengthOfString2: Int = stringValue2!!.length - NPE !!!

    println(lengthOfString)

}