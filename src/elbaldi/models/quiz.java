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
   private int id_promotion;
   private int id_user;
   
    public quiz() {
    }

    public quiz(int id_quiz, String difficulte, int score) {
        this.id_quiz = id_quiz;
        this.difficulte = difficulte;
        this.score = score;
    }

    public quiz(int id_quiz, String difficulte) {
        this.id_quiz = id_quiz;
        this.difficulte = difficulte;
    }

    public quiz(int id_quiz, String difficulte, int score, int id_promotion) {
        this.id_quiz = id_quiz;
        this.difficulte = difficulte;
        this.score = score;
        this.id_promotion = id_promotion;
    }

    public quiz(String difficulte, int score, int id_promotion) {
        this.difficulte = difficulte;
        this.score = score;
        this.id_promotion = id_promotion;
    }

    public quiz(String difficulte, int score, int id_promotion, int id_user) {
       
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

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "quiz{" + "id_quiz=" + id_quiz + ", difficulte=" + difficulte + ", score=" + score + ", id_promotion=" + id_promotion + ", id_user=" + id_user + '}';
    }

    
    
}
