/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;


import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.quiz;
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
public class AjouterQuizController implements Initializable {

    @FXML
    private Button ajoutQuiz;
    @FXML
    private TextField fxdifficulte;
    @FXML
    private TextField fxscore;
    @FXML
    private TextField fxid_promotion;
    
    @FXML
    private TextField fxid_user;
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
    private void ajouter_Quiz(ActionEvent event) {
    String difficulte = fxdifficulte.getText();
     int score = Integer.parseInt(fxscore.getText());
     
    promotion id_promotionn = new promotion();
    id_promotionn.setId_promotion(Integer.parseInt(fxid_promotion.getText()));
    
    Utilisateur id_user = new Utilisateur();
    id_user.setid_user(Integer.parseInt(fxid_user.getText()));
    
    quiz q = new quiz(difficulte,score,id_promotionn,id_user);
    QuizCRUD qc = new QuizCRUD();
    qc.ajouterQuiz(q);
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
