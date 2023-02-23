/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.Upload;
import elbaldi.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterProduitBackController implements Initializable {

    @FXML
    private TextField reffx;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button Ajouterfx;
    @FXML
    private ListView img;
    @FXML
    private Button insérer;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private TextField prixfx;
    @FXML
    private Button annulerfx;
    @FXML
    private ComboBox<categorie> categoriefx;
    @FXML
    private Button back;
    @FXML
    private TextField libellefx;
    @FXML
    private TextField quantitefx;
      File selectedfile;
    
    String path_img;
 
    Upload u = new Upload();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //reffx.setText("TUN61900");
       
       ListerCategorie();
        insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {

                    Upload u = new Upload();
                    try {
                        u.upload(selectedfile);
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    img.getItems().add(selectedfile.getName());

                    path_img = selectedfile.getAbsolutePath();

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
         Ajouterfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                categorie c = categoriefx.getSelectionModel().getSelectedItem();
                
                int prixx = Integer.parseInt(prixfx.getText());
                int quantite = Integer.parseInt(quantitefx.getText());
               
                
                produit pp= new produit(reffx.getText(), libellefx.getText(),descriptionfx.getText(),
                       selectedfile.getName(), prixx,quantite,c);
               
                ProduitCRUD PROD = new ProduitCRUD();
                try {
                    PROD.ajouterProduit(pp);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Ajout avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
                

            }
        });
           annulerfx.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                reffx.clear();
                libellefx.clear();
                descriptionfx.clear();
              prixfx.clear();
              quantitefx.clear();            
               categoriefx.getItems().clear();
               img.getItems().clear();
               
            }
            });
    }    

    @FXML
    private void prefixe(MouseEvent event) {
        reffx.setText("TUN61900");
        //String referenceProduit = produit.getReferenceProduit();
        //reffx.setText("TUN61900" + referenceProduit);
    }
      private void ListerCategorie() {

        CategorieCRUD categoriecrud = new CategorieCRUD();
        ObservableList<categorie> list = FXCollections.observableArrayList();
        try {
            String req = " select id_categorie,`nom_categorie`,`description` from `categorie`  ";
            
           Connection conn = MyConnection.getInstance().getConn();

            PreparedStatement pst=conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                categorie c = new categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }
   
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        categoriefx.setItems(null);
        categoriefx.setItems(list);
    }
 
}
