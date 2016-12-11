package com.codecool.avatar_generator_service.controller;

import com.codecool.avatar_generator_service.service.AvatarAPIService;
import spark.Request;
import spark.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AvatarAPIController {

    private AvatarAPIService service;

    public AvatarAPIController(AvatarAPIService service) {
        this.service = service;
    }

    public URI getAvatar(Request request, Response response) throws IOException, URISyntaxException {
        System.out.println("In apicontroller");
        return service.avatar(request.session().id());
    }
 }
