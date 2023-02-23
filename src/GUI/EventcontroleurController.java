/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import elbaldi.models.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import elbaldi.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventcontroleurController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label date_debut_label;
    @FXML
    private Label date_fin_label;
    @FXML
    private Button supprimerEventbtn;
    @FXML
    private Button modifierEvenbtn;

    EvenementService es = new EvenementService();
    @FXML
    private Label id_label;
    @FXML
    private Button participerbtn;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setEvenement(Evenement  E) {
     
       nomLabel.setText(E.getNom());
        date_debut_label.setText(E.getDate_debut() + " \n " + E.getDate_fin());
        date_fin_label.setText(E.getDescription());
        
      id_label.setText(String.valueOf(E.getId_event()));
      
       
    }
//     public int getIdEvenement(Evenement E){
//         
//         return E.getId_event();
//     }

    @FXML
    private void supprimerEvenement(ActionEvent event ) throws IOException {
       
   Evenement e =new Evenement();
    e.setId_event(Integer.parseInt(id_label.getText()));
        try {
            es.supprimer(e);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);

              alert.setTitle("Information Dialog");

              alert.setHeaderText(null);

              alert.setContentText("Evenement supprimer avec succés!");

              alert.show();
            Parent loader = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
            nomLabel.getScene().setRoot(loader);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierEvenement(ActionEvent event) throws IOException {
        Evenement e =new Evenement();
    e.setId_event(Integer.parseInt(id_label.getText()));
    Parent loader = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
    
            nomLabel.getScene().setRoot(loader);
            
    
        
    }

    @FXML
    private void ParticiperEvent(ActionEvent event) throws IOException {
        
            
            try {

            Parent loader = FXMLLoader.load(getClass().getResource("Participation.fxml"));
           nomLabel.getScene().setRoot(loader);
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    
    }
}
