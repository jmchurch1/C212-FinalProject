package edu.iu.c212.places.games.blackjack;

//This is a class to represent an individual blackjack card
public class BlackjackCard {
    // each card has a name and a list of integers containing any values associated with the card
    private String name;
    private int[] value;

    public BlackjackCard(String name, int[] value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public int[] getValue(){
        return value;
    }

}
