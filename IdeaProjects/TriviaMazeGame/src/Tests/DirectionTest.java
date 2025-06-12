package Tests;

import Model.Enum.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Direction enum methods
 */
class DirectionTest {
    /**
     * Tests getOpposite with direction up
     */
    @Test
    void testGetOppositeUp() {
        assertEquals(Direction.DOWN, Direction.UP.getOpposite());
    }
    /**
     * Tests getOpposite with direction down
     */
    @Test
    void testGetOppositeDown() {
        assertEquals(Direction.UP, Direction.DOWN.getOpposite());
    }
    /**
     * Tests getOpposite with direction left
     */
    @Test
    void testGetOppositeLeft() {
        assertEquals(Direction.RIGHT, Direction.LEFT.getOpposite());
    }
    /**
     * Tests getOpposite with direction right
     */
    @Test
    void testGetOppositeRight() {
        assertEquals(Direction.LEFT, Direction.RIGHT.getOpposite());
    }

}