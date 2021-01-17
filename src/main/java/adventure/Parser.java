package adventure;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.Iterator;
import java.util.ArrayList;

public class Parser {


    /* PARSING METHODS: */

    /** 
     * Parses given String command into a Command object.
     *
     * @param userCommand  the String command that will be made into a Command object
     * @return the Command object that was created
     *
     */
    public Command parseUserCommand(String userCommand) throws InvalidCommandException {
        Command command;
        userCommand = userCommand.trim();

        if(userCommand.contains(" ")){
            command = parseTwoWordCommand(userCommand);
        } else {
            command = new Command(userCommand);
        }

        return command;
    }

    /** 
     * Parses given two-word command String into a Command object.
     *
     * @param userCommand  the String command that will be made into a Command object
     * @return the Command object that was created
     *
     */
    private Command parseTwoWordCommand(String userCommand) throws InvalidCommandException {
        String [] commands = userCommand.split(" ", 2);
        Command command = new Command(commands[0], commands[1]);

        command.throwCommandExceptionIfTrue(commands[0].equals("go") && !validDirection(commands[1]), 
                              "Invalid direction.");
        command.throwCommandExceptionIfTrue((commands[0].equals("look") || commands[0].equals("take")) 
                              && !validCurrentRoomItem(commands[1]), "That item does not exist in this room.");
        command.throwCommandExceptionIfTrue((commands[0].equals("eat") || commands[0].equals("wear") 
                              || commands[0].equals("toss") || commands[0].equals("read")) 
                              && !validInventoryCommandItem(commands[1], commands[0]), "You cannot do that.");

        return command;
    }

    /**
     * Takes Command interprets it.
     *
     * @param command  parsed command created from user input
     * @return output to be printed based on command given
     *
     */
    public String handleCommandInput(Command command){ 
        if (command.hasSecondWord()) {
            return handleTwoWordInput(command);
        } else {
            return handleOneWordInput(command);
        }
    }
  
    /**
     * Helps handle output for two-word commands.
     *
     * @param command  parsed Command object created from user input
     * @return output to be printed based on command given
     *
     */
    private String handleTwoWordInput(Command command){
        if (command.getActionWord().equals("look") || command.getActionWord().equals("take")){
            return twoWordRoomItems(command);

        } else if (command.getActionWord().equals("eat") || command.getActionWord().equals("wear") 
           || command.getActionWord().equals("toss") || command.getActionWord().equals("read")){
            return twoWordInventoryItemsHelper(command);

        } else if (command.getActionWord().equals("go")) {
            return Game.getAdventure().printCurrentRoom(Game.getAdventure().move(command.getNoun()));
        }
        // else:
        return null;
    }
  
    /**
     * Helps handle output for one-word commands.
     *
     * @param command  parsed Command object created from user input
     * @return output to be printed based on command given
     *
     */
    private String handleOneWordInput(Command command){
        String string = "";
        if (command.getActionWord().equals("inventory")) {
            string = string + Game.getPlayer().displayInventory();
        }
        if (command.getActionWord().equals("look")) {
            string = handleOneWordInputLook();
        }
        return string;
    }

    /**
     * Helps handle output for one-word command 'look'.
     *
     * @return output to be printed based on command given
     *
     */
    private String handleOneWordInputLook(){
        String string = "";
        if (!(Game.getAdventure().getCurrentRoom().getBeenHere())) {
            string = string + "I am not allowed to give you more detail. \n"
                            + "I will repeat the long description of your location again:\n\n";
        }
        string = string + Game.getAdventure().printCurrentRoom("l");
        return string;
    }
  
    /**
     * Handles output for two-word commands that affect items in current room.
     *
     * @param command  parsed Command object created from user input
     * @return output to be printed based on command given
     *
     */
    private String twoWordRoomItems(Command command){
        String string = "";
        Iterator<Item> iter = Game.getAdventure().getCurrentRoomItems().iterator();
        while (iter.hasNext()) {
            Item i = iter.next();
            if (string.equals("")) {
                string = twoWordRoomItemsHelper(command, i, iter);
            }
        }
        // else:
        return string;
    }
  
    /**
     * Helps handle output for two-word commands that affect items in current room.
     *
     * @param command  parsed Command object created from user input
     * @param i  Item that will be used with command
     * @param iter  iterator object that currently at Item object
     * @return output to be printed based on command given
     *
     */
    private String twoWordRoomItemsHelper(Command command, Item i, Iterator<Item> iter){
        String string = "";
        if (command.getNoun().equals(i.getName())) {
            if (command.getActionWord().equals("look")) {
                string = string + i.getLongDescription();
            } else if (command.getActionWord().equals("take")) {
                string = twoWordRoomItemsTakeHelper(i, iter);
            }
        }
        return string;
    }

    /**
     * Helps handle output for two-word command 'take [item]'.
     *
     * @param i  Item that will be used with command
     * @param iter  iterator object that currently at Item object
     * @return output to be printed based on command given
     *
     */
    private String twoWordRoomItemsTakeHelper(Item i, Iterator<Item> iter){
        String string = "";
        string = string + "You took the " + i.getName() + ".";
        Game.getPlayer().addToInventory(i);
        iter.remove();
        return string;
    }
  
    /**
     * Helps handle output for two-word commands that affect items in inventory.
     *
     * @param command  parsed Command object created from user input
     * @return output to be printed based on command given
     *
     */
    private String twoWordInventoryItemsHelper(Command command){
        String string = null;
        Iterator<Item> iter = Game.getPlayer().getInventory().iterator();
        while (iter.hasNext()) {
            Item i = iter.next();
            if (command.getNoun().equals(i.getName())) {
                string = twoWordInventoryItemsHelper2(command, i, iter);
            }
        }
        return string;
    }

    /**
     * Helps handle output for two-word commands that affect items in inventory.
     *
     * @param command  parsed Command object created from user input
     * @param i  item that could possibly be removeds
     * @param iter  iterator that helps go through player inventory
     * @return output to be printed based on command given
     *
     */
    private String twoWordInventoryItemsHelper2(Command command, Item i, Iterator<Item> iter){
        String string = null;
        string = handleEat(command, i, iter);
        string = handleTossBuff(string, command, i, iter);
        string = handleWearBuff(string, command, i);
        string = handleReadBuff(string, command, i);
        return string;
    }

    /**
     * Helps handle output for two-word commands 'toss [item]' that affect items in inventory.
     *
     * @param string  string used to determine if check is necessary
     * @param command  parsed Command object created from user input
     * @param i  item that could possibly be removeds
     * @param iter  iterator that helps go through player inventory
     * @return output to be printed based on command given
     *
     */
    private String handleTossBuff(String string, Command command, Item i, Iterator<Item> iter){
        if (string == null) {
            string = handleToss(command, i, iter);
        }
        return string;
    }

    /**
     * Helps handle output for two-word commands 'wear [item]' that affect items in inventory.
     *
     * @param string  string used to determine if check is necessary
     * @param command  parsed Command object created from user input
     * @param i  item that could possibly be removeds
     * @return output to be printed based on command given
     *
     */
    private String handleWearBuff(String string, Command command, Item i){
        if (string == null) {
            string = handleWear(command, i);
        }
        return string;
    }

    /**
     * Helps handle output for two-word commands 'read [item]' that affect items in inventory.
     *
     * @param string  string used to determine if check is necessary
     * @param command  parsed Command object created from user input
     * @param i  item that could possibly be removeds
     * @return output to be printed based on command given
     *
     */
    private String handleReadBuff(String string, Command command, Item i){
        if (string == null) {
            string = handleRead(command, i);
        }
        return string;
    }

    /**
     * Helps handle output for eat command.
     *
     * @param command  parsed Command object created from user input
     * @param i  Item that will be used with command
     * @param iter  iterator object that currently at Item object
     * @return output to be printed based on command given
     *
     */
    private String handleEat(Command command, Item i, Iterator<Item> iter){
        String string = null;
        if (command.getActionWord().equals("eat")) {
            string = ((Food)i).eat();
            iter.remove();
        }
        return string;
    }

    /**
     * Helps handle output for toss command.
     *
     * @param command  parsed Command object created from user input
     * @param i  Item that will be used with command
     * @param iter  iterator object that currently at Item object
     * @return output to be printed based on command given
     *
     */
    private String handleToss(Command command, Item i, Iterator<Item> iter){
        String string = null;
        if (command.getActionWord().equals("toss")) {
            string = handleTossHelp(i, iter);
        }
        return string;
    }

    /**
     * Helps handle output for toss command.
     *
     * @param i  Item that will be used with command
     * @param iter  iterator object that currently at Item object
     * @return output to be printed based on command given
     *
     */
    private String handleTossHelp(Item i, Iterator<Item> iter){
        String string = null;
        if (i instanceof SmallFood) {
            string = ((SmallFood)i).toss();
        } else {
            string = ((Weapon)i).toss();
        }
        i.setContainingRoom(Game.getAdventure().getCurrentRoom());
        Game.getAdventure().getCurrentRoom().addItem(i);
        iter.remove();
        return string;
    }

    /**
     * Helps handle output for wear command.
     *
     * @param command  parsed Command object created from user input
     * @param i  Item that will be used with command
     * @return output to be printed based on command given
     *
     */
    private String handleWear(Command command, Item i){
        String string = null;
        if (command.getActionWord().equals("wear")) {
            string = ((Clothing)i).wear();
        }
        return string;
    }

    /**
     * Helps handle output for read command.
     *
     * @param command  parsed Command object created from user input
     * @param i  Item that will be used with command
     * @return output to be printed based on command given
     *
     */
    private String handleRead(Command command, Item i){
        String string = null;
        if (command.getActionWord().equals("read")) {
            if (i instanceof BrandedClothing) {
                string = ((BrandedClothing)i).read();
            } else {
                string = ((Spell)i).read();
            }
        }
        return string;
    }

    /**
     * Determines if the given direction is valid or not.
     *
     * @param direction  the direction to validate
     * @return the boolean determining if the given direction is valid or not
     * 
     */
    public boolean validDirection(String direction) {
        String properDirection = getProperDirection(direction);

        if (properDirection == null) {
            return false;
        }
        if (connectedRoomIsNull(direction)) {
            return false;
        }
        // else:
        return true;
    }

    /**
     * Determines if the given item is in the player's current room or not.
     *
     * @param item  the item to validate
     * @return the boolean determining if the given item is valid or not
     * 
     */
    public boolean validCurrentRoomItem(String item) {
        for (Item i: Game.getAdventure().getCurrentRoomItems()){
            if (item.equals(i.getName())) {
                return true;
            }
        }
        // if item not found:
        return false;
    }

    /**
     * Determines if the given item's class and given inventory command are valid together.
     *
     * @param item  the item to validate
     * @param command  the command String to validate
     * @return the boolean determining if the given item is valid or not
     * 
     */
    public boolean validInventoryCommandItem(String item, String command) {
        if (validInventoryItem(item) != null && validItemClass(validInventoryItem(item), command)) {
            return true;
        }
        // if item not found:
        return false;
    }

    /**
     * Determines if the given item is in the player's inventory or not.
     *
     * @param item  the item to validate
     * @return the item object representing the valid item found
     * 
     */
    private Item validInventoryItem(String item) {
        for (Item i: Game.getPlayer().getInventory()){
            if (item.equals(i.getName())) {
                return i;
            }
        }
        // if item not found:
        return null;
    }

    /**
     * Determines if the given command is valid to use with the class of the given item.
     *
     * @param item  the item whose class will be validated
     * @param command  the command that determines the item class validation
     * @return the boolean determining if the given item class and command pairing is valid or not
     * 
     */
    private boolean validItemClass(Item item, String command) {
        if (command.equals("eat") && item instanceof Food) {
            return true;
        } else if (command.equals("wear") && item instanceof Clothing) {
            return true;
        } else if (command.equals("toss") && (item instanceof SmallFood || item instanceof Weapon)) {
            return true;
        } else if (command.equals("read") && (item instanceof BrandedClothing || item instanceof Spell)) {
            return true;
        }
        // if item not found:
        return false;
    }

    /**
     * Checks if Room object in given direction is null.
     *
     * @param direction  the direction of the room to validate
     * @return the boolean determining if the given direction leads to a null room or not
     * 
     */
    public boolean connectedRoomIsNull(String direction){
        if (Game.getAdventure().getCurrentRoom().getConnectedRoom(direction) == null){
            return true;
        }
        // else:
        return false;
    }

    /**
     * Takes a direction and returns usable HashMap String.
     *
     * @param direction  the direction in which the connected room is in realtion to the current room
     * @return usable HashMap String
     *
     */
    public static String getProperDirection(String direction) {
        String returnValue = checkNESW(direction);

        if (returnValue != null) {
            return returnValue;
        }
        returnValue = checkUpDown(direction);
        if (returnValue != null) {
            return returnValue;
        }
        // else:
        return null;
    }

    /**
     * Takes a direction and returns usable HashMap String if it is a valid NESW direction.
     *
     * @param direction  the direction in which the connected room is in realtion to the current room
     * @return usable HashMap String
     *
     */
    private static String checkNESW(String direction) {
        if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("n")){
            return "N";
        } else if (direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("e")){
            return "E";
        } else if (direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("s")){
            return "S";
        } else if (direction.equalsIgnoreCase("west") || direction.equalsIgnoreCase("w")){
            return "W";
        } 
        // else:
        return null;
    }

   /**
     * Takes a direction and returns usable HashMap String if it is a valid up/down direction.
     *
     * @param direction  the direction in which the connected room is in realtion to the current room
     * @return usable HashMap String
     *
     */
    private static String checkUpDown(String direction) {
        if (direction.equalsIgnoreCase("up")){
            return "up";
        } else if (direction.equalsIgnoreCase("down")){
            return "down";
        } 
        // else:
        return null;
    }

    /**
     * Checks if loaded JSON file is valid.
     *
     * @param roomArray  JSONArray of room objects in JSON file
     * @param itemArray  JSONArray of item objects in JSON file
     *
     */
    public void testJsonFile(JSONArray roomArray, JSONArray itemArray) throws Exception{
        ArrayList<Integer> validRoomIds = getValidIds(roomArray);
        ArrayList<Integer> validItemIds = getValidIds(itemArray);
        for (Object r: roomArray) {
            JSONObject roomJSON = (JSONObject) r;
            hasConnectedRoom(roomJSON);
            hasValidConnectedRoomIds(roomJSON, validRoomIds);
            hasValidRoomConnections(roomJSON, roomArray);
            hasExistingItems(roomJSON, validItemIds);
        }
    }

    /**
     * Creates array of valid ids.
     *
     * @param jsonArray  JSONArray of objects whose ids are valid
     * @return arrayList of valid ids
     *
     */
    private ArrayList<Integer> getValidIds(JSONArray jsonArray){
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (Object r: jsonArray) {
            JSONObject obj = (JSONObject) r;
            int id = (int) (long) obj.get("id");
            array.add(id);
        }
        return array;
    }

    /**
     * Checks if given room JSONObject has any connected rooms.
     *
     * @param roomJSON  room JSONObject to be checked
     *
     */
    private void hasConnectedRoom(JSONObject roomJSON) throws Exception{
        JSONArray connectedRooms = (JSONArray) roomJSON.get("entrance");
        if (connectedRooms == null){
            throw new Exception("At least one room has no entrances");
        } else if (connectedRooms.size() == 0) {
            throw new Exception("At least one room has no entrances");
        }
    }

    /**
     * Checks if given room JSONObject has valid connected room ids.
     *
     * @param roomJSON  room JSONObject to be checked
     * @param validRoomIds  array of valid room ids
     *
     */
    private void hasValidConnectedRoomIds(JSONObject roomJSON, ArrayList<Integer> validRoomIds) throws Exception{
        int currentRoomId = (int) (long) roomJSON.get("id");
        JSONArray connectedRooms = (JSONArray) roomJSON.get("entrance");
        for (Object r: connectedRooms){
            JSONObject connectedRoom = (JSONObject) r;
            int connectedRoomId = (int) (long) connectedRoom.get("id");
            hasValConnRmIdsThrwExcept(currentRoomId, connectedRoomId, validRoomIds);
        }
    }

    /**
     * Throws exceptions for hasValidConnectedRoomIds().
     *
     * @param currRmId  id of one of two rooms being compared
     * @param connRmId  id of one of two rooms being compared
     * @param validRmIds  array of valid room ids
     *
     */
    private void hasValConnRmIdsThrwExcept(int currRmId, int connRmId, ArrayList<Integer> validRmIds) throws Exception{
        if (connRmId == currRmId) {
            throw new Exception("At least one room is connected to itself");
        }
        if (!validId(connRmId, validRmIds)) {
            throw new Exception("At least one room is connected to a non-existant room");
        }
    }

    /**
     * Checks if given room JSONObject has valid room connections.
     *
     * @param roomJSON  room JSONObject to be checked
     * @param roomArray  JSONArray of all room objects in JSON file
     *
     */
    private void hasValidRoomConnections(JSONObject roomJSON, JSONArray roomArray) throws Exception{
        int currentRoomId = (int) (long) roomJSON.get("id");
        JSONArray connectedRooms = (JSONArray) roomJSON.get("entrance");
        for (Object r: connectedRooms){
            int connectedRoomId = (int) (long) ((JSONObject) r).get("id");
            String dir = (String) ((JSONObject) r).get("dir");
            if (!validConnection(currentRoomId, connectedRoomId, getOppositeDir(dir), roomArray)){
                throw new Exception("At least one room has a connection that is not reciprocated");
            }
        }
    }

    /**
     * Gets opposite direction of given direction.
     *
     * @param dir  given direction 
     * @return direction opposite to given direction 
     *
     */
    private String getOppositeDir(String dir){
        String string = null;
        string = getOppositeDirNESW(dir);
        if (string == null) {
            string = getOppositeDirUpDown(dir);
        }
        return string;
    }

    /**
     * Gets opposite direction of given NESW direction.
     *
     * @param dir  given direction 
     * @return direction opposite to given direction 
     *
     */
    private String getOppositeDirNESW(String dir){
        if (dir.equals("N")){
            return "S";
        } else if (dir.equals("S")){
            return "N";
        } else if (dir.equals("E")){
            return "W";
        } else if (dir.equals("W")){
            return "E";
        }
        // else:
        return null;
    }

    /**
     * Gets opposite direction of given up/down direction.
     *
     * @param dir  given direction 
     * @return direction opposite to given direction 
     *
     */
    private String getOppositeDirUpDown(String dir){
        if (dir.equals("up")){
            return "down";
        } else if (dir.equals("down")){
            return "up";
        }
        // else:
        return null;
    }

    /**
     * Checks if the information given provides a valid room connection.
     *
     * @param currentRoomId     room id of room that will be checked in connection
     * @param connectedRoomId   id of room connection will be checked from
     * @param dir               direction in which connection is being checked
     * @param roomArray         JSONArray of all room objects in JSON file
     * @return boolean determining of this information provides a valid room connection or not
     *
     */
    private boolean validConnection(int currentRoomId, int connectedRoomId, String dir, JSONArray roomArray){
        for (Object r: roomArray){
            int id = (int) (long) ((JSONObject) r).get("id");
            if (connectedRoomId == id) {
                JSONArray entrances = (JSONArray) ((JSONObject) r).get("entrance");
                if (validConnectionHelp(currentRoomId, dir, entrances)){
                    return true;
                }
            }
        }
        // else:
        return false;
    }

    /**
     * Helps check if the information given provides a valid room connection.
     *
     * @param currentRoomId     room id of room that will be checked in connection
     * @param dir               direction in which connection is being checked
     * @param entrances         JSONArray of all room objects connected to current room
     * @return boolean determining of this information provides a valid room connection or not
     *
     */
    private boolean validConnectionHelp(int currentRoomId, String dir, JSONArray entrances){
        for (Object e: entrances){
            if (validConnectionHelp2(e, currentRoomId, dir)){
                return true;
            }
        }
        // else:
        return false;
    }

    /**
     * Helps validConnectionHelp().
     *
     * @param e  object containing an entrance
     * @param currentRoomId  room id of room that will be checked in connection
     * @param dir  direction in which connection is being checked
     * @return boolean determining of this information provides a valid room connection or not
     *
     */
    private boolean validConnectionHelp2(Object e, int currentRoomId, String dir){
        JSONObject entrance = (JSONObject) e;
        String otherDir = (String) entrance.get("dir");
        if (otherDir.equals(dir)) {
            int otherId = (int) (long) entrance.get("id");
            if (currentRoomId == otherId) {
                return true;
            }
        }
        // else:
        return false;
    }

    /**
     * Checks if a room contains valid items.
     *
     * @param roomJSON  room object that is being checked
     * @param validItemIds  arrayList of valid item ids
     *
     */
    private void hasExistingItems(JSONObject roomJSON, ArrayList<Integer> validItemIds) throws Exception {
        JSONArray items = (JSONArray) roomJSON.get("loot");
        if (items != null) {
            for (Object i: items){
                JSONObject item = (JSONObject) i;
                int itemId = (int) (long) item.get("id");
                if (!validId(itemId, validItemIds)) {
                    throw new Exception("At least one room contains an invalid item");
                }
            }
        }
    }

    /**
     * Checks if a given id is a valid id.
     *
     * @param id  id value that is being checked
     * @param validIds  arrayList of valid ids
     * @return boolean determining whether the given id is valid or not 
     *
     */
    private boolean validId(int id, ArrayList<Integer> validIds){
        for (int i: validIds){
            if (id == i){
                return true;
            }
        }
        // else:
        return false;
    }


    /* OTHER: */

    /** 
     * Gets String of all commands, where each command is separated by a space.
     *
     * @return the String of all commands
     *
     */
    public String allCommands() {
        String commands = "";

        for (String i: Command.getCommands()) {
            commands = commands + i + " ";
        }
        commands = commands.trim();

        return commands;
    }

    /** 
     * Prints Parser object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("Instance of a Parser object.\n");
    }  
}
