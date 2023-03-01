/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProddetailsbackController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label reffx;
    @FXML
    private Label libellefx;
    @FXML
    private Label descfx;
    @FXML
    private Label prixfx;
    @FXML
    private Button modifierfx;
    @FXML
    private Button retourfx;
    private produit produitt;

    /**
     * Initializes the controller class.
     */
    public void setProduit(produit produit) {
        this.produitt = produit;
        libellefx.setText(produit.getLibelle());
         descfx.setText(produit.getDescription());
          reffx.setText(produit.getRef_produit());
        prixfx.setText(produit.getPrix_vente() + " ");
        File f = new File("C:\\xampp\\htdocs\\images\\" + produitt.getImage());

          img.setImage(new Image(f.toURI().toString()));
          

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierProduitBack.fxml"));
        Parent root = loader.load();
        

        ModifierProduitBackController cb = loader.getController();
        cb.setProduit(produitt);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("prodbacklist.fxml"));
        Parent root = loader.load();
        retourfx.getScene().setRoot(root);
    }
    
}
