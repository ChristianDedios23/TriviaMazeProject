package test;

import Model.*;
import Model.Enum.Difficulty;
import Model.Enum.Direction;
import Model.Enum.DoorState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void testInitialMazeState(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertEquals(0, maze.getMyCurrentRoom());
        assertTrue(maze.checkIfExit(24));
    }
    @Test
    void testGetDoor(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        for(Direction direction : Direction.values()){
            assertDoesNotThrow(()->{maze.getDoor(10, direction);});
        }
    }
    @Test
    void testGetRoomValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertNotNull(maze.getRoom(5));
    }
    @Test
    void testGetRoomInvalidTooLarge(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, ()->{maze.getRoom(55);});
    }
    @Test
    void testGetRoomInvalidNegative(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, ()->{maze.getRoom(-1);});
    }
    @Test
    void testGetDoorInvalidRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, ()->{maze.getDoor(25, Direction.LEFT);});
    }
    @Test
    void testCanMoveUpInvalidRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertThrows(IllegalArgumentException.class, ()->{maze.canMove(-1, Direction.UP);});
        assertThrows(IllegalArgumentException.class, ()->{maze.canMove(88, Direction.UP);});
    }
    @Test
    void testCanMoveUpValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(1,maze.canMove(8, Direction.UP) );
    }
    @Test
    void testCanMoveUpInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertTrue(maze.canMove(0, Direction.UP) < 0);
    }
    @Test
    void testCanMoveDownValid(){
        Maze maze =MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(7,maze.canMove(0, Direction.DOWN) );
    }
    @Test
    void testCanMoveDownInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertTrue(maze.canMove(42, Direction.DOWN) < 0);
    }
    @Test
    void testCanMoveLeftValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(0,maze.canMove(1, Direction.LEFT));
    }
    @Test
    void testCanMoveLeftInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(-1,maze.canMove(7, Direction.LEFT));

    }
    @Test
    void testCanMoveRightValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.MEDIUM);
        assertEquals(1,maze.canMove(0, Direction.RIGHT));
    }
    @Test
    void testCanMoveRightInvalid(){
        Maze maze =MazeFactory.createMaze(Difficulty.MEDIUM);
        assertEquals(-1,maze.canMove(35, Direction.RIGHT));

    }
    @Test
    void testAttemptMoveQuestion(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertEquals(DoorState.QUESTION, maze.attemptMove(Direction.RIGHT));
    }
    @Test
    void testAttemptMoveWall(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertEquals(DoorState.LOCKED, maze.attemptMove(Direction.UP));
    }
    @Test
    void testAttemptMoveOpen(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        Door front = maze.getDoor(0, Direction.RIGHT);
        Door back = maze.getDoor(1, Direction.LEFT);
        front.openDoor();
        back.openDoor();
        assertEquals(DoorState.OPEN, maze.attemptMove(Direction.RIGHT));
    }
    @Test
    void testMoveCorrect(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        boolean result = maze.move(Direction.RIGHT, true);
        assertTrue(result);
        assertEquals(1, maze.getMyCurrentRoom());
        Door front = maze.getDoor(0, Direction.RIGHT);
        assertEquals(DoorState.OPEN, front.getDoorState());
    }
    @Test
    void testMoveIncorrect(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        boolean result = maze.move(Direction.RIGHT, false);
        assertFalse(result);
        assertEquals(0, maze.getMyCurrentRoom());
        Door front = maze.getDoor(0, Direction.RIGHT);
        assertEquals(DoorState.LOCKED, front.getDoorState());
    }
    @Test
    void testMoveInvalidRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        boolean result = maze.move(Direction.LEFT, false);
        assertFalse(result);

    }
    @Test
    void testCheckIfExitCurrentRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        for(int i = 0; i< 5; i++){
            maze.move(Direction.RIGHT, true);
            maze.move(Direction.DOWN, true);
        }
        assertTrue(maze.checkIfExit(24));
    }
    @Test
    void testAvailablePathToExitValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertTrue(maze.availablePathToExit());
    }
    @Test
    void testAvailablePathToExitInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        maze.move(Direction.RIGHT, false);
        maze.move(Direction.DOWN, false);
        assertFalse(maze.availablePathToExit());
    }
    @Test
    void testAvailablePathToExitInvalidCloseToExit(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        maze.getDoor(24, Direction.UP).lockDoor();
        maze.getDoor(19, Direction.DOWN).lockDoor();
        maze.getDoor(24, Direction.LEFT).lockDoor();
        maze.getDoor(23, Direction.RIGHT).lockDoor();
        assertFalse(maze.availablePathToExit());
    }

}