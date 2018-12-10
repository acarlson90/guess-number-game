package com.aaroncarlson;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==

    // == fields ==
    private final Game game;

    // == constructors ==
    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == init ==
    @PostConstruct
    public void init() {
        log.info("game number = {}", game.getNumber());
    }

    // ==  public methods ==
    @Override
    public String getMainMessage() {
        return "Number is in between " +
                game.getMinimum() +
                " and " +
                game.getMaximum() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return "You guessed it!! The number was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "You lost. The number was " + game.getNumber();
        } else if (!game.isValidRange()) {
            return "Invalid guess, not in range!";
        } else if (game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess?";
        } else {
            String direction = "Lower";

            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }

            String noun = (game.getRemainingGuesses() > 1) ? "guesses" : "guess";
            return direction + "! You have " + game.getRemainingGuesses() + " " + noun + " left";
        }
    }

}
