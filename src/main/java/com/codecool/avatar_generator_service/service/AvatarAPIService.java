package com.codecool.avatar_generator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.07..
 */
public class AvatarAPIService {

    private static final String API_URL = "https://robohash.org/";

    private static AvatarAPIService INSTANCE;

    public static AvatarAPIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AvatarAPIService();
        }
        return INSTANCE;
    }

    public URI avatar(String characters) throws URISyntaxException, IOException {
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
