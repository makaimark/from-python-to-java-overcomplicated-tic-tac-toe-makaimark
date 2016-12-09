package com.codecool.avatar_generator_service;

import com.codecool.avatar_generator_service.controller.AvatarAPIController;
import com.codecool.avatar_generator_service.service.AvatarAPIService;
import com.codecool.fun_fact_generator_service.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.*;

/**
 * Created by makaimark on 2016.12.04..
 */
public class AvatarGeneratorService {

    private AvatarAPIController controller;
    private static final Logger logger = LoggerFactory.getLogger(AvatarGeneratorService.class);

    public static void main(String[] args) {

        logger.debug("Starting" + AvatarGeneratorService.class.getName());

        port(60003);

        AvatarGeneratorService application = new AvatarGeneratorService();

        application.controller = new AvatarAPIController(AvatarAPIService.getInstance());

        get("/getavatar", application.controller::getAvatar);

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
