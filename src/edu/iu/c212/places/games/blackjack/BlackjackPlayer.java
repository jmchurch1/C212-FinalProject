package edu.iu.c212.places.games.blackjack;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BlackjackPlayer extends BlackjackParticipant{

    public BlackjackPlayer(){
        ArrayList<BlackjackCard> deck = new ArrayList() {{
            add(new BlackjackCard("Ace of Spades", new int[]{11,1}));
            add(new BlackjackCard("King of Spades", new int[]{10,10}));
            add(new BlackjackCard("Queen of Spades", new int[]{10,10}));
            add(new BlackjackCard ("Jack of Spades", new int[]{10,10}));
            add(new BlackjackCard("10 of Spades", new int[]{10,10}));
            add(new BlackjackCard("9 of Spades", new int[]{9,9}));
            add(new BlackjackCard("8 of Spades", new int[]{8,8}));
            add(new BlackjackCard("7 of Spades", new int[]{7,7}));
            add(new BlackjackCard("6 of Spades", new int[]{6,6}));
            add(new BlackjackCard("5 of Spades", new int[]{5,5}));
            add(new BlackjackCard("4 of Spades", new int[]{4,4}));
            add(new BlackjackCard("3 of Spades", new int[]{3,3}));
            add(new BlackjackCard("2 of Spades", new int[]{2,2}));
            add(new BlackjackCard("Ace of Clubs", new int[]{11,1}));
            add(new BlackjackCard("King of Clubs", new int[]{10,10}));
            add(new BlackjackCard("Queen of Clubs", new int[]{10,10}));
            add(new BlackjackCard ("Jack of Clubs", new int[]{10,10}));
            add(new BlackjackCard("10 of Clubs", new int[]{10,10}));
            add(new BlackjackCard("9 of Clubs", new int[]{9,9}));
            add(new BlackjackCard("8 of Clubs", new int[]{8,8}));
            add(new BlackjackCard("7 of Clubs", new int[]{7,7}));
            add(new BlackjackCard("6 of Clubs", new int[]{6,6}));
            add(new BlackjackCard("5 of Clubs", new int[]{5,5}));
            add(new BlackjackCard("4 of Clubs", new int[]{4,4}));
            add(new BlackjackCard("3 of Clubs", new int[]{3,3}));
            add(new BlackjackCard("2 of Clubs", new int[]{2,2}));
            add(new BlackjackCard("Ace of Hearts", new int[]{11,1}));
            add(new BlackjackCard("King of Hearts", new int[]{10,10}));
            add(new BlackjackCard("Queen of Hearts", new int[]{10,10}));
            add(new BlackjackCard ("Jack of Hearts", new int[]{10,10}));
            add(new BlackjackCard("10 of Hearts", new int[]{10,10}));
            add(new BlackjackCard("9 of Hearts", new int[]{9,9}));
            add(new BlackjackCard("8 of Hearts", new int[]{8,8}));
            add(new BlackjackCard("7 of Hearts", new int[]{7,7}));
            add(new BlackjackCard("6 of Hearts", new int[]{6,6}));
            add(new BlackjackCard("5 of Hearts", new int[]{5,5}));
            add(new BlackjackCard("4 of Hearts", new int[]{4,4}));
            add(new BlackjackCard("3 of Hearts", new int[]{3,3}));
            add(new BlackjackCard("2 of Hearts", new int[]{2,2}));
            add(new BlackjackCard("Ace of Diamonds", new int[]{11,1}));
            add(new BlackjackCard("King of Diamonds", new int[]{10,10}));
            add(new BlackjackCard("Queen of Diamonds", new int[]{10,10}));
            add(new BlackjackCard ("Jack of Diamonds", new int[]{10,10}));
            add(new BlackjackCard("10 of Diamonds", new int[]{10,10}));
            add(new BlackjackCard("9 of Diamonds", new int[]{9,9}));
            add(new BlackjackCard("8 of Diamonds", new int[]{8,8}));
            add(new BlackjackCard("7 of Diamonds", new int[]{7,7}));
            add(new BlackjackCard("6 of Diamonds", new int[]{6,6}));
            add(new BlackjackCard("5 of Diamonds", new int[]{5,5}));
            add(new BlackjackCard("4 of Diamonds", new int[]{4,4}));
            add(new BlackjackCard("3 of Diamonds", new int[]{3,3}));
            add(new BlackjackCard("2 of Diamonds", new int[]{2,2}));
        }};
        this.cards = deck;
        this.handTotals = new int[] {0,0};
        this.hit();
        this.hit();
        System.out.println(handTotals);


    }
    public String getCurrentTotalsString(){
        if (handTotals[0] > handTotals[1] || handTotals[0] < handTotals[1]){
            if (handTotals[0] <= 21){
                return("Total(s): " + handTotals[1] + " | " + handTotals[0]);
            }
        }
        return  ("Total(s): " + handTotals[0]);


    }
    @Override
     public int getBestTotal(){
         //gets the total for the primary and secondary values
         if (this.handTotals[0] <= 21){
             return this.handTotals[0];
         }
         else{
             return this.handTotals[1];
         }
     }
}
