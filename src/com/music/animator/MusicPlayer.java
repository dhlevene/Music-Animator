package com.music.animator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Marcy on 3/26/2017.
 */
public class MusicPlayer {

    private static String _fileName;
    private static long _clipTime;
    private static Clip _clip;
    private static boolean _clipLoaded;

    public void play(String fileName) {

        if ( _clipLoaded == false) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
                _clip = AudioSystem.getClip();
                _clip.open(audioInputStream);
                _clip.start();
                _clipLoaded = true;
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        } else { //song was paused and will resume
            _clip.setMicrosecondPosition(_clipTime);
            _clip.start();
        }
    }

    public void pause() {
        _clipTime= _clip.getMicrosecondPosition();
        _clip.stop();
    }
}
