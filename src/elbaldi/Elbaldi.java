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
        
      System.out.println("----------Module BonPlan------------ ");
        
         //---------------------------------------------------------Crud Bonplan-------------------------------------------------------------------------------//
        //ajout bonplan
        // MyConnection conn = MyConnection.getInstance();
        bonplan bp = new bonplan(55,"Tunis","darjeld","resto","img",1);
        BonplanCrud per = new BonplanCrud();
        per.ajouterBonplan(bp);
       
       
       
         //Modifier bonplan
            //MyConnection conn = MyConnection.getInstance();
               bonplan bp1 = new bonplan(14,"sousse","darjeld","resto","img",1);
               //BonplanCrud per = new BonplanCrud();
              per.modifierBonplan(bp1);
       
       
       
        //afficher bonplan
             //MyConnection conn = MyConnection.getInstance();
                  //bonplan bp2 = new bonplan(23,"Tunis","darjeld","resto","img",1);
                  //BonplanCrud per = new BonplanCrud();
               // System.out.println(per.afficherBonplan());
         
         
       
         
        //supprimer bonplan
              //MyConnection conn = MyConnection.getInstance();
                   // bonplan bp3 = new bonplan(12,"Tunis","darjeld","resto","img",1);
                    //BonplanCrud per = new BonplanCrud();
                  /// per.supprimerbonplan(12);
         
         
         
         
       
        //ajout reservation
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        Reservation reservation = new Reservation(124, 3, date, 14, 1);
        ReservationCrud res1 = new ReservationCrud();
        res1.ajouterReservation(reservation);
         
     
       
         
         //modifier reservation
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(10, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
        res1.modifierReservation(reservation);
     
       
       
         
         
       //afficher reservation
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(45, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
       
       System.out.println(res1.afficherReservation());
       
     
       
       
   
         
         
        //supprimer reservation
        //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        //Reservation reservation = new Reservation(1, 3, date, 3, 20);
        //ReservationCrud res1 = new ReservationCrud();
       // res1.supprimerReservation(1);
         
       
       
       // filtreByDate
         Date datefiltre = Date.valueOf("2023-02-15");
         //System.out.println(res1.filtreByDate(datefiltre));
         //ou bien
         System.out.println(res1.filtreByDate(Date.valueOf("2023-02-15")));
         
    }
    
}