package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.FileUtils;
import edu.iu.c212.utils.http.HttpUtils;
import edu.iu.c212.utils.http.TriviaQuestion;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TriviaGame extends Game{

    // instance variables
    private double entryFee;

    public TriviaGame(double entryFee, Arcade arcade){
        this.arcade = arcade;
        this.entryFee = entryFee;
        placeName = "Trivia Game";
    }

    @Override
    public void onEnter(User user) throws IOException {
        System.out.printf("%s, welcome to the trivia game!", user.getUsername());
        Scanner s = new Scanner(System.in);
        List<TriviaQuestion> Questions = HttpUtils.getTriviaQuestions(5);
        //stores how many correct answers the player got
        int correctlyAns = 0;
        for (var i = 0; i < 5; i++){

            System.out.println(("=").repeat(10));
            System.out.println(("You're on question "+ (i +1) + ". Ready?"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Questions.get(i).getQuestion());
            //randomizes where the correct answer will be.
            int correctAnswer = (int) Math.floor(Math.random()*4);
            int count = 0;
            for (var n = 0; n < 4; n++){
                if (n == correctAnswer){
                    System.out.println((n + 1) + " " + Questions.get(i).getCorrectAnswer());
                }
                else{
                    System.out.println((n + 1) + " " + Questions.get(i).getIncorrectAnswers().get(count));
                    count = count + 1;
                }
            }

            int userGuess;
            while (true) {
                if (s.hasNextInt()) {
                    userGuess = s.nextInt();
                    if (userGuess < 0 || userGuess > 4){
                        System.out.println("Please guess a number between 0 and 4 (inclusive)");
                        continue;
                    }
                    break;
                }
                else
                {
                    System.out.println("please enter an integer");
                    s.nextLine();
                }
            }
            if (userGuess == correctAnswer + 1){
                System.out.println("You got it right! You earned $2\n");
                correctlyAns += 1;
                user.setBalance(user.getBalance() + 2);
                FileUtils.writeUserDataToFile(arcade.getAllUsers());
            }
            else{
                System.out.println("You got it wrong. The correct answer is "+ Questions.get(i).getCorrectAnswer());
            }
        }
        if (correctlyAns < 3){
            System.out.println("Aww, good try. You got "+ correctlyAns +" questions right.");
        }
        else {
            System.out.println("“Nice! You got "+ correctlyAns +" questions right.");
        }
        arcade.transitionArcadeState("Lobby");
    }

    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return String.format("Trivia (Cost: $%.2f). Game? yes",entryFee);
    }
}
