package edu.iu.c212.places;

// where the user goes to buy and sell things
// users can only hold up to 3 items in their inventory at
// a time

// selling an item will return 50% of its original value to the
// user's balance

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// store has a 0$ entry fee
public class Store extends Place{
    @Override
    void onEnter(User user) {
        List<String> mainMenuChoices = new ArrayList<>(Arrays.asList("Buy","Sell","Leave"));
        // loop infinitely until the user selects the leave action
        // print the menu to read a StoreAction asking whether to
        // buy, sell, or leave
        while(true) {
            String choiceOne = ConsoleUtils.printMenuToConsole("Store Main Menu", mainMenuChoices, true);

            if (choiceOne.equals("Buy")) {
                while (true){

                }

            } else if (choiceOne.equals("Sell")) {
                while (true){
                    if (user.getInventory() == null){
                        System.out.println("Make sure you have items in your inventory before you try and sell.");
                        break;
                    }
                    System.out.println("WARNING: If you decide to sell anything you will only get 50% of the item's value back.");
                    List<Item> userItems = user.getInventory();
                    Item itemChoice = ConsoleUtils.printMenuToConsole("Sell Menu", userItems, true);
                    List<String> confirmationChoices = new ArrayList<>(Arrays.asList("Sell","Cancel"));
                    String warningMessage = String.format("Are you sure you want to sell %s, you will only get $%.2f", itemChoice.toString(), itemChoice.getValue()/2);
                    String userConfirmation = ConsoleUtils.printMenuToConsole(warningMessage, confirmationChoices, true);
                    if (userConfirmation.equals("Sell")){
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        // makes sure there are only two decimal points
                        double itemValue = Double.parseDouble(df.format(itemChoice.getValue()/2));
                        // increase the user balance
                        user.setBalance(user.getBalance() + itemValue);
                        // save the user information


                    }
                    else {
                        // the user decided to not sell the item, go back to the main store menu
                        break;
                    }
                }

            } else if (choiceOne.equals("Leave")) {
                break;
            }
        }


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
