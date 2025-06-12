package Util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Utility class to play sound clips
 */
public class SoundClip {
    /** Dummy Constructor */
    private SoundClip() {}

    /**
     * Plays a sound clips
     * @param theFileName the filename of the sound clip
     */
    public static void playSound(final String theFileName){
        try {
            File soundFile = new File(theFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
