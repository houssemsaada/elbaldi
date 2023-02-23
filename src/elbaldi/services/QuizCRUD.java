/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUDQ;
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
            String req = "INSERT INTO `quiz`(`difficulte`,`score`,`id_promotion`,`id_user` ) VALUES (?,?,?,?)" ;
            PreparedStatement ps=conn.prepareStatement(req); 
           
            ps.setString(1,q.getDifficulte());
            ps.setInt(2,q.getScore());
            ps.setInt(3,q.getpromotion().getId_promotion());
            ps.setInt(4,q.getuser().getid_user());
            ps.executeUpdate();
            
            System.out.println("Quiz ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Quiz non ajouté");                  
        }   
    }
     
   
    @Override
    public void modifierquiz(quiz q) {
    try {
        String req = "UPDATE `quiz` SET `difficulte` = ?, `score` = ?, `id_promotion` = ?,`id_user` = ? WHERE `id_quiz` = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, q.getDifficulte());
        ps.setInt(2, q.getScore());
       ps.setInt(3,q.getpromotion().getId_promotion());
       ps.setInt(4,q.getuser().getid_user());
       ps.setInt(5, q.getId_quiz());
       
        ps.executeUpdate();
        System.out.println("Quiz modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

   
      @Override
    public void supprimerquiz(int id) {
        try {
            String req = "DELETE FROM `quiz` WHERE `id_quiz` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
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
         
         PromotionCRUD pro = new PromotionCRUD();
         int id_promotionn = RS.getInt(4);
         promotion pr = pro.getById(id_promotionn);
         q.setpromotion(pr);
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(5);
         Utilisateur uu = uc.getUserByID(user);
         q.setuser(uu);
         
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
            PromotionCRUD pro = new PromotionCRUD();
         int id_promotionn = RS.getInt(4);
         promotion pr = pro.getById(id_promotionn);
         q.setpromotion(pr);
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(5);
         Utilisateur uu = uc.getUserByID(user);
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
            PromotionCRUD pro = new PromotionCRUD();
         int id_promotionn = RS.getInt(4);
         promotion pr = pro.getById(id_promotionn);
         q.setpromotion(pr);
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(5);
         Utilisateur uu = uc.getUserByID(user);
         q.setuser(uu);
            list.add(q);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

}


