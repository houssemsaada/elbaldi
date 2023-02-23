/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;

import elbaldi.models.promotion;
import elbaldi.models.question;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuestionCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */

public class AjouterPromotionController implements Initializable {

    @FXML
    private Button ajoutPromotion;
    @FXML
    private TextField fixcode_promo;
    @FXML
    private TextField fxtaux;
    @FXML
    private TextField fxdatedebut;
    @FXML
    private TextField fxdatefin;
    @FXML
    private Button backfix;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_Promotion(ActionEvent event) {
        
    String code_promo = fixcode_promo.getText();
    float taux = Float.parseFloat(fxtaux.getText());
   SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date d;
        Date date_debut = null, date_fin = null;
        try {
            d = format.parse(fxdatedebut.getText());
            date_debut = new Date(d.getTime());
            d = format.parse(fxdatefin.getText());
            date_fin = new Date(d.getTime());
        } catch (ParseException ex) {
            System.out.println("le format de la date est incorrecte!! dd/MM/YYYY");
        }
        
        if (date_debut.after(date_fin)) {
    // La date de début est après la date de fin, afficher une alerte
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de date");
    alert.setHeaderText(null);
    alert.setContentText("La date de début doit être avant la date de fin.");

    alert.showAndWait();
    return;
}

    
   
    promotion p = new promotion(code_promo,taux,date_debut,date_fin);
    PromotionCRUD qc = new PromotionCRUD();
    qc.ajouterpromotion(p);
    
   // Créer une alerte
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Promotion ajoutée");
    alert.setHeaderText(null);
    alert.setContentText("La promotion a été ajoutée avec succès.");

    // Afficher l'alerte
    alert.showAndWait();
     
    
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backfix.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
    
    
     
}
