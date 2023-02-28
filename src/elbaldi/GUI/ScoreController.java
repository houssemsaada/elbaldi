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

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ScoreController implements Initializable {

    @FXML
    private Text scorefx;
    private double score;
    @FXML
    private Label fxfcx;
    /**
     * Initializes the controller class.
     */
    
    
    public void setscore(double scoree) {
          
          this.score=scoree;       
        System.out.println(score);
       // scorefx.setText("votre score est : "+ score);
       String test = score +"";
       fxfcx.setText(test);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
