package Tests;

import Model.Enum.Direction;
import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Room class
 */
class RoomTest {
    /** Test Room object */
    private Room myTestRoom;

    /**
     * Initializes a new room object for each test
     */
    @BeforeEach
    void setUp() {
        myTestRoom = new Room();
    }

    /**
     * Tests Room constructor
     */
    @Test
    void testRoomConstructor() {
        assertDoesNotThrow(Room::new);
    }

    /**
     * Tests that a door exists for each direction
     */
    @Test
    void testGetDoor(){
        for(Direction direction : Direction.values()){
            assertNotNull(myTestRoom.getDoor(direction));
        }
    }

}