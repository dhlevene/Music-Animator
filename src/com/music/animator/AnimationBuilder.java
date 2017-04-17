package com.music.animator;

/*Created by Damian Suski*/

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class AnimationBuilder {

    private static final int SAMPLE_SECONDS = 2; /*Goes n seconds before and after current frame for median value*/
    private int[] beatArray; /*Stores the beats from the beat detector class*/
    private AnimationLoop currentAnimation;
    private ImageLoader imageLoader;

    private int stratifyAmount; /*1/3 value between minimum BPM and max*/
    private int minBPM=0;
    private int maxBPM=0;
    private int currentFrame;


    public AnimationBuilder(int[] beatArray, int character){
        this.beatArray = beatArray;
        imageLoader = new ImageLoader(character);

        /*Find maximum and minimum values*/
        for(int i=0;i<beatArray.length;i++){
            if(beatArray[i]<minBPM)
                minBPM = beatArray[i];
            if(beatArray[i]>maxBPM)
                maxBPM = beatArray[i];
        }

        stratifyAmount = (maxBPM - minBPM) / 3;
    }

    public Image getCurrentFrame(int songTime){
        if(currentAnimation==null||currentFrame==currentAnimation.getFrameCount()){
            cycleAnimationLoop(songTime);
            return currentAnimation.getFrame(0);
        }

        currentFrame++;
        return currentAnimation.getFrame(currentFrame-1);
    }

    public void cycleAnimationLoop(int songTime){
        int medianBPM;
        int start=songTime-stratifyAmount;
        int end=songTime+stratifyAmount;
        int counter;
        int[] segment;
        Random random = new Random(System.nanoTime());

        currentFrame = 0;

        if(songTime - SAMPLE_SECONDS < 0)
            start = 0;
        if(songTime + SAMPLE_SECONDS >= beatArray.length)
            end = beatArray.length-1;

        /*Find the median*/
        segment = new int[end - start];
        counter=0;

        for(int i=start;i<=end;i++){
            segment[counter] = beatArray[i];
            counter++;
        }

        segment = bubbleSort(segment);

        if(segment.length>SAMPLE_SECONDS+1)
            medianBPM = segment[SAMPLE_SECONDS+1];

        else
            medianBPM = segment[0];

        /*Add animationLoop logic*/
        if(medianBPM<stratifyAmount+minBPM){
            currentAnimation = imageLoader.get_slow().get(random.nextInt(imageLoader.get_slow().size()));
        }
        else if(medianBPM<stratifyAmount*2+minBPM){
            currentAnimation = imageLoader.get_medium().get(random.nextInt(imageLoader.get_medium().size()));
        }
        else {
            currentAnimation = imageLoader.get_fast().get(random.nextInt(imageLoader.get_fast().size()));
        }
    }

    /*Bubble sort*/
    private int[] bubbleSort(int[] oldArray){

        int temp;

        for(int i=0;i<oldArray.length;i++){
            for(int j=0;j<oldArray.length-1;j++){
                if(oldArray[j]>oldArray[j+1]){
                    temp = oldArray[j];
                    oldArray[j] = oldArray[j+1];
                    oldArray[j+1] = temp;
                }
            }
        }

        return oldArray;
    }
}
