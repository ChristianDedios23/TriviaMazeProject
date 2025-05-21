package Model;

import Model.Enum.Difficulty;

/**
 * Factory class for Maze
 */
public class MazeFactory {
    /** Private constructor to prevent initialization */
    private MazeFactory(){};

    /**
     * Creates a maze based on the difficulty
     * @param theDifficulty the set difficulty
     * @return a maze object with the set difficulty
     */
    public static Maze createMaze(final Difficulty theDifficulty){
        int mazeSize = switch (theDifficulty) {
            case MEDIUM -> 6;
            case HARD -> 7;
            default -> 5;
        };

        return new Maze(mazeSize, theDifficulty);
    }
}
