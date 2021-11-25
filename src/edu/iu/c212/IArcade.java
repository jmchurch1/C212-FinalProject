package edu.iu.c212;

import edu.iu.c212.models.User;

import java.util.List;

public interface IArcade {

    // call FileUtils.getUserDataFromFile, and use System.exit
    // to exit the program if an exception is thrown
    List<User> getUserSaveDataFromFile();

    // call FileUtils.writeUserDataToFile to write all users to
    // the .txt file
    void saveUsersToFile();

    // transition the currentUser to a new place
    // conditions: if the user doesn't have enough money to go to
    // the place, print a warning and go move player to the LOBBY
    // if the user has enough money subtract the entry fee from the player
    // save it to the file, then enter the place
    void transitionArcadeState(String newPlaceNameToGoTo);

    // ask for a username to be entered
    // if the username isn't contained in the users as read
    // from users.txt, a new user must be created and saved to users.txt
    // a welcome message will be printed
    // if the username exists, print a welcome back message
    User getUserOnArcadeEntry();

    List<Place> getAllPlaces();
    // return a list of all the places
}
