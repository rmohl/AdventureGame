# Adventure Game

Author: Rachael Mohl

Language: Java

Creation Tools: VS Code, JSON, Maven, Swing, JUnit

Created: May 19, 2020

Last Updated: June 15, 2020

Description: Text-based adventure game (similar to Colossal Cave Adventure) in which the user is able to pick and choose where they move, what they pick up, how they use items, etc., by using specific commands. Games are saved as JSON files and can be loaded/rewritten as the user wishes. A default JSON game file is included if the user does not provide their own. JUnit tests have been created in order to check if the user has provided a valid JSON game file.

## Compilation (in macOSX terminal using bash)

### Creating executable JAR file using Maven 

Download and open the AdventureGame folder and enter:

* $ mvn clean compile assembly:assembly

## Execution (in macOSX terminal using bash)

### Running text-based version from the command line 

* $ java -cp target/2430_A2-1.0-jar-with-dependencies.jar adventure.Game -[a|l] [PATH_FROM_CWD_TO_JSONFILE|PATH_FROM_CWD_TO_SAVEFILE]

Ensure that you are still in the AdventureGame folder when typing this command OR alter the JAR file path accordingly.

### Running GUI version from the command line 

* $ java -cp target/2430_A2-1.0-jar-with-dependencies.jar adventure.AdventureView 

Ensure that you are still in the AdventureGame folder when typing this command OR alter the JAR file path accordingly.

## Game Instructions

1. Run the program from the command line using the given command line instructions.
2. Ensure you use the proper flags in order to either, a) continue a saved game using your selected save file or, b) start a new adventure using your selected JSON file. If no flags are used, the default game file that is located in the resource folder (default.json) will be used.
3. Read the instructions provided at the beggining of the game if you would like to (there is an option to skip).
4. When playing the game, use 'look', 'go', 'take', and 'inventory' commands to travel around the map and interact with your surroundings.
5. Type keyword 'quit' when you would like to stop playing.

## Notes

There is a text-based version of the game and a basic GUI version. Follow the instructions that explain how to run the specific version of the game that you would like to play (located under the 'Execution' header). It is recommended you use the JAR file that has dependencies when executing this program (2430_A2-1.0-jar-with-dependencies.jar). '['/']' backets in terminal commands indicate optional parameters.
