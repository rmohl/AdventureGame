## Class Room

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| Room() | constructs and initializes a Room object | none | none | none | 9 |
| Room(int idParam, String name, String shortD, String longD) | constructs and initializes a Room object with given parameters | int idParam, String name, String shortD, String longD | setId(), setRoomName(), setLongDescription(), setShortDescription(), setItemList(), setConnectedRooms(), setBeenHere() | none | 9 |
| int getId() | gets Room object's member variable 'id' | none | none | none | 3 |
| String getName() | gets Room object's member variable 'roomName' | none | none | none | 3 |
| String getLongDescription() | gets Room object's member variable 'longDescription' | none | none | none | 3 |
| String getShortDescription() | gets Room object's member variable 'shortDescription' | none | none | none | 3 |
| ArrayList<Item> listItems() | gets Room object's member variable 'itemList' | none | none | none | 3 |
| Room getConnectedRoom(String direction) | gets one of Room object's connected rooms that is located in given direction | String direction, String properDirection | none | Parser: getProperDirection() | 9 |
| boolean getBeenHere() | gets Room object's member variable 'beenHere' | none | none | none | 3 |
| void setId(int idParam) | sets Room object's member variable 'id' | int idParam | none | none | 3 |
| void setRoomName(String name) | sets Room object's member variable 'roomName' | String name | none | none | 3 |
| void setLongDescription(String longD) | sets Room object's member variable 'longDescription' | String longD | none | none | 3 |
| void setShortDescription(String shortD) | sets Room object's member variable 'shortDescription' | String shortD | none | none | 3 |
| void setItemList(ArrayList<Item> list) | sets Room object's member variable 'itemList' | ArrayList<Item> list | none | none | 3 |
| void setConnectedRoom(String direction, Room room) | sets one of Room object's connected rooms in given direction | String direction, Room room | none | Parser: getProperDirection() | 6 |
| void setConnectedRooms(HashMap<String, Room> map) | sets Room object's member variable 'connectedRooms' | HashMap<String, Room> map | none | none | 3 |
| void setBeenHere(boolean bool) | sets Room object's member variable 'beenHere' | boolean bool | none | none | 3 |
| void initializeRooms(JSONArray rArray, ArrayList<Item> iList, ArrayList<Room> rList) | initializes all rooms in a new adventure | JSONArray rArray, ArrayList<Item> iList, ArrayList<Room> rList | makeRooms(), finalizeRooms() | none | 4 |
| void makeRooms(JSONArray rArray, ArrayList<Item> iList, ArrayList<Room> rList) | creates all needed Room objects and adds them to the given Adventure object | JSONArray rArray, ArrayList<Item> iList, ArrayList<Room> rList, Object r, int roomId, String roomName, String shortD, String longD, Room roomOBJ | addRoom() | none | 11 |
| void addRoom(Room rO, JSONObject rJ, ArrayList<Item> iList, ArrayList<Room> rList) | initializes a single room and adds it to the Adventure's room list | Room rO, JSONObject rJ, ArrayList<Item> iList, ArrayList<Room> rList | setRoomLoot() | Game: getAdventure(), getPlayer; Adventure: setCurrentRoom(), addRoom(); Player: setCurrentPlayerRoom() | 9 |
| void setRoomLoot(Room roomOBJ, JSONObject roomJSON, ArrayList<Item> iList) | sets the loot for the Room object passed in | Room roomOBJ, JSONObject roomJSON, ArrayList<Item> iList, JSONArray placingItemsArray, Object i | addAdventureItem() | none | 8 |
| void addAdventureItem(Object i, ArrayList<Item> iList, Room roomOBJ) | intializes an item's containing room and updates its containing room's item list | Object i, ArrayList<Item> iList, Room roomOBJ, JSONObject itemJSON, int itemId, Item j | addItem() | Item: setContainingRoom() | 10 |
| void finalizeRooms(JSONArray rArray, ArrayList<Room> roomList) | initializes all room connections in Adventure object | JSONArray rArray, ArrayList<Room> roomList, Object r | finalizeRoom() | none | 5 |
| void finalizeRoom(Object r, ArrayList<Room> roomList) | initializes a single room's connections in an Adventure object | Object r, ArrayList<Room> roomList, JSONObject roomJSON, int currentRoomId, JSONArray roomArrayDir, Object e, JSONObject entranceJSON, int dirRoomId, String direction | setRoomConnect() | none | 11 |
| void setRoomConnect(ArrayList<Room> rList, int currRmId, int dirRmId, String dir) | initializes all connections in a Room object with given room id | ArrayList<Room> rList, int currRmId, int dirRmId, String dir, Room roomOBJ, Room r2, int roomId2 | findProperRoom(), setConnectedRoom() | none | 9 |
| Room findProperRoom(ArrayList<Room> roomList, int currentRoomId) | finds proper Room object that matches given id | ArrayList<Room> roomList, int currentRoomId, Room roomOBJ, Room r2, int roomId2 | none | none | 10 |
| void addItem(Item item) | adds an item to Room object's member variable 'itemList' | Item item | none | none | 3 |
| String shortOrLong() | determines if Room object's short or long description should be printed | none | getBeenHere() | none | 6 |
| String toString() | returns a printable String for current Room object | none | none | none | 12 |
