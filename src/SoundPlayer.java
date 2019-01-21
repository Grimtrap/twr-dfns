

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.sampled.*;
import java.io.File;
import javax.sound.sampled.*;

/**
 * SoundPlayer.java
 * plays sound
 * @author Eric Ke
 * 2019/1/16
 */
public class SoundPlayer {


    /**
     * plays a sound
     * @param filename the name of the file to be played
     */
    public static void playSound(String filename) {
        try {
            File audioFile = new File("resources/Sounds/" + filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            DataLine.Info infoThing = new DataLine.Info(Clip.class, audioStream.getFormat());
            Clip soundClip = (Clip) AudioSystem.getLine(infoThing);
            soundClip.addLineListener(new SoundListener());
            soundClip.open(audioStream);
            soundClip.start();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void playMusic() {
        Clip clip;
        File audioFile;
        int num = (int)(Math.random()* 3 + 1);
        try {
            if (num == 1){
                audioFile = new File("resources/Sounds/InGameMusic.wav");
            }
            else if (num == 2){
                audioFile = new File("resources/Sounds/InGameMusic2.wav");
            }
            else{
                audioFile = new File("resources/Sounds/InGameMusic3.wav");
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            DataLine.Info infoThing = new DataLine.Info(Clip.class, audioStream.getFormat());
            clip = (Clip) AudioSystem.getLine(infoThing);
            clip.addLineListener(new MusicListener());
            clip.open(audioStream);
            clip.start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class MusicListener implements LineListener {
        /**
         * closes the music and restarts it when it finishes
         * @param event the music event
         */
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
                event.getLine().close();
                playMusic();
            }
        }
    }

    static class SoundListener implements LineListener {

        /**
         * closes the sound when it is finished
         * @param event the sound
         */
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
                event.getLine().close();

            }


        }
    }
}