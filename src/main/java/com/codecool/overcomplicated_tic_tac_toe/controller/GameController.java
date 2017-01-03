package com.codecool.overcomplicated_tic_tac_toe.controller;

import com.codecool.overcomplicated_tic_tac_toe.model.Game;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class GameController {

    public static ModelAndView renderWelcome(Request req, Response res) throws Exception {
        Map params = new HashMap<>();
        params.put("yoda", GreetingServiceController.getMessage());
        params.put("avatar_url", AvatarGeneratorServiceController.getAvatar());
        return new ModelAndView(params, "welcome");
    }

    public static ModelAndView renderGame(Request req, Response res) throws IOException, URISyntaxException {
        Map params = new HashMap<>();
        String bycategory = FunFactServiceController.getRandomByCategory();
        String comicurl = ComicGeneratorServiceController.getComic();
        params.put("comic_url", comicurl);
        params.put("joke", bycategory);
        params.put("avatar_url", AvatarGeneratorServiceController.getAvatar());
        params.put("gamestate", Game.getInstance().getState());
        params.put("state", Game.getInstance().getGameResult());
        return new ModelAndView(params, "game");
    }
}
