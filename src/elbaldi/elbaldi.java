/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import elbaldi.GUI.ItemBackController;
import elbaldi.models.*;
import static elbaldi.models.Role.client;
import elbaldi.services.*;
import java.sql.Date;
import java.sql.SQLException;
import static java.sql.Timestamp.valueOf;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Yasmine
 */
public class elbaldi {
     /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, ParseException {
        /*
       /* 
         System.out.println("-------------------------------------------------------------------------------");
         System.out.println("                              PARTIE UTILISATEUR");
         System.out.println("-------------------------------------------------------------------------------");
     
       PromotionCRUD e = new PromotionCRUD();
       System.out.println(e.promocodeExiste("ELBALDI753"));
      
        Utilisateur u1 = new Utilisateur("youssef", "selim","selim@tesprit.tn","10/16/2000",2345564,"tunis","test","test",client);
        UtilisateurCRUD uti = new UtilisateurCRUD();
       /* 
        uti.ajouterUtlisateur(u1);
        uti.supprimerUtilisateur(2459);
        Utilisateur mod = new Utilisateur(2460,"abc", "test","test@test","10/16/2000",2345564,"tunis","test","test",client);
        uti.modifierUtilisateur(mod, mod.getid_user());
      
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
        
          */     
        //3CRUD YASMINE 
        System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Gestion des categories ----------------");
        System.out.println("-------------------------------------------------------------");
        CategorieCRUD categ = new CategorieCRUD();
        categorie c1 = new categorie( "test","ttttt");
        
        //ajouter categorie 
        categ.ajouterCategorie(c1);
        
        //afficher categories
        System.out.println(categ.affichercategorie());
        
        //Récupérer une categorie existante avec un  id categorie existant
        categorie c = categ.getCategorieById(1); 
         
        c.setNom_categorie("testmodif"); //Modifier le nom de categorie
        categ.modifierCategorie(c,1); //Appeler la méthode pour mettre à jour la categorie
                
        //filtrer les categorie par nom de categorie 
       System.out.println(categ.filtrerCategorie("testmodif"));
       
       //supprimer categorie
        //categ.supprimerCategorie(c1);
        
        
         System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Gestion des Produits ----------------");
        System.out.println("-------------------------------------------------------------");
        
         ProduitCRUD prod = new ProduitCRUD();  
         //categorie c2 = new categorie( 2,"test","ttttt");
        //ajouter produit
      //  produit p1 = new produit("64","tableautest","rrrrr aaaaaa","zzz",45.5f,5,categ.getCategorieById(1));
     //   prod.ajouterProduit(p1);
        
        //afficher
        System.out.println(prod.afficherProduit());
        

        //recuperer produit par reference produit
       System.out.println(prod.getByRefProduit("TUN6190090"));
        
        //modifier produit
        
        //Récupérer un produit existant avec une référence de produit existante
        //Modifier le libellé de produit
     //  prod.modifierProduit(p1); //Appeler la méthode pour mettre à jour le produit       

       //filtrer les produits par prix de vente entre une valeur maximale et minimale 
       System.out.println(prod.filtreByPrixVente(45.0f, 55.5f));
        

       // filtrer les produits par quantite
       System.out.println(prod.filtrerProduitParQuantite(3));
       
        //supprimer produit 
        //prod.supprimerProduit(p1);
       
         System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Gestion des commentaires ----------------");
        System.out.println("-------------------------------------------------------------");
        
        

       // Utilisateur u2 = new Utilisateur("youssef", "selim","selim@tesprit.tn","10/16/2000","2345564","tunis","test","test",client);
        UtilisateurCRUD uti = new UtilisateurCRUD();
  //      uti.ajouterUtlisateur(u2);
        //ajouter commentaire
        commentaireCRUD comment = new commentaireCRUD();
  //      commentaire commentaire1 = new commentaire("aaaa",p1,uti.getUserByID(1));
    //    comment.ajouterCommentaire(commentaire1);
        
        //modifier commentaire 
        commentaire cmm = comment.getCommentaireById(42); 
        cmm.setContenu("eeeeee"); //Modifier le contenu de commentaire
        comment.modifierCommentaire(cmm); //Appeler la méthode pour mettre à jour le commentaire
    
        
        //supprimer commentaire
        //comment.supprimerCommentaire(comment.getCommentaireById(17));
        
        //afficher commentaires 
        System.out.println(comment.afficherCommentaire());
        //Date
        String date_commentai = "2023-02-18";
        Date date_comment = Date.valueOf(date_commentai);
      System.out.println(comment.filterParDate(date_comment));
      //afficher commentaire par article 
    //  System.out.println(comment.getCommentairesByArticle(p1));
      
      
      
        
        /*
        
         System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Module quiz et promotions ----------------");
        System.out.println("-------------------------------------------------------------");
        
      //----------------------------Module QUIZ-----------------------------

/*
        
        quiz q1= new quiz("facile",0,1,1);
=======
      */  
         

              
             

//----------------------------Module QUIZ-----------------------------

        Utilisateur ui = new Utilisateur(1);
      
        quiz q1= new quiz(5,"Sousse","facile",0,ui,"aaa");
        QuizCRUD qu= new QuizCRUD();
        //Ajout d'un Quiz 
                 //qu.ajouterQuiz(q1);
        //Modification d'un Quiz          
                  //qu.modifierquiz(q1);
               //  qu.modifierquiz(q1,0);
        //Suppression d'un Quiz 
               // qu.supprimerquiz(q1);
        //Affichage d'un Quiz 
                //System.out.println( qu.afficherQuiz());
               // System.out.println( qu.afficherQuiz());

        //Get By Id 
              // System.out.println(qu.getById(65));
        //Filtre By difficulte

             //  System.out.println(qu.filtreByDifficulte("Moyenne"));

               //System.out.println(qu.filtreByDifficulte("aa"));
              

        
          
  //-----------------------------Module Question------------------------------------------------               
       quiz q5= new quiz();
       q5.setId_quiz(81);
       question qq1= new question("Facile","Quel est le nom du plat national tunisien à base de semoule et de légumes?","Brik","Couscous","Chorba","Couscous",q5);
       question qq2= new question("Facile","Quel est le nom de la ville tunisienne connue pour ses maisons blanches et bleues?","Sidi Bou Said","Hammamet","Tozeur","Sidi Bou Said",q5);
       question qq3= new question("Facile","Quel est le nom du célèbre festival de musique qui se déroule chaque été à Hammamet?","Jazz à Carthage","Festival de Carthage","Festival international de Hammamet","Festival international de Hammamet",q5);
       QuestionCRUD qq= new QuestionCRUD();
        System.out.println(qq.filtreByidquiz(q5));
   
       //Ajout d'une Question      
              // qq.ajouterQuestion(qq3,11);
       //Modification d'une Question    
                 // qq.modifierquestion(qq1);   

                 qq.modifierquestion(qq1,178);   

       //Suppression d'une Question  
                // qq.supprimerquestion(qq1);
       //Affichage d'une Question 

                // System.out.println( qq.afficherQuestion());
       //Get By Id 
                //System.out.println(qq.getById(2));
       //Filtre By difficulte
 

   /*             
//-----------------------------Module Promotion---------------------------------------------------                 
                
      // créer un objet Promotion
          promotion prom1 = new promotion(21,"yyyyyy", 0.25f, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 21));
     // appeler la méthode pour ajouter la promotion
          PromotionCRUD pc2 = new PromotionCRUD();
  
         //Ajout d'une Promotion
                // pc2.ajouterpromotion(prom1);
         //Modification d'une Promotion        
              //  pc2.modifierpromotion(prom1);
         //Suppression d'une Promotion 
                pc2.supprimerpromotion(21);
         //Affichage d'une Promotion
               System.out.println( pc2.afficherpromotion());
         //Get By Id 
              System.out.println(pc2.getById(4));
         //Filtre By difficulte
              System.out.println(pc2.filtreBytaux(0.25f));
     
       
     //----------------------------------------------------------------------------------------------------------/
              
         System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Module commandes et livraison  ----------------");
        System.out.println("-------------------------------------------------------------");
              
          
        String date_comm = "2024-02-14";
=======
                 //System.out.println( qq.afficherQuestion());
       //Get By Id 
              //System.out.println(qq.getById(39));
       //Filtre By difficulte
                //System.out.println(qq.filtreByDifficulte("facile"));
    
/*           

//----------------------------------------------------------------------------------------------------------------------                
/*
        String date_comm = "2023-02-10";
>>>>>>> Origin/selim
        Date date_com = Date.valueOf(date_comm);
        String date_liv = "2023-02-18";
        Date date_livr = Date.valueOf(date_liv);
//---------------------- module commande--------------------------------------------------------------------        
        commande com1 = new commande(2460, "anunule", date_com);
        CommandeCRUD comm = new CommandeCRUD();
        // comm.ajouterCommande(com1);
         //comm.modifierCommande(com1);
        //comm.supprimerCommande(9);
        // System.out.println( comm.afficherCommande());
         //System.out.println(comm.filtreByDate(date_com));
         //System.out.println(comm.sortCommandesByDate());
        // System.out.println(comm.filtreByuser(2));
//----------------------module livraison--------------------------------------------------------------------        
   /*   livraison l1 = new livraison(24, "cbon", "tozeuur", date_livr, com1);
        livraisonCRUD liv = new livraisonCRUD();
              //  liv.ajouterLivraison(l1, com1);
        //liv.modifierLivraison(l1, com1);
        //liv.supprimerLivraison(6);
        System.out.println(liv.afficherLivraison());
       // System.out.println(liv.filtreByDate(date_livr));
        //System.out.println(liv.sortlivraisonByDate());
        //System.out.println(liv.filtreBycommande(c1)); */
//----------------------module panier------------------------------------------------------------------------
/*     
panier pan1 = new panier("f03",5,5,22);
        panierCRUD pan = new panierCRUD();
        pan.ajouterPanier(pan1);
        pan.modifierPanier(pan1);
        //pan.supprimerPanier(3);
        System.out.println(pan.afficherPanier());
        */
        /*
         System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Module evenements ----------------");
        System.out.println("-------------------------------------------------------------");
        
        
        
        
       EvenementServices es = new EvenementServices();
        
        Evenement e1= new Evenement(23, 8, "skander", "public", "15-10-2021", "19-10-2023", "100dt");
        // es.ajouter(e1);
         es.supprimer(e1);
        System.out.println(es.recuperer(e1));   
        
         System.out.println("-------------------------------------------------------------");
        System.out.println("----------------Module Bonplan ----------------");
        System.out.println("-------------------------------------------------------------");
        
         //---------------------------------------------------------Crud Bonplan-------------------------------------------------------------------------------//
        //ajout bonplan
        // MyConnection conn = MyConnection.getInstance();
        bonplan bp = new bonplan(25,"Tunis","darjeld","resto","img",1);
        BonplanCrud per = new BonplanCrud();
        per.ajouterBonplan(bp);
       
       
       
         //Modifier bonplan
            //MyConnection conn = MyConnection.getInstance();
               bonplan bp1 = new bonplan(25,"beja","darjeld","resto","img",1);
               //BonplanCrud per = new BonplanCrud();
              per.modifierBonplan(bp1);
       
       
       
        //afficher bonplan
             //MyConnection conn = MyConnection.getInstance();
                  //bonplan bp2 = new bonplan(10,"Tunis","darjeld","resto","img",1);
                 //BonplanCrud per = new BonplanCrud();
                System.out.println(per.afficherBonplan());
         
         
       
         
        //supprimer bonplan
              //MyConnection conn = MyConnection.getInstance();
                    bonplan bp3 = new bonplan(10,"Tunis","darjeld","resto","img",1);
                    //BonplanCrud per = new BonplanCrud();
                   per.supprimerbonplan(25);
         
         
         
         
       
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
<<<<<<< HEAD
      */  
    }
}


