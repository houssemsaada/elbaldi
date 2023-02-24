/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.services.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeclientController implements Initializable {

    float total;
    int id_panier;
    panier p;

    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private Button addBtn;

    @FXML
    private TableView<produit> tableView;

    @FXML
    private TableColumn<produit, String> libelleCol;

    @FXML
    private TableColumn<produit, Float> prixCol;
    @FXML
    private TableColumn<produit, Integer> quantiteCol;
    @FXML
    private TableColumn<produit, Float> totCol;
    @FXML
    private TextField totalTF;
    ObservableList<produit> produitObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField addrTF;
    @FXML
    private AnchorPane adresseTF;
    @FXML
    private TextField id_panTF;

    /**
     * Initializes the controller class.
     *
     * @param id_panTF
     */
    public void setTotalTF(String totalTF) {
        this.totalTF.setText(totalTF);
    }

    public void setId_panTF(TextField id_panTF) {
        this.id_panTF = id_panTF;
    }

    public void setNomTF(String nomTF) {
        this.nomTF.setText(nomTF);
    }

    public void setPrenomTF(String prenomTF) {
        this.prenomTF.setText(prenomTF);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        panierCRUD pc = new panierCRUD();
        p = pc.filtreByidPanier(Integer.parseInt(id_panTF.getText()));

        produitObservableList = FXCollections.observableList(pc.afficherListProduitPanier(p));

        libelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
        quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        totCol.setCellValueFactory(cellData -> {
            produit prod = cellData.getValue();
            Float totalprod = prod.getPrix_vente() * prod.getQuantite();
            return new SimpleObjectProperty<>(totalprod);
        });
        tableView.setItems(produitObservableList);
        totalTF.setText(p.sommePanier(produitObservableList) + "");
        nomTF.setText(p.getU1().getNom());
        prenomTF.setText(p.getU1().getPrenom());

    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "consulterPanier.fxml", "Panier");
    }

    @FXML
    private void confirmerOnAction(ActionEvent event) {
        try {
            if (commandeGUI.isTextFieldEmpty(nomTF, prenomTF, addrTF)) {
                commandeGUI.AlertShow("Please fill all fields", "Empty fields", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        panierCRUD pc = new panierCRUD();
        p = pc.filtreByidPanier(Integer.parseInt(id_panTF.getText()));
        CommandeCRUD cr = new CommandeCRUD();
        livraisonCRUD lr = new livraisonCRUD();
        commande c = new commande(p);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to confirm the order?");
        alert.setHeaderText("Please confirm your action");
        Optional<ButtonType> result = alert.showAndWait();
        // if the user confirms the update action
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cr.ajouterCommande(c);

            commande c2 = cr.filtreBypanier(p);
            livraison l = new livraison();
            l.setC1(c2);
            String adress = addrTF.getText();

            l.setAdresse_livraison(adress);
            lr.ajouterLivraison(l);
            commandeGUI.AlertShow("order added ! ", "order", Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    private void changeQTE(MouseEvent event) {
    }

}
