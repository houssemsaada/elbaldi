/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class JouerQuizController implements Initializable {

    @FXML
    private Text fixquestion;
    @FXML
    private RadioButton fixreponse1;
    @FXML
    private RadioButton fixreponse2;
    @FXML
    private RadioButton fixreponse3;
    
    @FXML
    private ToggleGroup tunisie;
    
    @FXML
    private Button fixsuivante;

    @FXML
    private Label numerofx;

    private ArrayList<question> questions; // Liste de questions
    private int currentQuestionIndex; // Index de la question courante
    private ArrayList<String> reponsesUtilisateur; // Liste des réponses de l'utilisateur
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Récupérer les 4 questions à partir de la source de données
        QuestionCRUD qc = new QuestionCRUD();
        question c = new question();
        questions = new ArrayList<>();
        
        questions.add(qc.getById(118));
        questions.add(qc.getById(119));
        questions.add(qc.getById(120));
        questions.add(qc.getById(122));
         questions.add(qc.getById(123));
       
 
        // Mélanger la liste de questions
        Collections.shuffle(questions);

        // Initialiser l'index de la question courante et la liste des réponses de l'utilisateur
        currentQuestionIndex = 0;
        reponsesUtilisateur = new ArrayList<>();

        // Afficher la première question
        
        afficherQuestion();
        int numQuestionn = 1 ;
            int totalQuestions = questions.size();
            numerofx.setText("Question 1/" + totalQuestions );
        
    }

    @FXML
    private void suivante() {
        // Enregistrer la réponse de l'utilisateur
        String reponse = null;
        if (fixreponse1.isSelected()) {
            reponse = fixreponse1.getText();
        } else if (fixreponse2.isSelected()) {
            reponse = fixreponse2.getText();
        } else if (fixreponse3.isSelected()) {
            reponse = fixreponse3.getText();
        }
        reponsesUtilisateur.add(reponse);
        
        // Vérifier si une réponse a été sélectionnée
        if (tunisie.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner une réponse", ButtonType.OK);
            alert.showAndWait();
            return;
        }
   
        
        // Passer à la question suivante 
        if (currentQuestionIndex < questions.size() -1) {  
            currentQuestionIndex++;
            afficherQuestion();
            int numQuestion = currentQuestionIndex+1 ;
            int totalQuestions = questions.size();
            numerofx.setText("Question "+ numQuestion +"/" + totalQuestions );
        } 
        else if (currentQuestionIndex == questions.size()-1) {
                // Afficher la classe ScoreControlleur pour afficher le score
        
            try {
               Parent root = FXMLLoader.load(getClass().getResource("Score.fxml"));
               Scene scene = new Scene(root);
        Stage stage = (Stage) fixsuivante.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
            } catch (IOException ex) {
                
            }
                }
        
        
        
       
        
   
        }    
    
    private void afficherQuestion() {
        // Récupérer la question courante
        question q = questions.get(currentQuestionIndex);

        // Afficher la question et les réponses
        fixquestion.setText(q.getQuestionn());
        fixreponse1.setText(q.getReponse1());
        fixreponse2.setText(q.getReponse2());
        fixreponse3.setText(q.getReponse3());

        int lastQuestionIndex = questions.size() - 1;
              if (lastQuestionIndex == currentQuestionIndex) {
                    fixsuivante.setText("Voir Score");
              } else {
                    fixsuivante.setText("Question suivante");
                      }


        
        // Désélectionner les boutons radio
        tunisie.selectToggle(null);
    }

    


    
}
      


    

