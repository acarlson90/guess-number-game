package com.aaroncarlson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaroncarlson.service.GameService;
import com.aaroncarlson.util.constants.AttributeNames;
import com.aaroncarlson.util.constants.Mappings;
import com.aaroncarlson.util.constants.ViewNames;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GameController {

    // == fields ==
    private final GameService gameService;

    // == constructors ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == request mappings ==
    @GetMapping(Mappings.PLAY)
    public String play(Model model) {
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.info("model = {}", model);
        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        } else {
            return ViewNames.PLAY;
        }
    }

    /*
     * This method is called when the form is submitted (aka: when the submit button is clicked)
     * The guess parameter needs to match the name attribute in the input tag inside the form
     */
    @PostMapping(Mappings.PLAY)
    public String processMessage(@RequestParam int guess) {
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);

        return Mappings.REDIRECT_PLAY;
    }

    @GetMapping(Mappings.RESTART)
    public String restart() {
        log.info("reset game");
        gameService.reset();

        return Mappings.REDIRECT_PLAY;
    }
}