/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.livraisonCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class LivraisonDeleteController implements Initializable {

    @FXML
    private Button delBtn;
    @FXML
    private Button loginButton12;
    @FXML
    private Button loginButton121;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button refreshbtn;

    ObservableList<livraison> livraisonObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<livraison> ListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());

           ListView.setCellFactory(lva -> new LivraisonListCell());
        ListView.setItems(livraisonObservableList);

        //search bar 
        FilteredList<livraison> filteredData = new FilteredList<>(livraisonObservableList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(livraison -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeywords = newValue.toLowerCase();

                if (livraison.getStatus_livraison().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((livraison.getDate_livraison() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (livraison.getC1().getPan().getU1().getNom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (livraison.getC1().getPan().getU1().getPrenom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (String.valueOf(livraison.getC1().getPan().getU1().getid_user()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (String.valueOf(livraison.getC1().getPan().getU1().getNumTel()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (livraison.getC1().getPan().getU1().getEmail().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((livraison.getC1().getDate_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((livraison.getC1().getId_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                return (livraison.getId_livraison() + "").toLowerCase().contains(searchKeywords);
            });
        });
        // wrap the FilteredList in a SortedList.
//        SortedList<livraison> sortedList = new SortedList<>(filteredData);
//        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        ListView.setItems(filteredData);
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Please select an order to delete", "No order selected", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            livraison liv = new livraison();
            livraisonCRUD lv= new livraisonCRUD();
          
            liv.setId_livraison(ListView.getSelectionModel().getSelectedItem().getId_livraison());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete the order ?");
            alert.setHeaderText("Please confirm your action");

            Optional<ButtonType> result = alert.showAndWait();

            // if the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                lv.supprimerLivraison(liv);
               refreshTable();           }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        } 
    }
         void refreshTable() {
             livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());

           ListView.setCellFactory(lva -> new LivraisonListCell());
        ListView.setItems(livraisonObservableList);
    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "livraison interface");

    }

    @FXML
    private void refreshOnAction(ActionEvent event) {
       livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());

           ListView.setCellFactory(lva -> new LivraisonListCell());
        ListView.setItems(livraisonObservableList);
    }

}
