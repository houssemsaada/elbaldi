/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.services.CategorieCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CategorieBackModifierController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField nom;
    @FXML
    private TextArea descriptionC;
    @FXML
    private Button back;
    @FXML
    private Button annuler;
    public static categorie categ;
    @FXML
    private Button updateCat;

    /**
     * Initializes the controller class.
     */
       public void setCategorie(categorie c) {
        
        categ = c;
        this.nom.setText(categ.getNom_categorie());
        this.descriptionC.setText(categ.getDescription());
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         updateCat.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                CategorieCRUD categoriecrud = new CategorieCRUD();
                categorie c = new categorie();
 if (!nom.getText().equalsIgnoreCase("") && !descriptionC.getText().equalsIgnoreCase("")) {
                c.setNom_categorie(nom.getText());
                c.setDescription(descriptionC.getText());
               
                try {
                    categoriecrud.modifierCategorie(c,categ.getId_categorie());

                }
                catch (SQLException ex) {
                    Logger.getLogger(CategorieBackModifierController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Votre modification est enregistrée avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
 }
                else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Echec de la modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                        alert.showAndWait();
 }
            }

        });
    }    
    
}
