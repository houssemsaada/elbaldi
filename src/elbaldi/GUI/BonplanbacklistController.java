/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BonplanbacklistController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button ajoutfx;
    @FXML
    private Button showC;
    @FXML
    private Button Home1;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button btnSignout1111;
    @FXML
    private Button btnSignout11111;
    @FXML
    private Button btnSignout1111111;
    @FXML
    private Button btnSignout11111111;
    @FXML
    private Button btnSignout111111111;
    @FXML
    private Button btnSignout11112;
    @FXML
    private Button btnSignout111121;
    @FXML
    private Button btnSignout11112111;
    private List<bonplan> listebonplan = new ArrayList<>();
    BonplanCrud bp = new BonplanCrud();
    @FXML
    private Button rs;
    @FXML
    private Button bonp;
    @FXML
    private Button avis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    
public void afficher() {
        try {
            grid.getChildren().remove(0, listebonplan.size());
           
            listebonplan = bp.afficherBonplan();
      
            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpBack.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpBackController itemController = fxmlLoader.getController();
                          
                itemController.setData(listebonplan.get(i));
                            
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
       

        } catch (IOException ex) {
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutBonplanBack.fxml"));
        Parent root = loader.load();

        AjoutBonplanBackController cb = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        afficher();

    }

    @FXML
    private void refresh(ActionEvent event) {
            try {
            grid.getChildren().remove(0, listebonplan.size());
            listebonplan = bp.afficherBonplan();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpBack.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpBackController itemController = fxmlLoader.getController();
                itemController.setData(listebonplan.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
       

        } catch (IOException ex) {
        }
    }

    @FXML
    private void rsss(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AfficherReservationBack.fxml"));
        Parent root = loader.load();
        rs.getScene().setRoot(root);
    }

    @FXML
    private void bppp(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("bonplanbacklist.fxml"));
        Parent root = loader.load();
        bonp.getScene().setRoot(root);
    }

    @FXML
    private void avisss(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("avisback.fxml"));
        Parent root = loader.load();
        avis.getScene().setRoot(root);
    }
    
}
