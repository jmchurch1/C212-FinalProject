package edu.iu.c212.places;

import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

public class Lobby extends Place{

    @Override
    void onEnter(User user) {
        // print a menu using following method
        ConsoleUtils.printMenuToConsole();
        /* menu should
        1) welcome user to the arcade
        2) print the user's balance
        3) asks user to select a place to go from the list
        of all places in the arcade (arcade.getAllPlaces())

        then move to selected place
         */
    }

    @Override
    public String toString() {
        return null;
    }
}
