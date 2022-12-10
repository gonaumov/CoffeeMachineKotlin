package machine

fun main(args: Array<String>) {
    val coffeeMachine = CoffeeMachine(
        mlOfWater = 400,
        mlOfMilk = 540,
        gramsOfCoffeeBeans = 120,
        disposableCups = 9,
        money = 550
    )

    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        val userChoice = readln()
        if (userChoice == "exit") {
            break
        }
        when (userChoice) {
            "buy" -> {
                try {
                    coffeeMachine.buy()
                } catch (ex: NotEnoughResourceException) {
                    println("Sorry, not enough ${ex.message}!")
                }
            }

            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.takeMoney()
            "remaining" -> coffeeMachine.printState()
        }
    }
}
