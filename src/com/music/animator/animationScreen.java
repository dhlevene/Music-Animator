package com.music.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mende on 3/26/2017.
 */
/*
    This class is meant to be used as a Panel inside a new frame that only manages the screenShot and if it would like
    to be sent to the Email Address.
    May be changed to whatever seems more appropriate, such as removing the back and menu button, and adding a
    functionality specific to this smaller frame to go back to original animation.
*/
public class animationScreen extends JPanel implements ActionListener {

    Timer timer = new Timer(10,this);

    private JPanel left;
    private JPanel right;
    private JPanel animationBox;
    private JPanel videoControl;
    private JButton TakeScreenShot;
    private JButton backButton;

    public animationScreen() {
        init();

        right.add(TakeScreenShot);
        right.add(backButton);

        /*Makes sure that we can click on the panel*/
        setFocusable(true);
        requestFocus();

        timer.start();
    }

    public void init()
    {
        //left = new JPanel(new BoxLayout(left, BoxLayout.Y_AXIS));
        //right = new JPanel(new BoxLayout(right, BoxLayout.Y_AXIS));

        left = new JPanel();
        right = new JPanel();
        videoControl = new JPanel();
        animationBox = new JPanel();

        //Borders will eventually be commented out, only here for visual display of Panel size
        left.setPreferredSize(new Dimension(500,500));
        left.setBorder(BorderFactory.createTitledBorder("Animation goes here"));
        right.setPreferredSize(new Dimension(200,500));
        right.setBorder(BorderFactory.createTitledBorder("Screenshot and back button go here"));
        videoControl.setPreferredSize(new Dimension(475, 100));
        videoControl.setBorder(BorderFactory.createTitledBorder("Play/Pause stuff goes here"));
        animationBox.setPreferredSize((new Dimension(475, 370)));
        animationBox.setBorder(BorderFactory.createTitledBorder("animation goes here"));


        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        left.add(animationBox, BorderLayout.NORTH);
        left.add(videoControl, BorderLayout.SOUTH);
        //Want VideoControl to be rught under animation

        TakeScreenShot = new JButton("take a Screen Shot");
        backButton = new JButton("Go back to Main Screen");

        TakeScreenShot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PAUSE ANIMATION

                JFrame window = new JFrame("ScreenShot");

        /*Following code is used to get screen resolution. Probably unnecessary*/
        /*Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();*/
                window.setSize(700,450);
                window.add(new screenShotPanel());

                window.setResizable(false);
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
    }

    /*Takes care of all the rendering, necessary for drawing the animations*/
    public void paint(Graphics graphics){
        super.paint(graphics);
    }

    /*Called every time the timer executes (every 10 millis)*/
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
