package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        String username = "";
        while (true) {
            System.out.println("Please enter the current user's name");
            username = sc.nextLine();
            // if the user inputs a name with the length 0 or with a pole in it
            // do not allow them to input the name
            if (username.length() == 0 || username.contains("|")) {
                System.out.println("Make sure the name doesn't contain a | and is at least one character long");
                continue;
            }
            break;
        }
        // if the user is in the list of all users then return the user that is found
        for (User user : allUsers){
            if (user.getUsername().equals(username))
                return user;
        }
        // otherwise, add a new user to the UserData.txt file

    }

    @Override
    public List<Place> getAllPlaces() {
        return null;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }


}
