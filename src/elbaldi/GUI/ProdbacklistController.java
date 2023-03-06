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

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ProdbacklistController implements Initializable {

    @FXML
    private Button Accueilfx;
    @FXML
    private Button profilfx;
    @FXML
    private Button categoriefx;
    @FXML
    private Button produitfx;
    @FXML
    private Button commandefx;
    @FXML
    private Button Livrfx;
    @FXML
    private Button Bonplanfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button participationfx;
    @FXML
    private Button GestUser;
    @FXML
    private Button Decofx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void commandeinterf(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande interface");
    }

    private void livraisoninterf(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "commande interface");
    }

    @FXML
    private void prodd(ActionEvent event) {
    }

    @FXML
    private void accueilAction(ActionEvent event) {
    }

    @FXML
    private void profilAction(ActionEvent event) {
    }

    @FXML
    private void categ(ActionEvent event) {
    }

    @FXML
    private void commandesAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande interface");

    }

    @FXML
    private void LivraisonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "commande interface");

    }

    @FXML
    private void BonpalnsAction(ActionEvent event) {
    }

    @FXML
    private void QuizAction(ActionEvent event) {
    }

    @FXML
    private void eventaction(ActionEvent event) {
    }

    @FXML
    private void participationaction(ActionEvent event) {
    }

    @FXML
    private void GestuserAction(ActionEvent event) {
    }

    @FXML
    private void decoAction(ActionEvent event) {
    }

}
