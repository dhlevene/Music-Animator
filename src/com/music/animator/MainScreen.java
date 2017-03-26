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
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;

    public MainScreen(){
        init();
        //left = new JPanel();
        //add(left);
        //left.add(startAnimationButton);
        //add(uploadAudioButton);

        /*Makes sure that we can click on the panel*/
        setFocusable(true);
        requestFocus();

        timer.start();
    }

    public void init()
    {
        //top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        //left = new JPanel(new BoxLayout(left, BoxLayout.Y_AXIS));
        //right = new JPanel(new BoxLayout(right, BoxLayout.Y_AXIS));
        //bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

        top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        bottom = new JPanel();

        top.setPreferredSize(new Dimension(800,100));
        top.setBorder(BorderFactory.createTitledBorder("Music Animator"));
        left.setPreferredSize(new Dimension(350,350));
        left.setBorder(BorderFactory.createTitledBorder("Dancer"));
        right.setPreferredSize(new Dimension(350,350));
        right.setBorder(BorderFactory.createTitledBorder("Audio"));
        bottom.setPreferredSize(new Dimension(800,100));
        bottom.setBorder(BorderFactory.createTitledBorder("Start"));

        add(top, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        startAnimationButton = new JButton("Start Animation");
        selectDancerButton = new JButton("Select Dancer");
        selectAudioButton = new JButton("Select Audio");
        uploadAudioButton = new JButton("Upload Audio");
        bottom.add(startAnimationButton);
        left.add(selectDancerButton);
        right.add(selectAudioButton);
        right.add(uploadAudioButton);
    }

    /*Takes care of all the rendering, necessary for drawing the animations*/
    public void paint(Graphics graphics){
        super.paint(graphics);

        /*Paints a black rectangle over the whole window*/
       // graphics.setColor(Color.BLACK);
       // graphics.fillRect(0,0,800,600);
       // graphics.setColor(Color.WHITE);
       // graphics.drawString(" ANIMATION DANCER  ",325,50);
       // graphics.drawString("Please Select Dancer ",100,200);
       // graphics.drawString("Please Select or Upload Audio ",500,200);
    }

    /*Called every time the timer executes (every 10 millis)*/
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
