package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// where the user goes to see their inventory
// and net worth

// entry fee $0
public class Inventory extends Place{

    // instance variables
    private double entryFee;

    public Inventory(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Inventory";
    }

    @Override
    public void onEnter(User user) throws IOException {
        // print to the console all of the user's items, the amount
        // of each that the user has, and their value
        // initialize amounts of items at the first two indexes
        HashMap<Item, Integer> itemHashMap = new HashMap<>();
        for (Item item : user.getInventory()) {
            if (itemHashMap.containsKey(item)){
                // if the key already exists in the hashMap add 1 to the amount
                itemHashMap.replace(item, itemHashMap.get(item), itemHashMap.get(item) + 1);
            }
            else {
                // if the item is not in the hashMap add a new key with a default
                // value of 1
                itemHashMap.put(item, 1);
            }
        }
        System.out.print(String.format("Hi, %s! Your inventory looks like this:\n", user.getUsername()));
        for (Map.Entry<Item, Integer> entry : itemHashMap.entrySet()) {
            System.out.print(String.format("%s: %d (value: %f)\n",entry.getKey(),entry.getKey(),entry.getKey().getValue()));
        }
        // not sure if net worth should include the item values or not
        System.out.print(String.format("Total net worth: %.2f\n", user.getBalance()));
        if (user.getInventory().size() == 3){
            System.out.println("WARNING: Your inventory is full, you will not be able to buy any more items before you sell one");
        }
        System.out.print("REMEMBER! You can only have 3 items at a time. Sell one by going to the Store\n==========\n");
        // transition back to the arcade lobby
        arcade.transitionArcadeState("Lobby");
    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return "Inventory";
    }
}
