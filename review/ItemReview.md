## Class Item

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| Item() | constructs and initializes a Item object | none | none | none | 6 |
| Item(int idParam, String nameParam, String desc) | constructs and initializes a Item object with given parameters | int idParam, String nameParam, String desc | setId(), setName(), setDescription(), setContainingRoom() | none | 6 |
| int getId() | gets Item object's member variable 'id' | none | none | none | 3 |
| String getName() | gets Item object's member variable 'name' | none | none | none | 3 |
| String getLongDescription() | gets Item object's member variable 'description' | none | none | none | 3 |
| Room getContainingRoom() | gets Item object's member variable 'containingRoom' | none | none | none | 3 |
| void setId(int idParam) | sets Item object's member variable 'id' | int idParam | none | none | 3 |
| void setName(String itemName) | sets Item object's member variable 'name' | String itemName | none | none | 3 |
| void setDescription(String desc) | sets Item object's member variable 'description' | String desc | none | none | 3 |
| void setContainingRoom(Room room) | sets Item object's member variable 'containingRoom' | Room room | none | none | 3 |
| void initializeItems(JSONArray itemArray, ArrayList<Item> itemList) | initializes all items in a new adventure | JSONArray itemArray, ArrayList<Item> itemList, Object r, JSONObject itemJSON, int itemId, String itemName, String itemDesc, Item itemOBJ | Item() | Game: getAdventure(); Adventure: addItem() | 11 |
| boolean[] findCharacteristics(JSONObject itemJSON) | finds the implemented characteristics a JSONObject item has | JSONObject itemJSON, boolean[] characteristics, int i | none | none | 10 |
| Item makeItem(boolean[] chars, int itemId, String itemName, String itemDesc) | takes parsed item information and creates a new Item object of the proper class type | boolean[] chars, int itemId, String itemName, String itemDesc | makeSpecializedItem(), makeBaseItem(), Item() | none | 9 |
| Item makeBaseItem(boolean[] chars, int itemId, String itemName, String itemDesc) | finds the implemented characteristics a JSONObject item has | boolean[] chars, int itemId, String itemName, String itemDesc | none | Food: Food(); Clothing: Clothing(), Weapon: Weapon(); Spell: Spell() | 10 |
| Item makeSpecializedItem(boolean[] chars, int itemId, String iName, String iDesc) | takes parsed item information and creates a new Item object of the proper specialized class type | boolean[] chars, int itemId, String iName, String iDesc | none | SmallFood: SmallFood(); BrandedClothing: BrandedClothing() | 8 |
| void addAdventureItem(Item itemOBJ, ArrayList<Item> itemList) | adds given item object to the adventure being loaded | Item itemOBJ, ArrayList<Item> itemList | none | Game: getAdventure(); Adventure: addItem() | 4 |
| String inventoryItemPrint() | returns a printable Item object in desired format for displaying player's inventory | String string | getName() | Clothing: getWearing() | 9 |
| String toString() | returns a printable String for current Item object | none | none | none | 7 |
