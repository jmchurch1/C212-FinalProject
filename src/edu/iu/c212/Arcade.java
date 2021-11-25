package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

import java.util.List;

public class Arcade implements IArcade{

    // private instance variables
    User currentUser;
    List<User> allUsers;
    // read by getUserSaveDataFromFile() - should contain current user -
    List<Place> allPlaces;
    // - Lobby, Guess the number, Blackjack, Hangman, Trivia, Inventory, Store -

    // no argument constructor
    public Arcade(){
        getUserSaveDataFromFile();
        currentUser = allUsers.get(0);
        transitionArcadeState("Lobby");
    }

    @Override
    public List<User> getUserSaveDataFromFile() {
        return null;
    }

    @Override
    public void saveUsersToFile() {

    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) {

    }

    @Override
    public User getUserOnArcadeEntry() {
        return null;
    }

    @Override
    public List<Place> getAllPlaces() {
        return null;
    }
}
