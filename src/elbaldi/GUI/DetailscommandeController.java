/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class DetailscommandeController implements Initializable {

    @FXML
    private TextField tduser;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    public void setTduser(String message) {
        this.tduser.setText(message);
    }

    public void setTfetat(String message) {
        this.tfetat.setText(message);
    }

    public void setTfdate(String message) {
        this.tfdate.setText(message);
    }
}
