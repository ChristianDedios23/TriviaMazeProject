package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Door object to see if a direction in a room is still available 
 * A door is locked if the question with it is answered wrong
 */
public class Door implements Serializable {
    @Serial
    private static final long serialVersionUID = 345234345L;
    private boolean myLock;
    private boolean myOpen;
    
    /**
     * Constructs a Door object
     */
    public Door(){
        myLock = false;
     
    }
    /**
     * @return the state of the door
     */
    public DoorState getDoorState(){
        if(myOpen){
            return DoorState.OPEN;
        }else if (myLock){
            return DoorState.LOCKED;
        }
        return DoorState.QUESTION;
    }

    /**
     * Locks a door if the given question was answered wrong
     */
    public void lockDoor(){
        myLock = true;
    }
    public void openDoor(){
        myOpen = true;
    }

    
    
  
   
}
