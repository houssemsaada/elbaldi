/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

<<<<<<< HEAD
import elbaldi.models.Utilisateur;
import java.sql.SQLException;
import java.util.*;
=======
import elbaldi.models.quiz;
import java.util.List;
>>>>>>> origin/selim


/**
 *
<<<<<<< HEAD
 * @author sami
 * @param <T>
 **/


public interface InterfaceCRUD  <T>{
    
   public List<Utilisateur> Filter_utilisateur(String S, String SS);
   public Utilisateur getUserByID(int id_user ) throws SQLException;
    public void ajouterUtlisateur(Utilisateur u);
    void supprimerUtilisateur(int id_user);
    void modifierUtilisateur(Utilisateur u,int id_user);
    List<Utilisateur> afficherUtilisateur();
  
}
    
    

=======
 * @author selim
 */
public interface InterfaceCRUD {
   
    // class QuizCRUD
    
    public void ajouterQuiz(quiz q) ;
    public void modifierquiz(quiz q) ;
    public void supprimerquiz(int id) ;
    public List<quiz> afficherQuiz();
    public quiz getById(int id);
    public List<quiz> filtreByDifficulte(String difficulte);
    
    
}
>>>>>>> origin/selim
