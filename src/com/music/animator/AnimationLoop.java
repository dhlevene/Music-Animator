package com.music.animator;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Immortan on 3/26/2017.
 */
public class AnimationLoop {

    private int frameCount=0;
    private ArrayList<Image> frames;

    public AnimationLoop(){
        frames = new ArrayList<>();
    }

    public void addFrame(Image image){
        frames.add(image);
        frameCount++;
    }

    public int getFrameCount(){
        return frameCount;
    }

    public Image getFrame(int frameNumber){
        return frames.get(frameNumber);
    }
}
