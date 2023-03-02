/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.quiz;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import elbaldi.GUI.JouerQuizController;
import java.io.IOException;
import java.util.Hashtable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.services.PromotionCRUD;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ScoreController implements Initializable {

    private float score;

    @FXML
    private Label fxscore;
    @FXML
    private Label reduction;
    @FXML
    private Label termine;
    @FXML
    private Label bravo;
    @FXML
    private Label codeqr;
    @FXML
    private Label echec;

    float taux;
    String promoCode = "ELBALDI";

    /**
     * Initializes the controller class.
     */
    PromotionCRUD pc = new PromotionCRUD();
    Utilisateur u = new Utilisateur(2462);

    public void setscore(float score) throws IOException {

        this.score = score;
        System.out.println(score);
        fxscore.setText(Float.toString(score));

        fxscore.setText(String.format("%.0f%%", score));

        if (score == 100.0) {
            bravo.setText(" Bravo !");
            termine.setText("Quiz terminé avec succés");
            reduction.setText("Profitez d'une réduction de 15%");
            String promoCode = generatePromoCode((int) score);

            // Affichage du code promo généré dans le label codeqr
            codeqr.setText("avec le code Promo" + promoCode);

        } else if (score >= 75.0 && score <= 99.99) {
            bravo.setText(" Bravo !");
            termine.setText("Quiz terminé avec succés");
            reduction.setText("Vous bénéficiez d'une réduction de 10%");
            codeqr.setText(" Veuillez scanner ce code QR pour en profiter");
            String promoCode = generatePromoCode((int) score);

            // Affichage du code promo généré dans le label codeqr
            codeqr.setText("avec le code Promo" + promoCode);
        } else if (score >= 0.45 && score <= 74.99) {
            bravo.setText(" Bravo !");
            termine.setText("Quiz terminé avec succés");
            reduction.setText("Profitez d'une réduction de 8%");
            codeqr.setText(" Veuillez scanner ce code QR pour en profiter");
            String promoCode = generatePromoCode((int) score);

            // Affichage du code promo généré dans le label codeqr
            codeqr.setText("avec le code Promo " + promoCode);
        } else {
            echec.setText("Désolé, vous n'avez pas réussi le quiz !!!");
        }

    }

    public String generatePromoCode(int score) {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            promoCode += String.valueOf(random.nextInt(10));
        }
        if (score == 100) {
            taux = 0.15f;
            promotion promo = new promotion(promoCode, taux, u);
            pc.ajouterpromotion(promo);
        }
        if (score >= 75.0 && score <= 99.99) {
            taux = 0.10f;
            promotion promo = new promotion(promoCode, taux, u);
            pc.ajouterpromotion(promo);

        }
        if (score >= 0.45 && score <= 74.99) {
            taux = 0.08f;
            promotion promo = new promotion(promoCode, taux, u);
            pc.ajouterpromotion(promo);

        }

        // ce code permet d'ajouter des caractères aléatoires supplémentaires pour le code promo
     

        return promoCode;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}