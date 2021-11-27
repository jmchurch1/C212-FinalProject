package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.Store;
import edu.iu.c212.places.games.GuessTheNumberGame;
import edu.iu.c212.places.games.TriviaGame;
import edu.iu.c212.places.games.blackjack.BlackjackGame;
import edu.iu.c212.places.games.hangman.HangmanGame;
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
        currentUser = getUserOnArcadeEntry();
        allPlaces = getAllPlaces();
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
    public void transitionArcadeState(String newPlaceNameToGoTo) throws IOException{
        for (Place place : allPlaces){
            if (place.getPlaceName().equals(newPlaceNameToGoTo)){
                place.onEnter(currentUser);
                break;
            }
        }
    }

    @Override
    public User getUserOnArcadeEntry() throws IOException{
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
        double userBalance;
        while (true){
            System.out.println("Please enter your user's current balance. format: 00.00");
            if (sc.hasNextDouble()){
                userBalance = sc.nextDouble();
                break;
            }
            sc.next();
        }
        // create new user using the given username, given balance, and no items
        User newUser = new User(username, userBalance, new ArrayList<>());
        // add the user to the list of users
        allUsers.add(newUser);
        // save the user to the file
        FileUtils.writeUserDataToFile(allUsers);
        // return the newUser
        return newUser;
    }

    @Override
    public List<Place> getAllPlaces() {
        // instantiate all the places and put them in the place list
        List<Place> placeList = new ArrayList<>();
        /*
        Order of additions
        1: Lobby, $0
        2: Inventory, $0
        3: Store, $0
        4: TriviaGame, $0
        5: BlackjackGame, $20
        6: HangmanGame, $5
        7: GuessTheNumberGame, $5
         */
        placeList.add(new Lobby(0));
        placeList.add(new Inventory(0));
        placeList.add(new Store(0));
        placeList.add(new TriviaGame(0));
        placeList.add(new BlackjackGame(20));
        placeList.add(new HangmanGame(5));
        placeList.add(new GuessTheNumberGame(5));
        // return placeList with all the different visitable areas.
        return placeList;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }


}
