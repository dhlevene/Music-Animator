package com.music.animator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.*;


/**
 * Created by mende on 3/27/2017.
 */
public class screenShotPanel extends JPanel implements ActionListener {

    // Gmail credentials, for SoFloDev@gmail.com with the password
    private static String EMAIL_ADDRESS = "SoFloDev";
    private static String EMAIL_PASSWORD = "Soflodev123";

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
        emailButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String[] recipientArray = { emailInput.getText() }; // list of recipient email addresses
                String body = "Here's the Screenshot from your animation!!";

                sendScreenshot(recipientArray, body);

            }
        });

        backButton = new JButton("Back to Animation");
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {


            }
        });



        menuButton = new JButton("Back to Main Screen");

        emailLabel = new JLabel("Enter email here: ");
        emailInput = new JTextField("yourEmail@example.com");
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }


    private static void sendScreenshot(String[] recipientArray, String body)
    {
        JFrame emailSentFrame = new JFrame();

        String host = "smtp.gmail.com";
        String port = "587";
        String subject = "Your Animation Screenshot";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", EMAIL_ADDRESS);
        properties.put("mail.smtp.password", EMAIL_PASSWORD);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties);

        MimeMessage mimeMessage = new MimeMessage(session);

        try
        {
            mimeMessage.setFrom(new InternetAddress(EMAIL_ADDRESS));

            InternetAddress[] recipientAddress = new InternetAddress[recipientArray.length];

            for(int i = 0; i < recipientArray.length; i++)
            {
                recipientAddress[i] = new InternetAddress(recipientArray[i]);
            }

            for(int i = 0; i < recipientAddress.length; i++)
            {
                mimeMessage.addRecipient(Message.RecipientType.TO, recipientAddress[i]);
            }

            mimeMessage.setText(body);
            mimeMessage.setSubject(subject);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, EMAIL_ADDRESS, EMAIL_PASSWORD);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(emailSentFrame, "Screenshot has been emailed", "Sent", JOptionPane.PLAIN_MESSAGE);

        }

        catch (MessagingException messagingException)
        {
            JOptionPane.showMessageDialog(emailSentFrame, "Email Failed", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }






}
