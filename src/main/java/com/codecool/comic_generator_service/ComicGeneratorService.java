package com.codecool.comic_generator_service;

import com.codecool.comic_generator_service.controller.ComicGeneratorAPIController;
import com.codecool.comic_generator_service.service.ComicGeneratorAPIService;

import static spark.Spark.*;

/**
 * Created by makaimark on 2016.12.04..
 */
public class ComicGeneratorService {

    private ComicGeneratorAPIController controller;

    public static void main(String[] args) {

        port(60002);

        ComicGeneratorService application = new ComicGeneratorService();

        application.controller = new ComicGeneratorAPIController(ComicGeneratorAPIService.getInstance());

        get("/getcomic", application.controller::random);
    }
}
