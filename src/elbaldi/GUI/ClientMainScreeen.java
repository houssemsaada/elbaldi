package elbaldi.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMainScreeen {


    @FXML
    private GridPane grid;

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Button Quizfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button profilfx;
    @FXML
    private Button prodfx;
    @FXML
    private Button bonplanfx;
    @FXML
    private Button decofx;

    void ProfileUser(ActionEvent event) {
//        grid.getChildren().clear();
//        try {
//            // TODO
//            FXMLLoader cards = new FXMLLoader();
//            cards.setLocation(getClass().getResource("ProfileUser.fxml"));
//
//            AnchorPane anchorPane = cards.load();
//
//            grid.add(anchorPane, 1, 1);
//
//            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }


    @FXML
    private void QuizAction(ActionEvent event) {
    }

    @FXML
    private void profilAction(ActionEvent event) {
         grid.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ProfileUser.fxml"));

           AnchorPane anchorPane = cards.load();

           grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
 
    }

    @FXML
    private void produitsf(ActionEvent event) {
    }

    @FXML
    private void bonplanAction(ActionEvent event) {
    }

    @FXML
    private void EventAction(ActionEvent event) {
    }

    @FXML
    private void deconfx(ActionEvent event) {
    }

}
