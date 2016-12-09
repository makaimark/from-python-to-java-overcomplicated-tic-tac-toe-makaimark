package com.codecool.overcomplicated_tic_tac_toe.controller;

import com.codecool.fun_fact_generator_service.service.APIService;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by makaimark on 2016.12.06..
 */
public class ComicGeneratorServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ComicGeneratorServiceController.class);

    private static final String SERVICE_URL = "http://localhost:60002";

    public static String getComic() throws IOException, URISyntaxException {
//        String result =  execute("/getcomic");
//        result = result.substring(1, result.length()-1);
//        int index = result.indexOf("img");
//        result = result.substring(index+6);
//        ArrayList<String> comic = new ArrayList<>(Arrays.asList(result.replace("\"", "").split(",")));
//        return comic.get(0);
        logger.info("We send a request to the service, /comic");
        return execute("/comic");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
