/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.models.quiz;
import elbaldi.services.QuizCRUD;
import elbaldi.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ItemBackController implements Initializable {

    @FXML
    private ImageView imgLabel;
  
    @FXML
    private Button supprimer;
   
    @FXML
    private Label nomLabell;
    @FXML
    private Label di;
    @FXML
    private Button fxquestion;
    @FXML
    private Button modifierfix;
    @FXML
    public Label idLabel;
    private quiz qcc;
   
    QuizCRUD qc = new QuizCRUD();
    
      public void setquiz(quiz q) {
          
          this.qcc=q;
          
      nomLabell.setText(q.getNom());
      di.setText(q.getDifficulte());
     // idLabel.setText(String.valueOf(q.getId_quiz()));
     // imgLabel.setText(q.getImgview());            
       
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
   
    
    
       
        
      
    

    @FXML
 
private void delete(ActionEvent event) throws IOException {
       
      quiz q = new quiz();
      q.setNom(nomLabell.getText());

  
      qc.supprimerquiz(q);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Quiz a été supprimé avec succés!");
      alert.show();
      Parent loader = FXMLLoader.load(getClass().getResource("front.fxml"));
      nomLabell.getScene().setRoot(loader);

    }



   

   
    // Pour naviguer vers la liste des questions : 
    @FXML
    private void question(ActionEvent event) {
        
        
        System.out.println("hhhhhhhhhhhhhhh"+qcc);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBack.fxml"));
        try {
            Parent root = loader.load();
            QuestionBackController qbc= loader.getController();
            qbc.setQuiz(qcc);
            System.out.println("aaaaaaaaaaaaa"+qcc);
            nomLabell.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBack.fxml"));
//    try {
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) fxquestion.getScene().getWindow();// backButton est le bouton de retour
//        QuestionBackController qbc= loader.getController();
//        System.out.println("hhhhhhhhhhhhhhh"+qcc);
//        qbc.setQuiz(qcc);
//        System.out.println("ibc1");
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
      
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierQuiz.fxml"));
    try {
        Parent root1 = loader.load();
        Scene scene = new Scene(root1);
        Stage stage = (Stage) fxquestion.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
        
        
        
  /*  quiz q = new quiz();
    q.setId_quiz(Integer.parseInt(idLabel.getText()));
     Parent loader = FXMLLoader.load(getClass().getResource("font.fxml"));
      nomLabell.getScene().setRoot(loader);
    
    */
            
    }
    


    }
    
    
    
