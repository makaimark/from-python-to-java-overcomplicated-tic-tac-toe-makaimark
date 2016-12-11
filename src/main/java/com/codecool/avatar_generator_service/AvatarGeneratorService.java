package com.codecool.avatar_generator_service;

import com.codecool.avatar_generator_service.controller.AvatarAPIController;
import com.codecool.avatar_generator_service.service.AvatarAPIService;

import static spark.Spark.port;

import static spark.Spark.*;

public class AvatarGeneratorService {

    private AvatarAPIController controller;

    public static void main(String[] args) {

        port(60003);

        AvatarGeneratorService application = new AvatarGeneratorService();

        application.controller = new AvatarAPIController(AvatarAPIService.getInstance());

        get("/getavatar", application.controller::getAvatar);
    }
}
