package com.codecool.game_logic.service;

import com.codecool.fun_fact_generator_service.service.APIService;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    public String step(String match) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL + match + "/O");
        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
