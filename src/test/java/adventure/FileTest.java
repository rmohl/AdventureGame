package adventure;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;

public class FileTest{
    private Game game;
    private Adventure adventure;
    private JSONObject parsedFile;
    private String expectedNoExitErrorMessage;
    private String invalidConnectionErrorMessage;
    private String invalidItemMessage;
    private String invalidRoomMessage;

@Before
public void setup(){
    game = new Game();
    adventure = new Adventure();
    parsedFile = null;
    expectedNoExitErrorMessage = "At least one room has no entrances";
    invalidConnectionErrorMessage = "At least one room has a connection that is not reciprocated";
    invalidItemMessage = "At least one room contains an invalid item";
    invalidRoomMessage = "At least one room is connected to a non-existant room";
}

/* 
 * ASSUPTIONS:
 */


@Test
public void testHasNoExits(){
    System.out.println("Testing to see if an error will be thrown when json file has a room with no 'entrance' component");
    parsedFile = game.loadAdventureJson("hasNoExits.json");
    try{
        adventure.initializeAdventure(parsedFile);
    } catch (Exception e) {
        assertTrue(e.getMessage().equals(expectedNoExitErrorMessage));
    }
}
@Test
public void testHasEmptyExits(){
    System.out.println("Testing to see if an error will be thrown when json file has a room with an empty 'entrance' component");
    parsedFile = game.loadAdventureJson("hasEmptyExits.json");
    try{
        adventure.initializeAdventure(parsedFile);
    } catch (Exception e) {
        assertTrue(e.getMessage().equals(expectedNoExitErrorMessage));
    }
}


@Test
public void testHasInvalidConnectionDirection(){
    System.out.println("Testing to see if an error will be thrown when json file has a room with an unreciprocated room connection direction value");
    parsedFile = game.loadAdventureJson("hasInvalidConnectionDirection.json");
    try{
        adventure.initializeAdventure(parsedFile);
    } catch (Exception e) {
        assertTrue(e.getMessage().equals(invalidConnectionErrorMessage));
    }
}
@Test
public void testHasInvalidConnectionId(){
    System.out.println("Testing to see if an error will be thrown when json file has a room with an unreciprocated room connection id value");
    parsedFile = game.loadAdventureJson("hasInvalidConnectionId.json");
    try{
        adventure.initializeAdventure(parsedFile);
    } catch (Exception e) {
        assertTrue(e.getMessage().equals(invalidConnectionErrorMessage));
    }
}


@Test
public void testHasInvalidItem(){
    System.out.println("Testing to see if an error will be thrown when json file has a room with an invalid item");
    parsedFile = game.loadAdventureJson("hasInvalidItem.json");
    try{
        adventure.initializeAdventure(parsedFile);
    } catch (Exception e) {
        assertTrue(e.getMessage().equals(invalidItemMessage));
    }
}


@Test
public void testHasInvalidExits(){
    System.out.println("Testing to see if an error will be thrown when json file has a room that is connected to an invalid room");
    parsedFile = game.loadAdventureJson("hasInvalidExits.json");
    try{
        adventure.initializeAdventure(parsedFile);
    } catch (Exception e) {
        assertTrue(e.getMessage().equals(invalidRoomMessage));
    }
}

}