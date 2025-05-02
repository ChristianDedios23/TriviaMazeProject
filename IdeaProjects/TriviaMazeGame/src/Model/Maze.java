package Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Maze {
    private int myCurrentPosition;
    private Map<Integer, Room> myMaze;
    private final int STARTING_POSITION = 0;
    private HashSet<String> myVisitedDoors;
    private int myMazeLength;
    
    public Maze(final int theLength){
        myMaze = initializeRooms(theLength);
        myVisitedDoors = new HashSet<>();
        myCurrentPosition = STARTING_POSITION;
     
    }
    
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

    public Room getRoom(final int thePosition){
        if(thePosition < 0 || thePosition > myMaze.size()){
            throw new IllegalArgumentException("Can't access a room out of bounds!, Room Number :" + thePosition);
        }
        
        return myMaze.get(thePosition);
    }
    
    public int canMoveUp(){
        int position = myCurrentPosition - myMazeLength -1;
        if(position > 0){
            return position;
        }else{
            return -1;
        }
    }
    public int canMoveDown(){
        int position = myCurrentPosition + myMazeLength -1;
        if(position < Math.pow(myMazeLength, 2)){
            return position;
        }else{
            return -1;
        }
        
    }
    public int canMoveLeft(){
        int position = myCurrentPosition  -1;
        if(position > 0){
            return position;
        }else{
            return -1;
        }
    }
    public int canMoveRight(){
        int position = myCurrentPosition + 1;
        if(position < Math.pow(myMazeLength, 2)){
            return position;
        }else{
            return -1;
        }
    }
    public boolean attemptMove(final Direction theDirection){
        int position = getNextRoomPosition(theDirection); 
        if(position > 0){
            String frontSideDoor = myCurrentPosition + ": " + theDirection;
            String otherSideDoor = position+": " + theDirection.getOpposite(); 
            if(myVisitedDoors.contains(frontSideDoor) || myVisitedDoors.contains(otherSideDoor)){
                return true;
            }else{
                //ask question 
                return false;
            }
        }
        return false;
    }
    public boolean move(final Direction theDirection, final boolean theCorrectAnswer){
        if(!theCorrectAnswer){
            return false;
        }
        int position = getNextRoomPosition(theDirection);
        if(position > 0){
            String frontSideDoor = myCurrentPosition + ": " + theDirection;
            String otherSideDoor = position+": " + theDirection.getOpposite(); 

            myVisitedDoors.add(frontSideDoor);
            myVisitedDoors.add(otherSideDoor);

            myCurrentPosition = position;
            return true;
        }
        return false;
    }
    public boolean availablePathToExit(){
        //bfs or dfs
        return true;
    }
    private int getNextRoomPosition(final Direction theDirection){
        switch (theDirection) {
            case Direction.UP:
                return canMoveUp();
            case Direction.DOWN:
                return canMoveDown();
            case Direction.LEFT:
                return canMoveLeft();
            case Direction.RIGHT:
                return canMoveRight();
            default:
                throw new IllegalArgumentException("Invalid direction: "+ theDirection);
        }
    }
}
