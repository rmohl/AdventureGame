## Class Adventure

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| Adventure() | constructs and initializes an Adventure object | none | setRoomList(), setItemList(), setCurrentRoom() | none | 5 |
| ArrayList<Room> listAllRooms() | gets Adventure object's member variable 'roomList' | none | none | none | 3 |
| ArrayList<Item> listAllItems() | gets Adventure object's member variable 'itemList' | none | none | none | 3 |
| Room getCurrentRoom() | gets Adventure object's member variable 'currentRoom' | none | none | none | 3 |
| String getCurrentRoomDescription() | gets the current room's short description | none | getCurrentRoom() | Room: getShortDescription() | 3 |
| String getCurrentRoomDescriptionLong() | gets the current room's long description | none | getCurrentRoom() | Room: getLongDescription() | 3 |
| String getCurrentRoomName() | gets the current room's name | none | getCurrentRoom() | Room: getName() | 3 |
| ArrayList<Item> getCurrentRoomItems() | gets the current room's item list | none | getCurrentRoom() | Room: listItems() | 3 |
| void setRoomList(ArrayList<Room> list) | sets Adventure object's member variable 'roomList' | ArrayList<Room> list | none | none | 3 |
| void setItemList(ArrayList<Item> list) | sets Adventure object's member variable 'itemList' | ArrayList<Item> list | none | none | 3 |
| void setCurrentRoom(Room room) | sets Adventure object's member variable 'currentRoom' | Room room | none | none | 5 |
| Adventure initializeAdventure(JSONObject obj) | sets up an Adventure object using given JSON object | JSONObject obj, JSONObject testAdventure, JSONArray itemArray, JSONArray roomArray, ArrayList<Item> iList, ArrayList<Room> rList | none | Game: getParser(), getAdventure(); Parser: testJsonFile(); Item: initializeItems(); Room: initializeRooms() | 11 |
| void addRoom(Room room) | adds a room to an Adventure object's member variable 'roomList' | Room room | listAllRooms() | none | 3 |
| void addItem(Item item) | adds an item to an Adventure object's member variable 'itemList' | Item item | listAllItems() | none | 3 |
| String move(String direction) | moves player in given direction | String direction | getCurrentRoom(), setCurrentRoom() | Game: getPlayer(); Player: setCurrentPlayerRoom(); Room: getConnectedRoom(), shortOrLong() | 5 |
| String printCurrentRoom(String type) | returns string of description and item list of current room in desired format | String type, String string | getCurrentRoomName(), getCurrentRoomDescriptionLong(), getCurrentRoomDescription(), printCurrentRoomItems() | none | 9 |
| String printCurrentRoomItems() | returns a string of current room items in desired format | String string | getCurrentRoomItems() | Item: getName() | 11 |
| String toString() | returns a printable String for Adventure object | none | none | none | 12 |
