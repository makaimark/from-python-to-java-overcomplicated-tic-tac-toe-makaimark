package com.codecool.fun_fact_generator_service;

import com.codecool.fun_fact_generator_service.controller.FunFactAPIController;
import com.codecool.fun_fact_generator_service.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.*;

public class FunFactGeneratorService {
    private static final Logger logger = LoggerFactory.getLogger(FunFactGeneratorService.class);

    private FunFactAPIController controller;

    public static void main(String[] args) {
        logger.debug("Starting " + FunFactGeneratorService.class.getName() + "...");

        port(60000);

        FunFactGeneratorService application = new FunFactGeneratorService();

        application.controller = new FunFactAPIController(APIService.getInstance());

        // --- MAPPINGS ---
        get("/status", application.controller::status);
        get("/api/random", application.controller::random);
        get("/api/categories", application.controller::categories);
        get("/api/getbycategory", application.controller::getRandomByCategory);

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
