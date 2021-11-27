package edu.iu.c212.places.games;

import edu.iu.c212.models.User;

import java.io.IOException;

public class GuessTheNumberGame extends Game{

    // instance variables
    private double entryFee;
    private String placeName;

    public GuessTheNumberGame(double entryFee){
        this.entryFee = entryFee;
        placeName = "Guess The Number Game";
    }
    @Override
    protected void onEnter(User user) throws IOException {

    }

    @Override
    public String toString() {
        return null;
    }
}
