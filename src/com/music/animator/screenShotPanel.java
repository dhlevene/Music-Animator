package com.music.animator;

import com.sun.tools.javah.Util;

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

    // Left Panel
    JPanel picturePanel;

    // Right panel
    JPanel actionPanel;

    // Send Email
    JButton emailButton;

    // Back to home screen
    JButton menuButton;
    JLabel emailLabel;

    // Field which stores user email
    JTextField emailInput;


    public screenShotPanel()
    {
        init();

        // Added the UI elements to the Right panel
        actionPanel.add(emailLabel);
        actionPanel.add(emailInput);
        actionPanel.add(emailButton);
        actionPanel.add(menuButton);

    }

    public void init()
    {

        // Initialize Panes
        picturePanel = new JPanel();
        actionPanel = new JPanel();

        // Set sizes and titles of JPanels
        picturePanel.setPreferredSize(new Dimension(400,380));
        picturePanel.setBorder(BorderFactory.createTitledBorder("ScreenShot"));
        actionPanel.setPreferredSize(new Dimension(200,380));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        // Set panel to the left of frame
        this.add(picturePanel, BorderLayout.WEST);

        // Set panel to right of frame
        this.add(actionPanel, BorderLayout.EAST);

        // Initialize Buttons
        emailButton = new JButton("Send Email");
        menuButton = new JButton("Back to Main Screen");

        emailLabel = new JLabel("Enter email here: ");
        emailInput = new JTextField("yourEmail@example.com");

        // When the user presses the send email button,
        // send an email of the screenshot from SoFloDev.gmail to the address recieved from user input
        emailButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Store the recipient address in an array
                String[] recipientArray = { emailInput.getText() };

                // String representing the body of the email to be sent
                String body = "Here's the Screenshot from your animation!!";

                // method call to send email, passing in the recipient address and body of message
                sendScreenshot(recipientArray, body);

            }
        });


        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                
            }
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // Method which will send an email from:
    // SoFloDev.gmail.com to the recipient address entered by the user
    private static void sendScreenshot(String[] recipientArray, String body)
    {
        // Frame for email sent/error popup
        JFrame emailSentFrame = new JFrame();

        // String variables for the host, port and subject of the email
        String host = "smtp.gmail.com";
        String port = "587";
        String subject = "Your Animation Screenshot";

        // Properties used to create the session
        Properties properties = System.getProperties();

        // Host name of the mail server
        properties.put("mail.smtp.host", host);

        // Username to connect to mail server, email address used to send
        properties.put("mail.smtp.user", EMAIL_ADDRESS);

        // Password for the email address used to send the email
        properties.put("mail.smtp.password", EMAIL_PASSWORD);

        // Authenticate the user
        properties.put("mail.smtp.auth", "true");

        // Port number of the mail server
        properties.put("mail.smtp.port", port);

        // Enable use of starttls commands
        properties.put("mail.smtp.starttls.enable", "true");

        // New mail session, collects the properties and the defaults used by the mail API
        Session session = Session.getDefaultInstance(properties);

        // Create empty message object
        MimeMessage mimeMessage = new MimeMessage(session);

        // Try to send the email
        try
        {

            // set the "From" header field of the email
            mimeMessage.setFrom(new InternetAddress(EMAIL_ADDRESS));

            // New array of the class internet address to store the recipient email address in the format "user@host.domain"
            InternetAddress[] recipientAddress = new InternetAddress[recipientArray.length];

            // loop through the recipient email string array
            for(int i = 0; i < recipientArray.length; i++)
            {
                // Copy over to the InternetAddress array
                recipientAddress[i] = new InternetAddress(recipientArray[i]);
            }

            // Loop through the internet address
            for(int i = 0; i < recipientAddress.length; i++)
            {
                // Add the address to the recipient type
                mimeMessage.addRecipient(Message.RecipientType.TO, recipientAddress[i]);
            }

            // Sets the body of the message
            mimeMessage.setText(body);

            // set the "Subject" Header field
            mimeMessage.setSubject(subject);

            // Get a transport which implements smtp
            Transport transport = session.getTransport("smtp");

            // Connect transport to specified sending address
            transport.connect(host, EMAIL_ADDRESS, EMAIL_PASSWORD);

            // Send the message
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            // Close connection
            transport.close();

            // Pop up indicating successful email
            JOptionPane.showMessageDialog(emailSentFrame, "Screenshot has been emailed", "Sent", JOptionPane.PLAIN_MESSAGE);

        }

        // Catch an error in sending the email
        catch (MessagingException messagingException)
        {
            // Pop indicating an error with sending the email
            JOptionPane.showMessageDialog(emailSentFrame, "Email Failed", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }






}
