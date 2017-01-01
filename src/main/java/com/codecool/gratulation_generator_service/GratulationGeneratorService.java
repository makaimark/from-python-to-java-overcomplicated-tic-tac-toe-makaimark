package com.codecool.gratulation_generator_service;

import com.codecool.gratulation_generator_service.controller.GratulationAPIController;
import com.codecool.gratulation_generator_service.service.GratulationAPIService;

import static spark.Spark.port;
import static spark.Spark.get;

/**
 * Created by makaimark on 2017.01.01..
 */
public class GratulationGeneratorService {

    private GratulationAPIController controller;

    public static void main(String[] args) {

        port(60004);

        GratulationGeneratorService service = new GratulationGeneratorService();

        service.controller = new GratulationAPIController(GratulationAPIService.getInstance());

        get("/gratulation", service.controller::getPicture);
    }
}
