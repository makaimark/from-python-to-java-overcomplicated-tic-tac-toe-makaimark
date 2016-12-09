package com.codecool.game_logic;

import com.codecool.game_logic.controller.TicTacToeAPIController;
import com.codecool.game_logic.service.TicTacToeAPIService;

import static spark.Spark.*;

/**
 * Created by makaimark on 2016.12.07..
 */
public class TicTacToeGeneratorService {

    private TicTacToeAPIController controller;

    public static void main(String[] args) {

        port(60004);

        TicTacToeGeneratorService application = new TicTacToeGeneratorService();

        application.controller = new TicTacToeAPIController(TicTacToeAPIService.getInstance());

        get("/game-place", application.controller::step);
    }
}
