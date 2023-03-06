/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import java.sql.SQLException;
import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.services.EvenementService;
import elbaldi.services.ParticipationService;
import elbaldi.utils.MyConnection;

/**
 *
 * @author MSI
 */
public class mainn {
    public static void main(String[] args) throws SQLException {
        
        MyConnection cn = new MyConnection();
      
        EvenementService es = new EvenementService();
        
        Evenement e1= new Evenement(0, "skanderaaa", "ssdddes", "15-10-2021", "19-10-2023", "100dt");
       es.ajouter(e1);
      //es.modifier(e1); 
      //es.supprimer(e1);
       // System.out.println(es.recuperer(e1));   
        
        
         System.out.println("*********************PARTICIPATION***************");
 //Participation p1 = new Participation(21,2462,"12-10-2021",e1);
 ParticipationService ps= new ParticipationService();
        //  ps.supprimer(p1);
        e1.setId_event(26);
       //ps.ajouter(p1);
     //ps.modifier(p1);
       //      System.out.println(ps.recuperer(p1));
    }
    
}
