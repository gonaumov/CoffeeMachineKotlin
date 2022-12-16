package machine

import java.lang.Error

private const val WATER = "water"

private const val MILK = "milk"

private const val COFFEE_BEANS = "coffee beans"

private const val DISPOSABLE_CUPS = "disposable cups"

class CoffeeMachine(
    private var mlOfWater: Int,
    private var mlOfMilk: Int,
    private var gramsOfCoffeeBeans: Int,
    private var disposableCups: Int,
    private var money: Int,
) {
    /**
     * This property will serve as a state holder.
     * By default, coffee machine will show the general menu:
     * Write action (buy, fill, take, remaining, exit):
     */
    private var state: CoffeeMachineState = CoffeeMachineState.CHOOSING_AN_ACTION

    /**
     * This member function encapsulates working with resources.  It accepts
     * resourceType and amount of the resource which needs to bе spent.
     * If there is enough from this resource it uses it or  throws
     * exception if there is no enough resource.
     *
     * @param resourceType String
     * @param amount Int
     * @throws NotEnoughResourceException and RuntimeException
     * @return Unit
     * @author Georgi Naumov
     *
     * gonaumov@gmail.com for contacts and suggestions
     */
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

    /**
     * This member function encapsulates handling of NotEnoughResourceException.
     * It sets proper state depends on exception message
     *
     * @param exception NotEnoughResourceException
     * @author Georgi Naumov
     * @return CoffeeMachineState
     *
     * gonaumov@gmail.com for contacts and suggestions
     */
    private fun handleNotEnoughResourceExceptionMessage(exception: NotEnoughResourceException): CoffeeMachineState {
        return when (exception.message) {
            WATER -> {
                CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_WATER
            }

            MILK -> {
                CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_MILK
            }

            DISPOSABLE_CUPS -> {
                CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_DISPOSABLE_CUPS
            }

            COFFEE_BEANS -> {
                CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_COFFEE_BEANS
            }

            else -> {
                throw Error("Unknown error message")
            }
        }
    }

    /**
     *  This function is the only output of the coffee machine
     *  It returns status of the coffee machine.
     *
     *  @author Georgi Naumov
     *  @return String
     *
     * gonaumov@gmail.com for contacts and suggestions
     */
    fun getStatusMessage(): String {
        return when (state) {
            CoffeeMachineState.REMAINING -> String.format(
                CoffeeMachineState.REMAINING.message,
                mlOfWater,
                mlOfMilk,
                gramsOfCoffeeBeans,
                disposableCups,
                money
            ) + "\n" + CoffeeMachineState.CHOOSING_AN_ACTION.message

            CoffeeMachineState.TAKE -> String.format(
                CoffeeMachineState.TAKE.message,
                money
            ) + "\n" + CoffeeMachineState.CHOOSING_AN_ACTION.message

            else -> {
                return if (state.showMainMenuAfterIt) {
                    state.message + "\n" + CoffeeMachineState.CHOOSING_AN_ACTION.message
                } else {
                    state.message
                }
            }
        }
    }

    /**
     * This member function handles mine menu choices
     *
     * @param input String
     * @return Boolean
     *
     * @author Georgi Naumov
     *
     * gonaumov@gmail.com for contacts and suggestions
     */
    private fun handleMainMenu(input: String): Boolean {
        return when (input) {
            CoffeeMachineCommands.EXIT.commandText -> false
            CoffeeMachineCommands.BUY.commandText -> {
                state = CoffeeMachineState.BUY
                true
            }

            CoffeeMachineCommands.REMAINING.commandText -> {
                state = CoffeeMachineState.REMAINING
                true
            }

            CoffeeMachineCommands.TAKE.commandText -> {
                state = CoffeeMachineState.TAKE
                true
            }

            CoffeeMachineCommands.FILL.commandText -> {
                state = CoffeeMachineState.FILL_WATER
                true
            }

            else -> {
                state = CoffeeMachineState.CHOOSING_AN_ACTION
                true
            }
        }
    }

    /**
     * This is the only member function of Coffee Machine which accepts user
     * input as a string
     *
     *  @param input String
     *  @return Boolean
     *
     *  @author Georgi Naumov
     *
     *  gonaumov@gmail.com for contacts and suggestions
     */
    fun processUserInput(input: String): Boolean {
        return when (state) {
            CoffeeMachineState.CHOOSING_AN_ACTION -> handleMainMenu(input)
            CoffeeMachineState.REMAINING -> handleMainMenu(input)
            CoffeeMachineState.BUY_SUCCESS -> handleMainMenu(input)
            CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_WATER -> handleMainMenu(input)
            CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_MILK -> handleMainMenu(input)
            CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_COFFEE_BEANS -> handleMainMenu(input)
            CoffeeMachineState.BUY_NOT_SUCCESS_NOT_ENOUGH_DISPOSABLE_CUPS -> handleMainMenu(input)
            CoffeeMachineState.TAKE -> {
                money = 0
                handleMainMenu(input)
            }

            CoffeeMachineState.FILL_WATER -> {
                mlOfWater += input.toInt()
                state = CoffeeMachineState.FILL_MILK
                true
            }

            CoffeeMachineState.FILL_MILK -> {
                mlOfMilk += input.toInt()
                state = CoffeeMachineState.FILL_COFFEE_BEANS
                true
            }

            CoffeeMachineState.FILL_COFFEE_BEANS -> {
                gramsOfCoffeeBeans += input.toInt()
                state = CoffeeMachineState.FILL_DISPOSABLE_CUPS
                true
            }

            CoffeeMachineState.FILL_DISPOSABLE_CUPS -> {
                disposableCups += input.toInt()
                state = CoffeeMachineState.CHOOSING_AN_ACTION
                true
            }

            CoffeeMachineState.BUY -> {
                when (input) {
                    CoffeeMachineCommands.BUY_BACK_TO_MENU.commandText -> {
                        state = CoffeeMachineState.CHOOSING_AN_ACTION
                        true
                    }

                    CoffeeMachineCommands.BUY_ESPRESSO.commandText -> {
                        try {
                            useResource(resourceType = WATER, CoffeeMachineMaterialsNeed.ESPRESSO_WATER_NEED.amount)
                            useResource(
                                resourceType = COFFEE_BEANS,
                                CoffeeMachineMaterialsNeed.ESPRESSO_COFFEE_BEANS_NEED.amount
                            )
                            useResource(resourceType = DISPOSABLE_CUPS, 1)
                            state = CoffeeMachineState.BUY_SUCCESS
                            money += 4
                        } catch (ex: NotEnoughResourceException) {
                            state = handleNotEnoughResourceExceptionMessage(ex)
                        }
                        true
                    }

                    CoffeeMachineCommands.BUY_CAPPUCCINO.commandText -> {
                        try {
                            useResource(resourceType = WATER, CoffeeMachineMaterialsNeed.CAPPUCCINO_WATER_NEED.amount)
                            useResource(
                                resourceType = COFFEE_BEANS,
                                CoffeeMachineMaterialsNeed.CAPPUCCINO_COFFEE_BEANS_NEED.amount
                            )
                            useResource(resourceType = MILK, CoffeeMachineMaterialsNeed.CAPPUCCINO_MILK_NEED.amount)
                            useResource(resourceType = DISPOSABLE_CUPS, 1)
                            state = CoffeeMachineState.BUY_SUCCESS
                            money += 6
                        } catch (ex: NotEnoughResourceException) {
                            state = handleNotEnoughResourceExceptionMessage(ex)
                        }
                        true
                    }

                    CoffeeMachineCommands.BUY_LATTE.commandText -> {
                        try {
                            useResource(resourceType = WATER, CoffeeMachineMaterialsNeed.LATTE_WATER_NEED.amount)
                            useResource(
                                resourceType = COFFEE_BEANS,
                                CoffeeMachineMaterialsNeed.LATTE_COFFEE_BEANS_NEED.amount
                            )
                            useResource(resourceType = MILK, CoffeeMachineMaterialsNeed.LATTE_MILK_NEED.amount)
                            useResource(resourceType = DISPOSABLE_CUPS, 1)
                            state = CoffeeMachineState.BUY_SUCCESS
                            money += 7
                        } catch (ex: NotEnoughResourceException) {
                            state = handleNotEnoughResourceExceptionMessage(ex)
                        }
                        true
                    }

                    else -> {
                        state = CoffeeMachineState.CHOOSING_AN_ACTION
                        true
                    }
                }
            }
        }
    }
}
