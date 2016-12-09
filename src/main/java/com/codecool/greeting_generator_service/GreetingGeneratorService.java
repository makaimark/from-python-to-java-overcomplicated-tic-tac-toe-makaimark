package com.codecool.greeting_generator_service;

import com.codecool.greeting_generator_service.controller.GreetingAPIController;
import com.codecool.greeting_generator_service.service.GreetingAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.*;

/**
 * Created by makaimark on 2016.12.04..
 */
public class GreetingGeneratorService {

    private GreetingAPIController controller;

    private static final Logger logger = LoggerFactory.getLogger(GreetingGeneratorService.class);

    public static void main(String[] args) {

        port(60001);

        GreetingGeneratorService service = new GreetingGeneratorService();

        service.controller = new GreetingAPIController(GreetingAPIService.getInstance());

        get("/greeting", service.controller::getYoda);

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
