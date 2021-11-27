package edu.iu.c212.places.games;

import edu.iu.c212.models.User;

import java.io.IOException;

public class TriviaGame extends Game{

    // instance variables
    private double entryFee;

    public TriviaGame(double entryFee){
        this.entryFee = entryFee;
        placeName = "Trivia Game";
    }

    @Override
    public void onEnter(User user) throws IOException {

    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return null;
    }
}
