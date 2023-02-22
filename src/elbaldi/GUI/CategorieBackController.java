/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.services.CategorieCRUD;
import java.awt.Desktop;
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
import javafx.scene.control.TableCell;
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
public class CategorieBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
   
   
    @FXML
    private TableColumn<categorie, String> nom;
    @FXML
    private TableColumn<categorie, String> desc;

    ObservableList<categorie> obl = FXCollections.observableArrayList();
    @FXML
    private TableView<categorie> tableC;
    
    public static categorie c;
    @FXML
    private Button ajouterC;
    @FXML
    private Button updateC;
    @FXML
    private Button deleteC;
    @FXML
    private Button showC;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
              tableInit();
            Afficher();
           
        } catch (SQLException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Afficher();
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // TODO
    } 
     public void tableInit() {

        nom.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    public void Afficher() throws SQLException {
        obl = FXCollections.observableArrayList();

        CategorieCRUD categoriecrud = new CategorieCRUD();

       
        
        ObservableList<categorie> cat = FXCollections.observableArrayList(categoriecrud.affichercategorie());
       tableC.setItems(cat);

        /* add column to the tableview and set its items */
        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<categorie, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<categorie, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom_categorie());
            }
        });
        desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<categorie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<categorie, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });

        

    }

    @FXML
    private void ajouterCateg(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CategorieBackAjout.fxml"));
        Parent root = loader.load();
        // AjoutP.getScene().setRoot(root);

        CategorieBackAjoutController cb = loader.getController();
 
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
          try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieBackAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void updateCateg(ActionEvent event) throws IOException, SQLException {

        if (tableC.getSelectionModel().getSelectedItem() != null) {

            categorie categor = tableC.getSelectionModel().getSelectedItem();
            c = categor;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieBackModifier.fxml"));
            Parent root = loader.load();
            CategorieBackModifierController FM = loader.getController();
            FM.setCategorie(c);
               try {
            
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
              try {
         
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une catégorie");
            alert.show();
        }
    }
     @FXML
    private void SupCateg(ActionEvent event) throws SQLException {
        
         if (tableC.getSelectionModel().getSelectedItem() != null) {
             
            try {
                categorie c=tableC.getSelectionModel().getSelectedItem();
                System.out.println(c);
                CategorieCRUD categoriecrud = new CategorieCRUD();
                categoriecrud.supprimerCategorie(c);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Supression avec succes");
                alert.show();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
              Afficher();   
           
        
         } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une promotion");
            alert.show();
        }
 }
  

}
