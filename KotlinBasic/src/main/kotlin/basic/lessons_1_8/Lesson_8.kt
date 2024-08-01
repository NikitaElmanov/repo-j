fun main() {
    // methods
    printUserData(userAge = getAge(), userName = getName())
}

fun getName() = readLine()

fun getAge() = readLine()?.toInt()

fun printUserData(userName: String?, userAge: Int?) {
    println("User data has follow values: name: $userName, age: $userAge")
}
