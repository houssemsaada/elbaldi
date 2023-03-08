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
   EvenementService es = new EvenementService();
      Evenement e1;
    @FXML
    private Button Home1;
    @FXML
    private Button bonplan1;
    @FXML
    private Button Home1111;
    @FXML
    private Button Home11111;
    @FXML
    private Button Home111111;
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
    private void bpbtn(ActionEvent event) {
    }

    
}
