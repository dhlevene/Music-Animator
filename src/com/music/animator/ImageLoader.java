package com.music.animator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
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
    public static final int HARAMBE = 1;

    public static final String[] SPEEDS = {"Slow/"};
    //public static final String[] SPEEDS = {"Slow/", "Medium/", "Fast/"};

    private int _character;

    private ArrayList<AnimationLoop> _slow;
    private ArrayList<AnimationLoop> _medium;
    private ArrayList<AnimationLoop> _fast;
    private static String _fileName = "Animations/";

    public ImageLoader(int characterSelected) {
        _character = characterSelected;
        _slow = new ArrayList<AnimationLoop>();
        _medium = new ArrayList<AnimationLoop>();
        _fast = new ArrayList<AnimationLoop>();

        if (_character == TRUMP) {
            _fileName =_fileName + "Trump";

        } else if (_character == HARAMBE){
            _fileName =_fileName + "Harambe/";
            traverseFile(_fileName);
        } else {
            System.out.println("Invalid character selection.");
        }

    }

    public void traverseFile(String path) {
        String directory = path;

        for (int i=0; i < SPEEDS.length; i++) {
            ArrayList<AnimationLoop>  list;

            if (i == 0)
               list = _slow;
            else if (i == 1)
                list = _medium;
            else if (i == 2)
                list = _fast;

            path = path + SPEEDS[i];
            System.out.println("speed path: " + path);
            File file = new File(path);
            String filePath = file.getAbsolutePath();
            file = new File(filePath);
            System.out.println("filepath:" + filePath);

            if (file != null) {
                System.out.println("speed file " + file);
                if (file.isDirectory()) {
                    System.out.println("file is directory ");

                }
            } else {
                System.out.println("null");

            }


            String filePathINVALID = "C:\\Users\\Marcy\\IdeaProjects\\Music-Animator\\Animations\\Harambe\\Slow";
            System.out.print("tester file");
            File tester = new File("C:\\Users\\Marcy\\IdeaProjects\\Music-Animator\\src\\com\\music\\animator\\Animations\\Harambe\\Slow");
            String[] testerList = tester.list();

            if (tester.isDirectory()) {
                System.out.println("is directory :" + file.getAbsolutePath());
                if (testerList != null) {
                    System.out.println("tester list not null");
                }
            } else if (tester.isFile()) {
                System.out.println("is file");
            } else {
                System.out.println("is not directory or file!");

            }

            // gets all files & subdirectories in here
            // but we'll only have subdirectories
            String[] animationLoopNames = file.list();
            if (animationLoopNames == null) {
                System.out.println("animation loop file names list = null ");

            }
            /*
            for (String name: animationLoopNames) {
                // create new animation object for each file
                System.out.println( "Subdirectory: " + name);
                AnimationLoop animationLoop = new AnimationLoop();

                // loop into image files
                path = path + name + "/";
                System.out.println("Animation Folder: " + name);
                file = new File(path);
                String[] imageNames = file.list();
                for (String imageName: imageNames) {
                    path = path + imageName;
                    URL url = MusicPlayer.class.getResource(path);
                    System.out.println("Url:" + url );

                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new File(url.getPath()));
                        animationLoop.addFrame(img);
                    } catch (IOException e) {
                    }
                }
            } */
        }

    }
}
