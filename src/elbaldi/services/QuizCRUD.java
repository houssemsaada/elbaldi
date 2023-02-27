/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUDQ;
import elbaldi.models.Evenement;
import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.quiz;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author selim
 */
public class QuizCRUD implements InterfaceCRUDQ{
    
    Connection conn = MyConnection.getInstance().getConn();
   
    
    @Override
    public void ajouterQuiz(quiz q) {
         try { 
            String req = "INSERT INTO `quiz`(`nom`,`difficulte`,`score`,`id_user`,`image` ) VALUES (?,?,?,?,?)" ;
            PreparedStatement ps=conn.prepareStatement(req); 
           
            ps.setString(1,q.getNom());
            ps.setString(2,q.getDifficulte());
            ps.setInt(3,q.getScore());
          
            ps.setInt(4,q.getuser().getid_user());
            ps.setString(5,q.getImgview());
            ps.executeUpdate();
            
            System.out.println("Quiz ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Quiz non ajouté");                  
        }   
    }
     
   
    @Override
    public void modifierquiz(quiz q) {
    try {
        String req = "UPDATE `quiz` SET  `nom` = ?,`difficulte` = ?, `score` = ?,`id_user` = ?,`image` = ? WHERE `id_quiz` = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        
        ps.setString(1, q.getNom());
        ps.setString(2, q.getDifficulte());
        ps.setInt(3, q.getScore());
  
        ps.setInt(4,q.getuser().getid_user());
       
        ps.setString(5, q.getImgview());
        ps.setInt(6, q.getId_quiz());
       
        ps.executeUpdate();
        System.out.println("Quiz modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

   
      @Override
    public void supprimerquiz(quiz q) {
        try {
  
         String req = " DELETE FROM quiz where nom  = ?   ";
       
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setString(1, q.getNom());
             ps.executeUpdate();
              System.out.println("Quiz deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
   @Override
   public List<quiz> afficherQuiz() {
   List<quiz> list = new ArrayList<>();
    try {
        String req = "Select * from quiz";
        Statement st = conn.createStatement();
       
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
         quiz q = new quiz ();
        q.setId_quiz(RS.getInt("id_quiz"));
        
         q.setDifficulte(RS.getString(2));
        q.setScore(RS.getInt(3));
         
         
         
       UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(4);
         Utilisateur uu = uc.getUserByID(user);
         q.setuser(uu);
         
          q.setNom(RS.getString(5));
          q.setImgview(RS.getString(6));
         
         list.add(q);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

public quiz getById(int id) {
    quiz q = new quiz();
    try {
        String req = "Select * from quiz where id_quiz = " + id;
        Statement st = conn.createStatement();
       
        ResultSet RS = st.executeQuery(req);
        if (RS.next()) {
            
            
            q.setId_quiz(RS.getInt("id_quiz"));
           
            q.setDifficulte(RS.getString(2));
            q.setScore(RS.getInt(3));
           
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(4);
         Utilisateur uu = uc.getUserByID(user);
          q.setNom(RS.getString(5));
         q.setImgview(RS.getString(6));
         q.setuser(uu);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return q;
}


    @Override
    public List<quiz> filtreByDifficulte(String difficulte) {
    List<quiz> list = new ArrayList<>();
    try {
        String req = "Select * from quiz where difficulte = '" + difficulte + "'";
        Statement st = conn.createStatement();
       
        ResultSet RS = st.executeQuery(req);
        while (RS.next()) {
            quiz q = new quiz();
            q.setId_quiz(RS.getInt("id_quiz"));
         
            q.setDifficulte(RS.getString(2));
            q.setScore(RS.getInt(3));
         
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(4);
         Utilisateur uu = uc.getUserByID(user);
         q.setNom(RS.getString(5));
         q.setImgview(RS.getString(6));
         q.setuser(uu);
         
      
            list.add(q);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

}


