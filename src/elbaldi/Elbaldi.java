/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import elbaldi.models.Evenement;
import elbaldi.services.EvenementServices;

/**
 *
 * @author houss
 */
public class Elbaldi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        EvenementServices es = new EvenementServices();
        
        Evenement e1= new Evenement(10, "amen", "ssdddes", "15-10-2021", "19-10-2023", "100dt");
       es.ajouter(e1);
      es.supprimer(e1);
        System.out.println(es.recuperer(e1));   
    }
    
}
