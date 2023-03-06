/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.http.client.RedirectStrategy;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import elbaldi.models.SMSNotifier;
import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.Upload;
import elbaldi.utils.MyConnection;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class AjouterProduitBackController implements Initializable {

    @FXML
    private TextField reffx;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button Ajouterfx;
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
    private TextField libellefx;
    @FXML
    private TextField quantitefx;
    File selectedfile;

    String path_img;

    Upload u = new Upload();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //reffx.setText("TUN61900");
        
        ListerCategorie();
        insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {
                    try {
                        resizeFile(selectedfile.getAbsolutePath(),"C:\\xampp\\htdocs\\images\\"+selectedfile.getName(),227,207);
                        
                        img.getItems().add(selectedfile.getName());
                        
                        path_img = selectedfile.getAbsolutePath();
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
        Ajouterfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                categorie c = categoriefx.getSelectionModel().getSelectedItem();
                float prixx = Float.parseFloat(prixfx.getText());
                int quantite = Integer.parseInt(quantitefx.getText());
                produit pp = new produit(reffx.getText(), libellefx.getText(), descriptionfx.getText(),
                        selectedfile.getName(), prixx, quantite, c);
                //for example, you can copy the uploaded file to the server
                //note that you probably don't want to do this in real life!
                //upload it to a file host like S3 or GCS instead
//////	    File fileToSave = new File("C:\\xampp\\htdocs\\images" + selectedfile.getName());
//////            Path path = Paths.get(selectedfile.getAbsolutePath());
//////                try {
//////                    Files.copy(path, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
//////                } catch (IOException ex) {
//////                    Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
//////                }
                ProduitCRUD PROD = new ProduitCRUD();
                try {
                    PROD.ajouterProduit(pp);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Ajout avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
                
              
                
                
                /*
                
                String[] phoneNumbersArray = {"+21697618378"};
//List<String> phoneNumbers = new ArrayList<>("97618378");
                List<String> phoneNumbers = Arrays.asList(phoneNumbersArray);
// récupérer les numéros de téléphone à partir de la base de données ou du fichier
// les ajouter à la liste phoneNumbers
                SMSNotifier notifier = new SMSNotifier();
                String mess="cher(e) client , Profitez de notre nouveauté ,un nouvelle produit est disponible sur notre application ELBALDI. Attention : stock limité. Faites vite ! ".
                notifier.notifyClients(phoneNumbers, mess);
                */
            }
        });
        annulerfx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reffx.clear();
                libellefx.clear();
                descriptionfx.clear();
                prixfx.clear();
                quantitefx.clear();
                categoriefx.getItems().clear();
                img.getItems().clear();

            }
        });
    }

    @FXML
    private void prefixe(MouseEvent event) {
        reffx.setText("TUN61900");
        //String referenceProduit = produit.getReferenceProduit();
        //reffx.setText("TUN61900" + referenceProduit);
    }

    private void ListerCategorie() {

        CategorieCRUD categoriecrud = new CategorieCRUD();
        ObservableList<categorie> list = FXCollections.observableArrayList();
        try {
            String req = " select id_categorie,`nom_categorie`,`description` from `categorie`  ";

            Connection conn = MyConnection.getInstance().getConn();

            PreparedStatement pst = conn.prepareStatement(req);

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

    public void resizeFile(String imagePathToRead,
            String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
    }

}
