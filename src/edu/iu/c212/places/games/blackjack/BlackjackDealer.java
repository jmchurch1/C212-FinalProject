package edu.iu.c212.places.games.blackjack;

public class BlackjackDealer extends BlackjackParticipant{
    private BlackjackCard shownCard;
    private int dealerBest;
    private BlackjackPlayer player;

    public BlackjackDealer(BlackjackPlayer player){
        this.handTotals = new int[] {0,0};
        dealerBest = -1;
        this.player = player;
        hit();
        hit();

    }

    @Override
    public void hit(){
        BlackjackCard cardDrawn = (player.cards.remove((int)Math.floor(Math.random() * player.cards.size())));
        if (handTotals[0] == 0){
            this.shownCard = cardDrawn;
        }
        handTotals[0] += cardDrawn.getValue()[0];
        handTotals[1] += cardDrawn.getValue()[1];
    }
    public String getPartialHand(){
        return (shownCard.getName() + " + ?");
    }
    public void play(){
        while (true){
            if (handTotals[1] > 21){
                this.dealerBest = -1;
                break;
            }
            if (handTotals[0] > 16 && handTotals[0] <= 21){
                this.dealerBest = handTotals[0];
                break;
            }
            else if (handTotals[1] > 16){
                this.dealerBest = handTotals[1];
                break;
            }
            else{
                hit();
            }
        }
    }
    @Override
    public int getBestTotal() {
        return dealerBest;
    }
}
