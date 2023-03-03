package elbaldi.GUI;

import com.twilio.type.PhoneNumber;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Code {

    public int x;
    public String y, z;
    public String username, pass, mesg, email, code;

    Stage stage = new Stage();


    UtilisateurCRUD userService = new UtilisateurCRUD();

    @FXML
    private ImageView anchorPane;

    @FXML
    private Button btnid;

    @FXML
    private TextField mailid;

    @FXML
    private void SendMail(ActionEvent event) throws AddressException, MessagingException, IOException, SQLException {

        if (mailid.getText().equals(this.code)) {
            System.out.println(mailid);
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("resetpassword.fxml")));
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Code Invalide");
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
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
        System.out.println("====>" + code);
    }
}
