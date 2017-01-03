package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2017.01.01..
 */
public class GratulationGeneratorServiceController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceController.class);

    private static final String SERVICE_URL = "http://localhost:60004";

    public static String getGratulationPicture() throws IOException, URISyntaxException {
        logger.info("Send the request to the service /greeting");
        return execute("/gratulation");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
