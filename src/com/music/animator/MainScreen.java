package com.music.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Damian Suski on 3/19/2017.
 */
public class MainScreen extends JPanel implements ActionListener{

    Timer timer = new Timer(10,this);

    private JPanel mainPanel;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JButton startAnimationButton;
    private JButton selectDancerButton;
    private JButton selectAudioButton;
    private JButton uploadAudioButton;
    private JButton humanDancer;
    private JButton animalDancer;
    private JLabel animatedMusicDancer;
    private JLabel dancer;
    private JLabel audio;

    public MainScreen(){
        init();

        top.add(animatedMusicDancer);
        right.add(audio);
        left.add(dancer);

        bottom.add(startAnimationButton);
        left.add(humanDancer);
        left.add(animalDancer);
        left.add(selectDancerButton);
        right.add(selectAudioButton);
        right.add(uploadAudioButton);

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
        mainPanel = new JPanel();
        //top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        //left = new JPanel(new BoxLayout(left, BoxLayout.Y_AXIS));
        //right = new JPanel(new BoxLayout(right, BoxLayout.Y_AXIS));
        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));


        top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        //bottom = new JPanel();

        mainPanel.setPreferredSize(new Dimension(800,600));
        top.setPreferredSize(new Dimension(800,100));
        top.setBorder(BorderFactory.createTitledBorder("Music Animator"));
        left.setPreferredSize(new Dimension(350,350));
        left.setBorder(BorderFactory.createTitledBorder("Dancer"));
        right.setPreferredSize(new Dimension(350,350));
        right.setBorder(BorderFactory.createTitledBorder("Audio"));
        bottom.setPreferredSize(new Dimension(800,100));
        bottom.setBorder(BorderFactory.createTitledBorder("Start"));

        this.add(mainPanel);
        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(left, BorderLayout.WEST);
        mainPanel.add(right, BorderLayout.EAST);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        startAnimationButton = new JButton("Start Animation");
        selectDancerButton = new JButton("Select Dancer");
        humanDancer = new JButton("     TRUMP     "); // This Button Should contain the image of the Dancer
        animalDancer = new JButton("HARAMBE");        //This button should contain the image of the Dancer
        selectAudioButton = new JButton("Select Song");
        uploadAudioButton = new JButton("Upload Audio");


        uploadAudioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                if (fileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null))
                {
                    File audioFile = fileChooser.getSelectedFile();

                    String songName = audioFile.getName();

                    System.out.println(songName);

                }

                else
                {
                    System.out.println("File Not Selected");
                }


            }
        });


        startAnimationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this.add(new DancerSelection());
                remove(mainPanel);
                add(new animationScreen());
                validate();

            }
        });
        //selectDancerButton.addActionListener(this);
        //selectAudioButton.addActionListener(this);
        //uploadAudioButton.addActionListener(this);

        animatedMusicDancer = new JLabel("Animated Music Dancer");
        dancer = new JLabel("Please select a Dancer for the animation");
        audio = new JLabel("Please select a song or upload an audio file");
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
