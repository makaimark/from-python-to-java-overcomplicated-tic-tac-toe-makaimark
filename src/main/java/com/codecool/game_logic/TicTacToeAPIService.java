package com.codecool.game_logic;

import com.codecool.overcomplicated_tic_tac_toe.model.Game;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TicTacToeAPIService {

    private static TicTacToeAPIService INSTANCE;
    private static final String API_URL = "http://tttapi.herokuapp.com/api/v1/";
    private static final Logger logger = LoggerFactory.getLogger(TicTacToeAPIService.class);

    public static TicTacToeAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicTacToeAPIService();
        }
        return INSTANCE;
    }

    public static String step(String place, Response res) throws URISyntaxException, IOException {
        logger.info("New step in game field");
        Game game = Game.getInstance();
        logger.info("Set user step");
        game.setUserStep(Integer.parseInt(place));
        List<String> state = game.getState();
        if (game.checkStatus()) {
            res.redirect("/gratulation");
            return "";
        }
        String str = String.join(",", state);
        str = str.replace(",", "");
        URIBuilder builder = new URIBuilder(API_URL + str + "/X");
        String response = execute(builder.build());
        JSONObject json = new JSONObject(response);
        Integer gameRecomm = json.getInt("recommendation");
        logger.info(("Set computer step"));
        game.setComputerStep(gameRecomm);
        if (game.checkStatus()) {
            res.redirect("/gratulation");
            return "";
        }
        return "";
    }

    private static String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
