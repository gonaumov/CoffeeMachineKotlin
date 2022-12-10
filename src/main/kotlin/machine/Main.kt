package machine

fun main(args: Array<String>) {

    println("Write how many cups of coffee you will need:")
    val coffeeCups = readln().toInt()
    println("For $coffeeCups cups of coffee you will need:")
    println("${coffeeCups * 200} ml of water")
    println("${coffeeCups * 50} ml of milk")
    println("${coffeeCups * 15} g of coffee beans")

}
