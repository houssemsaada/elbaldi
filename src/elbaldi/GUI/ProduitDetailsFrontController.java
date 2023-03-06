/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commentaire;
import elbaldi.models.produit;
import elbaldi.services.UtilisateurCRUD;
import elbaldi.services.commentaireCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ProduitDetailsFrontController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label reffx;
    @FXML
    private Label libellefx;
    @FXML
    private Label descfx;
    @FXML
    private Label prixfx;
    @FXML
    private TextArea commfx;
    private produit produitt;
    @FXML
    private ListView<commentaire> listfx;
    @FXML
    private Button commenterfx;

    commentaireCRUD comm = new commentaireCRUD();
    @FXML
    private Button retourfx;
    List<String> badWords;

    public void setProduit(produit produit) {
        this.produitt = produit;
        libellefx.setText(produit.getLibelle());
        descfx.setText(produit.getDescription());
        reffx.setText(produit.getRef_produit());
        prixfx.setText(produit.getPrix_vente() + " ");
        File f = new File("C:\\xampp\\htdocs\\images\\" + produitt.getImage());

        img.setImage(new Image(f.toURI().toString()));
        afficherCommentaires(produit); // Afficher les commentaires pour ce produit

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        badWords = new ArrayList<String>(); //liste de mots interdits
        badWords.add("mot1");
        badWords.add("mot2");
        badWords.add("mot3");

        commenterfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    // Récupérer le commentaire à partir de la zone de texte
                    String texte = commfx.getText();

                    UtilisateurCRUD uti = new UtilisateurCRUD();
                    commentaire commentaire = new commentaire(texte, produitt, uti.getUserByID(2498));

                    commentaireCRUD comm = new commentaireCRUD();

                    try {
                        if (checkComment(commfx.getText(), badWords)) {
                            comm.ajouterCommentaire(commentaire);
                            commfx.clear();

                        } else {
                            //System.out.println("Votre commentaire contient des mots interdits. Veuillez modifier votre commentaire.");
                             Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Echec de l'ajout de ce commentaire");
                        alert.setHeaderText(null);
                        alert.setContentText("Attention ! Votre commentaire est non acceptable ");
                        alert.showAndWait();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ProduitDetailsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    

                    // Rafraîchir la liste des commentaires
                    afficherCommentaires(produitt);
                } catch (SQLException ex) {
                    Logger.getLogger(ProduitDetailsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public List<commentaire> afficherCommentaires(produit produit) {
         // Récupérer les commentaires du produit à partir de la base de données
    List<commentaire> commentaires = null;
    try {
        commentaires = comm.getCommentairesByArticle(produit);
    } catch (SQLException ex) {
        Logger.getLogger(ProduitDetailsFrontController.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Définir la cellule personnalisée pour la ListView
    listfx.setCellFactory(list -> new CommentaireCell());

    // Ajouter les commentaires à la ListView
    listfx.getItems().setAll(commentaires);
    return commentaires;
//       
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProduitFront2.fxml"));
        Parent root = loader.load();
        retourfx.getScene().setRoot(root);
    }

    public static boolean checkComment(String comment, List<String> badWords) {
        for (String word : badWords) {
            if (comment.toLowerCase().contains(word.toLowerCase())) {
                return false; // Si un mot interdit est trouvé, le commentaire est bloqué
            }
        }
        return true;
    }

}
