package com.logincashplus.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private static final String SMTP_HOST = "smtp.gmail.com"; // Use your SMTP host
    private static final String SMTP_PORT = "587";           // Use your SMTP port
    private static final String USERNAME = "your-email@gmail.com"; // Your email here
    private static final String PASSWORD = "your-email-password"; // Your email password here

    public static void sendVerificationEmail(String toEmail, String verificationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Email Verification - LoginCashPlus");

            String emailContent = "Your verification code is: " + verificationCode;
            message.setText(emailContent);

            Transport.send(message);
            System.out.println("Verification email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
