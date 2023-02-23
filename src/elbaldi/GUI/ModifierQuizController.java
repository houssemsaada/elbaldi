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
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuestionCRUD;
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
import javafx.scene.control.Label;
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
    private ComboBox<promotion> fxid_promotion;
    @FXML
    private ComboBox<Utilisateur> fxid_user;
    @FXML
    private Button backfix;
    @FXML
    private ListView<quiz> listview;
    @FXML
    private Label fxid_quiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
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
                //fxid_promotion.setText(String.valueOf(idpromotion));
               
                //fxid_user.setText(String.valueOf(iduser));
                 fxid_promotion.setValue(selectedQuestion.getpromotion());
                 fxid_user.setValue(selectedQuestion.getuser());
              
                
                ;
            }
        });
    
       // Charger la liste des promotions dans le ComboBox a partir de la base de donée
        PromotionCRUD pc = new PromotionCRUD();
        List<promotion> promotions = pc.afficherpromotion();
        ObservableList<promotion> observableListPromotion = FXCollections.observableArrayList(promotions);
        fxid_promotion.setItems(observableListPromotion);
       
        // Charger la liste des utilisateurs dans le ComboBox a partir de la base de donée
        UtilisateurCRUD uc = new UtilisateurCRUD();
        List<Utilisateur> utilisateurs = uc.afficherUtilisateur();
        ObservableList<Utilisateur> observableListUtilisateurs = FXCollections.observableArrayList(utilisateurs);
        fxid_user.setItems(observableListUtilisateurs);
       
    }    

    @FXML
    private void Modifier_Quiz(ActionEvent event) {
        
        if (fxdifficulte.getText().isEmpty() || fxscore.getText().isEmpty() || fxid_promotion.getValue()== null || fxid_user.getValue()== null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Il faut sélectionner les champs à modifier!!!");
        alert.showAndWait();
        return;
    }
        
        
        
     int id_quiz = Integer.parseInt(fxid_quiz.getText());
     String difficulte = fxdifficulte.getText();
     int score = Integer.parseInt(fxscore.getText());     
     promotion id_promotionn = fxid_promotion.getValue();
    Utilisateur id_user = fxid_user.getValue();
   
    
    
    
    quiz q = new quiz(id_quiz,difficulte,score,id_promotionn,id_user );
    QuizCRUD qc = new QuizCRUD();
    qc.modifierquiz(q);
    
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Modification de Quiz");
      alert.setHeaderText(null);
      alert.setContentText("Le quiz a été modifiée avec succès !");
      alert.showAndWait();
    
    
   

    // Rafraîchir la liste des promotions
    List<quiz> quizs = qc.afficherQuiz();
    ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
    listview.setItems(observableList);

    }

    @FXML
    private void goback(ActionEvent event) {
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
