package com.music.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Damian Suski on 3/19/2017.
 */
public class MainScreen extends JPanel implements ActionListener{

    Timer timer = new Timer(10,this);
    private JButton startAnimationButton;
    private JButton selectDancerButton;
    private JButton selectAudioButton;
    private JButton uploadAudioButton;
    private JPanel screen;
    private JPanel left;
    private JPanel right;

    public MainScreen(){
        /*Makes sure that we can click on the panel*/
        left = new JPanel();
        add(left);
        left.add(startAnimationButton);
        setFocusable(true);
        requestFocus();

        timer.start();
    }

    /*Takes care of all the rendering, necessary for drawing the animations*/
    public void paint(Graphics graphics){
        super.paint(graphics);

        /*Paints a black rectangle over the whole window*/
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,800,600);
        graphics.setColor(Color.WHITE);
        graphics.drawString(" ANIMATION DANCER  ",325,50);
        graphics.drawString("Please Select Dancer ",100,200);
        graphics.drawString("Please Select or Upload Audio ",500,200);
    }

    /*Called every time the timer executes (every 10 millis)*/
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
