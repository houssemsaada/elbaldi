/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.bonplan;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReservationItemBackController implements Initializable {

    @FXML
    private Label lieuBp;
    @FXML
    private Label nomBp;
    @FXML
    private Label nombrePersonne;
    @FXML
    private Label statutBp;
    @FXML
    private Button confirmerBp;
    private Reservation reservation;
    private int id_res;
    @FXML
    private ImageView imagefx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Reservation dest) {
       this.reservation = dest;
        id_res=dest.getId_reservation();
        this.nomBp.setText(reservation.getUser2().getNom()+" "+reservation.getUser2().getPrenom());
        this.lieuBp.setText(reservation.getBonplan2().getTitre_bonplan());
        this.nombrePersonne.setText(reservation.getNombre_personnes()+"");
        this.statutBp.setText(reservation.getStatut_reservation());
        
        
        //Image f = new Image("C:\\xampp\\htdocs\\images\\" + bonplan1.getImage_bonplan());

        //imagefx.setImage(f);
         String imagePath = "C:\\xampp\\htdocs\\images\\"+ reservation.getBonplan2().getImage_bonplan().toString();
       
        // Create an ImageView object
        ImageView imageView = new ImageView();
        // Create a File object with the path of your image
        File file = new File(imagePath);
       
        // Check if the file exists
        if (file.exists()) {
            // Create an Image object with the file path
            Image image = new Image(file.toURI().toString());
            // Set the image to the ImageView
            this.imagefx.setImage(image);
        } else {
            System.out.println("Image not found.");
        }

    }
    @FXML
    private void confirmerRes(ActionEvent event) {if (TableView.getSelectionModel().getSelectedItem() != null) {
////             
////            Reservation r=TableView.getSelectionModel().getSelectedItem();
////            ReservationCrud resvS = new ReservationCrud();
////            System.out.println(r.getId_reservation());
////            r.setStatut_reservation("confrimee");
////            resvS.modifierReservation(r);
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("confirmation de reservation");
////            alert.show();
////         
////              Afficher();   
////           
////        
////         } else {
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("Vous devez selectionner une categorie");
////            alert.show();
////        }
    }
    
}
