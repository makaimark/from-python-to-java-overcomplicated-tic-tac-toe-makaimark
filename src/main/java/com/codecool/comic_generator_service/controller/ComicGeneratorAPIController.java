package com.codecool.comic_generator_service.controller;

import com.codecool.comic_generator_service.service.ComicGeneratorAPIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.06..
 */
public class ComicGeneratorAPIController {

    public static final String PARAM_KEY = "img";
    private ComicGeneratorAPIService apiService;

    public ComicGeneratorAPIController(ComicGeneratorAPIService service) {
        this.apiService = service;
    }

    public String random(Request request, Response response) throws IOException, URISyntaxException {
        String img = request.queryParams(PARAM_KEY);
        int random = (int) (Math.random() * (1500 - 1)) + 1;
        return apiService.random(random);
    }
}
