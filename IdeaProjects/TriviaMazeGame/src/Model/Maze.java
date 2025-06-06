package Model;

import Model.Enum.Difficulty;
import Model.Enum.Direction;
import Model.Enum.DoorState;
import Model.Enum.QuestionType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Maze object to manage the state of the game
 */



public class Maze implements PropertyChangeListenerMaze, Serializable {
    /** UID for Serialization*/
    @Serial
    private static final long serialVersionUID = 129348938L;

    /** An int representing the current room */
    private int myCurrentRoom;

    /** A map representing the maze  */
    private final Map<Integer, Room> myMaze;

    /** Default starting room */
    private final int STARTING_POSITION = 0;
    /** Player State */
    private final Player myPlayer;
    /** Current state of desired direction*/
    private Direction myDesiredDirection;
    /** The length dimension for the maze */
    private int myMazeLength;
    /** The room of the exit */
    private final int myExit;
    /** State of the current question*/
    private AbstractQuestion myCurrentQuestion;
    private Set<QuestionType> myQuestionTypes;
    /** PCS to signal to view */
    private transient PropertyChangeSupport myPcs;
    /**
     * Constructs a maze object based on the given length
     * @param theLength, the desired length of the maze
     */
    Maze(final int theLength, final Difficulty theDifficulty) {
        myMaze = initializeRooms(theLength);

        myCurrentRoom = STARTING_POSITION;
        myExit = (int) Math.pow(myMazeLength, 2) - 1;
        myPcs = new PropertyChangeSupport(this);
        myPlayer = new Player(theDifficulty, myPcs);
        myCurrentQuestion = null;
        myQuestionTypes = new HashSet<>();
        QuestionFactory.setupQuestions();
    }
    public void editMyQuestionTypeSet(QuestionType questionType) {
        if (myQuestionTypes.contains(questionType)) {
            myQuestionTypes.remove(questionType);
        } else {
            myQuestionTypes.add(questionType);
        }

        QuestionFactory.shuffleList(questionType);
    }
    /**
     * Gets a new question for the user to answer
     */
    public void getQuestion(){
        myCurrentQuestion = QuestionFactory.getQuestion(myQuestionTypes);
        myPcs.firePropertyChange(PROPERTY_NEW_QUESTION, null, myCurrentQuestion);
    }

    /**
     * @return the current active question
     */
    public AbstractQuestion getMyCurrentQuestion(){
       return myCurrentQuestion;
    }
    /**
     * Initializes myMaze with Room objects and links them
     * to a room number
     * Locks door if its a wall
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
            Room room = new Room();
            if(roomNum/myMazeLength == 0){
                Door door = room.getDoor(Direction.UP);
                door.lockDoor();
            }
            if(roomNum/myMazeLength == 0){
                Door door = room.getDoor(Direction.UP);
                door.lockDoor();
            }
            if(roomNum%myMazeLength == 0){
                Door door = room.getDoor(Direction.LEFT);
                door.lockDoor();
            }
            if(roomNum%myMazeLength == myMazeLength -1){
                Door door = room.getDoor(Direction.RIGHT);
                door.lockDoor();
            }
            if(roomNum/myMazeLength == myMazeLength -1){
                Door door = room.getDoor(Direction.DOWN);
                door.lockDoor();
            }
            maze.put(roomNum++, room);
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
     * @return my maze length
     */
    public int getMyMazeLength(){
        return myMazeLength;
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
        }
        

    /**
     * Attempts a move to another room by checking the door
     * If not open, but not locked, it will ask the user a quetion
     * @param theDirection the direction the user wants to move
     * @return the door state of the direction
     */
   
    public DoorState checkDoorState(final Direction theDirection){
        int room = canMove(myCurrentRoom, theDirection); 
        if(room >= 0){
            myDesiredDirection = theDirection;
            Door frontSide = getDoor(myCurrentRoom, theDirection);
            Door backSide = getDoor(room, theDirection.getOpposite());
            DoorState frontDoorState = frontSide.getDoorState();
            if(frontDoorState != backSide.getDoorState()){
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
     * @param theCorrectAnswer true if the user answered the question correcly, else false
     * @return true if the move was valid, false if else 
     */
    public boolean move(final boolean theCorrectAnswer){
        myCurrentQuestion = null;
        int room = canMove(myCurrentRoom, myDesiredDirection);
        if(room < 0){
            return false;
        }
        Door frontSide = getDoor(myCurrentRoom, myDesiredDirection);
        Door backSide = getDoor(room, myDesiredDirection.getOpposite());

        if(!theCorrectAnswer){
            frontSide.lockDoor();
            backSide.lockDoor();
            availablePathToExit();
            myPlayer.resetStreak();
            myPcs.firePropertyChange(PROPERTY_QUESTION_WRONG, null, myCurrentRoom);//here
            return false;
        }

        if(frontSide.getDoorState() == DoorState.QUESTION|| backSide.getDoorState() == DoorState.QUESTION){


            frontSide.openDoor();
            backSide.openDoor();

            //Change new value
            myPcs.firePropertyChange(PROPERTY_QUESTION_RIGHT, myCurrentRoom, myDesiredDirection);//here
            setCurrentRoom(room);
            myPlayer.addStreak();
            return true;
        }else if (frontSide.getDoorState() == DoorState.OPEN || backSide.getDoorState() == DoorState.OPEN){
            setCurrentRoom(room);
            myPlayer.addStreak();
            return true;
        }
        return false;
    }
    /**
     * Checks if the given room is the exit or not
     * @param theRoom the given room to check
     * @return true if its the exit, false if else
     */
    public boolean checkIfExit(final int theRoom){
        if(myCurrentRoom == myExit){
            myPcs.firePropertyChange(PROPERTY_VICTORY, null, true);
        }
        return theRoom == myExit;
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
        }
        return -1;
    }
    
    /**
     * Checks if there is a valid path to the exit
     * using BFS
     */
    private void availablePathToExit(){
     
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
                    if(nextRoom >= 0){
                        if(checkIfExit(nextRoom)){
                            return;
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
    }
    /**
     * Gets the door associated with the room and direction
     * @param theRoomNum the given room
     * @param theDirection the given direction
     * @return the door associated with the room and direction
     */
    public Door getDoor(final int theRoomNum, final Direction theDirection){
        Room room = myMaze.get(theRoomNum);
        if(room == null){
            throw new IllegalArgumentException("Invalid Room Number: " + theRoomNum);
        }
        return room.getDoor(theDirection);
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

    public Player getPlayer()
    {
        return myPlayer;
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
    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Deserialize non-transient fields
        myPcs = new PropertyChangeSupport(this); // Reinitialize transient field'
        QuestionFactory.setupQuestions();

    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Maze\n");
        builder.append("Current Position: " + myCurrentRoom + "\n");
        return builder.toString();
    }
}
