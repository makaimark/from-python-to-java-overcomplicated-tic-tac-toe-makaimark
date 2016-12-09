package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class FunFactServiceController {
    private static final Logger logger = LoggerFactory.getLogger(FunFactServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60000";
    private  static  final String SELECTED_CATEGORY = "food";

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
        String result = execute("/api/random");
        String[] temp;
        temp = result.split(",");
        String joke = temp[4].substring(9, temp[4].length()-2);
        return joke;
    }

    public static ArrayList<String> getCategories() throws URISyntaxException, IOException {
        logger.info("Listing categories, send the request to the service");
        String result = execute("/api/categories");
        result = result.substring(1, result.length()-1);
        ArrayList<String> categories = new ArrayList<>(Arrays.asList(result.replace("\"", "").split(",")));
        return categories;
    }

//    // TODO: Getting by category is not implemented yet in the service
    public static String getRandomByCategory() throws URISyntaxException, IOException {
        logger.info("Send request to the Service, we would like to Get a random joke by category");
        ArrayList categories = getCategories();
        if ( !categories.contains(SELECTED_CATEGORY)) {
            throw new IllegalArgumentException();
        }
        String result = execute("/api/getbycategory?category="+SELECTED_CATEGORY);
        return result;
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
