package edu.iu.c212.places.games.blackjack;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BlackjackGame extends Game {

    // instance variables
    private double entryFee;
    private static JLabel totalLabels;
    private static JButton hit;
    private static JButton stay;
    private static BlackjackPlayer player;
    private static BlackjackDealer dealer;


    public BlackjackGame(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Blackjack Game";
    }

    @Override
    public void onEnter(User user) throws IOException {
        player = new BlackjackPlayer();
        dealer = new BlackjackDealer();
        JFrame blackjackFrame = new JFrame("Blackjack");
        JPanel mainPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        statusPanel.add(totalLabels);
        JLabel dealerLabel = new JLabel(dealer.getPartialHand());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(hit);
        buttonsPanel.add(stay);
        mainPanel.setPreferredSize(new Dimension(500, 500));
        statusPanel.add(dealerLabel);
        mainPanel.add(statusPanel);
        mainPanel.add(buttonsPanel);
        blackjackFrame.getContentPane().add(mainPanel);
        blackjackFrame.pack();
        blackjackFrame.setVisible(true);
        blackjackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    static class HitButtonListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            player.hit();
            if (player.getBestTotal() > 21){
                totalLabels.setText("Bust\nYou lose!");
            }
            else{
                totalLabels.setText(player.getCurrentTotalsString());
            }
        }
    }
    static class StayButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hit.setEnabled(false);
            stay.setEnabled(false);
            if (player.getBestTotal() > dealer.getBestTotal()){
                totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: " + dealer.getBestTotal()+ "\nYou Win!");
            }
            else if (player.getBestTotal() < dealer.getBestTotal()){
                totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: " + dealer.getBestTotal()+ "\nYou Lose!");
            }
            else if (player.getBestTotal() == dealer.getBestTotal()){
                totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: " + dealer.getBestTotal()+ "\n It's a Tie!");
            }

        }
    }
    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return String.format("Blackjack (Cost: $%.2f). Game? yes",entryFee);
    }
}
