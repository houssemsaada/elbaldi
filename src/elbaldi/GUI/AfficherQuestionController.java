/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;


import elbaldi.models.question;
import elbaldi.services.QuestionCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class AfficherQuestionController implements Initializable {

    @FXML
    private VBox fxvbox;
    @FXML
    private Button backfix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         // Retrieve all questions from the database
    QuestionCRUD qc = new QuestionCRUD();
    List<question> questions = qc.afficherQuestion();

    // Add each question to the questionContainer
    for (question q : questions) {
       
        Label questionLabel = new Label("Id_question: "+ q.getId_question() + " || "+"Difficulté: " 
   + q.getDifficulte() + " || " + "Question: " + q.getQuestionn() + " || "  
   + "Réponse 1 : "+ q.getReponse1() + " || " + "Réponse 2: " + q.getReponse2() + 
   " || "  + "Réponse 3: "+ q.getReponse3() + " || " + "Solution: " + q.getSolution() + " || "  + "Id_quiz: "
   + q.getquiz());
        
        
            questionLabel.setWrapText(true);
            Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setPrefHeight(10);
        fxvbox.getChildren().addAll(questionLabel, separator);
    }
}

    @FXML
    private void goBack(ActionEvent event) {
        // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
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
    
    
         
    
       
    
