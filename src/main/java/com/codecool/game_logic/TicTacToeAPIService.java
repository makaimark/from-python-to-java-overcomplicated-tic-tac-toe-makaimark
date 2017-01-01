package com.codecool.game_logic;

import com.codecool.overcomplicated_tic_tac_toe.model.Game;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by makaimark on 2016.12.04..
 */
public class TicTacToeAPIService {

    private static TicTacToeAPIService INSTANCE;
    private static final String API_URL = "http://tttapi.herokuapp.com/api/v1/";

    public static TicTacToeAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicTacToeAPIService();
        }
        return INSTANCE;
    }

    public static String step(String place) throws URISyntaxException, IOException {
        Game game = Game.getInstance();
        List<String> state = game.getGameState();
        System.out.println(game.getGameState());
        state.set(Integer.parseInt(place), "X");
        String str = String.join(",", state);
        str = str.replace(",", "");
        URIBuilder builder = new URIBuilder(API_URL + str + "/X");
        String response = execute(builder.build());
        JSONObject json = new JSONObject(response);
        String gameState = json.getString("game");
        System.out.println("gameState =" + gameState);
        Integer gameRecomm = json.getInt("recommendation");
        System.out.println("gameRecomm =" + gameRecomm);
        state.set(Integer.parseInt(String.valueOf(gameRecomm)), "O");
        game.setGameState(state);
        System.out.println(game.getGameState());
        return "";
    }

    private static String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
