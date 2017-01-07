package com.codecool.gratulation_generator_service;

import com.codecool.gratulation_generator_service.controller.GratulationAPIController;
import com.codecool.gratulation_generator_service.service.GratulationAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.port;
import static spark.Spark.get;

public class GratulationGeneratorService {

    private GratulationAPIController controller;
    private static final Logger logger = LoggerFactory.getLogger(GratulationGeneratorService.class);

    public static void main(String[] args) {

        logger.debug("Starting service in port 60004");

        port(60004);

        GratulationGeneratorService service = new GratulationGeneratorService();

        service.controller = new GratulationAPIController(GratulationAPIService.getInstance());

        get("/gratulation", service.controller::getPicture);
    }
}
