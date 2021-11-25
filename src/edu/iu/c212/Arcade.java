package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arcade implements IArcade{

    // private instance variables
    User currentUser;
    List<User> allUsers;
    // read by getUserSaveDataFromFile() - should contain current user -
    List<Place> allPlaces;
    // - Lobby, Guess the number, Blackjack, Hangman, Trivia, Inventory, Store -

    // no argument constructor
    public Arcade() throws IOException{
        allUsers = getUserSaveDataFromFile();
        currentUser = allUsers.get(0);
        transitionArcadeState("Lobby");
    }

    @Override
    public List<User> getUserSaveDataFromFile() throws IOException {
        List<User> userList = FileUtils.getUserDataFromFile();
        return userList;
    }

    @Override
    public void saveUsersToFile() throws IOException{
        FileUtils.writeUserDataToFile(allUsers);
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

    public List<User> getAllUsers() {
        return allUsers;
    }


}
