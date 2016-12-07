package com.codecool.comic_generator_service.service;

import com.codecool.fun_fact_generator_service.service.APIService;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.06..
 */
public class ComicGeneratorAPIService {

    private static ComicGeneratorAPIService INSTANCE;
    private static final String API_URL = "http://xkcd.com/";

    public static ComicGeneratorAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ComicGeneratorAPIService();
        }
        return INSTANCE;
    }

    public String random(int randomNumber) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL + randomNumber + "/info.0.json");
        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
