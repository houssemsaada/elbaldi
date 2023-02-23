/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commentaire;
import elbaldi.models.produit;
import elbaldi.services.commentaireCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class CommentaireBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<commentaire> table;
    @FXML
    private Button Home;
    @FXML
    private Button btnSignout;
    @FXML
    private TableColumn<commentaire, String> contenu;
    @FXML
    private TableColumn<commentaire, Date> dateC;
    @FXML
    private TableColumn<commentaire, Utilisateur> client;
    @FXML
    private TableColumn<commentaire, produit> produit;
    @FXML
    private Button categorie;
    @FXML
    private Button produitt;
    @FXML
    private Button commentaires;
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            tableInit();
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void tableInit() {

        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateC.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
        produit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        ObservableList<String> data = FXCollections.observableArrayList("contenu", "Date", "Client","produit");
       //triBox.setItems(data);

    }
 
   public void Afficher() throws SQLException {

        commentaireCRUD commCRUD = new  commentaireCRUD();

        ObservableList<commentaire> liste = FXCollections.
                observableArrayList((commCRUD.afficherCommentaire()));

        table.setItems(liste);

        /* add column to the tableview and set its items */
        contenu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<commentaire, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<commentaire, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getContenu());
            }
        });
        
        dateC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<commentaire, java.util.Date>, ObservableValue<java.util.Date>>() {
            @Override
            public ObservableValue<java.util.Date> call(TableColumn.CellDataFeatures<commentaire, java.util.Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate());
            }
        });
        

        client.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<commentaire, Utilisateur>, ObservableValue<Utilisateur>>() {
            @Override
            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<commentaire, Utilisateur> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getUtilisateur().getNom());
            }
        });
         produit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<commentaire, produit>, ObservableValue<produit>>() {
            @Override
            public ObservableValue<produit> call(TableColumn.CellDataFeatures<commentaire, produit> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getProduit().getRef_produit());
            }
        });

       

        
    }

    @FXML
    private void categg(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("CategorieBack.fxml"));
        Parent root = loader.load();
        categorie.getScene().setRoot(root);
    }

    @FXML
    private void prodd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ProduitBack.fxml"));
        Parent root = loader.load();
        produitt.getScene().setRoot(root);
    }

    @FXML
    private void comen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("CommentaireBack.fxml"));
        Parent root = loader.load();
        commentaires.getScene().setRoot(root);
    }
    
    
}
