package edu.iu.c212.places;

// where the user goes to buy and sell things
// users can only hold up to 3 items in their inventory at
// a time

// selling an item will return 50% of its original value to the
// user's balance

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// store has a 0$ entry fee
public class Store extends Place{
    @Override
    void onEnter(User user) throws IOException {
        List<String> mainMenuChoices = new ArrayList<>(Arrays.asList("Buy","Sell","Leave"));
        // loop infinitely until the user selects the leave action
        // print the menu to read a StoreAction asking whether to
        // buy, sell, or leave
        while(true) {
            String choiceOne = ConsoleUtils.printMenuToConsole("Store Main Menu", mainMenuChoices, true);

            if (choiceOne.equals("Buy")) {
                while (true){
                    // check if the user is able to buy anything at the store
                    boolean canBuy = false;
                    for (Item item : Item.values()){
                        if (item.getValue() <= user.getBalance()){
                            canBuy = true;
                        }
                    }
                    if (user.getInventory().size() == 3){
                        System.out.println("You already have three items in your inventory, you can't buy another item yet.");
                        break;
                    }
                    if (!canBuy){
                        // send the user back to the menu with three options
                        System.out.println("With your current finances you are unable to purchase anything at the store.");
                        break;
                    }
                    // the user can buy at least one thing from the store, display the store menu
                    Item buyItem = ConsoleUtils.printMenuToConsole("Buy Menu", Arrays.asList(Item.values()), true);
                    // ask the user if they are sure if they want to buy the item
                    String warningMessage = String.format("Are you sure you would like to buy (%s)", buyItem.toString());
                    List<String> confirmationChoices = new ArrayList<>(Arrays.asList("Buy", "Cancel"));
                    String userConfirmation = ConsoleUtils.printMenuToConsole(warningMessage, confirmationChoices, true);
                    if (userConfirmation.equals("Buy")){
                        // remove the cost of the item from the user's balance
                        user.setBalance(user.getBalance() - buyItem.getValue());
                        // add the item to the user's inventory
                        user.addItem(buyItem);
                        // save the user information
                        arcade.saveUsersToFile();
                        break;
                    }
                    else {
                        break;
                    }
                }

            } else if (choiceOne.equals("Sell")) {
                while (true){
                    if (user.getInventory() == null){
                        System.out.println("Make sure you have items in your inventory before you try and sell.");
                        break;
                    }
                    System.out.println("WARNING: If you decide to sell anything you will only get 50% of the item's value back.");
                    List<Item> userItems = user.getInventory();
                    Item sellItem = ConsoleUtils.printMenuToConsole("Sell Menu", userItems, true);
                    List<String> confirmationChoices = new ArrayList<>(Arrays.asList("Sell","Cancel"));
                    String warningMessage = String.format("Are you sure you want to sell %s, you will only get $%.2f", sellItem.toString(), sellItem.getValue()/2);
                    String userConfirmation = ConsoleUtils.printMenuToConsole(warningMessage, confirmationChoices, true);
                    if (userConfirmation.equals("Sell")){
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        // makes sure there are only two decimal points
                        double itemValue = Double.parseDouble(df.format(sellItem.getValue()/2));
                        // remove the item from the user's inventory
                        user.removeItem(sellItem);
                        // increase the user balance
                        user.setBalance(user.getBalance() + itemValue);
                        // save the user information
                        arcade.saveUsersToFile();
                        break;
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
