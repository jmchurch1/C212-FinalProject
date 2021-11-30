package edu.iu.c212.places.games.blackjack;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

import java.io.IOException;

public class BlackjackGame extends Game {

    // instance variables
    private double entryFee;

    public BlackjackGame(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Blackjack Game";
    }

    @Override
    public void onEnter(User user) throws IOException {

    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return "Blackjack Game";
    }
}
