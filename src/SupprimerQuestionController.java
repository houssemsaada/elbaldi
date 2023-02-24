/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;

import elbaldi.models.question;
import elbaldi.models.quiz;
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
public class SupprimerQuestionController implements Initializable {

    private TextField fxid_question;
    @FXML
    private Button buttonSupprimerQ;
    @FXML
    private Button backfix;
   
    @FXML
    private Text fxdifficulte;
    @FXML
    private Label fxreponse1;
    @FXML
    private Label fxreponse2;
    @FXML
    private Label fxreponse3;
    @FXML
    private Label fxsolutionn;
    @FXML
    private Label fxquiz;
    @FXML
    private ListView<question> listview;
    
    @FXML
    private Label fxquestionn;

    /**
     * Initializes the controller class.
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
        QuestionCRUD a= new QuestionCRUD();
        List<question> questions = a.afficherQuestion();
        ObservableList<question> observableList = FXCollections.observableArrayList(questions);
        listview.setItems(observableList);
       listview.setCellFactory(questionListView -> new questionListViewCell());

       listview.setOnMouseClicked(e -> {
            question selectedQuestion = listview.getSelectionModel().getSelectedItem();
            if ( selectedQuestion != null) {
                // Récupérer les valeurs de l'objet Promotion sélectionné
               // int idQuestion =  selectedQuestion.getId_question();
                String questionn =  selectedQuestion.getQuestionn();
                String Difficulté =  selectedQuestion.getDifficulte();
                String Reponse1 =  selectedQuestion.getReponse1();
                String Reponse2 =  selectedQuestion.getReponse2();
                String Reponse3 =  selectedQuestion.getReponse3();
                 String Solution =  selectedQuestion.getSolution();
                int idQuiz =  selectedQuestion.getquiz().getId_quiz();

               

                // Mettre à jour les champs de texte avec les valeurs récupérées
               // fxidquestion.setText(String.valueOf(idQuestion));
                fxquestionn.setText(questionn);
                fxdifficulte.setText(Difficulté);
                fxreponse1.setText(Reponse1);
                fxreponse2.setText(Reponse2);
                fxreponse3.setText(Reponse3);
                fxsolutionn.setText(Solution);
                fxquiz.setText(String.valueOf(idQuiz));
              
                
                ;
            }
        });
    
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
    
    @FXML
    private void supprimer_Question(ActionEvent event) {
        if (fxquestionn.getText().isEmpty() || fxdifficulte.getText().isEmpty() || fxreponse1.getText().isEmpty() || fxreponse2.getText().isEmpty() || fxreponse3.getText().isEmpty() ||  fxsolutionn.getText().isEmpty() ||  fxquiz.getText().isEmpty()) {
     
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Avertissement");
                  alert.setHeaderText(null);
                  alert.setContentText("Veuillez sélectionner la question à supprimer !");
                  alert.showAndWait();
                 return;
}
        
         
        // Récupérer la promotion sélectionnée
        question selectedPromotion = listview.getSelectionModel().getSelectedItem();
    if (selectedPromotion != null) {
        // Supprimer la promotion
        QuestionCRUD a = new QuestionCRUD();
        a.supprimerquestion(selectedPromotion.getId_question());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression de promotion");
        alert.setHeaderText(null);
        alert.setContentText("La question a été supprimée avec succès !");
        alert.showAndWait();

        // Rafraîchir la liste des promotions
        List<question> questions = a.afficherQuestion();
        ObservableList<question> observableList = FXCollections.observableArrayList(questions);
        listview.setItems(observableList);

        // Effacer les champs de texte
        fxquestionn.setText("");
        fxdifficulte.setText("");
        fxreponse1.setText("");
        fxreponse2.setText("");
        fxreponse3.setText("");
         fxsolutionn.setText("");
         fxquiz.setText("");
    } }

    
    
      private class questionListViewCell extends ListCell<question> {

        @Override
        protected void updateItem(question question, boolean empty) {
            super.updateItem(question, empty);
            if (empty || question == null) {
                setText(null);
            } else {
                  setText(/*String.format("ID Question: %d\n",question.getId_question()) 
                    +*/ String.format("-Question: %s\n", question.getQuestionn())
                    + String.format("- Difficulté: %s\n", question.getDifficulte())
                    + String.format("- Réponse1: %s\n",question.getReponse1())
                    + String.format("- Réponse2: %s\n",question.getReponse2())
                    + String.format("- Réponse3: %s\n",question.getReponse3())
                    + String.format("- Solution: %s\n", question.getSolution())
                    + String.format("- id quiz: %d\n", question.getquiz().getId_quiz()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}
    
}
