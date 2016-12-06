package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * Created by makaimark on 2016.12.05..
 */
public class GreetingServiceController {

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(GreetingServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60001";

    public static String getMessage() throws IOException, URISyntaxException {
        System.out.println("In getMessage");
        return execute("/greeting");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        System.out.println("Uri");
        System.out.println(uri);
        return Request.Get(uri).execute().returnContent().asString();
    }
}
