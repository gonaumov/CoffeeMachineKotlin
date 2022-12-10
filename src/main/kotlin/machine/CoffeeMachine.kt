package machine

private const val MILLILITERS_OF_WATER_FOR_A_COFFEE_CUP = 200

private const val MILLILITERS_OF_MILK_FOR_A_COFFEE_CUP = 50

private const val GRAMS_OF_COFFEE_BEANS_FOR_A_COFFEE_CUP = 15

class CoffeeMachine(
    private val mlOfWater: Int,
    private val mlOfMilk: Int,
    private val gramsOfCoffeeBeans: Int
) {

    fun getResponse(neededCoffeeCups: Int) {
        val maximumCups = listOf<Int>(
            mlOfWater / MILLILITERS_OF_WATER_FOR_A_COFFEE_CUP,
            mlOfMilk / MILLILITERS_OF_MILK_FOR_A_COFFEE_CUP,
            gramsOfCoffeeBeans / GRAMS_OF_COFFEE_BEANS_FOR_A_COFFEE_CUP
        ).sorted().let {
            it.first()
        }
        return when {
            maximumCups == neededCoffeeCups -> println("Yes, I can make that amount of coffee")
            maximumCups < neededCoffeeCups -> println("No, I can make only $maximumCups cups of coffee")
            else -> {
                println("Yes, I can make that amount of coffee (and even ${maximumCups - neededCoffeeCups} more than that)")
            }
        }
    }

    fun calculateNeededProducts() {
        println("Write how many cups of coffee you will need:")
        val coffeeCups = readln().toInt()
        println("For $coffeeCups cups of coffee you will need:")
        println("${coffeeCups * 200} ml of water")
        println("${coffeeCups * 50} ml of milk")
        println("${coffeeCups * 15} g of coffee beans")
    }

}