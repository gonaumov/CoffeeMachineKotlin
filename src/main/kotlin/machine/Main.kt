package machine

/**
 * This file is implemented for Coffee Machine JetBrains academy project:
 *  https://hyperskill.org/projects/67
 *  which is part from Kotlin Basic track
 *  https://hyperskill.org/tracks/18
 *  @author Georgi Naumov
 *  gonaumov@gmail.com for contacts and suggestions
 */
fun main(args: Array<String>) {

    val coffeeMachine = CoffeeMachine(
        mlOfWater = 400,
        mlOfMilk = 540,
        gramsOfCoffeeBeans = 120,
        disposableCups = 9,
        money = 550
    )

    do {
        println(coffeeMachine.getStatusMessage())
    } while (coffeeMachine.processUserInput(readln()))

}
