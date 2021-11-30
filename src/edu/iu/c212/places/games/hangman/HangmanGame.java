package edu.iu.c212.places.games.hangman;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.games.Game;

import java.io.IOException;

public class HangmanGame extends Game implements IHangmanGame{

    // instance variables
    private double entryFee;

    public HangmanGame(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Hangman Game";
    }

    @Override
    public void onEnter(User user) throws IOException {

    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString(){
        return null;
    }

}
