/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.services.CommandeCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeupdateController implements Initializable {

    @FXML
    private TableView<commande> tableView;
    @FXML
    private TableColumn<commande, Integer> IDCol;
    @FXML
    private TableColumn<commande, String> etatCol;
    @FXML
    private TableColumn<commande, String> DateCol;
    @FXML
    private TableColumn<commande, Integer> userCol;
    @FXML
    private TableColumn<commande, String> usernameCol;

    @FXML
    private TableColumn<commande, String> emailCol;
    @FXML
    private TableColumn<commande, String> telCol;
    @FXML
    private TableColumn<commande, Integer> PanierCol;
    @FXML
    private TableColumn<commande, Integer> articleCol;
    @FXML
    private TableColumn<commande, Float> totalCol;
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
    private Button UpdatelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeCRUD cc = new CommandeCRUD();
        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        IDCol.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
        userCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getU1().getid_user()));
        usernameCol.setCellValueFactory(cellData -> {
            Utilisateur utilisateur = cellData.getValue().getPan().getU1();
            String fullName = utilisateur.getNom() + " " + utilisateur.getPrenom();
            return new SimpleStringProperty(fullName);
        });

        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPan().getU1().getEmail()));
        telCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPan().getU1().getNumTel())));
        PanierCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getId_panier()));
        articleCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getNombre_article()));
        totalCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getTotal_panier()));
        tableView.setItems(commandeObservableList);
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
                if (String.valueOf(commande.getPan().getU1().getid_user()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
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
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(filteredData);
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

        IDCol.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
        userCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getU1().getid_user()));
        usernameCol.setCellValueFactory(cellData -> {
            Utilisateur utilisateur = cellData.getValue().getPan().getU1();
            String fullName = utilisateur.getNom() + " " + utilisateur.getPrenom();
            return new SimpleStringProperty(fullName);
        });

        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPan().getU1().getEmail()));
        telCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPan().getU1().getNumTel())));
        PanierCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getId_panier()));
        articleCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getNombre_article()));
        totalCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPan().getTotal_panier()));
        tableView.setItems(commandeObservableList);
    }

    @FXML
    private void UpdateOnAction(ActionEvent event) {
        try {
                if (tableView.getSelectionModel().getSelectedItem() == null) {
                    commandeGUI.AlertShow("Please select an order to update", "No order selected", Alert.AlertType.ERROR);
                    return;
                }
            } catch (Exception ewww) {
                ewww.printStackTrace();
                ewww.getCause();
            }
        
        commande c =tableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeupdate2.fxml"));
        try {
            Parent root = loader.load();
            Commandeupdate2Controller dc = loader.getController();
            dc.setIdField(c.getId_cmd()+"");
            dc.setEtatField(c.getEtat());
            dc.setDateField(c.getDate_cmd()+"");
            dc.setPanierField(c.getPan().getId_panier()+"");
            
            searchTextField.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
