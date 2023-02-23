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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class AjouterQuestionController implements Initializable {

    @FXML
    private Button ajoutQuestion;
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
    private TextField fxsolution;
    @FXML
    private TextField fxid_quiz;
    @FXML
    private Button backfix;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_Question(ActionEvent event) {
    String difficulte = fxdifficulte.getText();
    String questionn = fxquestion.getText();
    String reponse1 = fxreponse1.getText();
    String reponse2 = fxreponse2.getText();
    String reponse3 = fxreponse3.getText();
    String solution = fxsolution.getText(); 
    quiz id_quiz = new quiz();
    id_quiz.setId_quiz(Integer.parseInt(fxid_quiz.getText()));
    
    question q = new question(difficulte,questionn,reponse1,reponse2,reponse3,solution,id_quiz);
    QuestionCRUD qc = new QuestionCRUD();
    qc.ajouterQuestion(q);
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
    

