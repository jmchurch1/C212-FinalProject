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

    // instance variables and enums
    private StoreAction storeAction;
    private double entryFee;

    public Store(double entryFee){
        this.entryFee = entryFee;
        placeName = "Store";
    }

    @Override
    public void onEnter(User user) throws IOException {
        List<StoreAction> mainMenuChoices = new ArrayList<>(Arrays.asList(StoreAction.BUY, StoreAction.SELL, StoreAction.LEAVE));
        // loop infinitely until the user selects the leave action
        // print the menu to read a StoreAction asking whether to
        // buy, sell, or leave
        while(true) {
            StoreAction choiceOne = ConsoleUtils.printMenuToConsole("Store Main Menu", mainMenuChoices, true);

            switch (choiceOne){
                case BUY:
                    while (true) {
                        // check if the user is able to buy anything at the store
                        boolean canBuy = false;
                        for (Item item : Item.values()) {
                            if (item.getValue() <= user.getBalance()) {
                                canBuy = true;
                            }
                        }
                        if (user.getInventory().size() == 3) {
                            System.out.println("You already have three items in your inventory, you can't buy another item yet.");
                            break;
                        }
                        if (!canBuy) {
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
                        // remove the cost of the item from the user's balance
                        user.setBalance(user.getBalance() - buyItem.getValue());
                        // add the item to the user's inventory
                        user.addItem(buyItem);
                        // save the user information
                        arcade.saveUsersToFile();
                        break;
                    }

                    case SELL:
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
                    case LEAVE:
                        // transition back to the lobby
                        arcade.transitionArcadeState("Lobby");
                        break;
                    }
            if (choiceOne == StoreAction.LEAVE){
                break;
            }
        }
    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return null;
    }

    enum StoreAction {
        BUY ("Buy"),
        SELL ("Sell"),
        LEAVE("Leave");

        private String actionName;

        StoreAction(String actionName){
            this.actionName = actionName;
        }

        @Override
        public String toString(){
            return actionName;
        }
    }
}
