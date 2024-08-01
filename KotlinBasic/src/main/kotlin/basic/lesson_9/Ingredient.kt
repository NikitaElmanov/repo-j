package basic.lesson_9

class Ingredient(val name: String, val weight: Int, val count: Int) {

    private var isNeedToPrepare = false;

    constructor(
        name: String,
        weight: Int,
        count: Int,
        isNeedToPrepare: Boolean
    ) : this(name, weight, count) {
        this.isNeedToPrepare = isNeedToPrepare
    }

    override fun toString(): String {
        return "Ingredient(name='$name', weight=$weight, count=$count, isNeedToPrepare=$isNeedToPrepare)"
    }


}