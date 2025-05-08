package Model;
/**
 * Factory class for Maze
 */
public class MazeFactory {
    /** Private constructor to prevent initialization */
    private MazeFactory(){};
    /**
     * @return a 5x5 Maze object
     */
    public static Maze create5x5Maze(){
        return new Maze(5);
    }
    /**
     * @return a 6x6 Maze object
     */
    public static Maze create6x6Maze(){
        return new Maze(6);
    }
    /**
     * @return a 7x7 Maze object
     */
    public static Maze create7x7Maze(){
        return new Maze(7);
    }
}
