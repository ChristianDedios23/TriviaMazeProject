package Model;

import Model.Enum.Difficulty;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;

/**
 * Player class to hold the player state
 */
public class Player implements Serializable {
    /** UID for Serialization*/
    @Serial
    private static final long serialVersionUID = 92323L;
    /** The set Difficulty */
    private final Difficulty myDifficulty;
    /** The current streak of correct questions*/
    private int myStreak;
    /** Current held hints*/
    private int myHints;
    /** PCS from the Maze*/
    private transient PropertyChangeSupport myPcs;

    /**
     * Constructs the Player with set difficulty
     * @param theDifficulty the set difficulty
     * @param thePcs pcs from maze object
     */
    public Player(final Difficulty theDifficulty, final PropertyChangeSupport thePcs) {
       myDifficulty = theDifficulty;
       myStreak = 0;
       myHints = (theDifficulty == Difficulty.EASY ? 1 : 0);
       myPcs = thePcs;
    }

    /**
     * @return current streak
     */
    public int getStreak(){
        return myStreak;
    }

    /**
     * @return current num of hints
     */
    public int getHints(){
        return myHints;
    }

    /**
     * Adds one to current streak
     * then checks if the player is due for a hint
     */
    public void addStreak(){
        myStreak++;
        myPcs.firePropertyChange("addStreak", null, myStreak);
        canAddHint();
    }

    /**
     * Resets streak, only called when they answer a question wrong
     */
    public void resetStreak(){
        myStreak = 0;
        myPcs.firePropertyChange("resetStreak", null, myStreak);
    }

    /**
     * Uses a hint for a question
     */
    public void useHint(){
        if(myHints <= 0){
            throw new IllegalArgumentException("Can't use a non-existent hint");
        }
        myHints--;
        myPcs.firePropertyChange("useHint", null, myHints);

    }
    public void setPcs(final PropertyChangeSupport thePcs) {
        myPcs =thePcs;
    }
    /**
     * Determines if the user is due for a hint
     * depending on the difficulty
     */
    private void canAddHint(){
        switch(myDifficulty){
            case EASY:
                if(myStreak % 3 == 0){

                    myHints++;
                    myPcs.firePropertyChange("addHint", null, myHints);

                }
                break;
            case MEDIUM:
                if(myStreak % 5 == 0){

                    myHints++;
                    myPcs.firePropertyChange("addHint", null, myHints);
                }
                break;

            default:
                break;
        }
    }
}
