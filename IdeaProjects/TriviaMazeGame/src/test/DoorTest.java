package test;
import Model.Door;
import Model.Enum.DoorState;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Door class
 */
class DoorTest {
    /** Test Door object*/
    private static Door myTestDoor;

    /**
     * Creates a new door object for each test
     */
    @BeforeEach
    void setUp(){
        myTestDoor = new Door();
    }

    /**
     * Tests door constructor and the state of the door
     */
    @Test
    void testDoorConstructor() {
        assertDoesNotThrow(Door::new);
        assertEquals(myTestDoor.getDoorState(), DoorState.QUESTION);
    }

    /**
     * Tests getDoorState on an openDoor
     */
    @Test
    void getDoorStateOpen() {
        myTestDoor.openDoor();
        assertEquals(myTestDoor.getDoorState(), DoorState.OPEN);
    }

    /**
     * Tests getDoorState on a new object
     */
    @Test
    void getDoorStateQuestion() {
        assertEquals(myTestDoor.getDoorState(), DoorState.QUESTION);
    }

    /**
     * Tests getDoorState on a locked door
     */
    @Test
    void getDoorStateLocked() {
        myTestDoor.lockDoor();
        assertEquals(myTestDoor.getDoorState(), DoorState.LOCKED);
    }


}