/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import elbaldi.models.*;
import static elbaldi.models.Role.client;
import elbaldi.services.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class elbaldi {
     /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        
         System.out.println("-------------------------------------------------------------------------------");
         System.out.println("                              PARTIE UTILISATEUR");
         System.out.println("-------------------------------------------------------------------------------");
     
       
       
      
        Utilisateur u1 = new Utilisateur("ben mahmoud", "khaled","test@test","10/16/2000",2345564,"tunis","test","test",client);
        UtilisateurCRUD uti = new UtilisateurCRUD();
        
        uti.ajouterUtlisateur(u1);
        uti.supprimerUtilisateur(2423);
        Utilisateur mod = new Utilisateur("testttt", "test","test@test","10/16/2000",2345564,"tunis","test","test",client);
        uti.modifierUtilisateur(mod,2424);
      
        System.out.println( uti.afficherUtilisateur());
        
        System.out.println(uti.getUserByID(2433)); 
        System.out.println(uti.Filter_utilisateur("nom", "test"));
        
        
        
     //-----------------------------------------------------------------------------------------------------------------------------------------
         System.out.println("-------------------------------------------------------------------------------");
         System.out.println("                             PARTIE RECLAMATION");
         System.out.println("-------------------------------------------------------------------------------");
     
     
     Reclamation r1 = new Reclamation("10/16/2000","tunis",2425);
        ReclamationCRUD rec = new ReclamationCRUD();
        
        rec.ajouterReclamation(r1);
         rec.supprimerReclamation(2425);
        Reclamation mod2 = new Reclamation("10/16/2000","testetst",2425);
        rec.modifierReclamation(mod2,2424);
      
         System.out.println( rec.afficherReclamation());
        
         System.out.println(rec.getReclamationByID(2433)); 
        System.out.println(rec.Filter_Reclamation("10/16/2000", "tunis"));
        
                
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Module Produits et categories ----------------");
        System.out.println("-------------------------------------------------------------");
         categorie c1 = new categorie(2, "art");
         categorie c2 = new categorie(3, "art et culture");
         CategorieCRUD categ = new CategorieCRUD();
         categ.ajouterCategorie(c1);
         categ.ajouterCategorie(c2);
         
         System.out.println("--------La liste des categories apres l'ajout----------- ");
        //afficher la liste des categories
        System.out.println(categ.affichercategorie());
        
        //ajouter produit
        produit p1 = new produit("f03","tableau","tableau artistique","zzz",45.1f,0.3f,55.5f,2,1,2);
        produit p2 = new produit("f04","piano","musique","zdd",45.1f,0.3f,55.5f,3,1,2);
        ProduitCRUD prod = new ProduitCRUD();
        
        prod.ajouterProduit(p1);
        prod.ajouterProduit(p2);
        System.out.println("------------La liste des produits apres l'ajout------------- ");

        //afficher la liste des produits
        System.out.println(prod.afficherProduit());
        
         //modifier categorie
        categorie c = categ.getCategorieById(2); //Récupérer une categorie existante avec un  id categorie existant
       c.setNom_categorie("fan _modif"); //Modifier le nom de categorie
       categ.modifierCategorie(c); //Appeler la méthode pour mettre à jour la categorie
        //afficher categorie apres modification
        System.out.println("-------------La liste des categories apres modification-------------");
        System.out.println(categ.affichercategorie());
        
        //modifier produit
       produit p = prod.getByRefProduit("f02"); //Récupérer un produit existant avec une référence de produit existante
       p.setLibelle("tableau de yasmine"); //Modifier le libellé de produit
       prod.modifierProduit(p); //Appeler la méthode pour mettre à jour le produit
       //afficher produit apres modification
        System.out.println("---------------La liste des produits  apres modification------------- ");
        System.out.println(prod.afficherProduit());
        //recuperer categorie par id categorie
       System.out.println("La categorie dont l'id donnee en parametre ");

       System.out.println(categ.getCategorieById(2));
        
       System.out.println("----------------Le produit dont la reference donnee en parametre-------- ");

       //recuperer produit par reference produit
       System.out.println(prod.getByRefProduit("f01"));
       
        
       System.out.println("----------------La liste des produits qui ont un prix de ventre entre la valeur min et max------- ");

       //filtrer les produits par prix de vente entre une valeur maximale et minimale 
       System.out.println(prod.filtreByPrixVente(55.0f, 55.5f));
        
       System.out.println("-----La liste des produits qui ont la quantite superieure a la quantite donnee en parametre------- ");

       // filtrer les produits par quantite (on affiche les prooduits qui on ont une quantite superieure a la quantite donnee en parametre 
       System.out.println(prod.filtrerProduitParQuantite(3));
       
       System.out.println("-------La liste des categories par nom de categorie------ ");

       //filtrer les categorie par nom de categorie 
       System.out.println(categ.filtrerCategorie("art"));
       
       //supprimer categorie
        
        //categ.supprimerCategorie(1);
        System.out.println("------La liste des categories apres suppression------- ");

        System.out.println(categ.affichercategorie());
        
        //supprimer produit 
        
       // prod.supprimerProduit("f01");
        System.out.println("-------La liste des produits  apres suppression----- ");

        System.out.println(prod.afficherProduit());
        
        
      //----------------------------Module QUIZ-----------------------------


        
        quiz q1= new quiz("facile",0,1,1);
        QuizCRUD qu= new QuizCRUD();
        //Ajout d'un Quiz 
                  qu.ajouterQuiz(q1);
        //Modification d'un Quiz          
                  qu.modifierquiz(q1);
        //Suppression d'un Quiz 
                 //qu.supprimerquiz(4);
        //Affichage d'un Quiz 
                System.out.println( qu.afficherQuiz());
        //Get By Id 
               //System.out.println(qu.getById(2));
        //Filtre By difficulte
               System.out.println(qu.filtreByDifficulte("Moyenne"));
        
        
//-----------------------------Module Question------------------------------------------------               
               
       question qq1= new question("Facile"," Quelle est la plus gande ville de la Tunisie?","Tunis","Sousse","Sfax","Tunis",8);
       QuestionCRUD qq= new QuestionCRUD();
   
       //Ajout d'une Question      
                  qq.ajouterQuestion(qq1);
       //Modification d'une Question    
                  qq.modifierquestion(qq1);   
       //Suppression d'une Question  
                 //qq.supprimerquestion(3);
       //Affichage d'une Question 
                 System.out.println( qq.afficherQuestion());
       //Get By Id 
                System.out.println(qq.getById(2));
       //Filtre By difficulte
                 System.out.println(qq.filtreByDifficulte("difficile"));
    

                
//-----------------------------Module Promotion---------------------------------------------------                 
                
      // créer un objet Promotion
          promotion prom1 = new promotion("TYIBF67", 0.25f, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 21));
     // appeler la méthode pour ajouter la promotion
          PromotionCRUD pc2 = new PromotionCRUD();
  
         //Ajout d'une Promotion
                 pc2.ajouterpromotion(prom1);
         //Modification d'une Promotion        
                pc2.modifierpromotion(prom1);
         //Suppression d'une Promotion 
                //pc2.supprimerpromotion(2);
         //Affichage d'une Promotion
               System.out.println( pc2.afficherpromotion());
         //Get By Id 
              System.out.println(pc2.getById(4));
         //Filtre By difficulte
              System.out.println(pc2.filtreBytaux(0.25f));
              
              
              
          
        String date_comm = "2023-02-10";
        Date date_com = Date.valueOf(date_comm);
        String date_liv = "2023-02-18";
        Date date_livr = Date.valueOf(date_liv);
//---------------------- module commande--------------------------------------------------------------------        
        commande com1 = new commande(6,1, "delic", date_com);
        CommandeCRUD comm = new CommandeCRUD();
         comm.ajouterCommande(com1);
         comm.modifierCommande(com1);
        //comm.supprimerCommande(1);
         System.out.println( comm.afficherCommande());
         System.out.println(comm.filtreByDate(date_com));
         System.out.println(comm.sortCommandesByDate());
         System.out.println(comm.filtreByuser(2));
//----------------------module livraison--------------------------------------------------------------------        
        livraison l1 = new livraison(22, "cbon", "tozeuur", date_livr, com1);
        livraisonCRUD liv = new livraisonCRUD();
                liv.ajouterLivraison(l1, com1);
        liv.modifierLivraison(l1, com1);
        //liv.supprimerLivraison(6);
        System.out.println(liv.afficherLivraison());
        System.out.println(liv.filtreByDate(date_livr));
        System.out.println(liv.sortlivraisonByDate());
        //System.out.println(liv.filtreBycommande(c1));
//----------------------module panier------------------------------------------------------------------------
        panier pan1 = new panier("f03",5,5,22);
        panierCRUD pan = new panierCRUD();
        pan.ajouterPanier(pan1);
        pan.modifierPanier(pan1);
        //pan.supprimerPanier(3);
        System.out.println(pan.afficherPanier());
        
        
        System.out.println("----------Module Evenements------------ ");
        
       EvenementServices es = new EvenementServices();
        
        Evenement e1= new Evenement(10, "amen", "ssdddes", "15-10-2021", "19-10-2023", "100dt");
         es.ajouter(e1);
         es.supprimer(e1);
        System.out.println(es.recuperer(e1));   
        
        
         System.out.println("----------Module BonPlan------------ ");
        
         //---------------------------------------------------------Crud Bonplan-------------------------------------------------------------------------------//
        //ajout bonplan
        // MyConnection conn = MyConnection.getInstance();
        bonplan bp = new bonplan(12,"Tunis","darjeld","resto","img",1);
        BonplanCrud per = new BonplanCrud();
        per.ajouterBonplan(bp);
       
       
       
         //Modifier bonplan
            //MyConnection conn = MyConnection.getInstance();
               bonplan bp1 = new bonplan(10,"Tunis","darjeld","resto","img",1);
               //BonplanCrud per = new BonplanCrud();
              per.modifierBonplan(bp);
       
       
       
        //afficher bonplan
             //MyConnection conn = MyConnection.getInstance();
                  //bonplan bp2 = new bonplan(10,"Tunis","darjeld","resto","img",1);
                 //BonplanCrud per = new BonplanCrud();
                System.out.println(per.afficherBonplan());
         
         
       
         
        //supprimer bonplan
              //MyConnection conn = MyConnection.getInstance();
                    bonplan bp3 = new bonplan(10,"Tunis","darjeld","resto","img",1);
                    //BonplanCrud per = new BonplanCrud();
                   //per.supprimerbonplan(2);
         
         
         
         
       
        //ajout reservation
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        Reservation reservation = new Reservation(2, 3, date, 3, 1);
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
