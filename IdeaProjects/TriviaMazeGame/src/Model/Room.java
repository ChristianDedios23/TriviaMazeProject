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
     * INDEX:0 = UP
     * INDEX:1 = DOWN
     * INDEX:2 = LEFT
     * INDEX:3 = RIGHT
     */
    private void intializeDoors(){
        for(int i = 0; i < myDoors.length ;i++){
            myDoors[i] = new Door();
        }
    }
    /**
     * @return the array of all doors, myDoors
     */
    public Door[] getDoors(){
        return myDoors;
    }
    public Door getDoor(final Direction theDirection ){
        switch (theDirection) {
           case Direction.UP:
               return myDoors[0];
           case Direction.DOWN:
               return myDoors[1];
           case Direction.LEFT:
               return myDoors[2];
           case Direction.RIGHT:
               return myDoors[3];
           default:
               throw new IllegalArgumentException("Invalid direction: "+ theDirection);
       }
   }
    
}