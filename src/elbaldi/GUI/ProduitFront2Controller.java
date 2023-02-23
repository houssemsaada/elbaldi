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
import elbaldi.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ProduitFront2Controller implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
     private List<produit> listeProduit = new ArrayList<>();
    ProduitCRUD ds = new ProduitCRUD();
    @FXML
    private ComboBox<categorie> categoriefx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ListerCategorie();
     afficher();
    }  
    public void afficher(){
        try {
            
            listeProduit = ds.afficherProduit();
            
            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItem.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ProduitItemController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {

        } catch (IOException ex) {
        }
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
