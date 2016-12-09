package com.codecool.avatar_generator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.07..
 */
public class AvatarAPIService {

    private static final String API_URL = "https://robohash.org/";
    private static final Logger logger = LoggerFactory.getLogger(AvatarAPIService.class);
    private static AvatarAPIService INSTANCE;

    public static AvatarAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AvatarAPIService();
        }
        return INSTANCE;
    }

    public URI avatar(String characters) throws URISyntaxException, IOException {
        logger.info("Send the request to the avatar API to get the avatar picture url");
        URIBuilder builder = new URIBuilder(API_URL + characters +".png");
        return builder.build();
    }
}
