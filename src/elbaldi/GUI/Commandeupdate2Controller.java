/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class Commandeupdate2Controller implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private TextField etatField;
    private TextField dateField;
    @FXML
    private Button update2Btn;
    @FXML
    private DatePicker datepicker;
    private commande comm ; 

    /**/

    /**
     *
     * @param com
     */

    public void setCom(commande com) {
        comm = com;
      
        etatField.setText(comm.getEtat());
   
        datepicker.setValue(comm.getDate_cmd().toLocalDate());
     
        
       
    }
//    
//    public void setIdField(String idField) {
//        this.idField.setText(idField);
//    }
//
//    public void setEtatField(String etatField) {
//        this.etatField.setText(etatField);
//    }
//
//    public void setDateField(String dateField) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ; 
//        LocalDate date =LocalDate.parse(dateField, formatter);
//        this.datepicker.setValue(date);
//    }
//
//    public void setPanierField(String panierField) {
//        this.panierField.setText(panierField);
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeupdate.fxml", "commande update");
    }

    @FXML
    private void update2OnAction(ActionEvent event) {
        try {

            CommandeCRUD cc = new CommandeCRUD();
            commande c = new commande();
            commande c2 = new commande();
            c2.setId_cmd(comm.getId_cmd());
            int id;
            Date date_com;
            String etat;
            int id_panier;
            
            id = comm.getId_cmd();
            c.setId_cmd(id);

            etat = etatField.getText();
            c.setEtat(etat);

//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            date_com = Date.valueOf(datepicker.getValue());

            //date_com = Date.valueOf(dateField.getText());
            c.setDate_cmd(date_com);


            id_panier = comm.getPan().getId_panier();
            panier pan = new panier(id_panier);
            c.setPan(pan);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to update the order data?");
            alert.setHeaderText("Please confirm your action");
            Optional<ButtonType> result = alert.showAndWait();
            // if the user confirms the update action
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cc.modifierCommande(c2, c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // refreshTable();
            commandeGUI.clearTextFields( etatField);
            datepicker.setValue(null);
        }
    }

}
