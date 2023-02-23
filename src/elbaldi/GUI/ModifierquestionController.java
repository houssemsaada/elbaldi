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
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
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
    private TextField fxquiz;
    @FXML
    private Button modifierquestionfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
      int id_question = Integer.parseInt(fxidquestion.getText());
      String question = fxquestion.getText();
      String difficulte = fxdifficulte.getText();
      String Reponse1 = fxreponse1.getText();
      String Reponse2 = fxreponse2.getText();
      String Reponse3 = fxreponse3.getText();
      String solution = fxsolutionn.getText(); 
     quiz id_quiz = new quiz();
     id_quiz.setId_quiz(Integer.parseInt(fxquiz.getText()));
     
    
    question q = new question(id_question, difficulte, question, Reponse1,Reponse2, Reponse3, solution, id_quiz);
    QuestionCRUD qc = new QuestionCRUD();
    qc.modifierquestion(q);
    }
    
}
