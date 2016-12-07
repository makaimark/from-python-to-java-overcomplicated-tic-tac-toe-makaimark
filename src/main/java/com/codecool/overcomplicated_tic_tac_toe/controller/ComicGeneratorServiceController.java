package com.codecool.overcomplicated_tic_tac_toe.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by makaimark on 2016.12.06..
 */
public class ComicGeneratorServiceController {

    private static final String SERVICE_URL = "http://localhost:60002";

    public static String getComic() throws IOException, URISyntaxException {
//        String result =  execute("/getcomic");
//        result = result.substring(1, result.length()-1);
//        int index = result.indexOf("img");
//        result = result.substring(index+6);
//        ArrayList<String> comic = new ArrayList<>(Arrays.asList(result.replace("\"", "").split(",")));
//        return comic.get(0);
        return execute("/comic");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
