/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import java.util.*;
import javax.mail.*;
import elbaldi.models.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.*;

/**
 *
 * @author houss
 */
public class MailerService {

    Session session;
    String ourMail = "elbaldinotification@gmail.com";
    
    public MailerService() {
        
        String password = "eymmlmaxtvwotrzo";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        properties.put("mail.smtp.port", "587");
        String host = "localhost";
        Session newsession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ourMail, password);
            }
        });
        this.session = newsession;
    }

    public void sendCommandeMail(commande c) {
       // session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getPan().getU1().getEmail()));
            message.setSubject("Confirmation de commande");
             // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            
            String emailBody = "Bonjour " + c.getPan().getU1().getPrenom() + "!,\n\n"
                    + "Merci pour votre commande n°:"
                    + c.getId_cmd() + "\n\n"
                    + "Vous recevrez rapidement un mail lorsque celle-ci sera envoyée.\n\n"
                    + "A bientôt !\n"
                    + "L'équipe Elbaldi";
            messageBodyPart.setText(emailBody);
            MimeBodyPart attachmentPart = new MimeBodyPart();

            // Set the file path as a DataSource
            FileDataSource fileDataSource = new FileDataSource("C:\\ordrePdfn" + c.getId_cmd() + ".pdf");
            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
            attachmentPart.setFileName(fileDataSource.getName());
             MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Set the multipart message as the content of the email
            message.setContent(multipart);
            //message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
            
        }
    }

    public void sendLivraisonMail(livraison l, commande c) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getPan().getU1().getEmail()));
            message.setSubject("Votre collier est en route!");
            String emailBody = "cher(e) " + c.getPan().getU1().getPrenom() + "!,\n\n"
                    + "Nous vous remercions d'avoir fait vos achats chez nous. Votre numéro de commande :"
                    + c.getId_cmd()
                    + " a été expédié. Vous pouvez trouver ci-dessous les détails de votre commande :\n\n"
                    + "INFORMATIONS SUR LA COMMANDE\n"
                    + "Numéro de commande :" + c.getId_cmd() + " passé le " + c.getDate_cmd() + "\n"
                    + "Adresse de livraison :" + l.getAdresse_livraison() + "\n"
                    + "Valeur des produits :" + c.getTotal() + "TND \n"
                    + "Cordialement,\n"
                    + "L'équipe Elbaldi";
            message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
