package com.music.animator;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Marcy on 3/26/2017.
 */
public class ImageLoader {

    // constructor going to initialize chracter Folder Trump and Folder Harambe
    // youre gonna already know the folder location 1 for each character
    // the folders are gonna have subfolder
    // make an animation loop object -- in that animation loop obj gonna store
    // how many frames in that animation  and how many frames in the image

    // in loader have 3 seperate lists gonna contain all the loops - slow medium and fast
    //load all those into memory, getSlow medium and return an Animation list object
    // the actual return type is gonna be animation loop

    // Trump = 0, Harambe = 1
    public static final int TRUMP = 0;
    public static final int HARAMBE = 0;


    private int _character;
    private ArrayList<String> _slow;
    private ArrayList<String> _medium;
    private ArrayList<String> _fast;

    public ImageLoader(int characterSelected) {
        _character = characterSelected;

        if (_character == TRUMP) {

        } else if (_character == HARAMBE){

        } else {
            System.out.println("Invalid character selection.");
        }

    }
}
