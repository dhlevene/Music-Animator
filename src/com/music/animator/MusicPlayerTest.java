package com.music.animator;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class MusicPlayerTest {
    private static String _fileName = "preselected_songs/Shape_of_you.wav";
    public static void main(String args[]) {

// image loader
//ImageLoader loader = new ImageLoader(1);

        MusicPlayer mp = new MusicPlayer();

        int counter = 0;

        mp.play(_fileName);

//try{
//    TimeUnit.SECONDS.sleep(4);
//    mp.pause();
//} catch (InterruptedException e) {
//    e.printStackTrace();
//}


        System.out.println(counter + ". Hello World!");
        counter++;

    }
}