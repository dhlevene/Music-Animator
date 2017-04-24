package com.music.animator;

import java.awt.*;
import java.util.ArrayList;


public class AnimationLoop
{

    private int _frameCount=0;
    private ArrayList<Image> frames;

    public AnimationLoop()
    {
        frames = new ArrayList<>();
    }

    public void addFrame(Image image)
    {
        frames.add(image);
        _frameCount++;
    }

    public int getFrameCount()
    {
        return _frameCount;
    }

    public Image getFrame(int frameNumber)
    {
        return frames.get(frameNumber);
    }
}
