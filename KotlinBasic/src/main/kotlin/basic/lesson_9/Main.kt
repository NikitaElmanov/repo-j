package basic.lesson_9

fun main() {
    val dish1: basic.lesson_9.Dish = basic.lesson_9.Dish(
        1,
        "fried eggs",
        "Breakfast",
        listOf("eggs", "slat", "tomato", "pepper", "oil")
    )

    val dish2: basic.lesson_9.Dish = basic.lesson_9.Dish(
        1,
        "Noodles",
        "Lunch",
        listOf("vermicelli", "slat", "chicken", "pepper", "oil"),
        true
    )

    println(dish2)
    println()
    println(dish1.inFavorites)
    dish1.addInFavorites()
    println(dish1.inFavorites)
    println(dish1.downloadIngredients())

    val ingredient1 = basic.lesson_9.Ingredient(
        "Onion",
        1,
        12
    )

    println(ingredient1)

}