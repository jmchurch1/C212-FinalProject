package edu.iu.c212.places.games;

import edu.iu.c212.models.User;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

// user will be guessing a number between 0 and 2500. If they guess the number within 5 tires they get $10. Otherwise, they don't get anything :0. $5 entry fee
public class GuessTheNumberGame extends Game{

    // instance variables
    private double entryFee;
    private double reward;
    private int tries;
    private boolean guessed;
    private int randomNumber;

    public GuessTheNumberGame(double entryFee, double reward){
        this.entryFee = entryFee;
        this.reward = reward;
        placeName = "Guess The Number Game";
        tries = 5;
        guessed = false;
        randomNumber = 0;
    }

    @Override
    public void onEnter(User user) throws IOException {
        // check whether the User has enough money to get into the game
        if (user.getBalance() < entryFee){
            System.out.println("You do not have enough money to participate in this game, you are being removed to the lobby");
            arcade.transitionArcadeState("Lobby");
        }
        else
        {
            // remove the entry fee from the user's balance and save the user info to the file
            user.setBalance(user.getBalance() - entryFee);
            FileUtils.writeUserDataToFile(arcade.getAllUsers());
        }

        Random rd = new Random();
        Scanner sc = new Scanner(System.in);
        // reset/set all the values
        tries = 5;
        guessed = false;
        randomNumber = rd.nextInt(2501);
        int guessedNumber;
        System.out.printf("Welcome %s! You have %d tries to guess a random number between 0 and 2500 (both ends inclusive)\n",user.getUsername(), tries);
        while (!guessed && tries > 0){
            // rather than this while (true) loop I need to use ConsoleUtils.readIntegerLineFromConsoleOrElseComplainAndRetry(Function<Integer, Boolean> condition, String failureMessage)
            // but I don't know how Function<Integer, Boolean> works
            while (true) {
                System.out.println("Please guess an integer between 0 and 2500 !!!");
                if (sc.hasNextInt()) {
                    guessedNumber = sc.nextInt();
                    if (guessedNumber < 0 || guessedNumber > 2500) {
                        System.out.println("Make sure the integer is between 0 and 2500 inclusive");
                    } else {
                        break;
                    }
                } else {
                    sc.next();
                    System.out.println("Please input an integer");
                }
            }
            if (guessedNumber > randomNumber){
                tries--;
                System.out.printf("Your guess was larger than the random number, you have %d tries remaining.\n", tries);
            } else if (guessedNumber < randomNumber){
                tries--;
                System.out.printf("Your guess was smaller than the random number, you have %d tries remaining.\n", tries);
            } else {
                tries--;
                guessed = true;
                System.out.printf("You guessed the number with %d tries remaining! You earned $%f.\n",tries,reward);
            }
        }

        if (guessed){
            // add the reward to the user balance
            user.setBalance(user.getBalance() + reward);
            // save the information to the file since there was a state change
            FileUtils.writeUserDataToFile(arcade.getAllUsers());
        }
        else
        {
            System.out.printf("To bad, you were not able to guess the number this time. The random number was %d\n", randomNumber);
        }
        // transition lobby state to Lobby
        arcade.transitionArcadeState("Lobby");
    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return null;
    }
}
