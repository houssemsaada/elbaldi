/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.UtilisateurCRUD;
import elbaldi.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeinterfaceController implements Initializable {

    private TextField userField;
    @FXML
    private Button addBtn;
    @FXML
    private Button updBtn;
    @FXML
    private Button delBtn;

    @FXML
    private Button loginButton12;
    @FXML
    private TextField searchTextField;
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    ObservableList<commande> commandeObservableList = FXCollections.observableArrayList();

    @FXML
    private Button refreshbtn;
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
        // TODO
    }
    // method to refresh the table view to show the updated data after any action

    void refreshTable() {
        CommandeCRUD cc = new CommandeCRUD();

        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        ListView.setCellFactory(lv -> new CommandeListCell());
        ListView.setItems(commandeObservableList);
    }

    @FXML
    private void addBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeajout.fxml", "Ajouter commande");
    }

    @FXML
    private void updateBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeupdate.fxml", "modifier commande");
    }

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeDelete.fxml", "supprimer commande");
    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void refreshOnAction(ActionEvent event) {
        refreshTable();
    }

}

