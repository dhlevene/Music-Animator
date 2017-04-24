package com.music.animator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by anthony on 4/16/2017.
 */
public class DrawingPanel extends JPanel implements ActionListener
{

    private boolean active;
    private Timer timer;
    private float time;
    private AnimationBuilder animationBuilder;
    private Image lastImage;
    private Image background;
    private static final String path = "src/com/music/animator/curtains.jpg";

    public DrawingPanel(AnimationBuilder animationBuilder)
    {
        timer = new Timer(250,this);
        this.animationBuilder = animationBuilder;
        try
        {
            background = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(background, 0, 0, 475, 375, null);
        if(active)
        {
            lastImage = animationBuilder.getCurrentFrame((int)time);
            g.drawImage(animationBuilder.getCurrentFrame((int)time),0,0,475,375,null);
            time+=0.125f;
        }
        else
        {
            g.drawImage(lastImage,0,0,475,375,null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}
