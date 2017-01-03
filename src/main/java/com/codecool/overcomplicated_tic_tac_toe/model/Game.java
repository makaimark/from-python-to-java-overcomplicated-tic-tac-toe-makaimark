package com.codecool.overcomplicated_tic_tac_toe.model;

import com.codecool.game_logic.TicTacToeAPIService;
import com.codecool.overcomplicated_tic_tac_toe.controller.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Game implements AIGame {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static Game INSTANCE;

    private List<String> gameState;

    private GameResult gameResult;

    public Game() {
        setInitialState();
        this.gameResult = GameResult.NO_WINNER;
    }

    public static Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
            logger.debug("New Game instance created.");
        } else {
            logger.debug("Existing Game instance used.");
        }
        return INSTANCE;
    }

    public String step(Request request, Response response) throws IOException, URISyntaxException {
        String place = request.queryParams("place");
        if (this.gameState.get(Integer.parseInt(place)) != "-") {
            response.redirect("/game");
            return "";
        }

        String result = TicTacToeAPIService.step(request.queryParams("place"));
        response.redirect("/game");
        return "";
    }

    @Override
    public void setInitialState() {
        this.gameState = Arrays.asList("-", "-", "-", "-", "-", "-", "-", "-", "-");
    }

    @Override
    public List<String> getState() {
        return gameState;
    }

    @Override
    public void setUserStep(int place) {
        this.gameState.set(place, "X");
    }

    @Override
    public void setComputerStep(int place) {
        this.gameState.set(place, "O");
    }

    @Override
    public GameResult getGameResult() {
        return this.gameResult;
    }

    private void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public boolean checkStatus() {
        for (int i = 0; i <= 8; i += 3) {
            if (Objects.equals(this.gameState.get(i), "X") && Objects.equals(this.gameState.get(i + 1), "X") && Objects.equals(this.gameState.get(i + 2), "X")) {
                setGameResult(GameResult.USER_WON);
                return true;
            }
            if (Objects.equals(this.gameState.get(i), "O") && Objects.equals(this.gameState.get(i + 1), "O") && Objects.equals(this.gameState.get(i + 2), "O")) {
                setGameResult(GameResult.COMPUTER_WON);
                return true;
            }
        }

        for (int j = 0; j <= 2; j++) {
            if (Objects.equals(this.gameState.get(j), "X") && Objects.equals(this.gameState.get(j + 3), "X") && Objects.equals(this.gameState.get(j + 6), "X")) {
                setGameResult(GameResult.USER_WON);
                return true;
            }
            if (Objects.equals(this.gameState.get(j), "O") && Objects.equals(this.gameState.get(j + 3), "O") && Objects.equals(this.gameState.get(j + 6), "O")) {
                setGameResult(GameResult.COMPUTER_WON);
                return true;
            }
        }

        if (Objects.equals(this.gameState.get(0), "O") && Objects.equals(this.gameState.get(4), "O") && Objects.equals(this.gameState.get(8), "O")) {
            setGameResult(GameResult.COMPUTER_WON);
            return true;
        }

        if (Objects.equals(this.gameState.get(0), "X") && Objects.equals(this.gameState.get(4), "X") && Objects.equals(this.gameState.get(8), "X")) {
            setGameResult(GameResult.USER_WON);
            return true;
        }

        if (Objects.equals(this.gameState.get(2), "O") && Objects.equals(this.gameState.get(4), "O") && Objects.equals(this.gameState.get(6), "O")) {
            setGameResult(GameResult.COMPUTER_WON);
            return true;
        }

        if (Objects.equals(this.gameState.get(2), "X") && Objects.equals(this.gameState.get(4), "X") && Objects.equals(this.gameState.get(6), "X")) {
            setGameResult(GameResult.USER_WON);
            return true;
        }
        int occurrences = Collections.frequency(this.gameState, "-");
        if ( occurrences == 0) {
            setGameResult(GameResult.TIE_GAME);
            return true;
        }
        return false;
    }
}
