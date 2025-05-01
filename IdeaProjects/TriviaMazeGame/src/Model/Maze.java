package Model;

import java.util.HashMap;
import java.util.Map;

public class Maze {
    private int myCurrentPosition;
    private Map<Integer, Room> myMaze;
    private final int STARTING_POSITION = 0;
    public Maze(final int theLength){
        myMaze = initializeRooms(theLength);
        myCurrentPosition = STARTING_POSITION;
    }
    
    private HashMap<Integer, Room> initializeRooms(final int theLength){
        if(theLength < 5){
            throw new IllegalArgumentException("Maze Length too small: "+ theLength);
        }else if(theLength > 7){
            throw new IllegalArgumentException("Maze Length too large: "+ theLength);
        }
        HashMap<Integer, Room> maze = new HashMap<>();
        int roomNum = 0;
        while(roomNum < Math.pow(theLength-1, 2)){
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
    
    
}
