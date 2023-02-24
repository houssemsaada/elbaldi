/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import elbaldi.models.categorie;
import elbaldi.services.BonplanCrud;
import elbaldi.services.CategorieCRUD;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherBonplanController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Pane pnlOverview1;
    @FXML
    private Button ajouterC;
    @FXML
    private Button updateC;
    @FXML
    private Button deleteC;
    @FXML
    private Button showC;
    @FXML
    private TableView<bonplan> tableC;
    @FXML
    private TableColumn<bonplan, String> nom;
    @FXML
    private TableColumn<bonplan, String> desc;
    @FXML
    private TableColumn<bonplan, String> type;
    @FXML
    private TableColumn<bonplan, String> img;
    ObservableList<bonplan> obl = FXCollections.observableArrayList();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
              tableInit();
            Afficher();
           
        } catch (SQLException ex) {
            Logger.getLogger(BonplanCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Afficher();
                } catch (SQLException ex) {
                    Logger.getLogger(BonplanCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // TODO
    }    
    public void tableInit() {

        nom.setCellValueFactory(new PropertyValueFactory<>("titre_bonplan"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description_bonplan"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_bonplan"));
        img.setCellValueFactory(new PropertyValueFactory<>("image_bonplan"));
    }

    public void Afficher() throws SQLException {
        obl = FXCollections.observableArrayList();

        BonplanCrud bp= new BonplanCrud();

       
        
        ObservableList<bonplan> bnp = FXCollections.observableArrayList(bp.afficherBonplan());
       tableC.setItems(bnp);

        /* add column to the tableview and set its items */
        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<bonplan, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<bonplan, String> param) {
                 return new ReadOnlyObjectWrapper(param.getValue().getTitre_bonplan()); //To change body of generated methods, choose Tools | Templates.
            }


        });
        desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<bonplan, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<bonplan, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription_bonplan());
            }
        });
        
         /* add column to the tableview and set its items */
        type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<bonplan, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<bonplan, String> param) {
                 return new ReadOnlyObjectWrapper(param.getValue().getType_bonplan()); //To change body of generated methods, choose Tools | Templates.
            }


        });
        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<bonplan, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<bonplan, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImage_bonplan());
            }
        });

        

    }

    @FXML
    private void ajouterBP(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutBonplanBack.fxml"));
        Parent root = loader.load();
        ajouterC.getScene().setRoot(root);
        
        
    }

    @FXML
    private void supprimerBP(ActionEvent event) throws SQLException {
         if (tableC.getSelectionModel().getSelectedItem() != null) {
             
             bonplan c=tableC.getSelectionModel().getSelectedItem();
             System.out.println(c);
             BonplanCrud bp= new BonplanCrud();
             System.out.println(c.getId_bonplan());
             bp.supprimerbonplan(c.getId_bonplan());
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("Supression avec succes");
             alert.show();
         
              Afficher();   
           
        
         } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une promotion");
            alert.show();
        }
    }

    @FXML
    private void modifierBP(ActionEvent event) throws IOException {
          if (tableC.getSelectionModel().getSelectedItem() != null) {

            bonplan c=tableC.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierBonPlanBack.fxml"));
            Parent root = loader.load();
            ModifierBonplanBackController FM = loader.getController();
            FM.setCategorie(c);
         
           
               try {
            
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherBonplanController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
              try {
         
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherBonplanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une catégorie");
            alert.show();
        }
    }
     
}

