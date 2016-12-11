package com.codecool.avatar_generator_service;

import com.codecool.avatar_generator_service.controller.AvatarAPIController;
import com.codecool.avatar_generator_service.service.AvatarAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.port;

import static spark.Spark.*;

public class AvatarGeneratorService {

    private AvatarAPIController controller;
    private static final Logger logger = LoggerFactory.getLogger(AvatarAPIController.class);

    public static void main(String[] args) {

        logger.debug("Starting" + AvatarAPIController.class.getName());

        port(60003);

        AvatarGeneratorService application = new AvatarGeneratorService();

        application.controller = new AvatarAPIController(AvatarAPIService.getInstance());

        get("/getavatar", application.controller::getAvatar);
    }
}
