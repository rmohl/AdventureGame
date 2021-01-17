package adventure;

import java.util.ArrayList;
//import java.time.Duration;

public class Player implements java.io.Serializable {

    private static final long serialVersionUID = -109648365398870710L;
    private String name;
    private String saveName;
    private ArrayList<Item> inventory;
    private Room currentPlayerRoom;
    //private PlayTime playTime;


    /* CONTRUCTORS: */

    /**
     * Default Player constructor.
     *
     */
    public Player() {
        setName("");
        setSaveName("");
        setInventory(new ArrayList<Item>());
        setCurrentPlayerRoom(null);

        // setting interface method
        //playTime = (start, end) -> (Duration.between(start, end));
    }


    /* ACCESSORS: */

    /**
     * Gets player name.
     *
     * @return the player's name
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Gets player save name.
     *
     * @return the player's save name
     *
     */
    public String getSaveGameName(){
        return saveName;
    }

    /**
     * Gets player inventory.
     *
     * @return the player's inventory
     *
     */
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    /**
     * Gets the current room that the player is in.
     *
     * @return the player's current room
     *
     */
    public Room getCurrentRoom(){
        return currentPlayerRoom;
    }


    /* MUTATORS: */

    /**
     * Sets player's name.
     *
     * @param nameParam  the player name to be set
     *
     */
    public void setName(String nameParam){
        name = nameParam;
    } 

    /**
     * Sets player's save name.
     *
     * @param saveParam  the player save name to be set
     *
     */
    public void setSaveName(String saveParam){
        saveName = saveParam;
    } 

    /**
     * Sets player's inventory.
     *
     * @param items  the inventory to be set
     *
     */
    public void setInventory(ArrayList<Item> items){
        inventory = items;
    } 

    /**
     * Sets player's current room.
     *
     * @param room  the current room to be set
     *
     */
    public void setCurrentPlayerRoom(Room room){
        currentPlayerRoom = room;
    } 

    /**
     * Gets the player's PlayTime interface.
     *
     * @return the player's PlayTime interface
     *
     */
    /*
    public PlayTime getPlayTime(){
        return playTime;
    }*/


    /* OTHER: */

    /**
     * Adds an item to the player's inventory.
     *
     * @param item  the item to add to the player's inventory
     *
     */
    public void addToInventory(Item item){
        getInventory().add(item);
    }

    /**
     * Displays player's inventory in desired format.
     * 
     * @return the string of the player's current inventory
     *
     */
    public String displayInventory(){  
        if (getInventory().size() == 0) {
           return "There are no items in your inventory.\n";
        } else {
           String string = "You have:\n";
           for (Item i: getInventory()){
              string = string + i.inventoryItemPrint() + "\n";
           }
           return string;
        }
    }

    /** 
     * Prints Player object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        String string = "===== Player Object =====\n"
                        + "Name: " + getName() + "\n"
                        + "Save name: " + getSaveGameName() + "\n"
                        + "Current room: " + getCurrentRoom().getName() + "\n"
                        + "Inventory:\n";
        for (Item i: getInventory()) {
            string = string + " - " + i.getName() + "\n";
        }
        return string + "\n";
    }  
}
