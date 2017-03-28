package com.music.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mende on 3/27/2017.
 */
public class screenShotPanel extends JPanel implements ActionListener {

    JPanel picturePanel;//left
    JPanel actionPanel;//right
    JButton emailButton;
    JButton backButton;
    JButton menuButton;
    JLabel emailLabel;
    JTextField emailInput;

    public screenShotPanel(){
        init();
        actionPanel.add(emailLabel);
        actionPanel.add(emailInput);
        actionPanel.add(emailButton);
        actionPanel.add(backButton);
        actionPanel.add(menuButton);

    }

    public void init(){
        picturePanel = new JPanel();
        actionPanel = new JPanel();

        picturePanel.setPreferredSize(new Dimension(400,380));
        picturePanel.setBorder(BorderFactory.createTitledBorder("ScreenShot"));
        actionPanel.setPreferredSize(new Dimension(200,380));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Buttons and stuff"));

        this.add(picturePanel, BorderLayout.WEST);
        this.add(actionPanel, BorderLayout.EAST);

        emailButton = new JButton("send Email");
        backButton = new JButton("Back to Animation");
        menuButton = new JButton("Back to Main Screen");

        emailLabel = new JLabel("Enter email here: ");
        emailInput = new JTextField("yourEmail@example.com");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
