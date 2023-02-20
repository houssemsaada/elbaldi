/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import static elbaldi.models.Role.client;
import elbaldi.models.Utilisateur;
import elbaldi.models.categorie;
import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.UtilisateurCRUD;
import elbaldi.services.livraisonCRUD;
import elbaldi.services.panierCRUD;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author houss
 */
public class houssem {

    public static void main(String[] args) throws SQLException {
        String date_comm = "2023-02-10";
        Date date_com = Date.valueOf(date_comm);
        String date_liv = "2023-02-18";
        Date date_livr = Date.valueOf(date_liv);
        Utilisateur u1 = new Utilisateur(2460, "ben mahmoud", "khaled", "test@test", "10/16/2000", 2345564, "tunis", "test", "test", client);
        UtilisateurCRUD uti = new UtilisateurCRUD();
        Utilisateur u2 = new Utilisateur(2462);
        // uti.ajouterUtlisateur(u1);
        categorie c1 = new categorie(2, "art");
        CategorieCRUD categ = new CategorieCRUD();
        // categ.ajouterCategorie(c1);
        produit p1 = new produit("f03", "tableau", "tableau artistique", "zzz", 45.1f, 0.3f, 55.5f, 2, 1, 2);
        ProduitCRUD prod = new ProduitCRUD();
        // prod.ajouterProduit(p1);
//---------------------- module commande--------------------------------------------------------------------        
        commande com1 = new commande(128, u2, "aaaaaa", date_com);
        commande com2 = new commande(129, u2, "aaaaaa", date_com);

        CommandeCRUD comm = new CommandeCRUD();
        //comm.ajouterCommande(com2);
        //comm.modifierCommande(com1);
        //comm.supprimerCommande(1);
        // System.out.println( comm.afficherCommande());
        //System.out.println(comm.filtreByDate(date_com));
        //System.out.println(comm.sortCommandesByDate());
        // System.out.println(comm.filtreByuser(u2));
//----------------------module livraison--------------------------------------------------------------------        
        livraison l1 = new livraison("le", "nabeul", date_livr, com1);
        livraison l2 = new livraison("le", "nabeul", date_livr, com2);

        livraisonCRUD liv = new livraisonCRUD();
        //liv.ajouterLivraison(l2);
        //liv.modifierLivraison(l1);
        //liv.supprimerLivraison(6);
        //System.out.println(liv.afficherLivraison());
        //System.out.println(liv.filtreByDate(date_livr));
        //System.out.println(liv.sortlivraisonByDate());
        //System.out.println(liv.filtreBycommande(com2));
//----------------------module panier------------------------------------------------------------------------
        panier pan1 = new panier(p1,u1, 5, 5, 22);
        panierCRUD pan = new panierCRUD();
       // pan.ajouterPanier(pan1);
        //pan.modifierPanier(pan1);
        //pan.supprimerPanier(3);
        //System.out.println(pan.afficherPanier());
        System.out.println(pan.filtreByuser(u1));
    }
}
