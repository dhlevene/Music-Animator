package com.music.animator;/* Collaborators:
* Daniel Levene
* Damian Suski
* Mauricio Mendez
* Marcy Yi
* Anthony Chand
* James Harrison*/

import javax.swing.*;
import java.awt.*;

/*Last edit by Damian Suski, 3/19/2017 Added JFrame*/
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Animator");

        /*Following code is used to get screen resolution. Probably unnecessary*/
        /*Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();*/
        window.setSize(800,600);
        window.add(new MainScreen());

        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(3);
    }
}
