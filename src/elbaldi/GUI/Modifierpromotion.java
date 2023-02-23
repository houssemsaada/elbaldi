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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class Modifierpromotion implements Initializable {

    
    @FXML
    private ListView<promotion> listview;
    @FXML
    private TextField id;
    @FXML
    private TextField promo;
    @FXML
    private TextField taux;
    @FXML
    private TextField debut;
    @FXML
    private TextField fin;
    @FXML
    private Button modifierfix;
    @FXML
    private Button fxback;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PromotionCRUD a= new PromotionCRUD();
        List<promotion> promotions = a.afficherpromotion();
        ObservableList<promotion> observableList = FXCollections.observableArrayList(promotions);
        listview.setItems(observableList);
       listview.setCellFactory(promotionListView -> new PromotionListViewCell());

       listview.setOnMouseClicked(e -> {
            promotion selectedPromotion = listview.getSelectionModel().getSelectedItem();
            if (selectedPromotion != null) {
                // Récupérer les valeurs de l'objet Promotion sélectionné
                int idPromotion = selectedPromotion.getId_promotion();
                String codePromo = selectedPromotion.getCode_promo();
                Float tauxx = selectedPromotion.getTaux();
              Date dateDebut = selectedPromotion.getDate_debut(); 
              Date dateFin = selectedPromotion.getDate_fin();       

               

                // Mettre à jour les champs de texte avec les valeurs récupérées
               id.setText(String.valueOf(idPromotion));
                promo.setText(codePromo);
                taux.setText(String.valueOf(tauxx));
                debut.setText(String.valueOf(dateDebut));
                fin.setText(String.valueOf(dateFin));
                ;
            }
        });
    }

   


    @FXML
    private void modifier_promo(ActionEvent event) {
         // Récupérer les valeurs des champs de texte
    int idPromotion = Integer.parseInt(id.getText());
    String codePromo = promo.getText();
    float tauxx = Float.parseFloat(taux.getText());
    String dateDebutStr = debut.getText();
    String dateFinStr = fin.getText();

    // Convertir les chaînes de caractères en objets Date
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateDebut = null;
    Date dateFin = null;
    try {
        dateDebut = new Date(dateFormat.parse(dateDebutStr).getTime());
        dateFin = new Date(dateFormat.parse(dateFinStr).getTime());
    } catch (ParseException e) {
        e.printStackTrace();
    }

    // Mettre à jour la promotion sélectionnée
    PromotionCRUD a = new PromotionCRUD();
    promotion promotion = new promotion(idPromotion, codePromo, tauxx, dateDebut, dateFin);
    a.modifierpromotion(promotion);

    // Rafraîchir la liste des promotions
    List<promotion> promotions = a.afficherpromotion();
    ObservableList<promotion> observableList = FXCollections.observableArrayList(promotions);
    listview.setItems(observableList);
    }

    @FXML
    private void back(ActionEvent event) {
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
    

       
    

