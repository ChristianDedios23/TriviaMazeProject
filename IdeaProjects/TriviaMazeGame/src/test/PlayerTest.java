package test;

import Model.Enum.Difficulty;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player playerEasy;
    private Player playerMed;
    private Player playerHard;
    @BeforeEach
    void setUp() {
        playerEasy = new Player(Difficulty.EASY);
        playerMed = new Player(Difficulty.MEDIUM);
        playerHard = new Player(Difficulty.HARD);
    }
    @Test
    void testPlayerConstructorEasy() {
        Player playerEasy = new Player(Difficulty.EASY);
        assertEquals(1, playerEasy.getHints());
        assertEquals(0, playerEasy.getStreak());
    }
    @Test
    void testPlayerConstructorMedium() {
        Player playerMed = new Player(Difficulty.MEDIUM);

        assertEquals(0, playerMed.getHints());
        assertEquals(0, playerMed.getStreak());
    }
    @Test
    void testPlayerConstructorHard() {
        Player playerHard = new Player(Difficulty.HARD);

        assertEquals(0, playerHard.getHints());
        assertEquals(0, playerHard.getStreak());
    }
    @Test
    void testAddStreakEasy(){
        playerEasy.addStreak();
        playerEasy.addStreak();
        playerEasy.addStreak();
        assertEquals(3, playerEasy.getStreak());
        assertEquals(2, playerEasy.getHints());
    }
    @Test
    void testAddStreakMedium(){
        for(int i = 0; i < 5; i++){
            playerMed.addStreak();
        }
        assertEquals(5, playerMed.getStreak());
        assertEquals(1, playerMed.getHints());

    }
    @Test
    void testAddStreakHard(){
        for(int i = 0; i < 5; i++){
            playerHard.addStreak();
        }
        assertEquals(5, playerHard.getStreak());
        assertEquals(0, playerHard.getHints());
    }
    @Test
    void testUseHint(){
        assertDoesNotThrow(() -> playerEasy.useHint());
        assertEquals(0, playerEasy.getHints());
    }
    @Test
    void testUseHintInvalid(){
        assertThrows(IllegalArgumentException.class, () -> playerMed.useHint());
    }
    @Test
    void testResetStreak(){
        playerEasy.resetStreak();
        assertEquals(0, playerEasy.getStreak());
    }

}