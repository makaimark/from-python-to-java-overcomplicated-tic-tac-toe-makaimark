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

        setup(args);

        FunFactGeneratorService application = new FunFactGeneratorService();

        application.controller = new FunFactAPIController(APIService.getInstance());

        // --- MAPPINGS ---
        get("/status", application.controller::status);
        get("/api/random", application.controller::random);
        get("/api/categories", application.controller::categories);

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

    /**
     * Setting up port
     * @param args - app args
     */
    private static void setup(String[] args){
        if(args == null || args.length == 0){
            logger.error("Port must be given as the first argument.");
            System.exit(-1);
        }

        try {
            port(Integer.parseInt(args[0]));
        } catch (NumberFormatException e){
            logger.error("Invalid port given '{}', it should be number.", args[0]);
            System.exit(-1);
        }
    }
}
