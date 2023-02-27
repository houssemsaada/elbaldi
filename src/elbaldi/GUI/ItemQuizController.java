/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import elbaldi.models.quiz;
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
   
  

      private quiz quiz;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setquiz(quiz q) {
         
      
         dfiffiLabel.setText(q.getDifficulte());
          nomLabel.setText( q.getNom());
         // File f = new File(  q.getImgview());

       // imglabel.setImage(new Image(f.toURI().toString()));
         
        
    
        
    }

    @FXML
    private void jouez(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("JouerQuiz.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) jouerLabel.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }

   
    
    
    
    }}
    