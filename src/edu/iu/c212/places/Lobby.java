package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;

public class Lobby extends Place{

    // instanceVariables
    private double entryFee;

    public Lobby(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Lobby";
    }

    @Override
    public void onEnter(User user) throws IOException {
        /*
        1) welcome user to the arcade
        2) print the user's balance
        3) asks user to select a place to go from the list
        of all places in the arcade (arcade.getAllPlaces())

        then move to selected place
         */
        System.out.print(String.format("Welcome to the lobby %s!\n==========\n", user.getUsername()));
        System.out.print(String.format("You current balance is: %.2f\n==========\n", user.getBalance()));
        Place userChosenPlace = ConsoleUtils.printMenuToConsole("Lobby Menu", arcade.getAllPlaces(), true);
        arcade.transitionArcadeState(userChosenPlace.getPlaceName());
    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return String.format("Lobby (Cost: $%.2f). Game? no",entryFee);
    }
}
