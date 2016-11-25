package com.codecool.overcomplicated_tic_tac_toe;

import com.codecool.overcomplicated_tic_tac_toe.controller.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.net.URISyntaxException;
import static spark.Spark.*;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final int PORT = 9000;

    public static void main(String[] args) {
        logger.debug("Starting server...");

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        // --- SERVER SETUP ---
        staticFileLocation("/public");
        port(PORT);

        // --- TEMPLATE ENGINE ---
        TemplateResolver templateResolver = new TemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setResourceResolver(new ClassLoaderResourceResolver());

        // --- ROUTES ---
        get("/", GameController::renderWelcome, new ThymeleafTemplateEngine(templateResolver));
        get("/game", GameController::renderGame, new ThymeleafTemplateEngine(templateResolver));
        // TODO: define route for game logic, which will handle user's step
        // TODO: define route for congratulation page

        logger.info("Server started on port " + PORT);
    }
}
