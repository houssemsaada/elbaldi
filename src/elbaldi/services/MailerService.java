/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import java.util.*;
import javax.mail.*;
import elbaldi.models.*;
import javax.mail.internet.*;

/**
 *
 * @author houss
 */
public class MailerService {

    Session session;
    String ourMail = "elbaldinotification@gmail.com";

    public MailerService() {
        String password = "wheppfbzojknzwap";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String host = "localhost";
        Session newsession = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ourMail, password);
            }
        });
        this.session = newsession;
    }

    public void sendCommandeMail(commande c) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getPan().getU1().getEmail()));
            message.setSubject("Confirmation de commande");
            String emailBody = "Bonjour " + c.getPan().getU1().getPrenom() + "!,\n\n"
                    + "Merci pour votre commande n°:"
                    + c.getId_cmd() + "\n\n"
                    + "Vous recevrez rapidement un mail lorsque celle-ci sera envoyée.\n\n"
                    + "A bientôt !\n"
                    + "L'équipe Elbaldi";
            message.setText(emailBody);

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
            message.setSubject("votre collier est en route");
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
