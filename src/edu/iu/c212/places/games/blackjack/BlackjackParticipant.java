package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public abstract class  BlackjackParticipant {
    protected ArrayList<BlackjackCard> cards;
    protected int[] handTotals;
    public void hit(){
        BlackjackCard cardDrawn = (cards.remove((int)Math.floor(Math.random() * cards.size())));
        handTotals[0] += cardDrawn.getValue()[0];
        handTotals[1] += cardDrawn.getValue()[1];
    }
    public abstract int getBestTotal();

}
