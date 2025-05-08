package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * A Room object to hold the state of a given room
 */
public class Room{
    /** An Map to hold the state of the doors on all sides */
    private Map<Direction, Door> myDoors;
    /**
     * Constructs a Room object will all four doors
     */
    public Room(){
        myDoors = new HashMap<>();
        intializeDoors();

    }
    /**
     * Initializes all doors
     */
    private void intializeDoors(){
        for(Direction direction: Direction.getAllDirections()){
            myDoors.put(direction, new Door());
        }
    }
    
    /**
     * Retrives the door correlating to the given direction
     * @param theDirection desired direction of door 
     * @return the door relating to the direction
     */
    public Door getDoor(final Direction theDirection){
       return myDoors.get(theDirection);
       
   }
    
}