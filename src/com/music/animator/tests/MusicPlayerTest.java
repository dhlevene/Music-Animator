package com.music.animator.tests;

import com.music.animator.ImageLoader;
import com.music.animator.MusicPlayer;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marcy on 4/14/2017.
 */
public class MusicPlayerTest {
    private static String _fileName = "preselected_songs/Shape_of_you.wav";
    public static void main(String args[]) {

        // image loader
        //ImageLoader loader = new ImageLoader(1);

        int counter = 0;
        MusicPlayer.play(_fileName);

        try {
            TimeUnit.SECONDS.sleep(7);
            MusicPlayer.pause();
            System.out.println("pausing...");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MusicPlayer.play(_fileName);
        try {
            System.out.println("resuming..");
            TimeUnit.SECONDS.sleep(4);
            MusicPlayer.pause();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

