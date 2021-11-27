package edu.iu.c212.places.games.blackjack;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

import java.io.IOException;

public class BlackjackGame extends Game {

    // instance variables
    private double entryFee;
    private String placeName;

    public BlackjackGame(double entryFee){
        this.entryFee = entryFee;
        placeName = "Blackjack Game";
    }

    @Override
    protected void onEnter(User user) throws IOException {

    }

    @Override
    public String toString() {
        return null;
    }
}
