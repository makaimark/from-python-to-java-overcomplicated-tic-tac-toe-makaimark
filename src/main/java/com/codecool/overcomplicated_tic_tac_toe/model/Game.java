package com.codecool.overcomplicated_tic_tac_toe.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static Game INSTANCE;

    // TODO: store the Game state in this class

    private List<String> gameState;

    public Game() {
        this.gameState = Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-");
    }

    public static Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
            logger.debug("New Game instance created.");
        }else{
            logger.debug("Existing Game instance used.");
        }
        return INSTANCE;
    }

    public void setGameState(List<String> state) {
        gameState = state;
    }

    public List<String> getGameState() {
        return gameState;
    }
}
