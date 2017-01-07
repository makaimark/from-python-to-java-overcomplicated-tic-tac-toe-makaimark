package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class FunFactServiceController {
    private static final Logger logger = LoggerFactory.getLogger(FunFactServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60000";
    private  static  final String FunFactCategoryValue = "food";

    public Boolean isRunning() throws URISyntaxException, IOException {
        logger.info("Checking Service status");

        Boolean running = execute("/status").equalsIgnoreCase("ok");

        if(running){
            logger.info("Service is running");
        } else {
            logger.warn("Service is not running");
        }

        return running;
    }

    public static String getRandom() throws URISyntaxException, IOException {
        logger.info("Getting Random Fun Fact, send the request to the service ");

        return execute("/api/random");
    }

    private static ArrayList<String> getCategories() throws URISyntaxException, IOException {
        logger.info("Listing categories, send the request to the service");
        String result = execute("/api/categories");
        result = result.substring(1, result.length()-1);
        ArrayList<String> categories = new ArrayList<>(Arrays.asList(result.replace("\"", "").split(",")));
        return categories;
    }

    static String getRandomByCategory() throws URISyntaxException, IOException {
        logger.info("Send request to the Service ( /api/getbycategory?category=), we would like to Get a random joke by category");
        ArrayList categories = getCategories();
        if ( !categories.contains(FunFactCategoryValue)) {
            throw new IllegalArgumentException();
        }
        return execute("/api/getbycategory?category="+FunFactCategoryValue);
    }

    /**
     * Executes the actual GET request against the given URI
     *
     * @param url - obj containing path and params.
     * @return
     * @throws IOException
     */
    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
