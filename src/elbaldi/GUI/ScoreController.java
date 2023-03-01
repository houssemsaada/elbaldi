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

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ScoreController implements Initializable {

    private float score;
    
    @FXML
    private Text fxscore;
    /**
     * Initializes the controller class.
     */
    
    
    public void setscoree(float score) {
          
          this.score=score;       
          System.out.println(score);
          float scorePercentage = score * 100;
          System.out.println(scorePercentage + "%");
          
          fxscore.setText(String.valueOf(scorePercentage));
       
    }       
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
