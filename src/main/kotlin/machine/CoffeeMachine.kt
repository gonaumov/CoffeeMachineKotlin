package machine

private const val MILLILITERS_OF_WATER_FOR_A_COFFEE_CUP = 200

private const val MILLILITERS_OF_MILK_FOR_A_COFFEE_CUP = 50

private const val GRAMS_OF_COFFEE_BEANS_FOR_A_COFFEE_CUP = 15

private const val ESPRESSO = 1

private const val LATTE = 2

private const val CAPPUCCINO = 3

class CoffeeMachine(
    private var mlOfWater: Int,
    private var mlOfMilk: Int,
    private var gramsOfCoffeeBeans: Int,
    private var disposableCups: Int,
    private var money: Int,
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

    fun printState() {
        println(
            """
            The coffee machine has:
            $mlOfWater ml of water
            $mlOfMilk ml of milk
            $gramsOfCoffeeBeans g of coffee beans
            $disposableCups disposable cups
            $$money of money
        """.trimIndent()
        )
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
        when (readln().toInt()) {
            ESPRESSO -> {
                mlOfWater -= 250
                gramsOfCoffeeBeans -= 16
                money += 4
                disposableCups--
            }

            LATTE -> {
                mlOfWater -= 350
                mlOfMilk -= 75
                gramsOfCoffeeBeans -= 20
                money += 7
                disposableCups--
            }

            CAPPUCCINO -> {
                mlOfWater -= 200
                mlOfMilk -= 100
                gramsOfCoffeeBeans -= 12
                money += 6
                disposableCups--
            }

            else -> throw RuntimeException("Unknown coffee type passed")
        }
    }

    fun fill() {
        println("Write how many ml of water you want to add:")
        mlOfWater += readln().toInt()
        println("Write how many ml of milk you want to add:")
        mlOfMilk += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        gramsOfCoffeeBeans += readln().toInt()
        println("Write how many disposable cups you want to add:")
        disposableCups += readln().toInt()
    }

    fun takeMoney() {
        println("I gave you $$money")
        money = 0
    }

}