/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import static elbaldi.GUI.CategorieBackController.c;
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
import javafx.event.EventHandler;
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
 * @author USER
 */
public class ProduitBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Button ajouterP;
    @FXML
    private Button updateP;
    @FXML
    private Button deleteP;
    @FXML
    private Button showP;
    @FXML
    private Button detailsfx;
    @FXML
    private Button Home;
    @FXML
    private Button Profil;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reservation;
    @FXML
    private Button btnSignout;
    @FXML
    private TableView<produit> tableP;
    @FXML
    private TableColumn<produit, String> ref;
    @FXML
    private TableColumn<produit,String> libelle;
    @FXML
    private TableColumn<produit, String> prix;
    @FXML
    private TableColumn<produit, ImageView> img;
    
    public static produit produitB;
     ObservableList<produit> obl = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Afficher();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       deleteP.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                if (tableP.getSelectionModel().getSelectedItem() != null) {

                    try {
                        String ref_produit=tableP.getSelectionModel().getSelectedItem().getRef_produit();
                         ProduitCRUD produitcrud = new ProduitCRUD();
                        produitcrud.supprimerProduit(ref_produit);
                        
                        } catch (SQLException ex) {
                        Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Afficher();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner un produit");
                    alert.show();
                }
            }
        });
    }
       
//      public void tableInit() {
//
//        reffx.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
//        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//    }
       public void Afficher() throws SQLException {

        ProduitCRUD produitcrud = new  ProduitCRUD();

        ObservableList<produit> liste = FXCollections.
                observableArrayList(produit
                        .generateImageViews(produitcrud.afficherProduit()));

        tableP.setItems(liste);

        /* add column to the tableview and set its items */
        ref.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<produit, String>, ObservableValue<String>>() {

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
    }
        

    @FXML
    private void ajouterprod(ActionEvent event)throws IOException{
        
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterProduitBack.fxml"));
        Parent root = loader.load();
        // ajouterDest.getScene().setRoot(root);
        AjouterProduitBackController dc = loader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//     public void tableInit() {
//
//        .setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
//        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//    }


//    @FXML
//    private void updateprod(ActionEvent event) throws IOException , SQLException {
//         if (tableP.getSelectionModel().getSelectedItem() != null) {
//
//           // produit prod = tableP.getSelectionModel().getSelectedItem();
//            String ref_produit=tableP.getSelectionModel().getSelectedItem().getRef_produit();
//                       
//           // produitB = prod;
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduitBack.fxml"));
//            Parent root = loader.load();
//            ModifierProduitBackController FM = loader.getController();
//            FM.setProduit();
//           // FM.setProduit(produitB);
//               try {
//            
//            Afficher();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.showAndWait();
//              try {
//         
//            Afficher();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Vous devez selectionner un produit");
//            alert.show();
//        }
    //}

    
    @FXML
    private void details(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProduitDetails.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    
    }
    }
