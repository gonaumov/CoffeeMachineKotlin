package machine

private const val MILLILITERS_OF_WATER_FOR_A_COFFEE_CUP = 200

private const val MILLILITERS_OF_MILK_FOR_A_COFFEE_CUP = 50

private const val GRAMS_OF_COFFEE_BEANS_FOR_A_COFFEE_CUP = 15

private const val WATER = "water"

private const val MILK = "milk"

private const val COFFEE_BEANS = "coffee beans"

private const val DISPOSABLE_CUPS = "disposable cups"

private const val ESPRESSO = 1

private const val LATTE = 2

private const val CAPPUCCINO = 3

private const val ESPRESSO_WATER_NEED = 250

private const val ESPRESSO_COFFEE_BEANS_NEED = 16

private const val LATTE_WATER_NEED = 350

private const val LATTE_MILK_NEED = 75

private const val LATTE_COFFEE_BEANS_NEED = 20

private const val CAPPUCCINO_WATER_NEED = 200

private const val CAPPUCCINO_MILK_NEED = 100

private const val CAPPUCCINO_COFFEE_BEANS_NEED = 12


class CoffeeMachine(
    private var mlOfWater: Int,
    private var mlOfMilk: Int,
    private var gramsOfCoffeeBeans: Int,
    private var disposableCups: Int,
    private var money: Int,
) {

    fun getResponse(neededCoffeeCups: Int) {
        val maximumCups = listOf(
            mlOfWater / MILLILITERS_OF_WATER_FOR_A_COFFEE_CUP,
            mlOfMilk / MILLILITERS_OF_MILK_FOR_A_COFFEE_CUP,
            gramsOfCoffeeBeans / GRAMS_OF_COFFEE_BEANS_FOR_A_COFFEE_CUP
        ).minOf { it }
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

    private fun useResource(resourceType: String, amount: Int) {
        when (resourceType) {
            WATER -> {
                if (mlOfWater >= amount) {
                    mlOfWater -= amount
                } else {
                    throw NotEnoughResourceException(WATER)
                }
            }

            COFFEE_BEANS -> {
                if (gramsOfCoffeeBeans >= amount) {
                    gramsOfCoffeeBeans -= amount
                } else {
                    throw NotEnoughResourceException(COFFEE_BEANS)
                }
            }

            MILK -> {
                if (mlOfMilk >= amount) {
                    mlOfMilk -= amount
                } else {
                    throw NotEnoughResourceException(MILK)
                }
            }

            DISPOSABLE_CUPS -> {
                if (disposableCups >= amount) {
                    disposableCups -= amount
                } else {
                    throw NotEnoughResourceException(DISPOSABLE_CUPS)
                }
            }

            else -> throw RuntimeException("Incorrect resource type passed")
        }
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")

        val userInput = readln()

        if (userInput == "back") {
            return
        }

        when (userInput.toInt()) {
            ESPRESSO -> {
                useResource(resourceType = WATER, ESPRESSO_WATER_NEED)
                useResource(resourceType = COFFEE_BEANS, ESPRESSO_COFFEE_BEANS_NEED)
                useResource(resourceType = DISPOSABLE_CUPS, 1)
                money += 4
            }

            LATTE -> {
                useResource(resourceType = WATER, LATTE_WATER_NEED)
                useResource(resourceType = COFFEE_BEANS, LATTE_COFFEE_BEANS_NEED)
                useResource(resourceType = MILK, LATTE_MILK_NEED)
                useResource(resourceType = DISPOSABLE_CUPS, 1)
                money += 7
            }

            CAPPUCCINO -> {
                useResource(resourceType = WATER, CAPPUCCINO_WATER_NEED)
                useResource(resourceType = COFFEE_BEANS, CAPPUCCINO_COFFEE_BEANS_NEED)
                useResource(resourceType = MILK, CAPPUCCINO_MILK_NEED)
                useResource(resourceType = DISPOSABLE_CUPS, 1)
                money += 6
            }

            else -> throw RuntimeException("Unknown resource type passed")
        }

        println("I have enough resources, making you a coffee!")
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