import java.lang.Thread.sleep

fun main() {
    // working with boolean conditions

    var attemptCounter = 0

    while (attemptCounter < MAX_ATTEMPT_LIMIT) {
        println("try number is $attemptCounter")
        attemptCounter++
    }

    val result = attemptCounter in MIN_ATTEMPT_LIMIT..MAX_ATTEMPT_LIMIT
    println(result)

    //-----------------------------------

    var counter = 5;
    while (counter > 0) {
        println("Реклама закончится через: ${counter--}")
        sleep(1000)
    }

}

const val MIN_ATTEMPT_LIMIT = 2
const val MAX_ATTEMPT_LIMIT = 5
