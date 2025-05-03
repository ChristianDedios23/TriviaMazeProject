package Model;

public enum Direction {
    UP("UP"),
    DOWN("DOWN"),
    LEFT("LEFT"),
    RIGHT("RIGHT");

    private final String description;

    Direction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public Direction getOpposite(){
        switch (description) {
            case "UP":
                return Direction.DOWN;
            case "DOWN":
                return Direction.UP;
            case "LEFT":
                return Direction.RIGHT;
            case "RIGHT":
                return Direction.LEFT;  
            default:
                return null;
        }
    }
    public static Direction[] getAllDirections(){
        return new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    }
}
