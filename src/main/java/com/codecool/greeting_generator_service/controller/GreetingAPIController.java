package com.codecool.greeting_generator_service.controller;

import com.codecool.greeting_generator_service.service.GreetingAPIService;
import jdk.nashorn.internal.ir.RuntimeNode;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.05..
 */
public class GreetingAPIController {

    private final GreetingAPIService apiservice;

    public GreetingAPIController(GreetingAPIService service) {
        this.apiservice = service;
    }

    public String getYoda(Request req, Response res) throws IOException, URISyntaxException {
        System.out.println("In getYoda");
        String text = req.queryParams("sentence");
        return apiservice.buildYoda();
    }
}
