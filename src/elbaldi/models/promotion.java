/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.time.LocalDate;

/**
 *
 * @author selim
 */
public class promotion {
    
    private int id_promotion;
    private String code_promo;
    private float taux;
   private LocalDate date_debut;
    private LocalDate date_fin;
    
    public promotion() {
    }

    public promotion(int id_promotion, String code_promo, float taux, LocalDate date_debut, LocalDate date_fin) {
        this.id_promotion = id_promotion;
        this.code_promo = code_promo;
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public promotion(String code_promo, float taux, LocalDate date_debut, LocalDate date_fin) {
        this.code_promo = code_promo;
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    
    
    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public String getCode_promo() {
        return code_promo;
    }

    public void setCode_promo(String code_promo) {
        this.code_promo = code_promo;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    
    @Override
    public String toString() {
        return "promotion{" + "id_promotion=" + id_promotion + ", code_promo=" + code_promo + ", taux=" + taux + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    public boolean isValid() {
        return date_debut.isBefore(date_fin);
    }
}


