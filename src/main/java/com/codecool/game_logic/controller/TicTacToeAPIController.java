package com.codecool.game_logic.controller;

import com.codecool.game_logic.service.TicTacToeAPIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.07..
 */
public class TicTacToeAPIController {

    private TicTacToeAPIService service;

    public TicTacToeAPIController(TicTacToeAPIService service) {
        this.service = service;
    }

    public String step(Request request, Response response) throws IOException, URISyntaxException {
        return service.step(request.queryParams("game"));
    }
}
