package com.codecool.fun_fact_generator_service.service;

import com.codecool.fun_fact_generator_service.controller.FunFactAPIController;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.utils.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.postgresql.core.Oid.JSON;

public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);
    private static final String API_URL = "https://api.chucknorris.io/jokes";

    private static APIService INSTANCE;

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    /**
     * Gets a random Chuck Norris fact, with category filter, if given
     *
     * @param category - if not {@link StringUtils#isEmpty(Object)} acts as a filter.
     * @return - JSON received from the API as it is.
     * @throws IOException
     * @throws URISyntaxException
     */
    public String random(String category) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL + "/random");

        if (!StringUtils.isEmpty(category)) {
            logger.debug("Adding category filter: {}", category);
            builder.addParameter(FunFactAPIController.CATEGORY_PARAM_KEY, category);
        }

        logger.info("Getting a random fact");

        return execute(builder.build());
    }

    public String getRandomByCategory(String category) throws URISyntaxException, IOException {
        String response = execute(new URIBuilder(API_URL + "/random?category=" + category).build());
        JSONObject json = new JSONObject(response);
        return json.getString("value");
    }

    /**
     * Listing fact categories
     *
     * @return - JSON from the API
     * @throws URISyntaxException
     * @throws IOException
     */
    public String categories() throws URISyntaxException, IOException {
        logger.info("Listing categories");

        return execute(
                new URIBuilder(API_URL + "/categories").build()
        );
    }

    /**
     * Executes the actual GET request against the given URI
     *
     * @param uri - obj containing path and params.
     * @return
     * @throws IOException
     */
    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}