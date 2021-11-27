package edu.iu.c212.places.games;

import edu.iu.c212.models.User;

import java.io.IOException;

public class TriviaGame extends Game{

    // instance variables
    private double entryFee;
    private String placeName;

    public TriviaGame(double entryFee){
        this.entryFee = entryFee;
        placeName = "Trivia Game";
    }

    @Override
    protected void onEnter(User user) throws IOException {

    }

    @Override
    public String toString() {
        return null;
    }
}
