/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ProduitBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Button ajouterP;
    @FXML
    private Button updateP;
    @FXML
    private Button deleteP;
    @FXML
    private Button showP;
    @FXML
    private TableView<?> tableP;
    @FXML
    private TableColumn<?, ?> ref;
    @FXML
    private TableColumn<?, ?> libelle;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> img;
    @FXML
    private Button detailsfx;
    @FXML
    private Button Home;
    @FXML
    private Button Profil;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reservation;
    @FXML
    private Button btnSignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterprod(ActionEvent event) {
    }

    @FXML
    private void details(ActionEvent event) {
    }
    
}
