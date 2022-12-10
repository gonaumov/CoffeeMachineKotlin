package machine

fun main(args: Array<String>) {
    println("Write how many ml of water the coffee machine has:")
    val mlOfWater = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val mlOfMilk = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val gramsOfCoffeeBeans = readln().toInt()
    println("Write how many cups of coffee you will need:")
    val neededCoffeeCups = readln().toInt()
    val coffeeMachine = CoffeeMachine(mlOfWater, mlOfMilk, gramsOfCoffeeBeans)
    coffeeMachine.getResponse(neededCoffeeCups)
}
