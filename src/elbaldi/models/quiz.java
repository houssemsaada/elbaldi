/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author selim
 */
public class quiz {
      private int id_quiz;
   private String difficulte;
   private int score;
   private promotion id_promotion;
   private Utilisateur id_user;
   
    public quiz() {
    }

    public quiz(int id_quiz, String difficulte, int score, promotion id_promotion, Utilisateur id_user) {
        this.id_quiz = id_quiz;
        this.difficulte = difficulte;
        this.score = score;
        this.id_promotion = id_promotion;
        this.id_user = id_user;
    }

    public quiz(String difficulte, int score, promotion id_promotion, Utilisateur id_user) {
        this.difficulte = difficulte;
        this.score = score;
        this.id_promotion = id_promotion;
        this.id_user = id_user;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public promotion getpromotion() {
        return id_promotion;
    }

    public void setpromotion(promotion id_promotion) {
        this.id_promotion = id_promotion;
    }


    public Utilisateur getuser() {
        return id_user;
    }

    public void setuser(Utilisateur id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "quiz{ " /*+ "id_quiz=" + id_quiz */ + ", difficulte=" + difficulte + ", score=" + score + ", id_promotion=" + id_promotion + ", id_user=" + id_user + '}';
    }

    

   

    
    
}
