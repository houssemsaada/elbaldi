package elbaldi.GUI;

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
    private ListView<quiz> promotionListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuizCRUD a= new QuizCRUD();
        List<quiz> quizs = a.afficherQuiz();
        ObservableList<quiz> observableList = FXCollections.observableArrayList(quizs);
        promotionListView.setItems(observableList);
        promotionListView.setCellFactory(promotionListView -> new PromotionListViewCell());
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

    private class PromotionListViewCell extends ListCell<quiz> {

        @Override
        protected void updateItem(quiz quiz, boolean empty) {
            super.updateItem(quiz, empty);
            if (empty || quiz == null) {
                setText(null);
            } else {
                setText(String.format("ID Quiz: %d\n", quiz.getId_quiz())
                    + String.format("- Difficulté: %s\n", quiz.getDifficulte())
                    + String.format("- Score: %d\n", quiz.getScore())
                    + String.format("- ID Promotion: %d\n", quiz.getpromotion().getId_promotion())
                    + String.format("- ID Utilisateur: ", quiz.getuser()));
                setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            }
        }
    }
}
