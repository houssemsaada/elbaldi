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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProdbacklistController implements Initializable {

    @FXML
    private GridPane grid;
    private List<produit> listeProduit = new ArrayList<>();
    ProduitCRUD ds = new ProduitCRUD();
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button ajoutfx;
    @FXML
    private Button showC;
    @FXML
    private Button Home1;
    @FXML
    private Button categoriefx;
    @FXML
    private Button produitfx;
    @FXML
    private Button comm1;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button btnSignout111;
    @FXML
    private Button btnSignout1111;
    @FXML
    private Button btnSignout11111;
    @FXML
    private Button btnSignout1111111;
    @FXML
    private Button btnSignout11111111;
    @FXML
    private Button btnSignout111111111;
    @FXML
    private Button btnSignout11112;
    @FXML
    private Button btnSignout11;
    @FXML
    private Button btnSignout1111211;
    @FXML
    private Button btnSignout11112111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }

    public void afficher() {
        try {
            grid.getChildren().remove(0, listeProduit.size());

            listeProduit = ds.afficherProduit();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itemprodback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItemprodbackController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {

        } catch (IOException ex) {
        }
    }


    @FXML
    private void prodd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("prodbacklist.fxml"));
        Parent root = loader.load();
        produitfx.getScene().setRoot(root);
    }

    @FXML
    private void commen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("CommentaireBack.fxml"));
        Parent root = loader.load();
        comm1.getScene().setRoot(root);
    }

    @FXML

    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterProduitBack.fxml"));
        Parent root = loader.load();

        AjouterProduitBackController cb = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        afficher();

    }
@FXML
    private void refresh(ActionEvent event) {
        try {
            grid.getChildren().remove(0, listeProduit.size());
            listeProduit = ds.afficherProduit();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itemprodback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItemprodbackController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {

        } catch (IOException ex) {
        }
    }

    @FXML
    private void categ(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("catgeorielistBack.fxml"));
        Parent root = loader.load();
        categoriefx.getScene().setRoot(root);
    }

   

}
