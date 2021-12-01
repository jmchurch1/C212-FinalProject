package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;
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
    private int upperBound;
    private int lowerBound;

    public GuessTheNumberGame(double entryFee, double reward, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        this.reward = reward;
        placeName = "Guess The Number Game";
        tries = 5;
        guessed = false;
        randomNumber = 0;
        upperBound = 2500;
        lowerBound = 0;
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
        // reset/set all the values
        tries = 5;
        guessed = false;
        randomNumber = rd.nextInt(2501);
        int guessedNumber;

        System.out.printf("Welcome %s! You have %d tries to guess a random number between 0 and 2500 (both ends inclusive)\n",user.getUsername(), tries);
        while (!guessed && tries > 0){
            Function condition = (Object num) -> {
                if ((int) num < lowerBound || (int) num > upperBound){
                    return false;
                }
                else
                {
                    return true;
                }
            };
            String failureMessage = String.format("Your guess was not between %d and %d",lowerBound,upperBound);
            guessedNumber = ConsoleUtils.readIntegerLineFromConsoleOrElseComplainAndRetry(condition, failureMessage);

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
        return "Guess The Number Game";
    }
}
