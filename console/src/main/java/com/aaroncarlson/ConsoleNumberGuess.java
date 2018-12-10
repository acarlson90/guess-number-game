package com.aaroncarlson;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsoleNumberGuess {

    // == constants ==

    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    // == constructors
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == events ==
    /*
     * ContextRefreshedEvent is published when the ApplicationContext is initialized or refreshed (ex:refresh()
     * on the configurable ApplicationContextInterface). Initialization means that ll the beans are loaded
     * post-processor beans are detected and activated, Singletons are pre-instantiated and the ApplicationContext
     * object is ready for use. Basically once the container is ready for use its going to fire this event automatically.
     * @EventListener(ContextRefreshedEvent.class) annotation is Spring's way of implementing ApplicationListener.
     * Using this annotation provides greater flexibility in the fact the signature of the method and the name
     * does not need to be onApplicationEventConfig(ContextRefreshedEvent) but the consumer/developer can name and
     * pass parameters that provide business/functional value. The ContextRefreshedEvent.class attribute to the
     * annotation defines the type of event
     */
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() container ready for use");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            // handles enter key
            scanner.nextLine();
            // updating what the user has selected
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }

                game.reset();
            }
        }
        scanner.close();
    }
}
