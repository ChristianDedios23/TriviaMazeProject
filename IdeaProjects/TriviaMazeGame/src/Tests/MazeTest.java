package Tests;

import Model.*;
import Model.Enum.Difficulty;
import Model.Enum.Direction;
import Model.Enum.DoorState;
import Model.Enum.QuestionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests maze class
 */
class MazeTest {
    /**
     * Tests the state of a maze upon initialization
     */
    @Test
    void testInitialMazeState(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertEquals(0, maze.getMyCurrentRoom());
        assertTrue(maze.checkIfExit(24));
    }

    /**
     * Tests getMazeLength method
     */
    @Test
    void testGetMazeLength(){
        assertEquals(5, MazeFactory.createMaze(Difficulty.EASY).getMyMazeLength());
        assertEquals(6, MazeFactory.createMaze(Difficulty.MEDIUM).getMyMazeLength());
        assertEquals(7, MazeFactory.createMaze(Difficulty.HARD).getMyMazeLength());
    }

    /**
     * Tests getPlayer method
     */
    @Test
    void testGetPlayer(){
        assertNotNull(MazeFactory.createMaze(Difficulty.EASY).getPlayer());
    }
    /**
     * Tests getPlayer method
     */
    @Test
    void testGetQuestion(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        maze.editMyQuestionTypeSet(QuestionType.MULTIPLE_CHOICE);
        assertDoesNotThrow(maze::getQuestion);
        assertNotNull(maze.getMyCurrentQuestion());
    }

    /**
     * Tests getRoom on a valid roomNum
     */
    @Test
    void testGetRoomValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertNotNull(maze.getRoom(5));
    }

    /**
     * Tests getRoom on a too large roomNum
     */
    @Test
    void testGetRoomInvalidTooLarge(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, ()->{maze.getRoom(55);});
    }

    /**
     * Tests getRoom on a negative roomNum
     */
    @Test
    void testGetRoomInvalidNegative(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, ()->{maze.getRoom(-1);});
    }
    /**
     * Tests getDoor method for every door in a room
     */
    @Test
    void testGetDoor(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        for(Direction direction : Direction.values()){
            assertDoesNotThrow(()->{maze.getDoor(10, direction);});
        }
    }
    /**
     * Tests getDoor on a invalid room
     */
    @Test
    void testGetDoorInvalidRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, ()->{maze.getDoor(25, Direction.LEFT);});
    }

    /**
     * Test canMove on invalidRooms
     */
    @Test
    void testCanMoveInvalidRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertThrows(IllegalArgumentException.class, ()->{maze.canMove(-1, Direction.UP);});
        assertThrows(IllegalArgumentException.class, ()->{maze.canMove(88, Direction.UP);});
    }

    /**
     * Test canMove with Direction.UP on a valid room
     */
    @Test
    void testCanMoveUpValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(1,maze.canMove(8, Direction.UP) );
    }
    /**
     * Test canMove with Direction.UP where you cant
     */
    @Test
    void testCanMoveUpInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertTrue(maze.canMove(0, Direction.UP) < 0);
    }
    /**
     * Test canMove with Direction.DOWN on a valid room
     */
    @Test
    void testCanMoveDownValid(){
        Maze maze =MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(7,maze.canMove(0, Direction.DOWN) );
    }
    /**
     * Test canMove with Direction.DOWN where you cant
     */
    @Test
    void testCanMoveDownInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertTrue(maze.canMove(42, Direction.DOWN) < 0);
    }
    /**
     * Test canMove with Direction.LEFT with a valid room
     */
    @Test
    void testCanMoveLeftValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(0,maze.canMove(1, Direction.LEFT));
    }
    /**
     * Test canMove with Direction.LEFT where you cant
     */
    @Test
    void testCanMoveLeftInvalid(){
        Maze maze = MazeFactory.createMaze(Difficulty.HARD);
        assertEquals(-1,maze.canMove(7, Direction.LEFT));

    }
    /**
     * Test canMove with Direction.RIGHT with a valid room
     */
    @Test
    void testCanMoveRightValid(){
        Maze maze = MazeFactory.createMaze(Difficulty.MEDIUM);
        assertEquals(1,maze.canMove(0, Direction.RIGHT));
    }
    /**
     * Test canMove with Direction.RIGHT where you cant
     */
    @Test
    void testCanMoveRightInvalid(){
        Maze maze =MazeFactory.createMaze(Difficulty.MEDIUM);
        assertEquals(-1,maze.canMove(35, Direction.RIGHT));

    }

    /**
     * Tests checkDoorState to a Door with a question
     */
    @Test
    void testCheckDoorStateQuestion(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertEquals(DoorState.QUESTION, maze.checkDoorState(Direction.RIGHT));
    }
    /**
     * Tests checkDoorState to a Door thats locked
     */
    @Test
    void testCheckDoorStateWall(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        assertEquals(DoorState.LOCKED, maze.checkDoorState(Direction.UP));
    }
    /**
     * Tests checkDoorState to a Door thats open
     */
    @Test
    void testCheckDoorStateOpen(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        Door front = maze.getDoor(0, Direction.RIGHT);
        Door back = maze.getDoor(1, Direction.LEFT);
        front.openDoor();
        back.openDoor();
        assertEquals(DoorState.OPEN, maze.checkDoorState(Direction.RIGHT));
    }
    /**
     * Tests move after a correct answer
     */
    @Test
    void testMoveCorrect(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        maze.checkDoorState(Direction.RIGHT);
        boolean result = maze.move( true);
        assertTrue(result);
        assertEquals(1, maze.getMyCurrentRoom());
        Door front = maze.getDoor(0, Direction.RIGHT);
        assertEquals(DoorState.OPEN, front.getDoorState());
    }
    /**
     * Tests move after an incorrect answer
     */
    @Test
    void testMoveIncorrect(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        maze.checkDoorState(Direction.RIGHT);
        boolean result = maze.move( false);
        assertFalse(result);
        assertEquals(0, maze.getMyCurrentRoom());
        Door front = maze.getDoor(0, Direction.RIGHT);
        assertEquals(DoorState.LOCKED, front.getDoorState());
    }

    /**
     * Tests checkIfExit on current room
     */
    @Test
    void testCheckIfExitCurrentRoom(){
        Maze maze = MazeFactory.createMaze(Difficulty.EASY);
        for(int i = 0; i< 5; i++){
            maze.checkDoorState(Direction.RIGHT);
            maze.move( true);
            maze.checkDoorState(Direction.DOWN);
            maze.move( true);
        }
        assertTrue(maze.checkIfExit(24));
    }





}