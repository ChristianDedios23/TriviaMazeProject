package test;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void testInvalidMazeSmall(){
        assertThrows(IllegalArgumentException.class, () -> {new Maze(4);});
    }
    @Test
    void testInvalidMazeLarge(){
        assertThrows(IllegalArgumentException.class, () -> {new Maze(8);});
    }
    @Test
    void testValidMaze(){
        assertDoesNotThrow(()->{new Maze(6);});
    }
    @Test
    void testInitialMazeState(){
        Maze maze = MazeFactory.create5x5Maze();
        assertEquals(0, maze.getMyCurrentRoom());
        assertTrue(maze.checkAtExit(24));
    }
    @Test
    void testGetRoom(){
        Maze maze = MazeFactory.create5x5Maze();
        for(int i = 0; i < 25; i++){
            if (maze.getRoom(i) == null) {
                fail("Room at index " + i + " should not be null");
            }
        }
    }
    @Test
    void testGetInvalidRoomNegative(){
        Maze maze = MazeFactory.create5x5Maze();
        assertThrows(IllegalArgumentException.class, ()->{maze.getRoom(-1);});
    }
    @Test
    void testGetInvalidRoom(){
        Maze maze = MazeFactory.create5x5Maze();
        assertThrows(IllegalArgumentException.class, ()->{maze.getRoom(25);});
    }
    @Test
    void testGetDoor(){
        Maze maze = MazeFactory.create5x5Maze();
        for(Direction direction : Direction.values()){
            assertDoesNotThrow(()->{maze.getDoor(10, direction);});
        }
    }
    @Test
    void testCanMoveUpInvalidRoom(){
        Maze maze = MazeFactory.create7x7Maze();
        assertThrows(IllegalArgumentException.class, ()->{maze.canMove(-1, Direction.UP);});
        assertThrows(IllegalArgumentException.class, ()->{maze.canMove(88, Direction.UP);});
    }
    @Test
    void testCanMoveUpValid(){
        Maze maze = MazeFactory.create7x7Maze();
        assertEquals(1,maze.canMove(8, Direction.UP) );
    }
    @Test
    void testCanMoveUpInvalid(){
        Maze maze = MazeFactory.create7x7Maze();
        assertTrue(maze.canMove(0, Direction.UP) < 0);
    }
    @Test
    void testCanMoveDownValid(){
        Maze maze = MazeFactory.create7x7Maze();
        assertEquals(7,maze.canMove(0, Direction.DOWN) );
    }
    @Test
    void testCanMoveDownInvalid(){
        Maze maze = MazeFactory.create7x7Maze();
        assertTrue(maze.canMove(42, Direction.DOWN) < 0);
    }
    @Test
    void testCanMoveLeftValid(){
        Maze maze = MazeFactory.create7x7Maze();
        assertEquals(0,maze.canMove(1, Direction.LEFT));
    }
    @Test
    void testCanMoveLeftInvalid(){
        Maze maze = MazeFactory.create7x7Maze();
        assertEquals(-1,maze.canMove(7, Direction.LEFT));

    }
    @Test
    void testCanMoveRightValid(){
        Maze maze = MazeFactory.create6x6Maze();
        assertEquals(1,maze.canMove(0, Direction.RIGHT));
    }
    @Test
    void testCanMoveRightInvalid(){
        Maze maze = MazeFactory.create6x6Maze();
        assertEquals(-1,maze.canMove(35, Direction.RIGHT));

    }
    @Test
    void testAttemptMoveQuestion(){
        Maze maze = MazeFactory.create5x5Maze();
        assertEquals(DoorState.QUESTION, maze.attemptMove(Direction.RIGHT));
    }
    @Test
    void testAttemptMoveWall(){
        Maze maze = MazeFactory.create5x5Maze();
        assertEquals(DoorState.LOCKED, maze.attemptMove(Direction.UP));
    }
    @Test
    void testAttemptMoveOpen(){
        Maze maze = MazeFactory.create5x5Maze();
        Door front = maze.getDoor(0, Direction.RIGHT);
        Door back = maze.getDoor(1, Direction.LEFT);
        front.openDoor();
        back.openDoor();
        assertEquals(DoorState.OPEN, maze.attemptMove(Direction.RIGHT));
    }
    @Test
    void testMoveCorrect(){
        Maze maze = MazeFactory.create5x5Maze();
        boolean result = maze.move(Direction.RIGHT, true);
        assertTrue(result);
        assertEquals(1, maze.getMyCurrentRoom());
        Door front = maze.getDoor(0, Direction.RIGHT);
        assertEquals(DoorState.OPEN, front.getDoorState());
    }
    @Test
    void testMoveIncorrect(){
        Maze maze = MazeFactory.create5x5Maze();
        boolean result = maze.move(Direction.RIGHT, false);
        assertFalse(result);
        assertEquals(0, maze.getMyCurrentRoom());
        Door front = maze.getDoor(0, Direction.RIGHT);
        assertEquals(DoorState.LOCKED, front.getDoorState());
    }
    @Test
    void testMoveInvalidRoom(){
        Maze maze = MazeFactory.create5x5Maze();
        boolean result = maze.move(Direction.LEFT, false);
        assertFalse(result);

    }
    @Test
    void testCheckAtExitCurrentRoom(){
        Maze maze = MazeFactory.create5x5Maze();
        for(int i = 0; i< 5; i++){
            maze.move(Direction.RIGHT, true);
            maze.move(Direction.DOWN, true);
        }
        assertTrue(maze.checkAtExit(24));
    }
    @Test
    void testAvailablePathToExitValid(){
        Maze maze = MazeFactory.create5x5Maze();
        assertTrue(maze.availablePathToExit());
    }
    @Test
    void testAvailablePathToExitInvalid(){
        Maze maze = MazeFactory.create5x5Maze();
        maze.move(Direction.RIGHT, false);
        maze.move(Direction.DOWN, false);
        assertFalse(maze.availablePathToExit());
    }
    @Test
    void testAvailablePathToExitInvalidCloseToExit(){
        Maze maze = MazeFactory.create5x5Maze();
        maze.getDoor(24, Direction.UP).lockDoor();
        maze.getDoor(19, Direction.DOWN).lockDoor();
        maze.getDoor(24, Direction.LEFT).lockDoor();
        maze.getDoor(23, Direction.RIGHT).lockDoor();
        assertFalse(maze.availablePathToExit());
    }

}