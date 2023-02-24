/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.*;
import elbaldi.services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ConsulterPanierController implements Initializable {
    
    @FXML
    private TableView<produit> tableView;
    @FXML
    private TableColumn<produit, String> refCol;
    @FXML
    private TableColumn<produit, String> libelleCol;
    @FXML
    private TableColumn<produit, String> descCol;
    @FXML
    private TableColumn<produit, Float> prixCol;
    @FXML
    private TableColumn<produit, Integer> quantiteCol;
    @FXML
    private Button loginButton12;
    @FXML
    private TextField totalTF;
    @FXML
    private Button CommandeBtn;
    ObservableList<produit> produitObservableList = FXCollections.observableArrayList();
    @FXML
    private TextField panieridTF;
    @FXML
    private TableColumn<produit, Float> totCol;
    @FXML
    private Spinner<Integer> qtescroll;
    @FXML
    private Button ud;
    @FXML
    private Button updateBTN;
    
    public void setTotalTF(TextField totalTF) {
        this.totalTF = totalTF;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        panierCRUD pc = new panierCRUD();
        panier p = new panier();
        p.setId_panier(Integer.parseInt(panieridTF.getText()));
        produitObservableList = FXCollections.observableList(pc.afficherListProduitPanier(p));
        refCol.setCellValueFactory(new PropertyValueFactory<>("ref_produit"));
        libelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
        quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        totCol.setCellValueFactory(cellData -> {
            produit prod = cellData.getValue();
            Float totalprod = prod.getPrix_vente() * prod.getQuantite();
            return new SimpleObjectProperty<>(totalprod);
        });
        tableView.setItems(produitObservableList);
        totalTF.setText(p.sommePanier(produitObservableList) + "");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        this.qtescroll.setValueFactory(valueFactory);
    }
    
    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }
    
    @FXML
    private void commanderOnAction(ActionEvent event) {
        panier p = new panier();
        p.setId_panier(Integer.parseInt(panieridTF.getText()));
        try {
            if (p.sommePanier(produitObservableList) == 0) {
                commandeGUI.AlertShow("panier vide", "vide", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        
        float somme = p.sommePanier(produitObservableList);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeclient.fxml"));
        try {
            Parent root = loader.load();
           CommandeclientController dc = loader.getController();
            //System.out.println(p.getId_panier());
            int id_panier = p.getId_panier() ; 
          //  dc.setId_panTF(String.valueOf(id_panier));
           // dc.setId_panTF(panieridTF);
            dc.setTotalTF(somme+"");
            totalTF.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    @FXML
    private void DeleteOnAction(ActionEvent event) {
        try {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Please select a product to delete", "No product selected", Alert.AlertType.ERROR);
                return;
            }
            
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            panierCRUD pc = new panierCRUD();
            panier p = new panier();
            p.setId_panier(Integer.parseInt(panieridTF.getText()));
            
            produit prod = new produit();
            prod.setRef_produit(tableView.getSelectionModel().getSelectedItem().getRef_produit());
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete the order ?");
            alert.setHeaderText("Please confirm your action");
            
            Optional<ButtonType> result = alert.showAndWait();

            // if the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                pc.supprimerProdPanier(p, prod);
                pc.modifierPanier(p);
                refreshTable();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    void refreshTable() {
        panierCRUD pc = new panierCRUD();
        panier p = new panier();
        p.setId_panier(Integer.parseInt(panieridTF.getText()));
        produitObservableList = FXCollections.observableList(pc.afficherListProduitPanier(p));
        refCol.setCellValueFactory(new PropertyValueFactory<>("ref_produit"));
        libelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
        quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        tableView.setItems(produitObservableList);
        totalTF.setText(p.sommePanier(produitObservableList) + "");
    }
    
    @FXML
    private void changeQTE(MouseEvent event) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, tableView.getSelectionModel().getSelectedItem().getQuantite());
        this.qtescroll.setValueFactory(valueFactory);
    }
    
    @FXML
    private void updateOnAction(ActionEvent event) {
        try {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Please select a product to update", "No product selected", Alert.AlertType.ERROR);
                return;
            }
            
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            panierCRUD pc = new panierCRUD();
            panier p = new panier();
            p.setId_panier(Integer.parseInt(panieridTF.getText()));
            
            produit prod = new produit();
            prod.setRef_produit(tableView.getSelectionModel().getSelectedItem().getRef_produit());
            int quantite = qtescroll.getValue();
            pc.modifierQteProdPanier(p, prod, quantite);
            pc.modifierPanier(p);
            refreshTable();
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
