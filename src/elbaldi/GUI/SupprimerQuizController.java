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
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class SupprimerQuizController implements Initializable {

    @FXML
    private Label fxid_quiz;
    @FXML
    private Button buttonSupprimerQ;
    @FXML
    private Button backfix;
    @FXML
    private Label difffix;
    @FXML
    private Text scorefix;
    @FXML
    private Text promofix;
    @FXML
    private Text iduserfix;
  
    @FXML
    private ListView<quiz> list;

    /**
     * Initializes the controller class.
     */
    private class quizListViewCell extends ListCell<quiz> {

        @Override
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // TODO
        QuizCRUD a= new QuizCRUD();
        List<quiz> quizs = a.afficherQuiz();
        ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
        list.setItems(observableList);
       list.setCellFactory(questionListView -> new quizListViewCell());

       list.setOnMouseClicked(e -> {
            quiz selectedQuestion = list.getSelectionModel().getSelectedItem();
            if ( selectedQuestion != null) {
                // Récupérer les valeurs de l'objet Promotion sélectionné
            //    int idquiz =  selectedQuestion.getId_quiz();  
                String Difficulté =  selectedQuestion.getDifficulte();
                int score =  selectedQuestion.getScore();  
                int iduser =  selectedQuestion.getuser().getid_user();  
                int idpromotion =  selectedQuestion.getpromotion().getId_promotion();

    

                // Mettre à jour les champs de texte avec les valeurs récupérées
                //fxid_quiz.setText(String.valueOf(idquiz));
                difffix.setText(Difficulté);
                scorefix.setText(String.valueOf(score));
                promofix.setText(String.valueOf(idpromotion));
               
                iduserfix.setText(String.valueOf(iduser));
              
                
                ;
            }
        });
    }    

    @FXML
    private void supprimer_Quiz(ActionEvent event) {
        /////////////////////////////////////////////////////////////////////////////
     /*int id_quiz = Integer.parseInt(fxid_quiz.getText());
     String difficulte = difffix.getText();
     int score = Integer.parseInt(scorefix.getText());
     promotion id_promotionn = new promotion();
    id_promotionn.setId_promotion(Integer.parseInt(promofix.getText()));
    
    Utilisateur id_user = new Utilisateur();
    id_user.setid_user(Integer.parseInt(iduserfix.getText()));
    
    
    QuizCRUD qc = new QuizCRUD();
    qc.supprimerquiz(score);
    */
        
        
        if (difffix.getText().isEmpty() || scorefix.getText().isEmpty() || promofix.getText().isEmpty() || iduserfix.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Il faut sélectionner les champs à supprimer!!!");
        alert.showAndWait();
        return;
    }
        
        
        
        // Récupérer la promotion sélectionnée
   quiz selectedPromotion = list.getSelectionModel().getSelectedItem();
    if (selectedPromotion != null) {
        // Supprimer la promotion
        QuizCRUD a = new QuizCRUD();
        a.supprimerquiz(selectedPromotion.getId_quiz());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Suppression du Quiz");
      alert.setHeaderText(null);
      alert.setContentText("Le quiz a été supprimée avec succès !");
      alert.showAndWait();
    

        // Rafraîchir la liste des promotions
        List<quiz> quizs = a.afficherQuiz();
        ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
        list.setItems(observableList);

        // Effacer les champs de texte
        fxid_quiz.setText("");
        difffix.setText("");
        scorefix.setText("");
        promofix.setText("");
        iduserfix.setText("");
    } 
        
        
        
        
        
        
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
