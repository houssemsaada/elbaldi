package elbaldi.GUI;

import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.services.UtilisateurCRUD;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private CheckBox remember_me;

    UtilisateurCRUD userService = new UtilisateurCRUD();

    private final String path = "src\\LoginData.ini";
    @FXML
    private PasswordField PasswordLogin;

    @FXML
    private StackPane parentContainer;

    @FXML
    private TextField emailLogin;
    @FXML
    private Button Register_button;

    @FXML
    private ImageView anchorPane;

    @FXML
    private Hyperlink fp_hyperlink;

    @FXML
    private Button login_button;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void attempteToLogin(ActionEvent event) throws IOException {
        if (event.getSource() == login_button) {
            if (validatenGuest_Email(emailLogin) & validatenGuest_Password(PasswordLogin)) {

                Utilisateur utilisateur = userService.GetUserByMail(emailLogin.getText(), PasswordLogin.getText());
                //UserService.userSession = new UserSession();

                Role role = utilisateur.getRole();
                if (null != role) {
                    switch (role) {
                        case client:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                                //UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("ClientMainScreeen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                userService.createiniFile(path, emailLogin.getText(), PasswordLogin.getText());
                                //UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("ClientMainScreeen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
                            break;
                        case gerant:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                               // UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("GerantMainScreen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                userService.createiniFile(path, PasswordLogin.getText(), PasswordLogin.getText());
                               // UserService.userSession.setUserCin(user.getCin());
                               // System.out.println(UserSession.userString);
                                Parent root = FXMLLoader.load(getClass().getResource("GerantMainScreen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            }
                            break;
                        case admin:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                                //UserService.userSession = new UserSession();
                               // UserService.userSession.setUserCin(user.getCin());
                                //System.out.println(UserService.userSession.getUser());
                                Parent root = FXMLLoader.load(getClass().getResource("AdminMainScreen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                //userService.createiniFile(path, emailtxt.getText(), passwordtxt.getText());
                               // UserService.userSession = new UserSession();
                               // UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("AdminMainScreen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            }
                            break;
                        case livreur:
                            if (!remember_me.isSelected()) {
                                userService.Deleteinfo(path, path, path);
                                // UserService.userSession.setUserCin(user.getCin());
                                Parent root = FXMLLoader.load(getClass().getResource("LivreurMainScreen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } else if (remember_me.isSelected()) {
                                userService.createiniFile(path, PasswordLogin.getText(), PasswordLogin.getText());
                                // UserService.userSession.setUserCin(user.getCin());
                                // System.out.println(UserSession.userString);
                                Parent root = FXMLLoader.load(getClass().getResource("LivreurMainScreen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            }
                            break;


                        default:
                            break;
                    }
                }
            }
        }
    }

    private boolean validatenGuest_Email(TextField email) {

        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email.getText());
        if (((email.getText().length() > 8))
                && (m.find() && m.group().equals(email.getText()))) {
            email.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(email).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            email.setEffect(in);
            return false;
        }

    }

    private boolean validatenGuest_Password(TextField password) {

        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(password.getText());
        if (((password.getText().length() > 7))
                && (m.find() && m.group().equals(password.getText()))) {
            password.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(password).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            password.setEffect(in);
            return false;
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

    @FXML
    void forgetPassword(ActionEvent event) {

    }


    @FXML
    void registerClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.translateYProperty().set(stage.getHeight());
            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event2) -> {
                parentContainer.getChildren().remove(rootPane);

            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ;
        }
    }

    @FXML
    void signUp(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
