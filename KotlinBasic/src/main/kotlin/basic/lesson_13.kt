fun main() {

    val someString: (Int) -> String = {
        "$it aaaaa";
    }

    val result1: (String) -> Unit = {
        println(it)
    }

    result1(someString(12))

    val result2: (() -> String) -> Unit = {
        println(it())
    }

    println({ it: Int -> "$it ggggg" }(12))

    result2(
        { "asdasd" }
    )

}