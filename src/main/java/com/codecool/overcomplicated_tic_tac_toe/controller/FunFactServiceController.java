package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FunFactServiceController {
    private static final Logger logger = LoggerFactory.getLogger(FunFactServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60000";

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

    public String getRandom() throws URISyntaxException, IOException {
        logger.info("Getting Random Fun Fact");

        return execute("/random");
    }

    public String getCategories() throws URISyntaxException, IOException {
        logger.info("Listing categories");

        // TODO: This should parse the JSON and return an ArrayList<String> instead

        return execute("/categories");
    }

    // TODO: Getting by category is not implemented yet in the service
    public String getRandomByCategory(String category) throws URISyntaxException, IOException {
        throw new NotImplementedException();
    }

    /**
     * Executes the actual GET request against the given URI
     *
     * @param uri - obj containing path and params.
     * @return
     * @throws IOException
     */
    private String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
