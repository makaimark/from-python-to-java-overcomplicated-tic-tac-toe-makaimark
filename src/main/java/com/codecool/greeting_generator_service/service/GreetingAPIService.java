package com.codecool.greeting_generator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.05..
 */
public class GreetingAPIService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingAPIService.class);
    private static final String API_URL = "https://yoda.p.mashape.com/yoda";

    private static GreetingAPIService INSTANCE;

    public static GreetingAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GreetingAPIService();
        }
        return INSTANCE;
    }

    public String buildYoda() throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);
        builder.addParameter("sentence", "YodaText hellokaaa");

        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .addHeader("X-Mashape-Key", "ZE3Bba6sITmshTTIbNfu7va1zRdbp1pWGT0jsnd1J9m5dkyTgC")
                .addHeader("Accept", "text/plain")
                .execute()
                .returnContent()
                .asString();
    }
}
