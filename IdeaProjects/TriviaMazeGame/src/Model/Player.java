package Model;

import Model.Enum.Difficulty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;


public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 92323L;
    private final Difficulty myDifficulty;
    private int myStreak;
    private int myHints;
    private final PropertyChangeSupport myPcs;
    public Player(final Difficulty theDifficulty, final PropertyChangeSupport thePcs) {
       myDifficulty = theDifficulty;
       myStreak = 0;
       myHints = (theDifficulty == Difficulty.EASY ? 1 : 0);
       myPcs = thePcs;
    }
    public int getStreak(){
        return myStreak;
    }
    public int getHints(){
        return myHints;
    }
    public void addStreak(){
        myStreak++;
        myPcs.firePropertyChange("addStreak", null, myStreak);
        canAddHint();
    }
    public void resetStreak(){
        myStreak = 0;
        myPcs.firePropertyChange("resetStreak", null, myStreak);
    }
    public void useHint(){
        if(myHints -1 < 0){
            throw new IllegalArgumentException("Can't use a non-existent hint");
        }
        myHints--;
        myPcs.firePropertyChange("useHint", null, myHints);

    }
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
