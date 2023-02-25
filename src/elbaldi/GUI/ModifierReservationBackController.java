/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import static elbaldi.GUI.ModifierBonplanBackController.bnp;
import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import elbaldi.services.ReservationCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierReservationBackController implements Initializable {
    
    @FXML
    private Button modifier;
    @FXML
    private Button annuler;
    @FXML
    private TextField nombreR;
    @FXML
    private TextField dateR;
    @FXML
    private TextField statutR;
    @FXML
    private TextField idplanTF;
    @FXML
    private TextField IDuserTF;
    int id_reservation ;
    /**
     * Initializes the controller class.
     */
    
    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
        // TODO
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setCategorie(Reservation r) throws IOException {
        this.nombreR.setText(r.getNombre_personnes()+"");
        this.dateR.setText(r.getDate_reservation()+"");
        this.statutR.setText(r.getStatut_reservation());
        this.idplanTF.setText(r.getBonplan2().getId_bonplan()+"");
        this.IDuserTF.setText(r.getUser2().getid_user()+"");
        this.id_reservation = r.getId_reservation();
        //this.imageBP.setAccessibleText();

        



    }
    @FXML
    private void modifierBP(ActionEvent event) {
        bonplan c = new bonplan();
        int nombre_personnes = Integer.parseInt(nombreR.getText());
        Date date_reservation = Date.valueOf(dateR.getText());
        String statut_reservation = statutR.getText();
        int id_bonplan = Integer.parseInt(idplanTF.getText());
        int id_user = Integer.parseInt(IDuserTF.getText());
        ReservationCrud rc = new ReservationCrud();
        bonplan bp = new bonplan(id_bonplan);
        Utilisateur u = new Utilisateur();
        u.setid_user(id_user);
        Reservation res = new Reservation(nombre_personnes, date_reservation, statut_reservation, bp, u);
        res.setId_reservation(id_reservation);
        rc.modifierReservation(res);
        
        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
        alert0.setTitle("information Dialog");
        alert0.setHeaderText(null);
        alert0.setContentText("Votre modification est enregistrée avec succes ");
        alert0.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    @FXML
    private void prefixe(MouseEvent event) {
    }
    
}
