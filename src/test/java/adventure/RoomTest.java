package adventure;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

public class RoomTest{
    private Room testRoom;
    private Room testRoom1;
    private Room testRoom2;
    private Room testRoom3;
    private Room testRoom4;
    private Room testRoom5;
    private Room testRoom6;
    private Room testRoom7;
    private Room testRoom8;
    private Room testRoom9;

@Before
public void setup(){
    testRoom = new Room();
    testRoom1 = new Room();
    testRoom2 = new Room();
    testRoom3 = new Room();
    testRoom4 = new Room();
    testRoom5 = new Room();
    testRoom6 = new Room();
    testRoom7 = new Room();
    testRoom8 = new Room();
    testRoom9 = new Room();
}

/* 
 * ASSUPTIONS:
 * Tests are run assuming that "N", "S", "E", and "W" 
 * will always be valid direction parameters when  
 * used in setConnectedRoom() and getConnectedRoom().
 */

@Test
public void testSetNameWithValidInput(){
    System.out.println("Testing setName with valid name");
    String roomName = "one";
    testRoom.setRoomName(roomName);
    assertTrue(testRoom.getName().equals(roomName));

}
@Test
public void testGetConnectedRoomWithNullRoomInput(){
    System.out.println("Testing getConnectedRoom with null Room object");
    Room connectedTestRoom = null;
    testRoom1.setConnectedRoom("N", connectedTestRoom);
    assertTrue(testRoom1.getConnectedRoom("N") == null);

}

@Test
public void testGetConnectedRoomWithNInput(){
    System.out.println("Testing getConnectedRoom with direction input 'n'");
    Room connectedTestRoom = new Room();
    testRoom2.setConnectedRoom("N", connectedTestRoom);
    assertTrue(testRoom2.getConnectedRoom("n").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithNorthInput(){
    System.out.println("Testing getConnectedRoom with direction input 'north'");
    Room connectedTestRoom = new Room();
    testRoom3.setConnectedRoom("N", connectedTestRoom);
    assertTrue(testRoom3.getConnectedRoom("north").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithSInput(){
    System.out.println("Testing getConnectedRoom with direction input 's'");
    Room connectedTestRoom = new Room();
    testRoom4.setConnectedRoom("S", connectedTestRoom);
    assertTrue(testRoom4.getConnectedRoom("s").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithSouthInput(){
    System.out.println("Testing getConnectedRoom with direction input 'south'");
    Room connectedTestRoom = new Room();
    testRoom5.setConnectedRoom("S", connectedTestRoom);
    assertTrue(testRoom5.getConnectedRoom("south").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithEInput(){
    System.out.println("Testing getConnectedRoom with direction input 'e'");
    Room connectedTestRoom = new Room();
    testRoom6.setConnectedRoom("E", connectedTestRoom);
    assertTrue(testRoom6.getConnectedRoom("e").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithEastInput(){
    System.out.println("Testing getConnectedRoom with direction input 'east'");
    Room connectedTestRoom = new Room();
    testRoom7.setConnectedRoom("E", connectedTestRoom);
    assertTrue(testRoom7.getConnectedRoom("east").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithWInput(){
    System.out.println("Testing getConnectedRoom with direction input 'w'");
    Room connectedTestRoom = new Room();
    testRoom8.setConnectedRoom("W", connectedTestRoom);
    assertTrue(testRoom8.getConnectedRoom("w").equals(connectedTestRoom));

}

@Test
public void testGetConnectedRoomWithWestInput(){
    System.out.println("Testing getConnectedRoom with direction input 'west'");
    Room connectedTestRoom = new Room();
    testRoom9.setConnectedRoom("W", connectedTestRoom);
    assertTrue(testRoom9.getConnectedRoom("west").equals(connectedTestRoom));

}

}