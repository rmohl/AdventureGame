package adventure;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Item implements java.io.Serializable {

    private static final long serialVersionUID = 5768608581706080970L;
    private static final String[] CHARACTERISTICS = {"edible", "readable", "tossable", "wearable"};
    private int id;
    private String name;
    private String description;
    private Room containingRoom;


    /* CONSTRCUTORS: */

    /**
     * Default Item constructor.
     *
     */
    public Item() {
        this (0, "", "");
    }

    /**
     * Item constructor (with parameters).
     * 
     * @param idParam  id of item
     * @param nameParam  name of item
     * @param desc  description of item
     *
     */
    public Item(int idParam, String nameParam, String desc) {
        setId(idParam);
        setName(nameParam);
        setDescription(desc);

        // these variables are manually set later on
        setContainingRoom(null);
    }


    /* ACCESSORS: */

    /**
     * Gets id of item.
     *
     * @return the id of the current item object
     *
     */
    public int getId(){
        return id;
    }

    /**
     * Gets name of item.
     *
     * @return the name of current item object
     *
     */
    public String getName(){
        return(name);
    }

    /**
     * Gets description of item.
     *
     * @return the description of current item object
     *
     */
    public String getLongDescription(){
        return(description);
    }

    /**
     * Gets room that contains item.
     *
     * @return the room that contains the current item object
     *
     */
    public Room getContainingRoom(){
        return(containingRoom);
    }


    /* MUTATORS: */

    /**
     * Sets item id.
     *
     * @param idParam  the id that will be set for current item object
     *
     */
    public void setId(int idParam){
        id = idParam;
    } 

    /** 
     * Sets name of item.
     *
     * @param itemName  the name that will be set for current item object
     *
     */
    public void setName(String itemName){
        name = itemName;
    }   

    /** 
     * Sets description of item.
     *
     * @param desc  the description that will be set for current item object
     *
     */
    public void setDescription(String desc){
        description = desc;
    }   

    /** 
     * Sets containing room of item.
     *
     * @param room  the containing room that will be set for current item object
     *
     */
    public void setContainingRoom(Room room){
        containingRoom = room;
    }     


    /* FILE-PARSING METHODS: */

    /**
     * Completely initializes all items in adventure.
     *
     * @param itemArray  JSONArray of all items in adventure being loaded
     * @param itemList  Arraylist keeping track of items that have been loaded
     *
     */
    public static void initializeItems(JSONArray itemArray, ArrayList<Item> itemList) throws Exception{
        for (Object r: itemArray) {
            JSONObject itemJSON = (JSONObject) r;
            int itemId = (int) (long) itemJSON.get("id");
            String itemName = (String) itemJSON.get("name");
            String itemDesc = (String) itemJSON.get("desc");

            addAdventureItem(makeItem(findCharacteristics(itemJSON), itemId, itemName, itemDesc), itemList);
        }
    }

    /**
     * Finds the implemented characteristics a JSONObject item has.
     *
     * @param itemJSON  JSONObject holding item characteristic information
     * 
     * @return array of booleans determining which characteristics the given item has
     *
     */
    private static boolean[] findCharacteristics(JSONObject itemJSON) {
        boolean[] characteristics = {false, false, false, false};
        int i;
        
        for (i = 0; i < CHARACTERISTICS.length; i++) {
            if (itemJSON.get(CHARACTERISTICS[i]) != null) {
                characteristics[i] = (boolean) itemJSON.get(CHARACTERISTICS[i]);
            } 
        }

        return characteristics;
    }

    /**
     * Takes parsed item information and creates a new Item object of the proper class type.
     *
     * @param chars  array of booleans determining which characteristics the given item has
     * @param itemId  parsed id value of the item
     * @param itemName  parsed name of the item
     * @param itemDesc  parsed description of the item
     * 
     * @return the new item that has been created
     *
     */
    private static Item makeItem(boolean[] chars, int itemId, String itemName, String itemDesc) throws Exception{
        if (makeSpecializedItem(chars, itemId, itemName, itemDesc) != null){
            return makeSpecializedItem(chars, itemId, itemName, itemDesc);
        }
        if (makeBaseItem(chars, itemId, itemName, itemDesc) != null) {
            return makeBaseItem(chars, itemId, itemName, itemDesc);
        }
        return new Item(itemId, itemName, itemDesc);
    }

    /**
     * Takes parsed item information and creates a new Item object of the 
     * proper base class type; i. e. Food, Clothing, Weapon, or Spell.
     *
     * @param chars  array of booleans determining which characteristics the given item has
     * @param itemId  parsed id value of the item
     * @param itemName  parsed name of the item
     * @param itemDesc  parsed description of the item
     * 
     * @return the new item that has been created
     *
     */
    private static Item makeBaseItem(boolean[] chars, int itemId, String itemName, String itemDesc) throws Exception{
        if (chars[0]) {
            return new Food(itemId, itemName, itemDesc);
        } else if (chars[2+1]) {          // used 2 + 1 to avoid checkstyle error
            return new Clothing(itemId, itemName, itemDesc);
        } else if (chars[2]) {
            return new Weapon(itemId, itemName, itemDesc);
        } else if (chars[1]) {
            return new Spell(itemId, itemName, itemDesc);
        }
        return null;
    }

    /**
     * Takes parsed item information and creates a new Item object of the 
     * proper specialized class type; i. e. SmallFood or BrandedClothing.
     *
     * @param chars  array of booleans determining which characteristics the given item has
     * @param itemId  parsed id value of the item
     * @param iName  parsed name of the item
     * @param iDesc  parsed description of the item
     * 
     * @return the new item that has been created
     *
     */
    private static Item makeSpecializedItem(boolean[] chars, int itemId, String iName, String iDesc) throws Exception{
        if (chars[0] && chars[2]) {
            return new SmallFood(itemId, iName, iDesc);
        } else if (chars[2+1] && chars[1]) {        // used 2 + 1 to avoid checkstyle error
            return new BrandedClothing(itemId, iName, iDesc);
        } 
        return null;
    }

    /**
     * Adds given item object to the adventure being loaded.
     *
     * @param itemOBJ  Item object to be added
     * @param itemList  Arraylist keeping track of items that have been loaded
     *
     */
    private static void addAdventureItem(Item itemOBJ, ArrayList<Item> itemList) throws Exception{
        itemList.add(itemOBJ);
        Game.getAdventure().addItem(itemOBJ);
    }


    /* OTHER: */

    /** 
     * Prints Item object in desired format for displaying player's inventory.
     * 
     * @return the String to be printed
     *
     */
    public String inventoryItemPrint(){
        String string = " - " + getName();
        if (this instanceof Clothing) {
            if (((Clothing)this).getWearing()) {
                string = string + " (wearing)";
            }
        }
        return string;
    }  

    /** 
     * Prints Item object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Item Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
