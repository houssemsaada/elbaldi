/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;


import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.quiz;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuizCRUD;
import elbaldi.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<promotion> fxid_promotion;
    
    @FXML
    private ComboBox<Utilisateur> fxid_user;
    @FXML
    private Button backfix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PromotionCRUD quizCRUD = new PromotionCRUD();
         List<promotion> promotions = quizCRUD.afficherpromotion();
        fxid_promotion.getItems().addAll(promotions);
        
        UtilisateurCRUD u = new UtilisateurCRUD();
        List<Utilisateur> users = u.afficherUtilisateur();
        fxid_user.getItems().addAll(users);
       
    }    

    @FXML
    private void ajouter_Quiz(ActionEvent event) {
    String difficulte = fxdifficulte.getText();
    int score = Integer.parseInt(fxscore.getText());
     
    promotion pro = fxid_promotion.getSelectionModel().getSelectedItem();
    int selectedpromoIdd = pro.getId_promotion();
    
    
    Utilisateur u = fxid_user.getSelectionModel().getSelectedItem();
    int selecteduserId = u.getid_user();
    
    
    quiz q = new quiz(difficulte,score,pro,u);
    QuizCRUD qc = new QuizCRUD();
    qc.ajouterQuiz(q);
    
    // Créer une alerte
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Quiz ajoutée");
    alert.setHeaderText(null);
    alert.setContentText("Le quiz a été ajoutée avec succès.");

    // Afficher l'alerte
    alert.showAndWait();
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
