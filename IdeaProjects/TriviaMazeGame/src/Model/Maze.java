package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Maze object to manage the state of the game
 */
public class Maze implements PropertyChangeListenerMaze{

    /** An int representing the current room */
    private int myCurrentRoom;

    /** A map representing the maze  */
    private final Map<Integer, Room> myMaze;

    /** Default starting room */
    private final int STARTING_POSITION = 0;

    /** The length dimension for the maze */
    private int myMazeLength;
    /** The room of the exit */
    private final int myExit;
    /** PCS to signal to view */
    private final PropertyChangeSupport myPcs;
    /**
     * Constructs a maze object based on the given length
     * @param theLength, the desired length of the maze
     */
    public Maze(final int theLength){
        myMaze = initializeRooms(theLength);

        myCurrentRoom = STARTING_POSITION;
        myExit = (int) Math.pow(myMazeLength, 2) - 1;
        myPcs = new PropertyChangeSupport(this);
    }

    public int getMyMazeLength()
    {
        return myMazeLength;
    }

    /**
     * Initializes myMaze with Room objects and links them
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
        
        while(roomNum < Math.pow(myMazeLength, 2)){
            maze.put(roomNum++, new Room());
        }
       
        return maze;
    }

    /**
     * @return the current room position
     */
    public int getMyCurrentRoom(){
        return myCurrentRoom;
    }
    /**
     * Sets the current room to given room
     * @param theRoom the next room
     */
    private void setCurrentRoom(final int theRoom){
        if(theRoom < 0 || theRoom > myExit){
            throw new IllegalArgumentException("Can't set room out of bounds: "+ theRoom);
        }
        myPcs.firePropertyChange(PROPERTY_PLAYER_MOVE, myCurrentRoom, theRoom);
        myCurrentRoom = theRoom;
        firePCSSurroundingRooms();
        
    }
    /**
     * Fires a PCS to tell the state of all surrounding doors
     */
    private void firePCSSurroundingRooms(){
        for(Direction direction: Direction.values()){
            firePCSForDoor(direction);
        }
    }
    /**
     * Fires a PCS to tell the state of a door in a direction
     * @param theDirection the direction of the door/room
     */
    private void firePCSForDoor(final Direction theDirection){
        int room = canMove(myCurrentRoom, theDirection);
        String event = "door" + theDirection;
        if(room == -1){
            myPcs.firePropertyChange(event, null, DoorState.LOCKED);
            return;
        }
        DoorState state = getDoor(myCurrentRoom, theDirection).getDoorState();
        if(state == DoorState.LOCKED){
            myPcs.firePropertyChange(event, null, DoorState.LOCKED);
        }else if(state == DoorState.OPEN){
            myPcs.firePropertyChange(event, null, DoorState.OPEN);
        }else{
            myPcs.firePropertyChange(event, null, DoorState.QUESTION);
        }
    }
    /**
     * Attempts a move to another room by checking the door
     * If not open, but not locked, it will ask the user a quetion
     * @param theDirection the direction the user wants to move
     * @return the door state of the direction
     */
   
    public DoorState attemptMove(final Direction theDirection){
        int room = canMove(myCurrentRoom, theDirection); 
        if(room > 0){
            Door frontSide = getDoor(myCurrentRoom, theDirection);
            Door backSide = getDoor(room, theDirection.getOpposite());
            if(frontSide.getDoorState() != backSide.getDoorState()){
                throw new IllegalStateException(
                    "Doors should have the same state: Front Door: " + frontSide + ", Back Door: " + backSide);
            }
            return frontSide.getDoorState();
        }
        return DoorState.LOCKED;
    }
    /**
     * Moves to the next room based on the given direction
     * and if the user answered the question correctly or not
     * @param theDirection the desired direction to move 
     * @param theCorrectAnswer true if the user answered the question correcly, else false
     * @return true if the move was valid, false if else 
     */
    public boolean move(final Direction theDirection, final boolean theCorrectAnswer){
        int room = canMove(myCurrentRoom, theDirection);
        if(room < 0){
            return false;
        }
        Door frontSide = getDoor(myCurrentRoom, theDirection);
        Door backSide = getDoor(room, theDirection.getOpposite());

        if(!theCorrectAnswer){
            availablePathToExit();
            frontSide.lockDoor();
            backSide.lockDoor();
            return false;
        }

        if(frontSide.getDoorState() == DoorState.QUESTION|| backSide.getDoorState() == DoorState.QUESTION){
            

            frontSide.openDoor();
            backSide.openDoor();
            
            //Change new value
            myPcs.firePropertyChange(PROPERTY_QUESTION_RIGHT, theDirection, theDirection);
            setCurrentRoom(room);
            
            return true;
        }
        return false;
    }
    /**
     * Checks if the given room is the exit or not
     * @param theRoom the given room to check
     * @return true if its the exit, false if else
     */
    public boolean checkAtExit(final int theRoom){
        if(myCurrentRoom == myExit){
            myPcs.firePropertyChange(PROPERTY_VICTORY, null, true);
        }
        return theRoom == myExit;
    }
    /**
     * Gets the room from myMaze
     * @param theRoom, the desired room
     * @return the room correlating to the room num, error if invalid
     */
    public Room getRoom(final int theRoom){
        if(theRoom < 0 || theRoom > myExit){
            throw new IllegalArgumentException("Can't access a room out of bounds!, Room Number :" + theRoom);
        }
        
        return myMaze.get(theRoom);
    }
    /**
     * Checks if the given move is valid based on room 
     * @param theRoom the current given room
     * @param theDirection the desired direction
     * @return an int representing the valid next room, -1 if invalid
     */
    public int canMove(final int theRoom, final Direction theDirection){
        int room = -1;
        if(theRoom < 0 || theRoom > myExit){
            throw new IllegalArgumentException("Can't access a room out of bounds!: " + theRoom);
        }
        switch (theDirection) {
            case Direction.UP:
                room = theRoom - myMazeLength;
                if(room >= 0){
                    return room;
                }else{
                    return -1;
                }
            case Direction.DOWN:
                room = theRoom + myMazeLength;
                if(room <= myExit){
                    return room;
                }else{
                    return -1;
                }
            case Direction.LEFT:
                room = theRoom  -1;

                if(room >= 0 && (room/myMazeLength) == (theRoom/myMazeLength)){
                    return room;
                }else{
                    return -1;
                }
            case Direction.RIGHT:
                room = theRoom + 1;
                if(room <= myExit && (room/myMazeLength) == (theRoom/myMazeLength)){
                    return room;
                }else{
                    return -1;
                }
            default:
                throw new IllegalArgumentException("Invalid direction: "+ theDirection);
        }
    }
    
    /**
     * Checks if there is a valid path to the exit
     * using BFS
     */
    public boolean availablePathToExit(){
     
        Set<Integer> visitedRooms = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(myCurrentRoom);
        visitedRooms.add(myCurrentRoom);
        while(!queue.isEmpty()){
            Integer room = queue.poll();

            for(Direction direction: Direction.values()){
                Door door = getDoor(room, direction);
                if(door.getDoorState() != DoorState.LOCKED){
                    int nextRoom = canMove(room, direction);
                    if(nextRoom > 0){
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
        }
        myPcs.firePropertyChange(PROPERTY_GAMEOVER, null, true);
        return false;
    }
    /**
     * Gets the door associated with the room and direction
     * @param theRoom the given room
     * @param theDirection the given direction
     * @return the door associated with the room and direction
     */
    public Door getDoor(final int theRoom, final Direction theDirection){
        Room room = getRoom(theRoom);
        return room.getDoor(theDirection);
    }
   
   
    /** This method takes in a PropertyChangeListener object and adds it
     * to the myPcs object. Any PropertyChangeListener added will be notified
     * when myPcs fires a property change.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
}
