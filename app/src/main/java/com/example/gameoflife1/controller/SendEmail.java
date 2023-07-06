package com.example.gameoflife1.controller;

import android.os.AsyncTask;
import android.util.Log;


import com.example.gameoflife1.model.GenshinModel;
import com.example.gameoflife1.model.MlModel;
import com.example.gameoflife1.model.PubgmModel;
import com.example.gameoflife1.model.TransactionModel;
import com.example.gameoflife1.model.ValorantModel;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    private static final String EMAIL_HOST = "smtp.gmail.com";
    private static final int EMAIL_PORT = 587;
    private static final String EMAIL_USERNAME = "riygmly@gmail.com";
    private static final String EMAIL_PASSWORD = "xcjhxwfjckosxekc";


    public static void sendPasswordEmail(String recipientEmail, ValorantModel valorantModel) {
        // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", EMAIL_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a message
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Details Transaction");
            message.setText("Email: " + valorantModel.getUsername() + "\nID Game: " + valorantModel.getId()
                    + "\nGame: " + valorantModel.getGame() + "\nProduct: " + valorantModel.getProduct() + "\nPayment: " + valorantModel.getPayment() + "\nPayment: " + valorantModel.getTagline());

            // Send the message on a separate thread using AsyncTask
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Transport.send(message);
                        Log.d("EmailSender", "Email sent successfully!");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        Log.e("EmailSender", "Failed to send email.");
                    }
                    return null;
                }
            }.execute();
        } catch (MessagingException e) {
            e.printStackTrace();
            Log.e("EmailSender", "Failed to create or send email.");
        }
    }

    public static void sendPasswordEmail(String recipientEmail, GenshinModel genshinModel) {
        // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", EMAIL_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a message
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Details Transaction");
            message.setText("Email: " + genshinModel.getUsername() + "\nID Game: " + genshinModel.getId()
                    + "\nGame: " + genshinModel.getGame() + "\nProduct: " + genshinModel.getProduct() + "\nPayment: " + genshinModel.getPayment());

            // Send the message on a separate thread using AsyncTask
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Transport.send(message);
                        Log.d("EmailSender", "Email sent successfully!");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        Log.e("EmailSender", "Failed to send email.");
                    }
                    return null;
                }
            }.execute();
        } catch (MessagingException e) {
            e.printStackTrace();
            Log.e("EmailSender", "Failed to create or send email.");
        }
    }

    public static void sendPasswordEmail(String recipientEmail, PubgmModel pubgmModel) {
        // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", EMAIL_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a message
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Details Transaction");
            message.setText("Email: " + pubgmModel.getUsername() + "\nID Game: " + pubgmModel.getId()
                    + "\nGame: " + pubgmModel.getGame() + "\nProduct: " + pubgmModel.getProduct() + "\nPayment: " + pubgmModel.getPayment());

            // Send the message on a separate thread using AsyncTask
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Transport.send(message);
                        Log.d("EmailSender", "Email sent successfully!");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        Log.e("EmailSender", "Failed to send email.");
                    }
                    return null;
                }
            }.execute();
        } catch (MessagingException e) {
            e.printStackTrace();
            Log.e("EmailSender", "Failed to create or send email.");
        }
    }

    public static void sendPasswordEmail(String recipientEmail, MlModel mlModel) {
        // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", EMAIL_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a message
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Details Transaction");
            message.setText("Email: " + mlModel.getUsername() + "\nID Game: " + mlModel.getId()
                    + "\nGame: " + mlModel.getGame() + "\nProduct: " + mlModel.getProduct() + "\nPayment: " + mlModel.getPayment());

            // Send the message on a separate thread using AsyncTask
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Transport.send(message);
                        Log.d("EmailSender", "Email sent successfully!");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        Log.e("EmailSender", "Failed to send email.");
                    }
                    return null;
                }
            }.execute();
        } catch (MessagingException e) {
            e.printStackTrace();
            Log.e("EmailSender", "Failed to create or send email.");
        }
    }
}
