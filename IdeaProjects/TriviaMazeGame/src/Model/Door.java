package Model;

import Model.Enum.DoorState;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Door object to see if a direction in a room is still available 
 * A door is locked if the question with it is answered wrong
 */
public class Door implements Serializable {
    /** UID for Serialization*/
    @Serial
    private static final long serialVersionUID = 345234345L;
    /** Contains the state of the door*/
    private DoorState myDoorState;

    
    /**
     * Constructs a Door object
     */
    public Door(){
        myDoorState = DoorState.QUESTION;
     
    }
    /**
     * @return the state of the door
     */
    public DoorState getDoorState(){

        return myDoorState;
    }

    /**
     * Locks a door if the given question was answered wrong
     */
    public void lockDoor(){
        myDoorState = DoorState.LOCKED;
    }

    /**
     * Opens the door if question is answered right
     */
    public void openDoor(){
       myDoorState = DoorState.OPEN;
    }

    
    
  
   
}
