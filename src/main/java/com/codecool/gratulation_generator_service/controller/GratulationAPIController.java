package com.codecool.gratulation_generator_service.controller;

import com.codecool.gratulation_generator_service.service.GratulationAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class GratulationAPIController {

    private final GratulationAPIService apiservice;
    private static final Logger logger = LoggerFactory.getLogger(GratulationAPIController.class);

    public GratulationAPIController(GratulationAPIService service){
        this.apiservice = service;
    }

    public byte[] getPicture(Request request, Response response) throws URISyntaxException, IOException {
        logger.info("Getting the gratulation picture");
        return apiservice.getPicture(request.queryParams("top"), request.queryParams("bottom"));
    }
}
