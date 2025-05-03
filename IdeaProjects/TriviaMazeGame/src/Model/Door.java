package Model;

/**
 * A Door object to see if a direction in a room is still available 
 * A door is locked if the question with it is answered wrong
 */
public class Door {
    private boolean myLock;

    
    /**
     * Constructs a Door object
     */
    public Door(){
        myLock = false;
    }
    /**
     * @return true if the door is locked, false if else
     */
    public boolean isLocked(){
        return !myLock;
    }

    /**
     * Locks a door if the given question was answered wrong
     */
    public void lockDoor(){
        myLock = true;
    }

    
    
  
   
}
