/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProduitItemController implements Initializable {

    @FXML
    private ImageView imagefx;
    
    @FXML
    private Label prixfx;
    @FXML
    private Button detailsfx;
    @FXML
    private Label libellefx;
    private produit produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    
       public void setData(produit pr) {
        this.produit = pr;
        this.libellefx.setText(produit.getLibelle());
        this.prixfx.setText(String.valueOf(produit.getPrix_vente()));
//        Image img = new Image(getClass().getResourceAsStream("..\\..\\..\\..\\..\\..\\Desktop\\1.png"));
//        image.setImage(img);
        File f = new File(" C:\\Users\\USER\\Desktop\\3CRUD\\elbaldi\\src\\Ressources\\" + produit.getImage());

        imagefx.setImage(new Image(f.toURI().toString()));

    }

    @FXML
    private void details(ActionEvent event) {
    }
      
   
}
