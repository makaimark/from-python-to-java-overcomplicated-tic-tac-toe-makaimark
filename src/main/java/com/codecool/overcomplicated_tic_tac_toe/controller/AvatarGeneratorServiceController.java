package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by makaimark on 2016.12.07..
 */
public class AvatarGeneratorServiceController {

    private static final String SERVICE_URL = "http://localhost:60003";

    public  static  String getAvatar() throws IOException, URISyntaxException {
        System.out.println("In getavatar");
        String result = execute("/getavatar");
        return result;
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        System.out.println("uri" + uri);
        return Request.Get(uri).execute().returnContent().asString();
    }

}
