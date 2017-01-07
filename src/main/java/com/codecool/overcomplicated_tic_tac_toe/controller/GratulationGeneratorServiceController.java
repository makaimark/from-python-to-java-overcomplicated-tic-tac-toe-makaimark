package com.codecool.overcomplicated_tic_tac_toe.controller;

import com.codecool.overcomplicated_tic_tac_toe.model.GameResult;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class GratulationGeneratorServiceController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceController.class);

    private static final String SERVICE_URL = "http://localhost:60004";

    static String getGratulationPicture(GameResult result) throws IOException, URISyntaxException {
        logger.info("Send the request to the service /gratulation");
        URIBuilder builder = new URIBuilder(SERVICE_URL + "/gratulation");
        builder.addParameter("top", "Congratulation");
        switch (result) {
            case COMPUTER_WON: builder.addParameter("bottom", "Computer"); break;
            case USER_WON: builder.addParameter("bottom", "User"); break;
            case TIE_GAME:builder.addParameter("bottom", "Tie game"); break;
        }

        return execute(builder.build());
    }

    private static String execute(URI uri) throws IOException, URISyntaxException {

        return Request.Get(uri).execute().returnContent().asString();
    }
}
