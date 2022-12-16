package machine

enum class CoffeeMachineCommands(val commandText: String) {
    EXIT("exit"),
    BUY("buy"),
    BUY_ESPRESSO("1"),
    BUY_LATTE("2"),
    BUY_CAPPUCCINO("3"),
    BUY_BACK_TO_MENU("4"),
    REMAINING("remaining"),
    TAKE("take"),
    FILL("fill")
}
