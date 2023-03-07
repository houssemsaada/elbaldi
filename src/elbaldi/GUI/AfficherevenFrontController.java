/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.services.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherevenFrontController implements Initializable {

    @FXML
    private GridPane gridpane;
    @FXML
    private Button retour_btn;
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
   EvenementService es = new EvenementService();
      Evenement e1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             
            List<Evenement> events = es.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < events.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("eventfront.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                EventfrontController controller = loader.getController();
                controller.setEvenement(events.get(i));
                

                gridpane.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherevenFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }    }    

    @FXML
    private void retournerHome(ActionEvent event) {
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
    private void prodd(ActionEvent event) {
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
