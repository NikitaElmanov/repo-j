fun main() {
    // lists
    val list = listOf(1, 3, 2, 5, 6, 8, 9)

    val result = list.filter { it > 5 }
        .map { it * 2 }

    println(result)

}
