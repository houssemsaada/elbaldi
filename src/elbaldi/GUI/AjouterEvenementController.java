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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField awards;
    @FXML
    private TextField description;
    @FXML
    private DatePicker mydatedebut;
    @FXML
    private DatePicker mydatefin;
    @FXML
    private Button updatebtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficheEvenement(ActionEvent event) {
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) {
    }

    @FXML
    private void modifierEvenemet(ActionEvent event) {
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
    }
    
}
