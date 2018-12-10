package com.aaroncarlson;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aaroncarlson.annotation.GuessCount;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // == constants ==

    // == fields ==
    /*
     * Lombo will not generate a getter for this field by using the annotation @Getter(AccessLevel.NONE). All
     * other fields will have getter methods generated
     */
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;
    private int number;
    private int minimum;
    private int maximum;
    private int remainingGuesses;
    private boolean isValidRange = true;
    @Setter
    private int guess;

    // == constructors ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        minimum = numberGenerator.getMinimumNumber();
        guess = numberGenerator.getMinimumNumber();
        remainingGuesses = guessCount;
        maximum = numberGenerator.getMaximumNumber();
        number = numberGenerator.next();
        log.info("the number is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
    }

    // == public methods ==
    @Override
    public void check() {
        checkValidNumberRange();

        if (isValidRange) {
            if (guess > number) {
                maximum = guess - 1;
            }

            if (guess < number) {
                minimum = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==
    private void checkValidNumberRange() {
        isValidRange = (guess >= minimum) && (guess <= maximum);
    }
}
