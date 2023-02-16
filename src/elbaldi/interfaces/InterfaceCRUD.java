/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.quiz;
import java.util.List;


/**
 *
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
