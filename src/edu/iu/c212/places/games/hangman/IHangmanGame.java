package edu.iu.c212.places.games.hangman;

import java.util.List;

public interface IHangmanGame {
    String getBlurredWord(List<Character> guesses, String word);
    // This will blur out (with *) all letters in the word that have not been guessed
    List<Character> getValidLexicon();
    // This will get the lexicon of allowable input characters for the hangman game (this should be all lowercase alphabetical characters)
}
