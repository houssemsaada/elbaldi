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
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class SupprimerPromotionController implements Initializable {

    @FXML
    private TextField fxiid;
    @FXML
    private TextField fxcodee;
    @FXML
    private TextField fxtauux;
    @FXML
    private TextField fxdebutt;
    @FXML
    private TextField fxfinn;
    @FXML
    private Button fxsupprim;
    @FXML
    private Button fxback;
    @FXML
    private ListView<promotion> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PromotionCRUD a= new PromotionCRUD();
        List<promotion> promotions = a.afficherpromotion();
        ObservableList<promotion> observableList = FXCollections.observableArrayList(promotions);
        list.setItems(observableList);
       list.setCellFactory(promotionListView -> new PromotionListViewCell());

       list.setOnMouseClicked(e -> {
            promotion selectedPromotion = list.getSelectionModel().getSelectedItem();
            if (selectedPromotion != null) {
                // Récupérer les valeurs de l'objet Promotion sélectionné
                int idPromotion = selectedPromotion.getId_promotion();
                String codePromo = selectedPromotion.getCode_promo();
                Float tauxx = selectedPromotion.getTaux();
              Date dateDebut = selectedPromotion.getDate_debut(); 
              Date dateFin = selectedPromotion.getDate_fin();       

               

                // Mettre à jour les champs de texte avec les valeurs récupérées
               fxiid.setText(String.valueOf(idPromotion));
                fxcodee.setText(codePromo);
                fxtauux.setText(String.valueOf(tauxx));
                fxdebutt.setText(String.valueOf(dateDebut));
                fxfinn.setText(String.valueOf(dateFin));
                ;
            }
        });
    
    }    

    @FXML
    private void supprimer(ActionEvent event) {
    if (fxiid.getText().isEmpty() || fxcodee.getText().isEmpty() || fxtauux.getText().isEmpty() || fxdebutt.getText().isEmpty() || fxfinn.getText().isEmpty()) {
     
Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Avertissement");
alert.setHeaderText(null);
alert.setContentText("Veuillez sélectionner une promotion à supprimer !");
alert.showAndWait();
return;
}
        
        
        
        
         // Récupérer la promotion sélectionnée
    promotion selectedPromotion = list.getSelectionModel().getSelectedItem();
    if (selectedPromotion != null) {
        // Supprimer la promotion
        PromotionCRUD a = new PromotionCRUD();
        a.supprimerpromotion(selectedPromotion.getId_promotion());
        
        //Alerte 
           Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Suppression de promotion");
alert.setHeaderText(null);
alert.setContentText("La promotion a été supprimée avec succès !");
alert.showAndWait();
    
        
        
        

        // Rafraîchir la liste des promotions
        List<promotion> promotions = a.afficherpromotion();
        ObservableList<promotion> observableList = FXCollections.observableArrayList(promotions);
        list.setItems(observableList);

        // Effacer les champs de texte
        fxiid.setText("");
        fxcodee.setText("");
        fxtauux.setText("");
        fxdebutt.setText("");
        fxfinn.setText("");
    }
}

    @FXML
    private void goBack(ActionEvent event) {
        // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) fxback.getScene().getWindow(); // backButton est le bouton de retour
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
                 setText(String.format("ID Promotion: %d\n", promotion.getId_promotion())
                    +String.format("- Code_Promo: %s\n", promotion.getCode_promo())
                    + String.format("- Taux: %.1f\n", promotion.getTaux())
                    + String.format("- Date de début: %s\n", promotion.getDate_debut())
                    + String.format("- Date de fin: %s\n", promotion.getDate_fin()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}

}