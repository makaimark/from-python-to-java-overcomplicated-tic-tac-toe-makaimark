# Overcomplicated TicTacToe

The single player TicTacToe, where you can play against the computer.

## Setup

Create `src/main/resources/db/flyway.properties` from the following template:

```
flyway.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
flyway.user=YOUR_DATABASE_USERNAME
flyway.password=YOUR_DATABASE_PASSWORD
```

## Run

The Repository contains Run configurations for IntelliJ IDEA. 
If you start the Server configuration, it shall also run the pre-written FunFactGeneratorService configuration.

Don't forget to extend this configuration (in .idea/runConfigurations.xml; or by updating it in IntelliJ) for all later services.

## What to build?

### The vision

The architect guy created the following Component UML:

![UML](component-diagram.png)

The idea behind this system is to build multiple services for smaller parts. 
This will allow development and maintenance separately. 

For more details, read the [Services](#services) section!

## Services<a name="services"></a>

The System, beside the main Server application, contains several services. They should use ports from 60000.

### TicTacToeAIService

The logic behind the computer logic. This is a service, written in NodeJS, and running on Heroku.
You can decide to include the NodeJS based server inside the system, or use the hosted version.

Repository: https://github.com/stujo/tictactoe-api
Hosted version example call: http://tttapi.herokuapp.com/api/v1/-O-----X-/O

### GreetingGeneratorService

A service, that creates a greeting text for the welcome page. ('/')
It should use one of the following APIs:
- https://market.mashape.com/orthosie/yoda-translator
- https://market.mashape.com/ismaelc/yoda-speak
- https://market.mashape.com/montanaflynn/l33t-sp34k
 
### FunFactGeneratorService

This service generates a fun fact, to show it during the game to make it more fun.
It's already partly implemented with an API, that return Chuck Norris facts.
Extend it with the getRandomByCategory option.

### GratulationGeneratorService

This service generates an image to show gratulation when the user wins (or some sarcastic words, if [s]he looses)
- https://market.mashape.com/ronreiter/meme-generator
- https://www.mashape.com/gatheringpoint/word-cloud-maker

### ComicGeneratorService

A service to help showing a comic from xkcd (http://xkcd.com/1001/info.0.json), where 1001 is a random number between 1 and 1500.
This API returns a lot of info, the image is enough to show.

### AvatarGeneratorService

Generates a Profile Avatar picture's URL, based on the user's name. 
You can choose to ask the user for a user name (via JavaScript or an additional Form), 
or you can identify the user with a session level random string.

- https://robohash.org/
- http://avatars.adorable.io/

Read the documentations, and use the best solution that you like.


## Features to implement:

1. First of all, feel free to refactor the prototype! *But use proper git branching!*
1. Connect the **com.codecool.FunFactGeneratorService** to the Server
    - The FunFactGeneratorService is pre-implemented, but you should extend it with the getRandomByCategory option. 
    - Write unit tests for the FunFactGeneratorService.
        - Mock the Network communication.
        - Test the Service for valid, invalid and missing responses.
    - Replace the {...} placeholder in the template, with the fun fact.
1. Nice Greeting
    - Implement the **com.codecool.GreetingGeneratorService**.
    - Write unit tests for the GreetingGeneratorService.
        - Mock the Network communication.
        - Test the Service for valid, invalid and missing responses.
    - Connect it with the GameController, and show the greeting in the welcome page.
1. Comic strip
    - Implement the **com.codecool.ComicGeneratorService *IN* Python**.
        - This service needs to be implemented in Python.
        - It work the same way as other Services: It should include a simple webserver.
        - When it is called, it should respond with the URL of the comic strip image.
    - Connect it with the GameController, and show the image in the game page.
1. Funny avatar
    - Implement the **com.codecool.AvatarGeneratorService**.
    - Write unit tests for the AvatarGeneratorService.
        - Mock the Network communication.
        - Test the Service for valid, invalid and missing responses.
    - Change the avatar image to use the AvatarGeneratorService's response.
1. Optimise the templates
    - The current prototype contains duplication. Create a proper layout, where the <head> and the title is not repeated.
1. Implement the game logic
    - The whole repo contains a prototype for the game. This is enough to demo the idea to the management, but needs more work.
    - Implement the game, so after clicking the large "Start game!" button the user gets the playable game.
    - Each button should be a link to **/game-place** and send the place's id as a parameter.
    - The route that handles this logic should display tha game with the current state. (This depends on you how you want to handle the state)
    - When the game ends, a page (new one) should show the image, generated by the GratulationGeneratorService:
1. GratulationGeneratorService
    - Implement the **com.codecool.GratulationGeneratorService**.
    - Write unit tests for the GratulationGeneratorService.
        - Mock the Network communication.
        - Test the Service for valid, invalid and missing responses.
    - Connect it with the GameController, and show the image on the after-game page.
1. Extend this README with all the setup and running steps you changed/added!
    
## Migration

Currently the project has a test migration, which creates a table, inserts a record and deletes the test table.

Your task about migration is to create the "**Leaderboard**" feature. 

1. So you should store the session id and as an enum, who won the match. (player, computer, tie)
1. Then, create a new migration, that extends this table with a "created_at" column, always storing the datetime of insertion.
1. **OOOps**, the client needs a new feature! They want to identify the users. So if you did not implement this feature, it's the time! Place an input box on top of the "Start Game!" button, and save the user's not for the current session. Also create a migration to store the user's name in the leaderboard table.
1. Additionally, figure out a migration on your own. Extend the DB with your idea.
    
In total, you should end up with minimum 5 migrations. If you wish, you can split them up into multiple migration files.
 
## Logging

Use proper logging. Yeah, I'm this helpful...
Use all the levels of logging in your code for the proper messages. Don't forget to handle all exceptions!
