package Model;
/**
 * Enum to keeps track of the available direction a user can make
 */
public enum Direction {

    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);
    /** The index in an array the direction represents */
    private int myIndex;
    /**
     * Constructer for direction enum
     * @param theIndex the index the direction represents
     */
    private Direction(final int theIndex) {
        myIndex = theIndex;
    }
    /**
     * @return the index the direction represents
     */
    public int getIndex(){
        return myIndex;
    }
    /**
     * @return the opposite direction of the current direction
     */
    public Direction getOpposite(){
        switch (this) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;  
            default:
                throw new IllegalStateException("Unexpected error: " + this);
        }
    }
    /**
     * @return an array of all directions available
     */
    public static Direction[] getAllDirections(){
        return new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    }
    
}
