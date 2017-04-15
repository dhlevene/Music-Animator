package com.music.animator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

/**
 * Created by Marcy on 3/26/2017.
 */
public class MusicPlayer {

    private static String _fileName;
    private static long _clipTime;
    private static Clip _clip;
    private static boolean _clipLoaded;
    private static boolean _pause;

    public static void play(String fileName) {

        if ( _clipLoaded == false) {
            try {
                URL url = MusicPlayer.class.getResource(fileName);
                //System.out.println("Url.toString: " + url.toString().substring(6));
                //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(formattedPath).getAbsoluteFile());
                System.out.println("Url:" + url );
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                System.out.println("read file");
                _clip = AudioSystem.getClip();
                _clip.open(audioInputStream);
                //_clip.loop(10);
                _clip.start();
                _clipLoaded = true;


            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        } else { //song was paused and will resume
            System.out.println("else");
            _clip.setMicrosecondPosition(_clipTime);
            _clip.start();
        }
    }

    public static void pause() {
        _clipTime= _clip.getMicrosecondPosition();
        _clip.stop();
    }
}
