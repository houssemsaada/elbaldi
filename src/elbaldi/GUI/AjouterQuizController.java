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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    private ComboBox<String> fxdifficulte;
    
   
    
    private ComboBox<Utilisateur> fxid_user;
    @FXML
    private Button backfix;
    @FXML
    private TextField fxnom;
    @FXML
    private Text imgfx;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        
        
       ObservableList<String> difficultes = FXCollections.observableArrayList("Facile", "Moyenne", "Difficile");
        fxdifficulte.setItems(difficultes);
    }    

    @FXML
    private void ajouter_Quiz(ActionEvent event) {
       
         String difficulte = fxdifficulte.getValue(); 

     String nom = fxnom.getText();
     String img = imgfx.getText();
  if (difficulte == null || difficulte.isEmpty() || nom.isEmpty() ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs nécessaires.");
        alert.showAndWait();
        return;
    }
    
    
  
     
    
    quiz q = new quiz(nom,difficulte);
    QuizCRUD qc = new QuizCRUD();
     if (qc.quizExiste(nom)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le nom du quiz est déjà utilisé. Veuillez choisir un autre nom.");
        alert.showAndWait();
        return;}
     
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("front.fxml"));
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