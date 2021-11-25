package edu.iu.c212.models;

import java.util.List;

public class User {

    // private instance variables
    String username;
    double balance;
    List<Item> inventory;

    /*
    Arguments
    String username
    double balance
    List<Item> inventory
     */
    public User(String username, double balance, List<Item> inventory){
        this.username = username;
        this.balance = balance;
        this.inventory = inventory;
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean removeItem(Item item){
        if (inventory.contains(item)){
            inventory.remove(item);
            return true;
        }
        else
        {
            return false;
        }
    }

    // getters
    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
