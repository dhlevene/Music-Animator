package com.music.animator;

public class MusicPlayerTest {
    private static String _fileName = "preselected_songs/Shape_of_you.wav";
    public static void main(String args[]) {

// image loader
//ImageLoader loader = new ImageLoader(1);

        MusicPlayer mp = new MusicPlayer();

        int counter = 0;

        mp.play(_fileName);

//loop for ~5 seconds
        for(int i = 0; i < 2147483647 ; i++) {
//another loop because it's 2012 and PCs have gotten considerably faster :)
            for(int j = 0; j < 2147483647 ; j++){
//MusicPlayer.pause();
            }
        }
        System.out.println(counter + ". Hello World!");
        counter++;

    }
}