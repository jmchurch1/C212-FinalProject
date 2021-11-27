package edu.iu.c212.places;

import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

public class Lobby extends Place{

    // instanceVariables
    private double entryFee;
    private String placeName;

    public Lobby(double entryFee){
        this.entryFee = entryFee;
        placeName = "Lobby";
    }

    @Override
    public void onEnter(User user) {
        /*
        1) welcome user to the arcade
        2) print the user's balance
        3) asks user to select a place to go from the list
        of all places in the arcade (arcade.getAllPlaces())

        then move to selected place
         */
        System.out.print(String.format("Welcome to the lobby %s!\n==========\n", user.getUsername()));
        System.out.print(String.format("You current balance is: %f\n==========\n", user.getBalance()));
        Place userChosenPlace = ConsoleUtils.printMenuToConsole("Lobby Menu", arcade.getAllPlaces(), true);
        arcade.transitionArcadeState(userChosenPlace.placeName);
    }

    @Override
    public String toString() {
        return null;
    }
}
