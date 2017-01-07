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

    public String random(String category) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL + "/random");

        if (!StringUtils.isEmpty(category)) {
            logger.debug("Adding category filter: {}", category);
            builder.addParameter(FunFactAPIController.CATEGORY_PARAM_KEY, category);
        }

        logger.info("Getting a random fact from api.chucknorris");

        String response = execute(builder.build());

        JSONObject json = new JSONObject(response);

        return json.getString("value");
    }

    public String getRandomByCategory(String category) throws URISyntaxException, IOException {
        logger.info("Send request to the API, and we got the random joke");
        String response = execute(new URIBuilder(API_URL + "/random?category=" + category).build());
        JSONObject json = new JSONObject(response);
        return json.getString("value");
    }

    public String categories() throws URISyntaxException, IOException {
        logger.info("Listing categories");

        return execute(
                new URIBuilder(API_URL + "/categories").build()
        );
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}