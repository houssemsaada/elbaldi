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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class LivraisonajoutController implements Initializable {

    @FXML
    private Button addBtn;
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
        // TODO
    }

    @FXML
    private void addOnAction(ActionEvent event) {
        try {

            try {
                if (commandeGUI.isTextFieldEmpty(statusField, commandeField, adresseField) || datepicker.getValue() == null) {
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
                Integer.parseInt(commandeField.getText());

            } catch (Exception e) {
                commandeGUI.AlertShow("Please enter a valid order id, only digits are allowed", "Invalid order user ", Alert.AlertType.ERROR);
                return;
            }

            int id_cmd = Integer.parseInt(commandeField.getText());
            String status = statusField.getText();
//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            Date date_liv = Date.valueOf(datepicker.getValue());
//   Date date_liv = Date.valueOf(dateField.getText());
            String adresse = adresseField.getText();
            commande c = new commande();
            c.setId_cmd(id_cmd);
            livraisonCRUD lv = new livraisonCRUD();
            if (commandeGUI.isTextFieldEmpty(idField)) {
                livraison liv = new livraison(status, adresse, date_liv, c);
                try {
                    lv.ajouterLivraison(liv);
                } catch (Exception ex) {
                    commandeGUI.AlertShow("order id not found ! ", "order", Alert.AlertType.ERROR);
                }
            } else {
                int id = Integer.parseInt(idField.getText());
                livraison liv = new livraison(id, status, adresse, date_liv, c);
                try {
                    lv.ajouterLivraison(liv);
                } catch (Exception ex) {
                    commandeGUI.AlertShow("order id not found ! ", "order", Alert.AlertType.ERROR);
                }
            }
            commandeGUI.AlertShow(" added ! ", "shipping", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        } finally {
            commandeGUI.clearTextFields(idField, statusField, commandeField, adresseField);
            datepicker.setValue(null);

        }

    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "livraison interface");
    }

}
