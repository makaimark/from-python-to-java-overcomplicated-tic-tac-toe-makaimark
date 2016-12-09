package com.codecool.greeting_generator_service.controller;

import com.codecool.greeting_generator_service.GreetingGeneratorService;
import com.codecool.greeting_generator_service.service.GreetingAPIService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.05..
 */
public class GreetingAPIController {

    private final GreetingAPIService apiservice;

    private static final Logger logger = LoggerFactory.getLogger(GreetingAPIController.class);


    public GreetingAPIController(GreetingAPIService service) {
        this.apiservice = service;
    }

    public String getYoda(Request req, Response res) throws IOException, URISyntaxException {
        return apiservice.buildYoda();
    }
}
