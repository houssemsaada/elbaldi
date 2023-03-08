/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import elbaldi.services.ProduitCRUD;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.ConfigurationBuilder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemprodbackController implements Initializable {

    @FXML
    private AnchorPane dest;
    @FXML
    private ImageView imagefx;
    @FXML
    private Label libellefx;
    @FXML
    private Label prixfx;
    private produit produit1;
 private String Refproduit;
    @FXML
    private ImageView sharefx;
    
       public String getRefProduit() {
        return Refproduit;
    }

    public void setRefProduit(String Refproduit) {
        this.Refproduit = Refproduit;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void setData(produit dest) {
        this.produit1 = dest;
        Refproduit=dest.getRef_produit();
        this.libellefx.setText(produit1.getLibelle());
        this.prixfx.setText(produit1.getPrix_vente()+" ");

        //Image f = new Image("C:\\xampp\\htdocs\\images\\" + produit1.getImage());

        //imagefx.setImage(f);
        
        String imagePath = "C:\\xampp\\htdocs\\images\\"+ produit1.getImage().toString();
       
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
    private void details(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("proddetailsback.fxml"));
    Parent root = loader.load();
    ProddetailsbackController controller = loader.getController();
    controller.setProduit(produit1); // pass the selected product to the details view
    imagefx.getScene().setRoot(root);

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
           ProduitCRUD produitcrud = new ProduitCRUD();
     // produitcrud.supprimerProduit(Refproduit);
  
   
        Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
       alert2.setTitle("Confirmation de suppression");
       alert2.setHeaderText(null);
       alert2.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

      ButtonType confirmerButton = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
alert2.getButtonTypes().setAll(confirmerButton, cancelButton);

Optional<ButtonType> result = alert2.showAndWait();
if (result.get() == confirmerButton){
       produitcrud.supprimerProduit(Refproduit);         
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("produit a été supprimé avec succés!");
      alert.show();
    

   Parent loader = FXMLLoader.load(getClass().getResource("prodbacklist.fxml"));
     libellefx.getScene().setRoot(loader);
}

    }
/*
    @FXML
    private void partagerAction(MouseEvent event) throws FacebookException {
//        // Créer un objet FacebookFactory en utilisant votre jeton d'accès
//        FacebookFactory facebookFactory = new FacebookFactory(new OAuthAuthorization(new AccessToken("EAAakIZCni0c8BAPc5ssLTaZCpZCXIr4aLnZCJWrEdIXMmNPSqJf4gTbxOZBs4NesQPwiuwZCV2iLnrKTdKkrFmISLUWAND5D7EUZC9wHogsEfDZCza5HOPq1bj7SsOTBrUzp9xH2X4w8rNqD8hGeZCuPY4mk2MDHWeDXjaj8GBzKSXO8mupsdY4iW")));
//
//// Créer un objet Facebook en utilisant FacebookFactory.getInstance()
//        Facebook facebook = facebookFactory.getInstance();
//
//// Créer un objet PostUpdate avec le message que vous voulez partager et l'URL de la page produit
//        PostUpdate postUpdate = new PostUpdate("Message de partage").setLink(produit1);
//
//// Appelez la méthode postFeed avec l'objet PostUpdate pour partager le produit sur Facebook
//        facebook.postFeed(postUpdate);



      // configure Facebook4J
    String appId = "1869324283466191";
    String appSecret = "d8f014c3aa96d7c15fe58942ad50abe1";
    String accessTokenString = "EAAakIZCni0c8BACLEiy6i2Tx1t1KKW6KYFkWLMnt9qCSWmhVwFEI24O0vgoPI6I7zaiuY2gszBjVL8LOMR1z9s7CxZB3tlcV0ayho1GB3WqdqOxVkB9hqly3ZC6ef37ZCv4MZCHNmrdNQdG06ePYdL0mXzAi2uSTtS7DcDkx1mEMJIwELAKvlZAg4EhnqJUFpu9OmMwfzvCQZDZD";
    
AccessToken accessTokenObj = new AccessToken(accessTokenString);
    ConfigurationBuilder cb = new ConfigurationBuilder();
cb.setDebugEnabled(true)
  .setOAuthAppId(appId)
  .setOAuthAppSecret(appSecret)
   .setOAuthAccessToken(accessTokenString);
 // .setOAuthAccessToken(new AccessToken(accessTokenString));

Facebook facebook = new FacebookFactory(cb.build()).getInstance();
    //Facebook facebook = new FacebookFactory().getInstance(new OAuthAuthorization(appId, appSecret, new AccessToken(accessTokenString)));

    // build the message to share
    String message = "Découvrez notre produit " + produit1.getLibelle() + " à seulement " + produit1.getPrix_vente() + "€ sur notre site !";

    // share the message
    try {
        PostUpdate post = new PostUpdate(new URL("https://www.facebook.com/Elbaldi En Ligne")).message(message);
        facebook.postFeed(post);
        JOptionPane.showMessageDialog(null, "Le produit a été partagé sur Facebook !");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors du partage sur Facebook : " + ex.getMessage());
    }
    */
     @FXML
    private void partagerAction(MouseEvent event) throws FacebookException {
    // configure Facebook4J
    String appId = "1869324283466191";
    String appSecret = "d8f014c3aa96d7c15fe58942ad50abe1";
    String pageId = "l'ID de votre page Facebook";
    String accessTokenString = "EAAakIZCni0c8BACLEiy6i2Tx1t1KKW6KYFkWLMnt9qCSWmhVwFEI24O0vgoPI6I7zaiuY2gszBjVL8LOMR1z9s7CxZB3tlcV0ayho1GB3WqdqOxVkB9hqly3ZC6ef37ZCv4MZCHNmrdNQdG06ePYdL0mXzAi2uSTtS7DcDkx1mEMJIwELAKvlZAg4EhnqJUFpu9OmMwfzvCQZDZD";

    AccessToken accessTokenObj = new AccessToken(accessTokenString);
    Facebook facebook = new FacebookFactory().getInstance();
    facebook.setOAuthAppId(appId, appSecret);
    facebook.setOAuthAccessToken(accessTokenObj);

    // build the message to share
    String message = "Découvrez notre produit " + produit1.getLibelle() + " à seulement " + produit1.getPrix_vente() + "€ sur notre site !";

    // share the message
//       PostUpdate post = new PostUpdate(new URL("https://www.example.com"))
//                .message(message);
//                
//        Post postResponse = facebook.postFeed(post);
//        
//
//        System.out.println("Post publié sur Facebook. ID du post : " + postResponse.getId());
    }  
}






