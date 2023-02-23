/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;


import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.QuestionCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
    private Button backfix;
    @FXML
    private ListView<question> listview;

    /**
     * Initializes the controller class.
     */
    
     private class questionListViewCell extends ListCell<question> {

        @Override
        protected void updateItem(question question, boolean empty) {
            super.updateItem(question, empty);
            if (empty || question == null) {
                setText(null);
            } else {
                 setText(String.format("ID Question: %d\n",question.getId_question()) 
                    +String.format("- Difficulté: %s\n", question.getDifficulte())
                    + String.format("- Question: %s\n", question.getQuestionn())
                    + String.format("- Réponse1: %s\n",question.getReponse1())
                    + String.format("- Réponse2: %s\n",question.getReponse2())
                    + String.format("- Réponse3: %s\n",question.getReponse3())
                    + String.format("- Solution: %s\n", question.getSolution())
                    + String.format("- id quiz: %s\n", question.getquiz()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        QuestionCRUD a= new QuestionCRUD();
        List<question> questions = a.afficherQuestion();
        ObservableList<question> observableList = FXCollections.observableArrayList(questions);
        listview.setItems(observableList);
       listview.setCellFactory(questionListView -> new questionListViewCell());

       listview.setOnMouseClicked(e -> {
            question selectedQuestion = listview.getSelectionModel().getSelectedItem();
            if ( selectedQuestion != null) {
                // Récupérer les valeurs de l'objet Promotion sélectionné
                int idQuestion =  selectedQuestion.getId_question();
                String questionn =  selectedQuestion.getQuestionn();
                String Difficulté =  selectedQuestion.getDifficulte();
                String Reponse1 =  selectedQuestion.getReponse1();
                String Reponse2 =  selectedQuestion.getReponse2();
                String Reponse3 =  selectedQuestion.getReponse3();
                 String Solution =  selectedQuestion.getSolution();
                quiz idQuiz =  selectedQuestion.getquiz();

               

               
                
                ;
            }
        });
    
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         /*// Retrieve all questions from the database
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
    }*/
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

    
         
    
       
    
