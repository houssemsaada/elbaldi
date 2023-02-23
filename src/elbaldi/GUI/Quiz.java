/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;

import java.io.IOException;
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
public class Quiz extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AjouterQuiz.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Ajouter Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
        
       /* Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ModifierQuiz.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Modifier Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    /*
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("SupprimerQuiz.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Supprimer Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    */
       /* Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherQuiz.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Afficher Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }*/
        
     /* @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
