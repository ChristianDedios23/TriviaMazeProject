package test;

import Model.Enum.Difficulty;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Player class
 */
class PlayerTest {
    /** Test player object with easy difficulty */
    private Player playerEasy;
    /** Test player object with med difficulty */
    private Player playerMed;
    /** Test player object with hard difficulty */
    private Player playerHard;

    /**
     * Initializes players with a new player object
     */
    @BeforeEach
    void setUp() {
        playerEasy = new Player(Difficulty.EASY, new PropertyChangeSupport(this));
        playerMed = new Player(Difficulty.MEDIUM, new PropertyChangeSupport(this));
        playerHard = new Player(Difficulty.HARD, new PropertyChangeSupport(this));
    }

    /**
     * Tests state of a player with easy difficulty
     */
    @Test
    void testPlayerConstructorEasy() {
        Player playerEasy = new Player(Difficulty.EASY, null);
        assertEquals(1, playerEasy.getHints());
        assertEquals(0, playerEasy.getStreak());
    }
    /**
     * Tests state of a player with med difficulty
     */
    @Test
    void testPlayerConstructorMedium() {
        Player playerMed = new Player(Difficulty.MEDIUM,null);

        assertEquals(0, playerMed.getHints());
        assertEquals(0, playerMed.getStreak());
    }
    /**
     * Tests state of a player with hard difficulty
     */
    @Test
    void testPlayerConstructorHard() {
        Player playerHard = new Player(Difficulty.HARD, null);

        assertEquals(0, playerHard.getHints());
        assertEquals(0, playerHard.getStreak());
    }
    /**
     * Tests addStreak and the player state with a easy difficulty
     */
    @Test
    void testAddStreakEasy(){
        playerEasy.addStreak();
        playerEasy.addStreak();
        playerEasy.addStreak();
        assertEquals(3, playerEasy.getStreak());
        assertEquals(2, playerEasy.getHints());
    }
    /**
     * Tests addStreak and the player state with a med difficulty
     */
    @Test
    void testAddStreakMedium(){
        for(int i = 0; i < 5; i++){
            playerMed.addStreak();
        }
        assertEquals(5, playerMed.getStreak());
        assertEquals(1, playerMed.getHints());

    }
    /**
     * Tests addStreak and the player state with a hard difficulty
     */
    @Test
    void testAddStreakHard(){
        for(int i = 0; i < 5; i++){
            playerHard.addStreak();
        }
        assertEquals(5, playerHard.getStreak());
        assertEquals(0, playerHard.getHints());
    }

    /**
     * Tests useHint
     */
    @Test
    void testUseHint(){
        assertDoesNotThrow(() -> playerEasy.useHint());
        assertEquals(0, playerEasy.getHints());
    }

    /**
     * Tests useHint when none are available
     */
    @Test
    void testUseHintInvalid(){
        assertThrows(IllegalArgumentException.class, () -> playerMed.useHint());
    }

    /**
     * Tests resetStreak
     */
    @Test
    void testResetStreak(){
        playerEasy.resetStreak();
        assertEquals(0, playerEasy.getStreak());
    }

}