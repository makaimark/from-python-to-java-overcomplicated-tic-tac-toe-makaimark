package com.codecool.fun_fact_generator_service.controller;

import com.codecool.fun_fact_generator_service.service.APIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class FunFactAPIController {
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
        String category = request.queryParams(CATEGORY_PARAM_KEY);
        return apiService.random(category);
    }

    public String getRandomByCategory(Request request, Response response) throws IOException, URISyntaxException {
        return apiService.getRandomByCategory(request.queryParams("category"));
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String categories(Request request, Response response) throws IOException, URISyntaxException {
        return apiService.categories();
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
