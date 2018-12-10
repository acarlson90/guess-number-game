package com.aaroncarlson;

public interface Game {

    int getNumber();
    int getGuess();
    void setGuess(int guess);
    int getMinimum();
    int getMaximum();
    int getRemainingGuesses();
    int getGuessCount();
    void reset();
    void check();
    boolean isValidRange();
    boolean isGameWon();
    boolean isGameLost();

}
