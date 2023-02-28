/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;

import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import elbaldi.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ModifierQuizController implements Initializable {

    @FXML
    private Button ModifierQuiz;
    @FXML
    private ComboBox<String> fxdifficulte;
    @FXML
    private ComboBox<Utilisateur> fxid_user;
    @FXML
    private Button backfix;
  
    @FXML
    private TextField imgfx;

     private quiz quiz;
    @FXML
    private TextField idLabel2;
    
     public static quiz qu;
    /**
     * Initializes the controller class.
     */
    
     public void setQuiz(quiz quiz) {
        this.quiz = quiz;
       
        this.idLabel2.setText(quiz.getNom());
        this.fxdifficulte.setValue(quiz.getDifficulte());
    }
   
     

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         ModifierQuiz.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                QuizCRUD quizcrud = new QuizCRUD();
                quiz q = new quiz();
 if (!idLabel2.getText().equalsIgnoreCase("") && !fxdifficulte.getValue().equalsIgnoreCase("")) {
                q.setNom(idLabel2.getText());
                q.setDifficulte(fxdifficulte.getValue());
               
                quizcrud.modifierquiz(q,quiz.getId_quiz());
               
                 
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Votre modification est enregistrée avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
 }
                else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Echec de la modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                        alert.showAndWait();
 }
            }

        });
    }    
    

    
    @FXML
    private void Modifier_Quiz(ActionEvent event) {
      
       
    }

  
    
    @FXML
    private void goback(ActionEvent event) {
       
    FXMLLoader loader = new FXMLLoader(getClass().getResource("front.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backfix.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
    
 

    }
