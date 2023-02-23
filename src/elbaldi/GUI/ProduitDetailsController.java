/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.ProduitCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ProduitDetailsController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Button back;
    @FXML
    private TableView<produit> table;
    @FXML
    private TableColumn<produit, String> Réference;
    @FXML
    private TableColumn<produit, String> libelle;
    @FXML
    private TableColumn<produit, String> desc;
    @FXML
    private TableColumn<produit, ImageView> img;
    @FXML
    private TableColumn<produit, String> prix;
    @FXML
    private TableColumn<produit, String> quantite;
    @FXML
    private TableColumn<produit, categorie> categorie;
     public static produit p;
    
    ObservableList<produit> obl = FXCollections.observableArrayList();
    @FXML
    private Button updateP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             tableInit();
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
public void tableInit() {

        Réference.setCellValueFactory(new PropertyValueFactory<>("ref_produit"));
       libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       desc.setCellValueFactory(new PropertyValueFactory<>("description"));
       img.setCellValueFactory(new PropertyValueFactory<>("image"));
       prix.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
         quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
           categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));


               

    }

    
    public void Afficher() throws SQLException {

        ProduitCRUD produitcrud = new  ProduitCRUD();

        ObservableList<produit> liste = FXCollections.
                observableArrayList(produit.generateImageViews(produitcrud.afficherProduit()));

        table.setItems(liste);

        /* add column to the tableview and set its items */
        Réference.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getRef_produit());
            }
        });
        libelle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getLibelle());
            }
        });
          desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });

        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<produit, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
            prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix_vente());
            }
        });
             quantite.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<produit, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantite());
            }
        });
       
            
              categorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, categorie>, ObservableValue<categorie>>() {
            
            @Override
            public ObservableValue<categorie> call(TableColumn.CellDataFeatures<produit, categorie> param) {
              
                return new ReadOnlyObjectWrapper(param.getValue().getCategoriee().getNom_categorie());
            }
        });
              
                  
             
              
              
    }
    

    @FXML
    private void updateprod(ActionEvent event) throws IOException, SQLException {

        if (table.getSelectionModel().getSelectedItem() != null) {
            produit prod = table.getSelectionModel().getSelectedItem();
            p = prod;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduitBack.fxml"));
            Parent root = loader.load();
            ModifierProduitBackController pm = loader.getController();
            pm.setProduit(p);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            Afficher();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un produit");
            alert.show();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProduitBack.fxml"));
        Parent root = loader.load();
        back.getScene().setRoot(root);
    }

    
    
}
