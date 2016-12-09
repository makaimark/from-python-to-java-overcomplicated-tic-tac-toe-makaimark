package com.codecool.fun_fact_generator_service.controller;

import com.codecool.fun_fact_generator_service.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class FunFactAPIController {

    private static final Logger logger = LoggerFactory.getLogger(FunFactAPIController.class);
    public static final String CATEGORY_PARAM_KEY = "category";

    private final APIService apiService;

    /**
     * @param apiService
     */
    public FunFactAPIController(APIService apiService){
        this.apiService = apiService;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String random(Request request, Response response) throws IOException, URISyntaxException {
        logger.info("Get category name from request");
        String category = request.queryParams(CATEGORY_PARAM_KEY);
        return apiService.random(category);
    }

    public String getRandomByCategory(Request request, Response response) throws IOException, URISyntaxException {
        logger.info("Get random by category function calling");
        return apiService.getRandomByCategory(request.queryParams(CATEGORY_PARAM_KEY));
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String categories(Request request, Response response) throws IOException, URISyntaxException {
        logger.info("Get the categories");
        return apiService.categories();
    }

    public String status(Request request, Response response) {
        logger.info("Return status=ok if everything is fine");
        return "ok";
    }
}
