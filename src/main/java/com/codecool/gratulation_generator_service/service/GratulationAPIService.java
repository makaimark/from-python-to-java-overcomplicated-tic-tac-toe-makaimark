package com.codecool.gratulation_generator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2017.01.01..
 */
public class GratulationAPIService {

    private static GratulationAPIService INSTANCE;
    private static final String API_URL = "https://ronreiter-meme-generator.p.mashape.com/meme";

    public static GratulationAPIService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new GratulationAPIService();
        }
        return INSTANCE;
    }

    public String getPicture() throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);
        System.out.println("result" + builder.build());
        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .addHeader("X-Mashape-Key", "ZE3Bba6sITmshTTIbNfu7va1zRdbp1pWGT0jsnd1J9m5dkyTgC")
                .execute()
                .returnContent()
                .asString();
    }
}
