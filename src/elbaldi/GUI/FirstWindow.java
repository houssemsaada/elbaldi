

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elbaldi.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author selim
 */

public class FirstWindow extends Application {
   
    @Override
    public void start(Stage primaryStage)  {
       
     
  Parent root;
        try {
                root = FXMLLoader.load(getClass().getResource("client.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("QUIZ");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  }} 
  
  /*Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("client.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("QUIZ");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  }} */
    
    /*Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Acceuil Client.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Afficher le module Quiz et promotion");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  }}
    
    */
    public static void main(String[] args) {
        launch(args);
    }

}
