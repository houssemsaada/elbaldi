/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import elbaldi.models.Reservation;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import elbaldi.services.ReservationCrud;
import elbaldi.utils.MyConnection;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Elbaldi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //ajout bonplan 
        // MyConnection conn = MyConnection.getInstance();
        //bonplan p1 = new bonplan(12,"Tunis","darjeld","resto","img",8);
        //BonplanCrud per = new BonplanCrud();
        //per.ajouterBonplan(p1);
        
       
        //ajout reservation 
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(2, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
        //res1.ajouterReservation(reservation);
         
      
        //Modifier bonplan
        //MyConnection conn = MyConnection.getInstance();
        //bonplan p1 = new bonplan(10,"Tunis","darjeld","resto","img",1);
        //BonplanCrud per = new BonplanCrud();
        //per.modifierBonplan(p1); 
  
          
         //modifier reservation 
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(10, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
        //res1.modifierReservation(reservation);
      
       
       //afficher bonplan
          //MyConnection conn = MyConnection.getInstance();
          //bonplan p1 = new bonplan(10,"Tunis","darjeld","resto","img",1);
          //BonplanCrud per = new BonplanCrud();
          //System.out.println(per.afficherBonplan());
          
          
       //afficher reservation 
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(45, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
        
       //System.out.println(res1.afficherReservation());
        
     
        
       
       //supprimer bonplan
          //MyConnection conn = MyConnection.getInstance();
          //bonplan p1 = new bonplan(10,"Tunis","darjeld","resto","img",1);
          //BonplanCrud per = new BonplanCrud();
          //per.supprimerbonplan(2);
          
          
        //supprimer reservation 
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(1, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
       // res1.supprimerReservation(1);
          
        
       
       // filtreByDate 
         //Date datefiltre = Date.valueOf("2023-02-15");
         //System.out.println(res1.filtreByDate(datefiltre));
         //ou bien 
         //System.out.println(res1.filtreByDate(Date.valueOf("2023-02-15")));
          
    }
    
    
    
    
    
}