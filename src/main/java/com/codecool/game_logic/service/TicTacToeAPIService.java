package com.codecool.game_logic.service;

import com.codecool.fun_fact_generator_service.service.APIService;
import com.codecool.overcomplicated_tic_tac_toe.model.Game;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by makaimark on 2016.12.04..
 */
public class TicTacToeAPIService {

    private static TicTacToeAPIService INSTANCE;
    private static final String API_URL = "tttapi.herokuapp.com/api/v1/";

    public static TicTacToeAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicTacToeAPIService();
        }
        return INSTANCE;
    }

    public String step(String place) throws URISyntaxException, IOException {
        Game game = Game.getInstance();
        List<String> state = game.getGameState();
        state.set(Integer.parseInt(place), "X");
        game.setGameState(state);
        String str = String.join(",", state);
        str = str.replace(",", "");
        URIBuilder builder = new URIBuilder(API_URL + str + "/X");
        System.out.println("Builder" + builder);
        String response =  execute(builder.build());
        System.out.println("Response = " + response);
        return response;
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
