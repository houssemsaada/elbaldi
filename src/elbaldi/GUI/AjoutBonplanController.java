/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author user
 */
public class AjoutBonplanController implements Initializable {

    @FXML
    private TextField TFid;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfImage;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddBonplan(ActionEvent event) {
        int id_bonplan = Integer.parseInt(TFid.getText());
        String titre_bonplan = tfTitre.getText();
        String description_bonplan = tfDescription.getText();
        String type_bonplan = tfType.getText();
        String image_bonplan = tfImage.getText();
        
        bonplan B = new bonplan(id_bonplan, titre_bonplan, description_bonplan, type_bonplan, image_bonplan);
        BonplanCrud pc = new BonplanCrud();
        pc.ajouterBonplan(B);
        
    
}
}