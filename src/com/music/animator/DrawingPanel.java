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

    public DrawingPanel(AnimationBuilder animationBuilder){
        timer = new Timer(10,this);
        this.animationBuilder = animationBuilder;
    }

    public void paint(Graphics g){
        super.paint(g);
        if(active)
            g.drawImage(animationBuilder.getCurrentFrame((int)time),0,0,475,375,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(active)
            time+=0.1f;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
