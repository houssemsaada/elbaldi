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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import elbaldi.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField nom;

   

    public void setId(String id) {
        this.id.setText(id);
    }
    @FXML
    private TextField awards;
    @FXML
    private TextField description;
    @FXML
    private DatePicker mydatedebut;
    @FXML
    private DatePicker mydatefin;
    
    EvenementService es = new EvenementService();
    @FXML
    private Button updatebtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
 
    
    }    

    @FXML
    private void ajouterEvenement(ActionEvent event) {
        

          LocalDate date_debut,date_fin;
          date_debut = mydatedebut.getValue();
          date_fin = mydatefin.getValue();
          Evenement e = new Evenement();
          e.setNom(nom.getText());
          e.setDate_debut(date_debut.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setDate_fin(date_fin.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setAwards(awards.getText());
          e.setDescription(description.getText());
          
          
           try {
               es.ajouter(e);
               Alert alert = new Alert(AlertType.INFORMATION);

              alert.setTitle("Information Dialog");

              alert.setHeaderText(null);

              alert.setContentText("Evenement insérée avec succés!");

              alert.show();
               reset();
               
           } catch (SQLException ex) {
                
            System.out.println(ex.getMessage());
      }
    }
     private void reset() {
        nom.setText("");     
        awards.setText("");
        description.setText("");
    } 
    @FXML
    private void AfficheEvenement(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
            nom.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierEvenemet(ActionEvent event) {
        LocalDate date_debut,date_fin;
          date_debut = mydatedebut.getValue();
          date_fin = mydatefin.getValue();
          Evenement e = new Evenement();
          e.setNom(nom.getText());
          e.setDate_debut(date_debut.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setDate_fin(date_fin.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setAwards(awards.getText());
          e.setDescription(description.getText());
          e.setId_event(Integer.parseInt(id.getText()));
           try {
               
               es.modifier(e);
               Alert alert = new Alert(AlertType.INFORMATION);

              alert.setTitle("Information Dialog");

              alert.setHeaderText(null);

              alert.setContentText("Evenement Modifier avec succés!");

              alert.show();
               reset();
               
           } catch (SQLException ex) {
            System.out.println(ex.getMessage());
      }
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        
      Evenement e = new Evenement();
      e.setId_event(Integer.parseInt(id.getText()));
      try{
           es.supprimer(e);
           Alert alert = new Alert(AlertType.INFORMATION);

              alert.setTitle("Information Dialog");

              alert.setHeaderText(null);

              alert.setContentText("Evenement Supprimer avec succés!");

              alert.show();
      }catch(SQLException ex )
      {
          System.out.println(ex.getMessage());
      }
        
        
    }
        
    }


    

