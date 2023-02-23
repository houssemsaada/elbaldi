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
import elbaldi.services.Upload;
import elbaldi.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierProduitBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private ListView img;
    @FXML
    private Button insérer;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private TextField prixfx;
    @FXML
    private Button annulerfx;
    @FXML
    private ComboBox<categorie> categoriefx;
    @FXML
    private Button back;
    @FXML
    private TextField reffx;
    @FXML
    private TextField libellefx;
    @FXML
    private TextField quantitefx;
    File selectedfile;

    String path_img;

    Upload u = new Upload();
    public static produit produitP;
    private String  Refproduit;
    @FXML
    private Button Modifierfx;

    public String getRefProduitP() {
        return Refproduit;
    }

    public void setRefProduitP(String refpro) {
        this.Refproduit = refpro;
    }

     
    public void setProduit(produit p) {
        //System.out.println(idChambre);
        produitP = p;
       // ProduitCRUD cs = new ProduitCRUD();
        this.img.setAccessibleText(produitP.getImage());    
        this.prixfx.setText(produitP.getPrix_vente() + "");
        this.reffx.setText(produitP.getRef_produit());
        this.descriptionfx.setText(produitP.getDescription());
        this.libellefx.setText(produitP.getLibelle());
        this.quantitefx.setText(produitP.getQuantite()+ "");
        categoriefx.getSelectionModel().select(produitP.getCategoriee());
 
    }
  

    /**
     * Initializes the controller class.
     */
//       public void setProduit() throws SQLException   {
//        
//        ProduitCRUD cs = new ProduitCRUD();
//        produitP = cs.getByRefProduit(Refproduit);
//        this.img.setAccessibleText(produitP.getImage());    
//        this.prixfx.setText(produitP.getPrix_vente() + "");
//        this.reffx.setText(produitP.getRef_produit());
//        this.descriptionfx.setText(produitP.getDescription());
//        this.libellefx.setText(produitP.getLibelle());
//        this.quantitefx.setText(produitP.getQuantite()+ "");
//        categoriefx.getSelectionModel().select(produitP.getCategoriee());
//    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ListerCategorie();
        // TODO
     insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {

                    Upload u = new Upload();
                    try {
                        u.upload(selectedfile);
                    } catch (IOException ex) {
                        Logger.getLogger(ModifierProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    img.getItems().add(selectedfile.getName());

                    path_img = selectedfile.getAbsolutePath();

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
       Modifierfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                ProduitCRUD produitcrud = new ProduitCRUD();
                
                int q = Integer.parseInt(quantitefx.getText());
                float prixx = Float.parseFloat(prixfx.getText());
                 //categorie catego = categoriefx.getSelectionModel().getSelectedItem();
               
                 categorie c = categoriefx.getSelectionModel().getSelectedItem();

                produit p = new produit(produitP.getRef_produit(), libellefx.getText(), descriptionfx.getText(),prixx,q,c);
                       
                if (!libellefx.getText().equalsIgnoreCase("")
                        && !descriptionfx.getText().equalsIgnoreCase("")
                        && !reffx.getText().equalsIgnoreCase("")
                        && Float.parseFloat(prixfx.getText()) > 0
                        && Float.parseFloat(quantitefx.getText()) > 0
                        ) {
                    
                    try {

                        produitcrud.modifierProduit(p);

                    } catch (SQLException ex) {
                        Logger.getLogger(ModifierProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("information Dialog");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Votre modification est enregistrée avec succes ");
                    alert0.show();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de la modification");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                    alert.showAndWait();
                }
            }

        });
    }    

    @FXML
    private void prefixe(MouseEvent event) {
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

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProduitBack.fxml"));
        Parent root = loader.load();
        back.getScene().setRoot(root);
    }
    

    }    

    

//    private void modifierProduit(ActionEvent event) throws IOException  {
//         ProduitCRUD produitcrud = new ProduitCRUD();
//                //  Hotel h = hotel.getSelectionModel().getSelectedItem();
//
//                
//                categorie catego = categoriefx.getSelectionModel().getSelectedItem();
//                
//                float prixx = Float.parseFloat(prixfx.getText());
//                int quantitee = Integer.parseInt(quantitefx.getText());
//                
//                
//                //System.out.println(vssmm+""+typee+""+""+statu+""+prix+""+numero+"");
//                produit pr = new produit(reffx.getText(),libellefx.getText(),descriptionfx.getText(), selectedfile.getName(),prixx,quantitee,catego);
//                       
//
//                System.out.println(pr);
//
//                try {
////System.out.println("222222");
//                    produitcrud.modifierProduit(pr);
//                     FXMLLoader loader = new FXMLLoader();
//                        loader.setLocation(getClass().getResource("ProduitBack.fxml"));
//                        Parent root = loader.load();
//                        Modifierfx.getScene().setRoot(root);
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(ModifierProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
////                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
////                alert0.setTitle("information Dialog");
////                alert0.setHeaderText(null);
////                alert0.setContentText("Votre modification est enregistrée avec succes ");
////                alert0.show();
////                ((Node) event.getSource()).getScene().getWindow().hide();
//
//            }
    
    

