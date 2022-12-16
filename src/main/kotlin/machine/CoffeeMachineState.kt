package machine

enum class CoffeeMachineState(val message: String, val showMainMenuAfterIt: Boolean) {
    CHOOSING_AN_ACTION("Write action (buy, fill, take, remaining, exit):", false),
    BUY("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:", false),
    BUY_SUCCESS("I have enough resources, making you a coffee!", true),
    BUY_NOT_SUCCESS_NOT_ENOUGH_WATER("Sorry, not enough water!", true),
    BUY_NOT_SUCCESS_NOT_ENOUGH_COFFEE_BEANS("Sorry, not enough coffee beans!", true),
    BUY_NOT_SUCCESS_NOT_ENOUGH_MILK("Sorry, not enough milk!", true),
    BUY_NOT_SUCCESS_NOT_ENOUGH_DISPOSABLE_CUPS("Sorry, not enough disposable cups!", true),
    REMAINING("The coffee machine has:\n" +
            "%d ml of water\n" +
            "%d ml of milk\n" +
            "%d g of coffee beans\n" +
            "%d disposable cups\n" +
            "\$%d of money", true),
    TAKE("I gave you \$%d", true),
    FILL_WATER("Write how many ml of water you want to add:", false),
    FILL_MILK("Write how many ml of milk you want to add:", false),
    FILL_COFFEE_BEANS("Write how many grams of coffee beans you want to add:", false),
    FILL_DISPOSABLE_CUPS("Write how many disposable cups you want to add:", false)
}