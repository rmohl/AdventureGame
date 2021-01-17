package adventure;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Adventure implements java.io.Serializable {

    private static final long serialVersionUID = 4370354944440733386L;
    private ArrayList<Room> roomList;
    private ArrayList<Item> itemList;
    private Room currentRoom; 


    /* CONSTRCUTORS: */

    /**
     * Default Adventure constructor.
     *
     */
    public Adventure() {
        setRoomList(new ArrayList<Room>());
        setItemList(new ArrayList<Item>());
        setCurrentRoom(null);
    }


    /* ACCESSORS: */

    /**
     * Gets list of all rooms.
     *
     * @return the list of all rooms in current adventure
     *
     */
    public ArrayList<Room> listAllRooms(){
        return roomList;
    }

    /**
     * Gets list of all items.
     *
     * @return the list of all items in current adventure
     *
     */
    public ArrayList<Item> listAllItems(){
        return itemList;
    }

    /**
     * Gets current room.
     *
     * @return current room object
     *
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }   

    /**
     * Gets short description of current room.
     *
     * @return the short description of the current room in the current adventure
     *
     */
    public String getCurrentRoomDescription(){
        return getCurrentRoom().getShortDescription();
    }

    /**
     * Gets long description of current room.
     *
     * @return the long description of the current room in the current adventure
     *
     */
    public String getCurrentRoomDescriptionLong(){
        return getCurrentRoom().getLongDescription();
    }

    /**
     * Gets name of current room.
     *
     * @return the name of the current room in the current adventure
     *
     */
    public String getCurrentRoomName(){
        return getCurrentRoom().getName();
    }

    /**
     * Gets item list of current room.
     *
     * @return the item list of the current room in the current adventure
     *
     */
    public ArrayList<Item> getCurrentRoomItems(){
        return getCurrentRoom().listItems();
    }


    // MUTATORS:

    /**
     * Sets list of all rooms.
     *
     * @param list  sets room list in current adventure
     *
     */
    public void setRoomList(ArrayList<Room> list){
        roomList = list;
    }           

    /**
     * Sets list of all items.
     *
     * @param list  sets item list in current adventure
     *
     */
    public void setItemList(ArrayList<Item> list){
        itemList = list;
    }          

    /**
     * Sets current room.
     *
     * @param room  sets current room in current adventure
     *
     */
    public void setCurrentRoom(Room room){
        if (currentRoom != null) {
            currentRoom.setBeenHere(true);
        }
        currentRoom = room;
    }     


    /* FILE-PARSING METHODS: */

    /**
    * Sets up adventure using given json object.
    *
    * @param obj  the json object that the player is using
    * @return the Adventure object created from organizing given json object info
    *
    */
    public Adventure initializeAdventure(JSONObject obj) throws Exception{
        JSONObject testAdventure = (JSONObject) obj.get("adventure");
        JSONArray itemArray = (JSONArray) testAdventure.get("item");
        JSONArray roomArray = (JSONArray) testAdventure.get("room");
        ArrayList<Item> iList = new ArrayList<Item>();
        ArrayList<Room> rList = new ArrayList<Room>();

        Game.getParser().testJsonFile(roomArray, itemArray);
        Item.initializeItems(itemArray, iList);
        Room.initializeRooms(roomArray, iList, rList);

        return Game.getAdventure();
    }


    /* OTHER: */

    /** 
     * Adds a room to room list.
     *
     * @param room  adds a room to the room list of the adventure object
     *
     */
    public void addRoom(Room room){
        listAllRooms().add(room);
    }     

    /** 
     * Adds an item to item list.
     *
     * @param item  adds an item to the item list of the adventure object
     *
     */
    public void addItem(Item item){
        listAllItems().add(item);
    }   

    /**
     * Moves player given direction.
     *
     * @param direction  direction in which player will move
     * @return string that provides information about what move() method ended up doing
     *
     */
    public String move(String direction){ 
        Game.getPlayer().setCurrentPlayerRoom(getCurrentRoom().getConnectedRoom(direction));
        setCurrentRoom(getCurrentRoom().getConnectedRoom(direction));
        return getCurrentRoom().shortOrLong();
    } 

   /**
    * Prints description and item list of current room in desired format.
    *
    * @param type  determines if method should print long or short description, 
    *              and if it should be printed with item list or not
    *
    * @return String to be printed
    *
    */
    public String printCurrentRoom(String type){
        String string = "This room is called '" + getCurrentRoomName() + "'.\n\n"; 
        if (type.equals("l")) {
            string = string + getCurrentRoomDescriptionLong() + "\n";
        } else {
            string = string + getCurrentRoomDescription() + "\n";
        }
        return string + printCurrentRoomItems();
    }

   /**
    * Prints current room items in desired format.
    *
    * @return String to be printed
    *
    */
    public String printCurrentRoomItems(){
        String string;

        if (getCurrentRoomItems().size() == 0) {
            return "\nThere are no items in this room.\n";
        }
        // else: 
        string = "\nItems:\n";
        for (int i = 0; i < getCurrentRoomItems().size(); i++) {
            string = string + " - " + getCurrentRoomItems().get(i).getName() + "\n";
        }
        return string;
    }

    /** 
     * Prints Adventure object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        String string = "\n\n******************************\n***        Adventure       ***\n"
                        + "******************************\n\n\nROOMS (" + listAllRooms().size() + "):\n\n\n";
        for (int i = 0; i < listAllRooms().size(); i++) {
            string = string + ((i + 1) + " ") + listAllRooms().get(i) + "\n";
        }
        string = string + "\nITEMS(" + listAllItems().size() + "):\n\n\n";
        for (int i = 0; i < listAllItems().size(); i++) {
            string = string + ((i + 1) + " ") + listAllItems().get(i) + "\n";
        }
        return string + "\nCURRENT ROOM:\n\n\n" + getCurrentRoom().getName() + "\n\n";
    }  
}
