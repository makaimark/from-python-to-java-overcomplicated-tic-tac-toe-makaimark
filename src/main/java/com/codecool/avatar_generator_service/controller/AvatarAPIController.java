package com.codecool.avatar_generator_service.controller;

import com.codecool.avatar_generator_service.service.AvatarAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.07..
 */
public class AvatarAPIController {

    private AvatarAPIService service;
    private static final Logger logger = LoggerFactory.getLogger(AvatarAPIController.class);

    public AvatarAPIController(AvatarAPIService service) {
        this.service = service;
    }

    public URI getAvatar(Request request, Response response) throws IOException, URISyntaxException {
        logger.info("Get the session id from the request");
        return service.avatar(request.session().id());
    }
}
