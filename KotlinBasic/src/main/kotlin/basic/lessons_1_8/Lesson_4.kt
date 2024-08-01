private const val MAX_AGE = 65u
private const val MIN_AGE = 18u

fun main() {
    val userAge: UInt = readLine()!!.toUInt()

    val result = if (userAge < MIN_AGE) {
        "child"
    } else if (userAge >= MIN_AGE && userAge < MAX_AGE) {
        "middle age"
    } else if (userAge >= MAX_AGE) {
        "granny"
    } else {
        "fff"
    }

    println(result)

    when(userAge) {
        10u -> println("10 - $userAge")
        20u -> println("20 - $userAge")
        30u -> println("30 - $userAge")
        else -> println("else 1")
    }

    val res = when (userAge) {
        10u -> "10 - $userAge"
        20u -> "20 - $userAge"
        30u -> "30 - $userAge"
        else -> "else 2"
    }
    println(res)

}
