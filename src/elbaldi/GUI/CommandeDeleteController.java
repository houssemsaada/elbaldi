/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.services.CommandeCRUD;
import java.net.URL;
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
public class CommandeDeleteController implements Initializable {

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
    ObservableList<commande> commandeObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<commande> ListView;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CommandeCRUD cc = new CommandeCRUD();

        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        ListView.setCellFactory(lv -> new CommandeListCell());
        ListView.setItems(commandeObservableList);

        //search bar 
        FilteredList<commande> filteredData = new FilteredList<>(commandeObservableList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(commande -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeywords = newValue.toLowerCase();

                if (commande.getEtat().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((commande.getDate_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getNom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getPrenom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
//                if (String.valueOf(commande.getPan().getU1().getid_user()).toLowerCase().contains(searchKeywords)) {
//                    return true;
//                }
                if (String.valueOf(commande.getPan().getU1().getNumTel()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getEmail().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                return (commande.getId_cmd() + "").toLowerCase().contains(searchKeywords);
            });
        });
        // wrap the FilteredList in a SortedList.
        SortedList<commande> sortedList = new SortedList<>(filteredData);
        //sortedList.comparatorProperty().bind(ListView.comparatorProperty());
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
            commande c = new commande();
            CommandeCRUD cc = new CommandeCRUD();
            c.setId_cmd(ListView.getSelectionModel().getSelectedItem().getId_cmd());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete the order ?");
            alert.setHeaderText("Please confirm your action");

            Optional<ButtonType> result = alert.showAndWait();

            // if the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cc.supprimerCommande(c);
                refreshTable();
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
void refreshTable() {
        CommandeCRUD cc = new CommandeCRUD();

        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        ListView.setCellFactory(lv -> new CommandeListCell());
        ListView.setItems(commandeObservableList);
    }
    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande interface");
    }

    @FXML
    private void refreshOnAction(ActionEvent event) {
        CommandeCRUD cc = new CommandeCRUD();

        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        ListView.setCellFactory(lv -> new CommandeListCell());
        ListView.setItems(commandeObservableList);
    }

}
