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
        
        Evenement e1= new Evenement(2459,2459, "skanderaaa", "ssdddes", "15-10-2021", "19-10-2023", "100dt");
       // es.ajouter(e1);
      //es.modifier(e1); 
      //es.supprimer(e1);
        System.out.println(es.recuperer(e1));   
        
        
         System.out.println("*********************PARTICIPATION***************");
 Participation p1 = new Participation(1,2459,4, "12/12/2001");
 ParticipationService ps= new ParticipationService();
        //  ps.supprimer(p1);
         //ps.ajouter(p1);
       // ps.modifier(p1);
             System.out.println(ps.recuperer(p1));
    }
    
}
