package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AfficherQuizController implements Initializable {

    @FXML
    private Text fxafficherquiz;
    @FXML
    private Button backfix;
    
    @FXML
    private ListView<quiz> listview;

    
     private class quizListViewCell extends ListCell<quiz> {

        protected void updateItem(quiz quiz, boolean empty) {
            super.updateItem(quiz, empty);
            if (empty || quiz == null) {
                setText(null);
            } else {
                 setText(/*String.format("ID Quiz: %d\n",quiz.getId_quiz()) 
                    + */String.format("- Difficulté: %s\n", quiz.getDifficulte())
                    + String.format("- Score: %d\n", quiz.getScore())
                    + String.format("- id_promotion: %s\n",quiz.getpromotion())
                    
                    + String.format("- id user: %s\n", quiz.getuser()));
            setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        QuizCRUD a= new QuizCRUD();
        List<quiz> quizs = a.afficherQuiz();
        ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
        listview.setItems(observableList);
       listview.setCellFactory(questionListView -> new quizListViewCell());

     
    }

    @FXML
    private void goback(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
