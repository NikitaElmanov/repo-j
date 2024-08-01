package basic.lesson_9

class Dish(
    val id: Int,
    val name: String,
    val category: String,
    val ingredients: List<String>,
    var inFavorites: Boolean = false,
) {

    fun addInFavorites() {
        this.inFavorites = true
    }

    fun removeFromFavorites() {
        this.inFavorites = false
    }

    fun downloadIngredients(): List<String> = this.ingredients

    override fun toString(): String {
        return "Dish(id=$id, name='$name', category='$category', ingredients=$ingredients, inFavorites=$inFavorites)"
    }


}