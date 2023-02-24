/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.*;
import elbaldi.services.livraisonCRUD;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class LivraisoninterfaceController implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private Button updBtn;
    @FXML
    private Button delBtn;
    @FXML
    private TableView<livraison> tableView;
    @FXML
    private TableColumn<livraison, Integer> IDCol;
    @FXML
    private TableColumn<livraison, String> statusCol;
    @FXML
    private TableColumn<livraison, String> adresseCol;
    @FXML
    private TableColumn<livraison, String> usernameCol;
    @FXML
    private TableColumn<livraison, String> emailCol;
    @FXML
    private TableColumn<livraison, String> telCol;
    @FXML
    private TableColumn<livraison, Integer> articleCol;
    @FXML
    private TableColumn<livraison, String> totalCol;
    @FXML
    private TableColumn<livraison, String> DateCol;
    @FXML
    private TableColumn<livraison, Integer> idcomCol;
    @FXML
    private TableColumn<livraison, String> DatecomCol;
    @FXML
    private Button loginButton12;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button refreshbtn;
    ObservableList<livraison> livraisonObservableList = FXCollections.observableArrayList();

    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status_livraison"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        idcomCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getC1().getId_cmd()));
        usernameCol.setCellValueFactory(cellData -> {
            Utilisateur utilisateur = cellData.getValue().getC1().getPan().getU1();
            String fullName = utilisateur.getNom() + " " + utilisateur.getPrenom();
            return new SimpleStringProperty(fullName);
        });
        totalCol.setCellValueFactory(cellData -> {
            panier pan = cellData.getValue().getC1().getPan();
            float total = pan.getTotal_panier();
            return new SimpleObjectProperty(total);
        });
        DatecomCol.setCellValueFactory(cellData -> {
            commande com = cellData.getValue().getC1();
            Date date = com.getDate_cmd();
            return new SimpleObjectProperty(date);
        });
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getC1().getPan().getU1().getEmail()));
        telCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getC1().getPan().getU1().getNumTel())));
        articleCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getC1().getPan().getNombre_article()));
        tableView.setItems(livraisonObservableList);
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
        SortedList<livraison> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(filteredData);
    }

    private void refreshTable() {
        livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status_livraison"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        idcomCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getC1().getId_cmd()));
        usernameCol.setCellValueFactory(cellData -> {
            Utilisateur utilisateur = cellData.getValue().getC1().getPan().getU1();
            String fullName = utilisateur.getNom() + " " + utilisateur.getPrenom();
            return new SimpleStringProperty(fullName);
        });
        totalCol.setCellValueFactory(cellData -> {
            panier pan = cellData.getValue().getC1().getPan();
            float total = pan.getTotal_panier();
            return new SimpleObjectProperty(total);
        });
        DatecomCol.setCellValueFactory(cellData -> {
            commande com = cellData.getValue().getC1();
            Date date = com.getDate_cmd();
            return new SimpleObjectProperty(date);
        });
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getC1().getPan().getU1().getEmail()));
        telCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getC1().getPan().getU1().getNumTel())));
        articleCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getC1().getPan().getNombre_article()));
        tableView.setItems(livraisonObservableList);
    }

    @FXML
    private void addBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisonajout.fxml", "Ajouter livraison");
    }

    @FXML
    private void updateBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisonupdate.fxml", "modifier livraison");
    }

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisonDelete.fxml", "supprimer livraison");
    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void refreshOnAction(ActionEvent event) {
        refreshTable();

    }

}
