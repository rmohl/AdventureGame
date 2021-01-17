## Class Player

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| Player() | constructs and initializes a Player object | none | setName(), setSaveName(), setInventory(), setCurrentPlayerRoom() | none | 6 |
| String getName() | gets Player object's member variable 'name' | none | none | none | 3 |
| String getSaveGameName() | gets Player object's member variable 'saveName' | none | none | none | 3 |
| ArrayList<Item> getInventory() | gets Player object's member variable 'inventory' | none | none | none | 3 |
| Room getCurrentRoom() | gets Player object's member variable 'currentPlayerRoom' | none | none | none | 3 |
| void setName(String nameParam) | sets Player object's member variable 'name' | String nameParam | none | none | 3 |
| void setSaveName(String saveParam) | sets Player object's member variable 'saveName' | String saveParam | none | none | 3 |
| void setInventory(ArrayList<Item> items) | sets Player object's member variable 'inventory' | ArrayList<Item> items | none | none | 3 |
| void setCurrentPlayerRoom(Room room) | sets Player object's member variable 'currentPlayerRoom' | Room room | none | none | 3 |
| void addToInventory(Item item) | adds an item to the Player object's inventory | Item item | none | none | 3 |
| String displayInventory() | displays player's inventory in desired format | String string, Item i | getInventory() | Item: inventoryItemPrint() | 11 |
| String toString() | returns a printable String for current Player object | none | none | none | 12 |
