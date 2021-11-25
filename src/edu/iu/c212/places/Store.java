package edu.iu.c212.places;

// where the user goes to buy and sell things
// users can only hold up to 3 items in their inventory at
// a time

// selling an item will return 50% of its original value to the
// user's balance

import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

// store has a 0$ entry fee
public class Store extends Place{
    @Override
    void onEnter(User user) {
        // loop infinitely until the user selects the leave action
        // print the menu to read a StoreAction asking whether to
        // buy, sell, or leave
        ConsoleUtils.printMenuToConsole();
        /* if sell:
         user has empty inventory - tell them, don't sell anything
         print menu asking which element they want to sell, warn user that they
         can only get 50% of items value back
         print a menu asking for user confirmation of sell decision
         if the user selects yes, remove the item from the user inventory, increase their balance, and save
         if no, loop back to the menu with three options
         if buy:
         print a menu to the console of all the items to buy
         if the user doesn't have enough money to buy the item, or if the user's inventory
         already has 3 items, the user is not able to buy th item
         print a menu asking for confirmation that the user wants to buy the item
         if the user selects yes, add item to user inventory, decrease balance, and save
         if no, loop back to the menu with three options
         if leave:
         transitions back to lobby
        */
    }

    @Override
    public String toString() {
        return null;
    }

    enum StoreAction {
        // I don't know how enums work yet
    }
}
