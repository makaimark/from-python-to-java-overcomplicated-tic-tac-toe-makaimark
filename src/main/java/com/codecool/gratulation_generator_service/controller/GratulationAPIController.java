package com.codecool.gratulation_generator_service.controller;

import com.codecool.gratulation_generator_service.service.GratulationAPIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2017.01.01..
 */
public class GratulationAPIController {

    private final GratulationAPIService apiservice;

    public GratulationAPIController(GratulationAPIService service){
        this.apiservice = service;
    }

    public String getPicture(Request request, Response response) throws URISyntaxException, IOException {
        return apiservice.getPicture();
    }
}
