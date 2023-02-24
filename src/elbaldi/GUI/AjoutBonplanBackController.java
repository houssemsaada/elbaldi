/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Upload2;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutBonplanBackController implements Initializable {

    @FXML
    private TextField titreBP;
    @FXML
    private TextArea descriptionBP;
    private Button Ajout;
    @FXML
    private ListView imageBP;
    @FXML
    private Button insérer;
    File selectedfile;

    String path_img;

    Upload2 u = new Upload2();
    @FXML
    private Button annuler;
    @FXML
    private Button ajouterBTN;
    @FXML
    private TextField typeF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        Ajout.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//             //    int id_user = Integer.parseInt(num.getText());
//
//                        bonplan bp = new bonplan(titreBP.getText(),
//                        descriptionBP.getText(), 
//                        typeBp.getText(),
//                        selectedfile.getName());
//              //  bp.setImage_bonplan(selectedfile.getName());
//
//                BonplanCrud bonplan = new BonplanCrud();
//                try {
//                    if (!titreBP.getText().equalsIgnoreCase("") && !descriptionBP.getText().equalsIgnoreCase("") && !typeBp.getText().equalsIgnoreCase("")) {
//                        bonplan.ajouterBonplan(bp);
//                        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
//                        alert0.setTitle("information Dialog");
//                        alert0.setHeaderText(null);
//                        alert0.setContentText("Ajout avec succes ");
//                        alert0.show();
//                        ((Node) event.getSource()).getScene().getWindow().hide();
//                    } else {
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Echec de la modification");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
//                        alert.showAndWait();
//                    }
//                } catch (SQLException ex)
//                {
//                    Logger.getLogger(AjoutBonplanBackController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//           
//         }
//);
//         annuler.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event) {

//            
//            }
//            });
    }

    @FXML
    private void ajouterBonPlan(ActionEvent event) {
        try {

            try {
                if (titreBP.getText().equalsIgnoreCase("") && descriptionBP.getText().equalsIgnoreCase("") && typeF.getText().equalsIgnoreCase("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de l'ajout");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            
            String titre_bonplan = titreBP.getText();
               
            String Description_bonplan = descriptionBP.getText();
                
            String type = typeF.getText();
                
            if (selectedfile != null) {
                String file = selectedfile.getName();
                System.out.println(file);
               BonplanCrud bonplan = new BonplanCrud();
            bonplan bp = new bonplan(titre_bonplan, Description_bonplan, type, file);
            bonplan.ajouterBonplan(bp);} 
            else {
                BonplanCrud bonplan = new BonplanCrud();
            bonplan bp = new bonplan(titre_bonplan, Description_bonplan, type); 
             bonplan.ajouterBonplan(bp); }
            
               
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause(); }
       }

        @FXML
        private void prefixe
        (MouseEvent event
        
        
        
        
        ) {
    }

    @FXML
        private void insererOnClick(ActionEvent event) {
       

                FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
            );
            selectedfile = fc.showOpenDialog(null);
            if (selectedfile != null) {

                Upload2 u = new Upload2();
                try {
                    u.upload(selectedfile);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutBonplanBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                imageBP.getItems().add(selectedfile.getName());

                path_img = selectedfile.getAbsolutePath();

            } else {
                System.out.println("Fichier erroné");
            }

        }

    @FXML
    private void AnnulerOnClick(ActionEvent event) throws IOException {
        titreBP.clear();
        descriptionBP.clear();
        typeF.clear();
        imageBP.getItems().clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherBonplan.fxml"));
        Parent root = loader.load();
        annuler.getScene().setRoot(root);
    }

    }
