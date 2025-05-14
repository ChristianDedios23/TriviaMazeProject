package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A Room object to hold the state of a given room
 */
public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 465234523L;
    /** An Map to hold the state of the doors on all sides */
    private final Map<Direction, Door> myDoors;
    /**
     * Constructs a Room object will all four doors
     */
    public Room(){
        myDoors = new HashMap<>();
        initializeDoors();

    }

    /**
     * Initializes all doors
     */
    private void initializeDoors(){
        for(Direction direction: Direction.values()){
            myDoors.put(direction, new Door());
        }
    }
    
    /**
     * Gets the door correlating to the given direction
     * @param theDirection desired direction of door 
     * @return the door relating to the direction
     */
    public Door getDoor(final Direction theDirection){
       return myDoors.get(theDirection);
       
   }
    
}