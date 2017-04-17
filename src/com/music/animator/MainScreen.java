package com.music.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Created by Damian Suski on 3/19/2017.
 */
public class MainScreen extends JPanel implements ActionListener
{
    Timer timer = new Timer(10,this);

    private JPanel mainPanel;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JButton startAnimationButton;
    private JButton selectAudioButton;
    private JButton uploadAudioButton;
    private JToggleButton humanDancer;
    private JToggleButton animalDancer;
    private JLabel animatedMusicDancer;
    private JLabel dancer;
    private JLabel audio;
    private File song;
    private JComboBox songsList;


    public MainScreen()
    {
        init();

        top.add(animatedMusicDancer);
        right.add(audio);
        left.add(dancer);

        bottom.add(startAnimationButton);
        left.add(humanDancer);
        left.add(animalDancer);
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

        String songListArray[] = {"preselected_songs/Shape_of_you.wav", "preselected_songs/Shape_of_you.mp3"};

        songsList = new JComboBox(songListArray);

        songsList.setBounds(50, 50, 90, 20);

        right.add(songsList);


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
        humanDancer = new JToggleButton("     TRUMP     "); // This Button Should contain the image of the Dancer
        humanDancer.setSelected(true);
        humanDancer.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                    animalDancer.setSelected(false);
            }
        });
        animalDancer = new JToggleButton("HARAMBE");        //This button should contain the image of the Dancer
        animalDancer.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                    humanDancer.setSelected(false);
            }
        });
        selectAudioButton = new JButton("Select Song");
        uploadAudioButton = new JButton("Upload Audio");


        // Once the user presses the upload audio button, they can select a song from their computer
        uploadAudioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // New file chooser to allow the user to select a song
                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Wave .wav or MIDI .mid", "wav","mid");
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setFileFilter(filter);

                // If yes/ok is selected by the user, return the file
                if (fileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null))
                {
                    // Get the selected file from the user
                    File audioFile = fileChooser.getSelectedFile();

                    // Get the name of the selected file as a string, for the audio player
                    song = audioFile;
                }

                // If the file is not selected
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
                if(song==null)
                    return;
                remove(mainPanel);
                add(new animationScreen(song,humanDancer.isSelected()?ImageLoader.TRUMP:ImageLoader.HARAMBE));
                validate();

            }
        });
        //selectAudioButton.addActionListener(this);

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
