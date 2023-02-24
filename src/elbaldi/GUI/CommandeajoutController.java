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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeajoutController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField etatField;
    private TextField dateField;
    @FXML
    private TextField panierField;
    @FXML
    private Button addBtn;
    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private DatePicker datepicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {     
    }

    @FXML
    private void addOnAction(ActionEvent event) {
        try {

            try {
                if (commandeGUI.isTextFieldEmpty(etatField,  panierField) || datepicker.getValue()== null) {
                    commandeGUI.AlertShow("Please fill all fields", "Empty fields", Alert.AlertType.ERROR);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            try {
                if (commandeGUI.isTextFieldEmpty(idField)) {

                } else {
                    Integer.parseInt(idField.getText());
                }
            } catch (Exception e) {
                commandeGUI.AlertShow("Please enter a valid id, only digits are allowed", "Invalid id", Alert.AlertType.ERROR);
                return;
            }
            try {
                Integer.parseInt(panierField.getText());

            } catch (Exception e) {
                commandeGUI.AlertShow("Please enter a valid user id, only digits are allowed", "Invalid id user ", Alert.AlertType.ERROR);
                return;
            }

            int id_panier = Integer.parseInt(panierField.getText());
            String etat = etatField.getText();
//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            Date date_com =  Date.valueOf(datepicker.getValue());

           // Date date_com = Date.valueOf(dateField.getText());
            //Utilisateur u = new Utilisateur(id_user);
            panier pan = new panier();
            pan.setId_panier(id_panier);
            CommandeCRUD cc = new CommandeCRUD();
            if (commandeGUI.isTextFieldEmpty(idField)) {
                commande com = new commande(pan, etat, date_com);
                cc.ajouterCommande(com);
            } else {
                int id = Integer.parseInt(idField.getText());
                commande com = new commande(id, pan, etat, date_com);
                cc.ajouterCommande(com);
            }
            commandeGUI.AlertShow("order added ! ", "order", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        } finally {
            commandeGUI.clearTextFields(idField, etatField, dateField, panierField);

        }

    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande interface");
    }

}
