package elbaldi.GUI;

import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.services.UserSession;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfileAdmin implements Initializable {


    @FXML
    private TextField adminpageaddress;

    @FXML
    private DatePicker adminpagedate;

    @FXML
    private TextField adminpagelastname;

    @FXML
    private TextField adminpagename;

    @FXML
    private TextField adminpagetel;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Button updateadmin;

    void image_admin(MouseEvent event) {

    }

   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserSession userSession = new UserSession();
        UtilisateurCRUD userService = new UtilisateurCRUD();
        LocalDate ddn = userSession.getUser().getDateNaissance().toLocalDate();
        adminpagename.setText(userSession.getUser().getNom());
        adminpagelastname.setText(userSession.getUser().getPrenom());
        adminpagetel.setText(String.valueOf(userSession.getUser().getNumTel()));
        adminpageaddress.setText(userSession.getUser().getVille());
        adminpagedate.setValue(ddn);
    }
     @FXML
    void updtahost(ActionEvent event) {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(adminpagedate.getValue());
        UtilisateurCRUD u = new UtilisateurCRUD();
        UserSession session = new UserSession();
        int id = session.getUser().getid_user();
        String email = session.getUser().getEmail();

        String name = adminpagename.getText();
        String lastname = adminpagelastname.getText();
        int tel = Integer.parseInt(adminpagetel.getText());
        String ville = adminpageaddress.getText();
        Utilisateur user = new Utilisateur(Integer.parseInt(String.valueOf(id)),name, lastname,email, gettedDatePickerDate, tel, ville );
        u.UpdateUser(user);

    }

}
