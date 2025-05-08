package Model;
/**
 * Enum to keeps track of the available direction a user can make
 */
public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;
    
   
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
