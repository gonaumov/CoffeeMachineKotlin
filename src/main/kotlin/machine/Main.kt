package machine

fun main(args: Array<String>) {
    val coffeeMachine = CoffeeMachine(
        mlOfWater = 400,
        mlOfMilk = 540,
        gramsOfCoffeeBeans = 120,
        disposableCups = 9,
        money = 550
    )

    coffeeMachine.printState()

    println("Write action (buy, fill, take): ")

    when (readln()) {
        "buy" -> coffeeMachine.buy()
        "fill" -> coffeeMachine.fill()
        "take" -> coffeeMachine.takeMoney()
    }

    coffeeMachine.printState()
}
