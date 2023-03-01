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
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> statusField;
    private TextField dateField;
    @FXML
    private TextField adresseField;
    @FXML
    private DatePicker datepicker;
    commande com;

    public void setCom(commande com) {
        this.com = com;
        adresseField.setText(com.getAdresse());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusField.getItems().add("En attente");
        statusField.getItems().add("en expédition");
         statusField.getItems().add("livrée");
    }

    @FXML
    private void addOnAction(ActionEvent event) {
        try {

            try {
                if (commandeGUI.isTextFieldEmpty( adresseField) || datepicker.getValue() == null || statusField.getValue() == null ) {
                    commandeGUI.AlertShow("Please fill all fields", "Empty fields", Alert.AlertType.ERROR);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

            int id_cmd = com.getId_cmd();
            System.out.println(id_cmd);
            String status = statusField.getValue();
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

            livraison liv = new livraison(status, adresse, date_liv, c);
            try {
                lv.ajouterLivraison(liv);
            } catch (Exception ex) {
                commandeGUI.AlertShow("order id not found ! ", "order", Alert.AlertType.ERROR);
            }

            commandeGUI.AlertShow(" added ! ", "shipping", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        } finally {
            commandeGUI.clearTextFields( adresseField);
            statusField.setValue(null);
            datepicker.setValue(null);

        }

    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande interface");
    }

}
