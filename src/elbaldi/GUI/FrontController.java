/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.quiz;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class FrontController implements Initializable {

    
    
     private List<quiz> listeQuiz = new ArrayList<>();
    QuizCRUD ds = new QuizCRUD();
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane gridfx;
    @FXML
    private Button ajoutfx;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
     
    }
        // TODO
    public void afficher(){
        try {
         
            listeQuiz = ds.afficherQuiz();
            
            int column = 0;
            int row = 1;
            for (int i = 0; i < listeQuiz.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemBack.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ItemBackController itemController = fxmlLoader.getController();
                itemController.setquiz(listeQuiz.get(i) );
                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridfx.add(anchorpane, column++, row);
                gridfx.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridfx.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridfx.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridfx.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridfx.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridfx.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
      

        } catch (IOException ex) {
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterQuiz.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ajoutfx.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
}


    @FXML
    private void se(MouseEvent event) {
        
    }
}