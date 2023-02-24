/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.promotion;
import elbaldi.services.PromotionCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class AfficherpromotionController implements Initializable {

    @FXML
    private ListView<promotion> promotionListView;
    @FXML
    private Button backfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       PromotionCRUD a= new PromotionCRUD();
        List<promotion> promotions = a.afficherpromotion();
        ObservableList<promotion> observableList = FXCollections.observableArrayList(promotions);
        promotionListView.setItems(observableList);
        promotionListView.setCellFactory(promotionListView -> new PromotionListViewCell());
    }

    @FXML
    private void goback(ActionEvent event) {
         // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backfx.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    private class PromotionListViewCell extends ListCell<promotion> {

        @Override
        protected void updateItem(promotion promotion, boolean empty) {
            super.updateItem(promotion, empty);
            if (empty || promotion == null) {
                setText(null);
            } else {
                 setText(/*String.format("ID Promotion: %d\n", promotion.getId_promotion())
                    +  */String.format("- Code_Promo: %s\n", promotion.getCode_promo())
                    + String.format("- Taux: %f\n", promotion.getTaux())
                    + String.format("- Date de début: %s\n", promotion.getDate_debut())
                    + String.format("- Date de fin: %s\n", promotion.getDate_fin()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}}
    
