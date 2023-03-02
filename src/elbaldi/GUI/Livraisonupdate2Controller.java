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
import elbaldi.services.MailerService;
import elbaldi.services.SmsServicee;
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
import java.time.LocalDate;
import java.util.Locale;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class Livraisonupdate2Controller implements Initializable {

    @FXML
    private Button update2Btn;
    @FXML
    private Button exit1;
    @FXML
    private ChoiceBox<String> statusField;
    private TextField dateField;
    @FXML
    private TextField adresseField;
    @FXML
    private DatePicker datepicker;
    private livraison livrai;
    @FXML
    private Button loginButton12;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusField.getItems().add("En attente");
        statusField.getItems().add("en expédition");
         statusField.getItems().add("livrée");
    }

    public void setLivrai(livraison livrai) {
        this.livrai = livrai;
        statusField.setValue(livrai.getStatus_livraison());
    }

    public void setStatusField(String statusField) {
        this.statusField.setValue(statusField);
    }

    public void setDateField(Date dateField) {

        this.datepicker.setValue(dateField.toLocalDate());
    }

    public void setAdresseField(String adresseField) {
        this.adresseField.setText(adresseField);
    }

    @FXML
    private void update2OnAction(ActionEvent event) {
        try {
            livraisonCRUD lv = new livraisonCRUD();

            Date Date_livraison;
            String status;
            String adresse;

            status = statusField.getValue();
            livrai.setStatus_livraison(status);

//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            Date_livraison = Date.valueOf(datepicker.getValue());
            livrai.setDate_livraison(Date_livraison);

            adresse = adresseField.getText();
            livrai.setAdresse_livraison(adresse);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to update the shipping data?");
            alert.setHeaderText("Please confirm your action");
            Optional<ButtonType> result = alert.showAndWait();
            // if the user confirms the update action
            if (result.isPresent() && result.get() == ButtonType.OK) {
                lv.modifierLivraison(livrai);
                commandeGUI.AlertShow("updated !", "update", Alert.AlertType.INFORMATION);
                if ( statusField.getValue() == "en expédition"){
                    MailerService ms = new MailerService();
                    ms.sendLivraisonMail(livrai, livrai.getC1());
                    SmsServicee sms = new SmsServicee();
                    String phone = "+21690025123";
                    String message ="cher(e) " + livrai.getC1().getPan().getU1().getPrenom() + "!,\n\n"
                    + "Nous vous remercions d'avoir fait vos achats chez nous. Votre numéro de commande :"
                    + livrai.getC1().getId_cmd()
                    + " a été expédié. Vous pouvez trouver ci-dessous les détails de votre commande :\n\n"
                    + "INFORMATIONS SUR LA COMMANDE\n"
                    + "Numéro de commande :" + livrai.getC1().getId_cmd() + " passé le " + livrai.getC1().getDate_cmd() + "\n"
                    + "Adresse de livraison :" + livrai.getAdresse_livraison() + "\n"
                    + "Valeur des produits :" + livrai.getC1().getTotal() + "TND \n"
                    + "Cordialement,\n"
                    + "L'équipe Elbaldi";
                   // sms.sendSms(phone, message);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // refreshTable();
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
        commandeGUI.changeScene(event, "livraisonupdate.fxml", "commande update");

    }

}
