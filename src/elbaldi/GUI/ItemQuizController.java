/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import elbaldi.models.quiz;
import elbaldi.services.QuizCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ItemQuizController implements Initializable {

    
    @FXML
    private Label nomLabel;
    @FXML
    private Label dfiffiLabel;
    @FXML
    private ImageView imglabel;
    @FXML
    private Button jouerLabel;
    private quiz qcc;
    private quiz quiz;
      
      
      QuizCRUD qc = new QuizCRUD();
      
      public void setquiz(quiz q) {
          
          this.qcc=q;       
          nomLabel.setText(q.getNom());
          dfiffiLabel.setText(q.getDifficulte());
          // idLabel.setText(String.valueOf(q.getId_quiz()));
          // imgLabel.setText(q.getImgview());            
       
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
          
    

    @FXML
    private void jouez(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("JouerQuiz.fxml"));
    try {
            Parent root = loader.load();
            JouerQuizController qbc= loader.getController();
            qbc.setQuiz(qcc);
            nomLabel.getScene().setRoot(root);
            
    } catch (IOException ex) {
        ex.printStackTrace();
    }

   
    
    
    
    }}
    

