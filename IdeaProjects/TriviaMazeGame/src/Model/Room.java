package Model;
/**
 * A Room object to hold the state of a given room
 */
public class Room{
    /** An Array to hold the state of the doors on all sides */
    private Door[] myDoors;
    /**
     * Constructs a Room object will all four doors
     */
    public Room(){
        myDoors = new Door[4];
        intializeDoors();

    }
    /**
     * Initializes all doors
     */
    private void intializeDoors(){
        for(int i = 0; i < myDoors.length; i++){
            myDoors[i] = new Door();
        }
    }
    /**
     * @return the array of all doors, myDoors
     */
    public Door[] getDoors(){
        return myDoors;
    }
    
}