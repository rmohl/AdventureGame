package adventure;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class Room implements java.io.Serializable {

    private static final long serialVersionUID = -5353592830244577839L;
    private int id;
    private String roomName;
    private String longDescription;
    private String shortDescription;
    private ArrayList<Item> itemList;
    private HashMap<String, Room> connectedRooms;
    private boolean beenHere;


    /* CONSTRCUTORS: */

    /**
     * Default Room constructor.
     *
     */
    public Room() {
        this (0, "", "", "");
    }

    /**
     * Room constructor (with parameters).
     * 
     * @param idParam  id of room
     * @param name  name of room
     * @param longD  long description of room
     * @param shortD  short description of room
     *
     */
    public Room(int idParam, String name, String shortD, String longD) {
        setId(idParam);
        setRoomName(name);
        setLongDescription(longD);
        setShortDescription(shortD);
        setItemList(new ArrayList<Item>());
        setConnectedRooms(new HashMap<String, Room>());
        setBeenHere(false);
    }


    /* ACCESSORS: */
    
    /**
     * Gets id of current room object.
     *
     * @return the id of the current room object
     *
     */
    public int getId(){
        return id;
    }

    /**
     * Gets name of current room object.
     *
     * @return the name of the current room object
     *
     */
    public String getName(){
        return roomName;
    }

    /**
     * Gets long description of current room object.
     *
     * @return the long description of the current room object
     *
     */
    public String getLongDescription(){
        return longDescription;
    }

    /**
     * Gets short description of current room object.
     *
     * @return the short description of the current room object
     *
     */
    public String getShortDescription(){
        return shortDescription;
    }

    /**
     * Gets list of items in current room object.
     *
     * @return the list of items in the current room object
     *
     */
    public ArrayList<Item> listItems(){
        return itemList;
    }

    /**
     * Gets room that is in given direction of current room object.
     *
     * @param direction  the direction in which the connected room is in realtion to the current room
     * @return the connected room that is located in the given direction
     *
     */
    public Room getConnectedRoom(String direction) {
        String properDirection = Parser.getProperDirection(direction);
        if (properDirection == null) {
            return null;
        } else if (!connectedRooms.containsKey(properDirection)) {
            return null;
        }
        // else:
        return connectedRooms.get(properDirection);
    }

    /**
     * Gets boolean determining if player has been in room before.
     *
     * @return the boolean value determining if player has been in room before
     *
     */
    public boolean getBeenHere(){
        return beenHere;
    }


    /* MUTATORS: */

    /**
     * Sets current room's id.
     *
     * @param idParam  the id of current room object
     *
     */
    public void setId(int idParam){
        id = idParam;
    } 

    /**
     * Sets current room's name.
     *
     * @param name  the name of current room object
     *
     */
    public void setRoomName(String name){
        roomName = name;
    } 

    /**
     * Sets long description of current room object.
     *
     * @param longD  the long description of current room object
     *
     */
    public void setLongDescription(String longD){
        longDescription = longD;
    }    

    /**
     * Sets short description of current room object.
     *
     * @param shortD  the short description of current room object
     *
     */
    public void setShortDescription(String shortD){
        shortDescription = shortD;
    }   

    /** 
     * Sets list of all items in current room object.
     *
     * @param list  the item list in current room object
     *
     */
    public void setItemList(ArrayList<Item> list){
        itemList = list;
    } 

    /**
     * Sets given room to be in given direction of current room object.
     *
     * @param direction  the direction to set given room
     * @param room the room to be connected
     *
     */
    public void setConnectedRoom(String direction, Room room) {
        String properDirection = Parser.getProperDirection(direction);
        if (properDirection != null) {
            connectedRooms.put(properDirection, room);
        }
    }

    /** 
     * Sets HashMap of all current room's connected rooms.
     *
     * @param map  the HashMap to be set for current room object
     *
     */
    public void setConnectedRooms(HashMap<String, Room> map){
        connectedRooms = map;
    } 

    /** 
     * Sets boolean determining if player has been in room before.
     *
     * @param bool  the boolean value determining if player has been in room before
     *
     */
    public void setBeenHere(boolean bool){
        beenHere = bool;
    } 


    /* FILE-PARSING METHODS: */

    /**
     * Completely initializes all rooms in adventure.
     *
     * @param rArray  JSONArray of rooms in adventure
     * @param iList  ArrayList of items in adventure
     * @param rList  ArrayList of rooms in adventure
     *
     */
    public static void initializeRooms(JSONArray rArray, ArrayList<Item> iList, ArrayList<Room> rList) throws Exception{
        makeRooms(rArray, iList, rList);
        finalizeRooms(rArray, rList);
    }

    /**
     * Creates all needed room objects and adds them to the given Adventure object
     *
     * @param rArray  JSONArray of rooms in adventure
     * @param iList  ArrayList of items in adventure
     * @param rList  ArrayList of rooms in adventure
     *
     */
    private static void makeRooms(JSONArray rArray, ArrayList<Item> iList, ArrayList<Room> rList) throws Exception{
        for (Object r: rArray) {
            JSONObject roomJSON = (JSONObject) r;
            int roomId = (int) (long) roomJSON.get("id");
            String roomName = (String) roomJSON.get("name");
            String shortD = (String) roomJSON.get("short_description");
            String longD = (String) roomJSON.get("long_description");

            Room roomOBJ = new Room(roomId, roomName, shortD, longD);
            addRoom(roomOBJ, roomJSON, iList, rList);
        }
    }

    /**
     * Initializes a single room and adds it to the Adventure's room list
     *
     * @param rO  Room object of room being added
     * @param rJ  JSONObject of room being added
     * @param iList  ArrayList of items in adventure
     * @param rList  ArrayList of rooms in adventure
     *
     */
    private static void addRoom(Room rO, JSONObject rJ, ArrayList<Item> iList, ArrayList<Room> rList) throws Exception{
        setRoomLoot(rO, rJ, iList);
        if (rJ.get("start") != null) {
            Game.getAdventure().setCurrentRoom(rO);
            Game.getPlayer().setCurrentPlayerRoom(rO);
        }
        rList.add(rO);
        Game.getAdventure().addRoom(rO);
    }

    /**
     * Sets the loot for the Room object passed in
     *
     * @param roomOBJ  the given Room object
     * @param roomJSON  the JSON object used to get all 'loot' in adventure
     * @param iList  ArrayList of items in adventure
     *
     */
    private static void setRoomLoot(Room roomOBJ, JSONObject roomJSON, ArrayList<Item> iList) throws Exception{
        if (roomJSON.get("loot") != null) {
            JSONArray placingItemsArray = (JSONArray) roomJSON.get("loot");
            for (Object i: placingItemsArray) {         // going through items in room's "loot" key
                addAdventureItem(i, iList, roomOBJ);
            }
        }
    }

    /**
     * Intializes an item's containing room and updates its containing room's item list
     *
     * @param i  a suspected Room object from a JSONArray
     * @param iList  ArrayList of items in adventure
     * @param roomOBJ  the given Room object
     *
     */
    private static void addAdventureItem(Object i, ArrayList<Item> iList, Room roomOBJ) throws Exception{
        JSONObject itemJSON = (JSONObject) i;
        int itemId = (int) (long) itemJSON.get("id");
        for (Item j: iList) {                    // going through items in itemList
            if (j.getId() == itemId){
                j.setContainingRoom(roomOBJ);
                roomOBJ.addItem(j);
            }
        }
    }

    /**
     * Initializes all room connections in Adventure object.
     *
     * @param rArray  the JSONArray of rooms in adventure
     * @param roomList  the ArrayList of rooms in adventure
     *
     */
    private static void finalizeRooms(JSONArray rArray, ArrayList<Room> roomList) throws Exception{
        for (Object r: rArray) {
            finalizeRoom(r, roomList);
        }
    }

    /**
     * Initializes a single room's connections in an Adventure object.
     *
     * @param r  the Room object that we will be connecting rooms to
     * @param roomList  the ArrayList of rooms in adventure
     *
     */
    private static void finalizeRoom(Object r, ArrayList<Room> roomList) throws Exception{
        JSONObject roomJSON = (JSONObject) r;
        int currentRoomId = (int) (long) roomJSON.get("id");            // id of current room
        JSONArray roomArrayDir = (JSONArray) roomJSON.get("entrance");
        for (Object e: roomArrayDir) {
            JSONObject entranceJSON = (JSONObject) e;
            int dirRoomId = (int) (long) entranceJSON.get("id");        // id of room we want to connect
            String direction = (String) entranceJSON.get("dir");
            setRoomConnect(roomList, currentRoomId, dirRoomId, direction);
        }
    }

    /**
     * Initializes all connections in Room object with given room id.
     *
     * @param rList  the ArrayList of rooms in adventure
     * @param currRmId  the id of the Room object that will gain a room connection
     * @param dirRmId  the id of the Room object to be set in given direction
     * @param dir  the given direction in which to set room
     *
     */
    private static void setRoomConnect(ArrayList<Room> rList, int currRmId, int dirRmId, String dir) throws Exception{
        Room roomOBJ = findProperRoom(rList, currRmId);
        // setting room directions 
        for (Room r2: rList) {
            int roomId2 = r2.getId(); 
            if (dirRmId == roomId2) {
                roomOBJ.setConnectedRoom(dir, r2);
            }
        }
    }

    /**
     * Finds proper Room object that matches given id
     *
     * @param roomList  the ArrayList of rooms in adventure
     * @param currentRoomId  the id of the Room object that will gain a room connection
     * @return Room object that matches given id
     *
     */
    private static Room findProperRoom(ArrayList<Room> roomList, int currentRoomId) throws Exception{
        Room roomOBJ = null;
        for (Room r2: roomList) {
            int roomId2 = r2.getId(); 
            if (roomId2 == currentRoomId) {
                roomOBJ = r2; 
            }
        }
        return roomOBJ;
    }


    /* OTHER: */

    /** 
     * Adds an item to item list.
     *
     * @param item  the item added to the item list of the room object
     *
     */
    public void addItem(Item item){
        listItems().add(item);
    }

    /**
     * Determines if short or long description should be printed for current room.
     *
     * @return string that provides information about what move() method ended up doing
     *
     */
    public String shortOrLong(){ 
        if (getBeenHere()) {
            return "s";
        }
        // else:
        return "l";
    }

    /** 
     * Prints Room object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Room Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Short desc: " + getShortDescription() + "\n"
                + "Long desc: " + getLongDescription() + "\n"
                + "# of Items: " + listItems().size() + "\n"
                + "Has northern room: " + connectedRooms.containsKey("N") + "\n"
                + "Has eastern room: " + connectedRooms.containsKey("E") + "\n"
                + "Has southern room: " + connectedRooms.containsKey("S") + "\n"
                + "Has western room: " + connectedRooms.containsKey("W") + "\n");
    } 
}
