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
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private TextField fxdifficulte;
    @FXML
    private TextField fxscore;
    @FXML
    private TextField fxid_promotion;
    @FXML
    private TextField fxid_user;
    @FXML
    private TextField fxid_quiz;
    @FXML
    private Button backfix;
    @FXML
    private ListView<quiz> listview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        QuizCRUD a= new QuizCRUD();
        List<quiz> quizs = a.afficherQuiz();
        ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
        listview.setItems(observableList);
       listview.setCellFactory(questionListView -> new quizListViewCell());

       listview.setOnMouseClicked(e -> {
            quiz selectedQuestion = listview.getSelectionModel().getSelectedItem();
            if ( selectedQuestion != null) {
                // Récupérer les valeurs de l'objet Promotion sélectionné
                int idquiz =  selectedQuestion.getId_quiz();  
                String Difficulté =  selectedQuestion.getDifficulte();
                int score =  selectedQuestion.getScore();  
                int iduser =  selectedQuestion.getuser().getid_user();  
                int idpromotion =  selectedQuestion.getpromotion().getId_promotion();

    

                // Mettre à jour les champs de texte avec les valeurs récupérées
                fxid_quiz.setText(String.valueOf(idquiz));
                fxdifficulte.setText(Difficulté);
                fxscore.setText(String.valueOf(score));
                fxid_promotion.setText(String.valueOf(idpromotion));
               
                fxid_user.setText(String.valueOf(iduser));
              
                
                ;
            }
        });
    
    }    

    @FXML
    private void Modifier_Quiz(ActionEvent event) {
     int id_quiz = Integer.parseInt(fxid_quiz.getText());
     String difficulte = fxdifficulte.getText();
     int score = Integer.parseInt(fxscore.getText());
     promotion id_promotionn = new promotion();
    id_promotionn.setId_promotion(Integer.parseInt(fxid_promotion.getText()));
    
    Utilisateur id_user = new Utilisateur();
    id_user.setid_user(Integer.parseInt(fxid_user.getText()));
    
    quiz q = new quiz(id_quiz,difficulte,score,id_promotionn,id_user);
    QuizCRUD qc = new QuizCRUD();
    qc.modifierquiz(q);
    
   

    // Rafraîchir la liste des promotions
    List<quiz> quizs = qc.afficherQuiz();
    ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
    listview.setItems(observableList);

    }

    @FXML
    private void goback(ActionEvent event) {
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
     private class quizListViewCell extends ListCell<quiz> {

        protected void updateItem(quiz quiz, boolean empty) {
            super.updateItem(quiz, empty);
            if (empty || quiz == null) {
                setText(null);
            } else {
                 setText(String.format("ID Quiz: %d\n",quiz.getId_quiz()) 
                    +String.format("- Difficulté: %s\n", quiz.getDifficulte())
                    + String.format("- Score: %d\n", quiz.getScore())
                    + String.format("- id_promotion: %d\n",quiz.getpromotion().getId_promotion())
                    
                    + String.format("- id user: %d\n", quiz.getuser().getid_user()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}
        
}
