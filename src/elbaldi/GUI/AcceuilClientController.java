/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class AcceuilClientController implements Initializable {

    @FXML
    private Button fxquiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(ActionEvent event) throws IOException {
            
      FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("StartQUIZ.fxml"));
        Parent root = loader.load();
        fxquiz.getScene().setRoot(root);
    }
    
}
