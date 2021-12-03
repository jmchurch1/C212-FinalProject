package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public abstract class  BlackjackParticipant {
    protected ArrayList<BlackjackCard> cards;
    protected int[] handTotals;
    protected static ArrayList<BlackjackCard> playerHand;
    public void hit(){
        BlackjackCard cardDrawn = (cards.remove((int)Math.floor(Math.random() * cards.size())));
        playerHand.add(cardDrawn);
    }
    public abstract int getBestTotal();

}
