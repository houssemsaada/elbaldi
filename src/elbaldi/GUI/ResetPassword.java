package elbaldi.GUI;

import elbaldi.models.SharedData;
import elbaldi.models.Utilisateur;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ResetPassword {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView anchorPane;

    @FXML
    private Button btnid;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField newPassword1;
    private Utilisateur u;

    @FXML
    void ResetPassword(ActionEvent event) throws IOException {

        String newMdp = newPassword.getText();
        System.out.println(newMdp);
        String confirmMdp = newPassword1.getText();
        if (newMdp.equals(confirmMdp)) {
            UtilisateurCRUD us = new UtilisateurCRUD();
            Utilisateur utilisateur = us.GetUserByMailE(SharedData.data);
            utilisateur.setMdp(newMdp);
            us.UpdateUserPassword(utilisateur);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            btnid.getScene().setRoot(root);

        }
    }

    @FXML
    private void mini(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void max(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setFullScreen(true);
    }

    @FXML
    private void close(javafx.scene.input.MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

}
