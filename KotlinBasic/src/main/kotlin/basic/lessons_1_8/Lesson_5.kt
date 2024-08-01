fun main() {
    // ranges
    val intRange1: IntRange = 1..23
    val intRange2: IntRange = 1 until 23
    val intRange3: IntProgression = 1 downTo 23
    val intRange4: IntProgression = 1 downTo 23 step 2
    val intRange5: IntProgression = 1..23 step 2
    val charRange1: CharProgression = 'a'..'z' step 3

    // in
    println('c' in charRange1) // false due to range step is 3

    // for
    for (i in 5 downTo 1) {
        println("Реклама закончится через: $i")
        Thread.sleep(1000)
    }

}
