package com.codecool.comic_generator_service.controller;

import com.codecool.comic_generator_service.service.ComicGeneratorAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.06..
 */
public class ComicGeneratorAPIController {

    private static final Logger logger = LoggerFactory.getLogger(ComicGeneratorAPIController.class);

    private ComicGeneratorAPIService apiService;

    public ComicGeneratorAPIController(ComicGeneratorAPIService service) {
        this.apiService = service;
    }

    public String random(Request request, Response response) throws IOException, URISyntaxException {
        logger.info("generate the random number for the comic api");
        int random = (int) (Math.random() * (1500 - 1)) + 1;
        return apiService.random(random);
    }
}
