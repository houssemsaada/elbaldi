/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import elbaldi.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutReservationBackController implements Initializable {

    @FXML
    private Button ajouterBTN;
    @FXML
    private Button annuler;
    @FXML
    private TextField nombreR;
    @FXML
    private TextField dateR;
    @FXML
    private TextField statutR;
    @FXML
    private TextField idplanTF;
    @FXML
    private TextField IDuserTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterReservation(ActionEvent event) {
        
         try {

            try {
                if (nombreR.getText().equalsIgnoreCase("") && dateR.getText().equalsIgnoreCase("") && statutR.getText().equalsIgnoreCase("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de l'ajout");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            
            int nombre_personnes = Integer.parseInt(nombreR.getText());
            Date date_reservation = Date.valueOf(dateR.getText())  ;
            String statut_reservation = statutR.getText();
            int id_bonplan= Integer.parseInt(idplanTF.getText());
            int id_user =Integer.parseInt(IDuserTF.getText());
                
                
               ReservationCrud R= new ReservationCrud();
               
            bonplan bp = new bonplan(id_bonplan);
             Utilisateur u = new Utilisateur();
             u.setid_user(id_user);
             Reservation res = new Reservation(nombre_personnes, date_reservation, statut_reservation, bp, u);
             R.ajouterReservation(res);
            
              
            
               
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause(); }
       }

        @FXML
        private void prefixe
        (MouseEvent event
        
        
        
        
        ) {
    }

    @FXML
    private void annulerReservation(ActionEvent event) throws IOException {
        nombreR.clear();
        dateR.clear();
        statutR.clear();
       idplanTF.clear();
       IDuserTF.clear();
               
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservationBack.fxml"));
        
        Parent root = loader.load();
        IDuserTF.getScene().setRoot(root);
    }

    
    }
    

