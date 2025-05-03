package Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Maze object to manage the state of the game
 */
public class Maze {
    /** An int representing the current position */
    private int myCurrentPosition;
    /** A map representing the maze  */
    private Map<Integer, Room> myMaze;
    /** Default starting position */
    private final int STARTING_POSITION = 0;
    /** The set of all doors opened */
    private HashSet<String> myOpenDoors;
    /** The length dimension for the maze */
    private int myMazeLength;
    /** The room position of the exit */
    private int myExit;
    /**
     * Constructs a maze object based on the given length
     * @param theLength, the desired length of the maze
     */
    public Maze(final int theLength){
        myMaze = initializeRooms(theLength);
        myOpenDoors = new HashSet<>();
        myCurrentPosition = STARTING_POSITION;
        myExit = (int) Math.pow(myMazeLength -1, 2);
    }
    /**
     * Intializes myMaze with Room objects and links them
     * to a room number
     * @param theLength the desired length of the maze
     * @return a HashMap of all rooms for the maze
     */
    private HashMap<Integer, Room> initializeRooms(final int theLength){
        if(theLength < 5){
            throw new IllegalArgumentException("Maze Length too small: "+ theLength);
        }else if(theLength > 7){
            throw new IllegalArgumentException("Maze Length too large: "+ theLength);
        }
        myMazeLength = theLength;
        HashMap<Integer, Room> maze = new HashMap<>();
        int roomNum = 0;
        
        while(roomNum < Math.pow(myMazeLength-1, 2)){
            maze.put(roomNum++, new Room());
        }
       
        return maze;
    }
    /**
     * Attempts a move to another room by checking the door
     * If not open, but not locked, it will ask the user a quetion
     * @param theDirection the direction the user wants to move
     * @return true if already open, false if the door is not open yet
     */
    public boolean attemptMove(final Direction theDirection){
        int position = getNextRoomPosition(myCurrentPosition, theDirection); 
        if(position > 0){
            String frontSideDoor = myCurrentPosition + ": " + theDirection;
            String otherSideDoor = position+": " + theDirection.getOpposite(); 
            if(myOpenDoors.contains(frontSideDoor) || myOpenDoors.contains(otherSideDoor)){
                move(theDirection, true);
                return true;
            }else if(!isDoorLocked(position, theDirection)){
                //ask question 
                return false;
            }
        }
        return false;
    }
    /**
     * Moves to the next room based on the given direction
     * and if the user answered the question correctly or not
     * @param theDirection the desired direction to move 
     * @param theCorrectAnswer true if the user answered the question correcly, else false
     * @return true if the move was valid, false if else 
     */
    public boolean move(final Direction theDirection, final boolean theCorrectAnswer){
        int position = getNextRoomPosition(myCurrentPosition, theDirection);
        if(!theCorrectAnswer){
            lockDoor(myCurrentPosition, theDirection);
            lockDoor(position, theDirection.getOpposite());
            return false;
        }
        if(position > 0 && !isDoorLocked(position, theDirection)){
            String frontSideDoor = myCurrentPosition + ": " + theDirection;
            String otherSideDoor = position+": " + theDirection.getOpposite(); 

            myOpenDoors.add(frontSideDoor);
            myOpenDoors.add(otherSideDoor);

            myCurrentPosition = position;
            return true;
        }
        return false;
    }
    /**
     * Checks if the given position is the exit or not
     * @param thePosition the given position to check
     * @return true if its the exit, false if else
     */
    public boolean checkAtExit(final int thePosition){
        return thePosition == myExit;
    }
    /**
     * Gets the room from myMaze
     * @param thePosition, the desired room position
     * @return the room correlating to the room num, error if invalid
     */
    public Room getRoom(final int thePosition){
        if(thePosition < 0 || thePosition > myMaze.size()){
            throw new IllegalArgumentException("Can't access a room out of bounds!, Room Number :" + thePosition);
        }
        
        return myMaze.get(thePosition);
    }
    /**
     * Gets the room position if the user were to move up
     * depending on the given position
     * @param thePosition the current given position
     * @return the room position above the given position, -1 if not valid
     */
    public int canMoveUp(final int thePosition){
        int position = thePosition - myMazeLength;
        if(position > 0){
            return position;
        }else{
            return -1;
        }
    }
    /**
     * Gets the room position if the user were to move down
     * depending on the given position
     * @param thePosition the current given position
     * @return the room position below the given position, -1 if not valid
     */
    public int canMoveDown(final int thePosition){
        int position = thePosition + myMazeLength;
        if(position < myExit){
            return position;
        }else{
            return -1;
        }
        
    }
    /**
     * Gets the room position if the user were to move left
     * depending on the given position
     * @param thePosition the current given position
     * @return the room position left of the given position, -1 if not valid
     */
    public int canMoveLeft(final int thePosition){
        int position = thePosition  -1;
        if(position > 0 && (int)(position/myMazeLength) == (int)(thePosition/myMazeLength)){
            return position;
        }else{
            return -1;
        }
    }
    /**
     * Gets the room position if the user were to move right
     * depending on the given position
     * @param thePosition the current given position
     * @return the room position left of the given position, -1 if not valid
     */
    public int canMoveRight(final int thePosition){
        int position = thePosition + 1;
        if(position < myExit && (int)(position/myMazeLength) == (int)(thePosition/myMazeLength)){
            return position;
        }else{
            return -1;
        }
    }
    /**
     * Checks if there is a valid path to the exit 
     * using BFS
     * @return true if there is a valid path, false if else
     */
    public boolean availablePathToExit(){
        //bfs or dfs
        Set<Integer> visitedRooms = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(myCurrentPosition);
        visitedRooms.add(myCurrentPosition);
        while(!queue.isEmpty()){
            Integer position = queue.poll();

            for(Direction direction: Direction.getAllDirections()){
                if(!isDoorLocked(position, direction)){
                    int nextRoom = getNextRoomPosition(position, direction);
                    if(checkAtExit(nextRoom)){
                        return true;
                    }
                    if(!visitedRooms.contains(nextRoom)){
                        visitedRooms.add(nextRoom);
                        queue.add(nextRoom);
                    }
                }
            }
        }
        return false;
    }
    /**
     * Gets the next room position based on the given position
     * and the given direction
     * @param thePosition current posistion
     * @param theDirection desired direction
     * @returnthe next room position, -1 if invalid
     */
    private int getNextRoomPosition(final int thePosition, final Direction theDirection){
        switch (theDirection) {
            case Direction.UP:
                return canMoveUp(thePosition);
            case Direction.DOWN:
                return canMoveDown(thePosition);
            case Direction.LEFT:
                return canMoveLeft(thePosition);
            case Direction.RIGHT:
                return canMoveRight(thePosition);
            default:
                throw new IllegalArgumentException("Invalid direction: "+ theDirection);
        }
    }
    /**
     * Checks if a door is locked based on
     * the a given position and given direction
     * @param thePosition the given position
     * @param theDirection the direction of the door
     * @return true if its locked, false if not
     */
    private boolean isDoorLocked(final int thePosition, final Direction theDirection){
        Room room = getRoom(thePosition);
        Door door = room.getDoor(theDirection);
        return door.isLocked();
    }
    /**
     * Locks the door based on the given room and 
     * direction
     * @param thePosition the given position
     * @param theDirection the direction of the door
     */
    private void lockDoor(final int thePosition, final Direction theDirection){
        if(thePosition < 0 || thePosition > Math.pow(myMazeLength-1, 2)){
            throw new IllegalArgumentException("Postion is invalid: " + thePosition);
        }
        Room room = getRoom(thePosition);
        Door door = room.getDoor(theDirection);
        door.lockDoor();
    }
}
