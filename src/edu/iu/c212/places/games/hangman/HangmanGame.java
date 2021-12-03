package edu.iu.c212.places.games.hangman;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.FileUtils;
import edu.iu.c212.utils.http.HttpUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HangmanGame extends Game implements IHangmanGame{

    // instance variables
    private double entryFee;
    private double reward;
    private String randomWord;

    public HangmanGame(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Hangman Game";
        reward = 15;
        randomWord = "";
    }

    @Override
    public void onEnter(User user) throws IOException {
        // check whether the user has enough money to enter the game
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

        System.out.printf("==========\nWelcome to the Hangman Game. If you guess the word within six guesses you will earn $%.2f\n==========\n",reward);
        // create the random hangman word
        randomWord = HttpUtils.getRandomHangmanWord();
        // create the list that will hold the player's guesses
        List<Character> playerGuesses = new ArrayList<>();
        // create remaining guesses variable
        int remainingGuesses = 6;
        while (remainingGuesses > 0) {
            System.out.printf("Here is you word: %s, you have %d guesses remaining. Please enter a character to guess.\n", getBlurredWord(playerGuesses, randomWord), remainingGuesses);
            String playerGuess = ConsoleUtils.readLineFromConsole().toLowerCase(Locale.ROOT);
            if (playerGuess.length() != 1) {
                // the player is guessing the word, presumably
                if (playerGuess.equals(randomWord)) {
                    System.out.printf("Congrats! You guessed %s correctly!! You earned $%.2f", randomWord, reward);
                    remainingGuesses--;
                    // the player guessed the word. add money to the player's account, save their information, and break the while loop
                    user.setBalance(user.getBalance() + reward);
                    FileUtils.writeUserDataToFile(arcade.getAllUsers());
                    // transition the arcade back to the lobby state
                    arcade.transitionArcadeState("Lobby");
                    break;
                } else {
                    System.out.println("Sadly, your guess was incorrect.");
                    remainingGuesses--;
                }
            }
            else if (playerGuesses.contains(playerGuess.charAt(0))){
                System.out.printf("You already guessed %c, please try a different guess.\n", playerGuess.charAt(0));
            } else if (getValidLexicon().contains(playerGuess.charAt(0))){
                // the players guess is valid, add it to the player's guesses
                char characterGuessed = playerGuess.charAt(0);
                playerGuesses.add(characterGuessed);
                boolean isInWord = false;
                for (int i = 0; i < randomWord.length(); i++){
                    if (randomWord.charAt(i) == characterGuessed) {
                        isInWord = true;
                        break;
                    }
                }
                if (isInWord)
                    System.out.printf("%c was in the word!\n", characterGuessed);
                else
                    System.out.printf("%c was not in the word\n", characterGuessed);
                remainingGuesses--;
            }
            else{
                System.out.printf("%c was not in the lexicon!\n", playerGuess.charAt(0));
                remainingGuesses--;
            }
        }

        System.out.printf("Sadly you were not able to guess the word. The word was %s\n",randomWord);
        // transition the arcade state back to lobby
        arcade.transitionArcadeState("Lobby");
    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString(){
        return String.format("Hangman (Cost: $%.2f). Game? yes",entryFee);
    }

    @Override
    public String getBlurredWord(List<Character> guesses, String word) {
        String blurredWord = "";
        // create a string of the length of word that is completely *
        for(int i = 0; i < word.length(); i++){
            blurredWord = blurredWord.concat("*");
        }
        // loop through all the guesses
        for (int i = 0; i < guesses.size(); i++){
            // loop through the letters in the word
            for (int j = 0; j < word.length(); j++){
                if (word.charAt(j) == guesses.get(i)){
                    // if the guess is in the word, then replace all the characters
                    blurredWord = blurredWord.substring(0,j) + guesses.get(i) + blurredWord.substring(j);
                }
            }
        }

        return blurredWord;
    }

    @Override
    public List<Character> getValidLexicon() {
        // create a list with the entire alphabet
        return new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
    }
}
