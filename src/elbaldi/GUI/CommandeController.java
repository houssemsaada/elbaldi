/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.*;
import elbaldi.services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_nom;
    @FXML
    private Button btnvalider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveCommande(ActionEvent event) {
        try {
            int id = Integer.parseInt(tf_id.getText());
            String etat = tf_nom.getText();
            Date date_com = Date.valueOf(tf_prenom.getText());
            Utilisateur u = new Utilisateur(id);
            commande com = new commande(u, etat, date_com);
            CommandeCRUD cc = new CommandeCRUD();
            cc.ajouterCommande(com);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detailscommande.fxml")); 
            Parent root = loader.load() ;
            DetailscommandeController dcc = loader.getController();
            dcc.setTduser(""+u.getid_user());
            dcc.setTfdate(""+com.getDate_cmd());
            dcc.setTfetat(com.getEtat());
            tf_id.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
        
    }

}
