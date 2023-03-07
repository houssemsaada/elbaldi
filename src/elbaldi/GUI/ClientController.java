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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ClientController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
  
     private List<quiz> listeQuiz = new ArrayList<>();
     QuizCRUD ds = new QuizCRUD();
    @FXML
    private Button profilfx;
    @FXML
    private Button prodfx;
    @FXML
    private Button bonplanfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button decofx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                fxmlLoader.setLocation(getClass().getResource("ItemQuiz.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ItemQuizController itemController = fxmlLoader.getController();
                itemController.setquiz(listeQuiz.get(i)
                
                );
                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
      

        } catch (IOException ex) {
        }
    }

    @FXML
    private void profilAction(ActionEvent event) {
    }

    @FXML
    private void produitsf(ActionEvent event) {
    }

    @FXML
    private void bonplanAction(ActionEvent event) {
    }

    @FXML
    private void EventAction(ActionEvent event) {
    }

    @FXML
    private void QuizAction(ActionEvent event) {
    }

    @FXML
    private void deconfx(ActionEvent event) {
    }

    
}
