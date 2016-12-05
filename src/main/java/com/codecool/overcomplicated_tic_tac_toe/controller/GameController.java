package com.codecool.overcomplicated_tic_tac_toe.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    static String chuckNorrisJoke;
    final static String AVATAR_URL = "http://www.avatarpro.biz/avatar?size=90"; // TODO: Change this to be dynamic, and it should be the same for at least the session

    private static String getJoke() throws IOException, URISyntaxException {
        FunFactServiceController controller = new FunFactServiceController();
        String result = controller.getRandom();
        String[] temp;
        temp = result.split(",");
        String joke = temp[4].substring(9, temp[4].length()-2);
        return joke;
    }

    public static ModelAndView renderWelcome(Request req, Response res){
        Map params = new HashMap<>();
        params.put("avatar_url", AVATAR_URL);
        return new ModelAndView(params, "welcome");
    }

    public static ModelAndView renderGame(Request req, Response res) throws IOException, URISyntaxException {
        Map params = new HashMap<>();
        String joke = getJoke();
        params.put("joke", joke);
        params.put("avatar_url", AVATAR_URL);
        params.put("comic_url", "https://placeholdit.imgix.net/~text?txtsize=33&txt=place%20of%20a%20comic&w=700&h=200");
        return new ModelAndView(params, "game");
    }
}
