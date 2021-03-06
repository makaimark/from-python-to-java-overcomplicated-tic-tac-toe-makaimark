package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ComicGeneratorServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ComicGeneratorServiceController.class);

    private static final String SERVICE_URL = "http://localhost:60002";

    public static String getComic() throws IOException, URISyntaxException {
        logger.info("We send a request to the service, /comic");
        return execute("/comic");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
