package com.codecool.gratulation_generator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class GratulationAPIService {

    private static GratulationAPIService INSTANCE;
    private static final String API_URL = "https://ronreiter-meme-generator.p.mashape.com/meme";
    private static final Logger logger = LoggerFactory.getLogger(GratulationAPIService.class);

    public static GratulationAPIService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new GratulationAPIService();
        }
        return INSTANCE;
    }

    public byte[] getPicture(String top, String bottom) throws URISyntaxException, IOException {
        logger.info("Building the uri to get the gratulation picture");
        URIBuilder builder = new URIBuilder(API_URL);
        builder.addParameter("meme", "Brian Griffin");
        builder.addParameter("top", top);
        builder.addParameter("bottom", bottom);
        return execute(builder.build());
    }

    private byte[] execute(URI uri) throws IOException {
        return Base64.getEncoder().encode(Request.Get(uri)
                .addHeader("X-Mashape-Key", "ZE3Bba6sITmshTTIbNfu7va1zRdbp1pWGT0jsnd1J9m5dkyTgC")
                .execute()
                .returnContent()
                .asBytes());
    }
}
