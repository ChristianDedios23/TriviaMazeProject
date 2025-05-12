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
        return switch (this) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }


    
}
