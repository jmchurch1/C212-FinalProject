package edu.iu.c212.places.games.blackjack;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class BlackjackGame extends Game {

    // instance variables
    private double entryFee;
    private static JLabel totalLabels;
    private static  JLabel dealerLabel;
    private static JButton hit;
    private static JButton stay;
    private static BlackjackPlayer player;
    private static BlackjackDealer dealer;
    private static Object lock;
    private static Thread t;
    private static Boolean gameOver;
    private static Boolean win;
    private static Boolean tie;


    public BlackjackGame(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Blackjack Game";
    }

    @Override
    public void onEnter(User user) throws IOException {

        // check whether the User has enough money to get into the game
        if (user.getBalance() < entryFee){
            System.out.println("You do not have enough money to participate in this game, you are being removed to the lobby");
            arcade.transitionArcadeState("Lobby");
        }
        else
        {
            // remove the entry fee from the user's balance and save the user info to the file
            user.setBalance(user.getBalance() - entryFee);
            FileUtils.writeUserDataToFile(arcade.getAllUsers());
        }
        win = false;
        tie = false;
        gameOver = false;
        player = new BlackjackPlayer();
        dealer = new BlackjackDealer(player);
        JFrame blackjackFrame = new JFrame("Blackjack");
        JPanel mainPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        totalLabels = new JLabel(player.getCurrentTotalsString());
        statusPanel.add(totalLabels);
        dealerLabel = new JLabel("Dealer: "+dealer.getPartialHand());
        JPanel buttonsPanel = new JPanel();
        hit = new JButton("Hit");
        hit.addActionListener(new HitButtonListener());
        stay = new JButton("Stay");
        stay.addActionListener(new StayButtonListener());
        buttonsPanel.add(hit);
        buttonsPanel.add(stay);
        mainPanel.setPreferredSize(new Dimension(500, 500));
        statusPanel.add(dealerLabel);
        mainPanel.add(statusPanel);
        mainPanel.add(buttonsPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));;
        blackjackFrame.getContentPane().add(mainPanel);
        blackjackFrame.pack();
        blackjackFrame.setVisible(true);
        blackjackFrame.addWindowListener(new WindowClosedListener());
        blackjackFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lock = new Object();
        t = new Thread(() -> {
            synchronized (lock) {
                while (blackjackFrame.isVisible()) {
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException ignored) {}
                }
            }
        });
        t.start();
        try {
            BlackjackGame.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (win == true){
            System.out.println("Congrats won $50.00");
            user.setBalance(user.getBalance() + 50.00);
        }
        else if(tie == true){
            System.out.println("Since it was a tie. Money will be refunded.");
            user.setBalance(user.getBalance() + entryFee);
        }
        arcade.transitionArcadeState("Lobby");
        }
    static class HitButtonListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            player.hit();
            if (player.getBestTotal() > 21){
                totalLabels.setText("Bust\n You lose!");
                hit.setEnabled(false);
                stay.setEnabled(false);
                gameOver = true;


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
            dealer.play();
            if (player.getBestTotal() > dealer.getBestTotal()){
                if (dealer.getBestTotal() == -1){
                    totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: Busted"+ " You Win!");
                    dealerLabel.setText("");


                }
                totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: " + dealer.getBestTotal()+ " You Win!");
                dealerLabel.setText("");

            }
            else if (player.getBestTotal() < dealer.getBestTotal()){
                totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: " + dealer.getBestTotal()+ " You Lose!");
                dealerLabel.setText("");

            }
            else if (player.getBestTotal() == dealer.getBestTotal()){
                totalLabels.setText("You: " + player.getBestTotal() + " | Dealer: " + dealer.getBestTotal()+ " It's a Tie!");
                dealerLabel.setText("");
            }
            gameOver = true;

        }
    }

    static class WindowClosedListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {
            if (gameOver == true){
                if (player.getBestTotal() > dealer.getBestTotal() && player.getBestTotal() <= 21){
                    win = true;
                }
                else if (player.getBestTotal() == dealer.getBestTotal() && player.getBestTotal() <= 21){
                    tie = true;
                }
            }
            synchronized (lock) {
                lock.notify();
            }
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

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
