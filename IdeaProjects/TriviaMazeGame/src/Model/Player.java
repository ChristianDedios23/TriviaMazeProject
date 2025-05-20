package Model;

import Model.Enum.Difficulty;

import java.io.Serial;
import java.io.Serializable;


public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 92323L;
    private final Difficulty myDifficulty;
    private int myStreak;
    private int myHints;
    public Player(final Difficulty theDifficulty){
       myDifficulty = theDifficulty;
       myStreak = 0;
       myHints = (theDifficulty == Difficulty.EASY ? 1 : 0);
    }
    public int getStreak(){
        return myStreak;
    }
    public int getHints(){
        return myHints;
    }
    public void addStreak(){
        myStreak++;
        canAddHint();
    }
    public void resetStreak(){
        myStreak = 0;
    }
    public void useHint(){
        if(myHints -1 < 0){
            throw new IllegalArgumentException("Can't use a non-existent hint");
        }
        myHints--;
    }
    private void canAddHint(){
        switch(myDifficulty){
            case EASY:
                if(myStreak % 3 == 0){
                    myHints++;

                }
                break;
            case MEDIUM:
                if(myStreak % 5 == 0){
                    myHints++;
                }
                break;

            default:
                break;
        }
    }
}
