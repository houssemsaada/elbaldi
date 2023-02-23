/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ModifierquestionController implements Initializable {

    @FXML
    private Button backfx;
    @FXML
    private TextField fxidquestion;
    @FXML
    private TextField fxquestion;
    @FXML
    private TextField fxdifficulte;
    @FXML
    private TextField fxreponse1;
    @FXML
    private TextField fxreponse2;
    @FXML
    private TextField fxreponse3;
    @FXML
    private TextField fxsolutionn;
    @FXML
    private ComboBox<quiz> fxquiz;
    @FXML
    private Button modifierquestionfx;
    @FXML
    private ListView<question> listview;

    /**
     * Initializes the controller class.
     */
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
                quiz qu = selectedQuestion.getquiz();
               // int idQuiz =  selectedQuestion.getquiz().getId_quiz();

               

                // Mettre à jour les champs de texte avec les valeurs récupérées
                fxidquestion.setText(String.valueOf(idQuestion));
                fxquestion.setText(questionn);
                fxdifficulte.setText(Difficulté);
                fxreponse1.setText(Reponse1);
                fxreponse2.setText(Reponse2);
                fxreponse3.setText(Reponse3);
                fxsolutionn.setText(Solution);
                //fxquiz.setText(String.valueOf(idQuiz));
               fxquiz.setValue(qu);
              
                
                ;
            }
        });
    
           // Charger la liste des promotions dans le ComboBox
        QuizCRUD pc = new QuizCRUD();
        List<quiz> quizs = pc.afficherQuiz();
        ObservableList<quiz> observableListquiz = FXCollections.observableArrayList(quizs);
        fxquiz.setItems(observableListquiz);
       
       
       
    }    

    @FXML
    private void goBack(ActionEvent event) {
           // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backfx.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    @FXML
    private void modifierquestion(ActionEvent event) {
        
         if (fxquestion.getText().isEmpty() || fxdifficulte.getText().isEmpty() || fxreponse1.getText().isEmpty() || fxreponse2.getText().isEmpty() || fxreponse3.getText().isEmpty() ||  fxsolutionn.getText().isEmpty() ||  fxquiz.getValue() ==null) {
     
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Avertissement");
                  alert.setHeaderText(null);
                  alert.setContentText("Veuillez sélectionner la question à modifier !");
                  alert.showAndWait();
                  return;
}
    
        
        
        
        
                int idQuestion =  Integer.parseInt(fxidquestion.getText());
                String questionn =   fxquestion.getText();
                String Difficulté =  fxdifficulte.getText();
                String Reponse1 =  fxreponse1.getText();
                String Reponse2 =  fxreponse2.getText();
                String Reponse3 = fxreponse3.getText();
               
                String Solution = fxsolutionn.getText();
                quiz quiz = fxquiz.getValue();
               // quiz idQuiz = new quiz();
                //idQuiz.setId_quiz(Integer.parseInt(fxquiz.getText()));

    // Mettre à jour la promotion sélectionnée
    QuestionCRUD a = new QuestionCRUD();
    question question = new question(idQuestion, questionn , Difficulté ,Reponse1, Reponse2,Reponse3,Solution,quiz);
    a.modifierquestion(question);
    
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Modification de la question");
     alert.setHeaderText(null);
     alert.setContentText("La question a été modifiée avec succès !");
     alert.showAndWait();

    // Rafraîchir la liste des promotions
    List<question> questions = a.afficherQuestion();
    ObservableList<question> observableList = FXCollections.observableArrayList(questions);
    listview.setItems(observableList);
    }
        
        
         private class questionListViewCell extends ListCell<question> {

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
                    + String.format("- id quiz: %d\n", question.getquiz().getId_quiz()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}
        
        
        
        
        
        
        
        
        
        
        
        
       
    
}
