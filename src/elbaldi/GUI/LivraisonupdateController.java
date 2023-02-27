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
import elbaldi.services.livraisonCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class LivraisonupdateController implements Initializable {

    @FXML
    private Button UpdatelBtn;
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
    private void UpdateOnAction(ActionEvent event) {
        try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Please select an order to update", "No order selected", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }

        livraison liv = ListView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("livraisonupdate2.fxml"));
        try {
            Parent root = loader.load();
            Livraisonupdate2Controller lc = loader.getController();
            lc.setIdField(liv.getId_livraison() + "");
            lc.setAdresseField(liv.getAdresse_livraison());
            lc.setStatusField(liv.getStatus_livraison());
            lc.setDateField(liv.getDate_livraison());
            lc.setCommandeField(liv.getC1().getId_cmd() + "");
            UpdatelBtn.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
