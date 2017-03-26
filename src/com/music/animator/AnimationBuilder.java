package com.music.animator;

/*Created by Damian Suski*/

import java.awt.*;

public class AnimationBuilder {

    private static final int SAMPLE_SECONDS = 2; /*Goes n seconds before and after current frame for median value*/
    private int[] beatArray; /*Stores the beats from the beat detector class*/
    private AnimationLoop currentAnimation;

    private int stratifyAmount; /*1/3 value between minimum BPM and max*/
    private int minBPM=0;
    private int maxBPM=0;
    private int currentFrame;


    public void AnimationBuilder(int[] beatArray){
        this.beatArray = beatArray;

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

        bubbleSort(segment);

        if(segment.length>SAMPLE_SECONDS+1)
            medianBPM = segment[SAMPLE_SECONDS+1];

        else
            medianBPM = segment[0];

        /*Add animationLoop logic*/
        if(medianBPM<stratifyAmount+minBPM){

        }
        else if(medianBPM<stratifyAmount*2+minBPM){

        }
        else {

        }
    }

    /*Bubble sort*/
    public int[] bubbleSort(int[] oldArray){

        int[] newArray = oldArray;
        int temp;

        for(int i=0;i<newArray.length;i++){
            for(int j=0;j<newArray.length-1;j++){
                if(newArray[j]>newArray[j+1]){
                    temp = newArray[j];
                    newArray[j] = newArray[j+1];
                    newArray[j+1] = temp;
                }
            }
        }

        return newArray;
    }
}
