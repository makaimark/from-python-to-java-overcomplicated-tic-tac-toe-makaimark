package com.codecool.greeting_generator_service;

import com.codecool.greeting_generator_service.controller.GreetingAPIController;
import com.codecool.greeting_generator_service.service.GreetingAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Created by makaimark on 2016.12.04..
 */
public class GreetingGeneratorService {

    private GreetingAPIController controller;

    private static final Logger logger = LoggerFactory.getLogger(GreetingGeneratorService.class);

    public static void main(String[] args) {

        setup(args);

        GreetingGeneratorService service = new GreetingGeneratorService();

        service.controller = new GreetingAPIController(GreetingAPIService.getInstance());

        get("/greeting", service.controller::getYoda);

    }

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
