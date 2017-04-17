package com.music.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Immortan on 4/16/2017.
 */
public class DrawingPanel extends JPanel implements ActionListener{

    private boolean active;
    private Timer timer;
    private float time;
    private AnimationBuilder animationBuilder;
    private Image lastImage;

    public DrawingPanel(AnimationBuilder animationBuilder){
        timer = new Timer(250,this);
        this.animationBuilder = animationBuilder;
    }

    public void paint(Graphics g){
        super.paint(g);
        if(active){
            lastImage = animationBuilder.getCurrentFrame((int)time);
            g.drawImage(animationBuilder.getCurrentFrame((int)time),0,0,475,375,null);
            time+=0.125f;
        }
        else{
            g.drawImage(lastImage,0,0,475,375,null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
