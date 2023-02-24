/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.livraisonCRUD;
import java.net.URL;
import java.sql.Date;
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
import java.time.LocalDate ;
import java.util.Locale;
/**
 * FXML Controller class
 *
 * @author houss
 */
public class Livraisonupdate2Controller implements Initializable {

    @FXML
    private Button update2Btn;
    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private TextField idField;
    @FXML
    private TextField statusField;
    private TextField dateField;
    @FXML
    private TextField commandeField;
    @FXML
    private TextField adresseField;
    @FXML
    private DatePicker datepicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setIdField(String idField) {
        this.idField.setText(idField);
    }

    public void setStatusField(String statusField) {
        this.statusField.setText(statusField);
    }

    public void setDateField(String dateField) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ; 
        LocalDate date =LocalDate.parse(dateField, formatter);
        this.datepicker.setValue(date);
    }

    public void setCommandeField(String commandeField) {
        this.commandeField.setText(commandeField);
    }

    public void setAdresseField(String adresseField) {
        this.adresseField.setText(adresseField);
    }

    @FXML
    private void update2OnAction(ActionEvent event) {
        try {
            livraisonCRUD lv = new livraisonCRUD();
            livraison l = new livraison();
            livraison l2 = new livraison();

            l2.setId_livraison(Integer.parseInt(idField.getText()));
            int id;
            Date Date_livraison;
            String status;
            int id_com;
            String adresse;

            try {
                Integer.parseInt(idField.getText());
            } catch (Exception e) {
                commandeGUI.AlertShow("Please enter a valid id, only digits are allowed", "Invalid id", Alert.AlertType.ERROR);
                return;
            }
            id = Integer.parseInt(idField.getText());
            l.setId_livraison(id);

            status = statusField.getText();
            l.setStatus_livraison(status);

//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            Date_livraison = Date.valueOf(datepicker.getValue());
            System.out.println(Date_livraison);
            l.setDate_livraison(Date_livraison);

            try {
                Integer.parseInt(commandeField.getText());

            } catch (Exception e) {
                commandeGUI.AlertShow("Please enter a valid order id, only digits are allowed", "Invalid id order ", Alert.AlertType.ERROR);
                return;
            }
            id_com = Integer.parseInt(commandeField.getText());
            commande c = new commande();
            c.setId_cmd(id_com);
            l.setC1(c);
            adresse = adresseField.getText();
            l.setAdresse_livraison(adresse);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to update the shipping data?");
            alert.setHeaderText("Please confirm your action");
            Optional<ButtonType> result = alert.showAndWait();
            // if the user confirms the update action
            if (result.isPresent() && result.get() == ButtonType.OK) {
                lv.modifierLivraison(l, l2);
                commandeGUI.AlertShow("updated !", "update", Alert.AlertType.INFORMATION);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // refreshTable();
            commandeGUI.clearTextFields(idField, statusField , commandeField, adresseField);
        }
    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisonupdate.fxml", "commande update");

    }

}
