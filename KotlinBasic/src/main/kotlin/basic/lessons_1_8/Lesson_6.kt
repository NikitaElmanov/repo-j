fun main() {
    // arrays
    val arrayOfIngredients = arrayOf("egg", "salt", "tomato", "pepper", "oil")

    arrayOfIngredients[2] = "paprika"
    arrayOfIngredients.set(4, "butter")

    println(arrayOfIngredients.get(4))

    for (i in arrayOfIngredients) {
        println("Ingredient ${arrayOfIngredients.indexOf(i)}: $i")
    }

}
