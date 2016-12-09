package com.codecool.comic_generator_service;

import com.codecool.comic_generator_service.controller.ComicGeneratorAPIController;
import com.codecool.comic_generator_service.service.ComicGeneratorAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.*;

/**
 * Created by makaimark on 2016.12.04..
 */
public class ComicGeneratorService {
    private static final Logger logger = LoggerFactory.getLogger(ComicGeneratorService.class);


    private ComicGeneratorAPIController controller;

    public static void main(String[] args) {

        logger.debug("Starting" + ComicGeneratorService.class.getName());

        port(60002);

        ComicGeneratorService application = new ComicGeneratorService();

        application.controller = new ComicGeneratorAPIController(ComicGeneratorAPIService.getInstance());

        get("/getcomic", application.controller::random);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });
    }
}
