/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author MSI
 */
public class Evenement {
      int id_event,nb_participant;
    String nom,description,date_debut,date_fin,awards;

    public Evenement() {
    }

    public Evenement(int id_event, int nb_participant, String nom, String description, String date_debut, String date_fin, String awards) {
        this.id_event = id_event;
        this.nb_participant = nb_participant;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.awards = awards;
    }
     public Evenement( int nb_participant, String nom, String description, String date_debut, String date_fin, String awards) {
   
        this.nb_participant = nb_participant;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.awards = awards;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", nb_participant=" + nb_participant + ", nom=" + nom + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", awards=" + awards + "\n" + '}';
    }
    
    
    
    
    
}
