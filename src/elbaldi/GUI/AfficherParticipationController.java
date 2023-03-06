/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.services.ParticipationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherParticipationController implements Initializable {

    @FXML
    private GridPane gridpane;
    @FXML
    private Button retour_btn;
    ParticipationService ps = new ParticipationService();
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
         try {
             
            List<Participation> part = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < part.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("participeritem.fxml"));
                AnchorPane pane = loader.load();
                
                //passage de parametres
               ParticiperitemController controller = loader.getController();
                controller.setP(part.get(i));
                

                gridpane.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void retournerHome(ActionEvent event) {
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
    }

    @FXML
    private void LivraisonAction(ActionEvent event) {
    }

    @FXML
    private void BonpalnsAction(ActionEvent event) {
    }

    @FXML
    private void QuizAction(ActionEvent event) {
    }

     @FXML
    private void eventaction(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AfficherEvenement.fxml"));
        Parent root = loader.load();
        Eventfx.getScene().setRoot(root);
    }

    @FXML
    private void participationaction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("afficher Participation.fxml"));
        Parent root = loader.load();
        participationfx.getScene().setRoot(root);
        
    }

    @FXML
    private void GestuserAction(ActionEvent event) {
    }

    @FXML
    private void decoAction(ActionEvent event) {
    }
    
}
