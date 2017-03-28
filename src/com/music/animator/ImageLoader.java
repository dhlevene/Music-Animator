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

    // Trump = 0, Harambe = 1
    public static final int TRUMP = 0;
    public static final int HARAMBE = 1;
    private int _character;

    /*For testing purposes use the SPEEDS[] with only "Slow/"
    * until we get all the Speed Files for either character
    * */
    public static final String[] SPEEDS = {"Slow/"};
    //public static final String[] SPEEDS = {"Slow/", "Medium/", "Fast/"};

    private ArrayList<AnimationLoop> _slow;
    private ArrayList<AnimationLoop> _medium;
    private ArrayList<AnimationLoop> _fast;
    private static String _fileName = "src/com/music/animator/Animations/";

    public ImageLoader(int characterSelected) {
        _character = characterSelected;
        _slow = new ArrayList<AnimationLoop>();
        _medium = new ArrayList<AnimationLoop>();
        _fast = new ArrayList<AnimationLoop>();

        if (_character == TRUMP) {
            _fileName =_fileName + "Trump/";
            traverseFile(_fileName);
        } else if (_character == HARAMBE){
            _fileName =_fileName + "Harambe/";
            traverseFile(_fileName);
        } else {
            System.out.println("Invalid character selection.");
        }
        verification();
    }

    public void traverseFile(String path) {
        String directory = path;

        for (int i=0; i < SPEEDS.length; i++) {
            ArrayList<AnimationLoop>  list = null;

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

            // gets all files & subdirectories (we only have directories tho)
            String[] animationLoopNames = file.list();
            if (animationLoopNames == null) {
                System.out.println("animation loop file names list = null ");
            }

            String animationFolderPath = new String(path);
            for (String name: animationLoopNames) {
                // create new animation object for each file
                System.out.println( "Subdirectory: " + name);
                AnimationLoop animationLoop = new AnimationLoop();

                // loop into image files
                animationFolderPath = path + name + "/";
                System.out.println("Animation Folder: " + name);
                file = new File(animationFolderPath);
                System.out.println("Animation Folder path " + animationFolderPath);

                String[] imageNames = file.list();
                if (imageNames != null) {
                    String imgPath = new String(animationFolderPath);
                    for (String imageName: imageNames) {
                        imgPath = animationFolderPath + imageName;
                        BufferedImage image = null;
                        try {
                            System.out.println("Image file:" + imgPath);
                            image = ImageIO.read(new File(imgPath));
                            animationLoop.addFrame(image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // add animation loop into appropriate list
                    list.add(animationLoop);
                }
            }
        }

    }

    public void  verification(){
        if (_slow != null) {
            System.out.println("Slow list size: " + _slow.size());
        } else if (_medium != null) {
            System.out.println("medium list size: " + _medium.size());
        } else if (_fast != null) {
            System.out.println("Fast list size: " + _fast.size());
        }
    }
}
