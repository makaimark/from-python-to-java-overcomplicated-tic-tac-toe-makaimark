package com.codecool.avatar_generator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AvatarAPIService {

    private static final String API_URL = "https://robohash.org/";

    private static AvatarAPIService INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(AvatarAPIService.class);

    public static AvatarAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AvatarAPIService();
        }
        return INSTANCE;
    }

    public URI avatar(String characters) throws URISyntaxException, IOException {
        logger.info("Returning the url of the avatar");
        System.out.println("In api service " + characters);
        URIBuilder builder = new URIBuilder(API_URL + characters +".png");
        System.out.println(builder);
        return builder.build();
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
 }