/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GerantMainScreenController implements Initializable {

    @FXML
    private Button profilfx;
    @FXML
    private Button deconnexionfx;
    @FXML
    private Button reservationfx;
    @FXML
    private TableView<Reservation> TableView;
     ObservableList<Reservation> commandeObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reservation, Integer> nombre;
    @FXML
    private TableColumn<Reservation, String> statut;
    @FXML
    private TableColumn<Reservation, Date> date;
    @FXML
    private TableColumn<Reservation, Utilisateur> nom;
    @FXML
    private TableColumn<Reservation, bonplan> lieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ReservationCrud cc = new ReservationCrud();
        commandeObservableList = FXCollections.observableList(cc.afficherReservation());

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        try {
            Afficher();
        // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReservationBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void Afficher() throws SQLException {

        ReservationCrud resCRUD = new  ReservationCrud();

        ObservableList<Reservation> liste = FXCollections.
                observableArrayList((resCRUD.afficherReservation()));

        TableView.setItems(liste);

        /* add column to the tableview and set its items */
        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNombre_personnes());
            }
        });
        
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, java.sql.Date>, ObservableValue<java.sql.Date>>() {
            @Override
            public ObservableValue<java.sql.Date> call(TableColumn.CellDataFeatures<Reservation, java.sql.Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_reservation());
            }
        });
        

        statut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getStatut_reservation());
            }
        });
         nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Utilisateur>, ObservableValue<Utilisateur>>() {
            @Override
            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<Reservation, Utilisateur> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getUser2().getNom());
            }
        });
          lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, bonplan>, ObservableValue<bonplan>>() {
            @Override
            public ObservableValue<bonplan> call(TableColumn.CellDataFeatures<Reservation, bonplan> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBonplan2().getTitre_bonplan());
            }
        });
       

        
    }

    @FXML
    private void profilAction(ActionEvent event) {
    }

    @FXML
    private void deconnexionAction(ActionEvent event) {
    }

    @FXML
    private void reservationAction(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AfficherReservationBack.fxml"));
        Parent root = loader.load();
        reservationfx.getScene().setRoot(root);
    }
    
}
