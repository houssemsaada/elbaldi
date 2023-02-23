/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;


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
public class SupprimerQuizController implements Initializable {

    @FXML
    private TextField fxid_quiz;
    @FXML
    private Button buttonSupprimerQ;
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
    private void supprimer_Quiz(ActionEvent event) {
        int id_quiz = Integer.parseInt(fxid_quiz.getText());
           
       
        QuizCRUD qc = new QuizCRUD();
        qc.supprimerquiz(id_quiz);
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
