/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import elbaldi.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherReservationBackController implements Initializable {

    @FXML
    private Button ajouterC;
    @FXML
    private Button updateC;
    @FXML
    private Button deleteC;
    @FXML
    private Button showC;
    @FXML
    private TableView<Reservation> TableView;
    @FXML
    private TableColumn<Reservation, Integer> nombre;
    @FXML
    private TableColumn<Reservation, Date> date;
    @FXML
    private TableColumn<Reservation, String> statut;
    ObservableList<Reservation> commandeObservableList = FXCollections.observableArrayList();
    @FXML
    private TextField searchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationCrud cc = new ReservationCrud();
        commandeObservableList = FXCollections.observableList(cc.afficherReservation());

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_personnes"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut_reservation"));
        
//        nomCol.setCellValueFactory(cellData -> {
//            System.out.println("aaaaa");
//            Utilisateur utilisateur = cellData.getValue().getUser2();
//            System.out.println(utilisateur);
//            String fullName = utilisateur.getNom() + " " + utilisateur.getPrenom();
//            System.out.println(fullName);
//            return new SimpleStringProperty(fullName);
//        }
//        );
     

        TableView.setItems(commandeObservableList);
        //search bar 
        FilteredList<Reservation> filteredData = new FilteredList<>(commandeObservableList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Reservation -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeywords = newValue.toLowerCase();

                if (Reservation.getStatut_reservation().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((Reservation.getDate_reservation() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((Reservation.getNombre_personnes()+ "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
//                if (Reservation.getUser2().getNom().toLowerCase().contains(searchKeywords)) {
//                    return true;
//                }
//                 if (Reservation.getUser2().getPrenom().toLowerCase().contains(searchKeywords)) {
//                    return true;
//                }
                return (Reservation.getId_reservation()+ "").toLowerCase().contains(searchKeywords);
            });
        });
        // wrap the FilteredList in a SortedList.
        SortedList<Reservation> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(filteredData);
        // TODO
    }

    @FXML
    private void ajouterBP(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutReservationBack.fxml"));
        Parent root = loader.load();
        searchTextField.getScene().setRoot(root);
        
    }

    @FXML
    private void modifierBP(ActionEvent event) throws IOException {
        if (TableView.getSelectionModel().getSelectedItem() != null) {
            Reservation r= TableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservationBack.fxml"));
            Parent root = loader.load();
            ModifierBonplanBackController FM = loader.getController();
            ModifierReservationBackController rm = loader.getController();
       
            rm.setCategorie(r);
              
        searchTextField.getScene().setRoot(root);
              

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une catégorie");
            alert.show();
        }
    }
    

    @FXML
    private void supprimerBP(ActionEvent event) {
        
    }

}
