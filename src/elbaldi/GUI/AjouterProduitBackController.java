/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterProduitBackController implements Initializable {

    @FXML
    private TextField reffx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       reffx.setText("TUN61900");
    }    

    @FXML
    private void prefixe(MouseEvent event) {
        
        //String referenceProduit = produit.getReferenceProduit();
        //reffx.setText("TUN61900" + referenceProduit);
    }
    
    
}
