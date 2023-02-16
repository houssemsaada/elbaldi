/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.util.Date;
/**
 *
 * @author user
 */
public class Reservation {
    private int id_reservation;
    private int nombre_personnes;
    private Date date_reservation;
    private int id_bonplan;
    private int id_user;

//mon constructeur par défaut 
public Reservation() {
    }


//mon constructeur parametre

  
    public Reservation(int id_reservation, int nombre_personnes, Date date_reservation, int id_bonplan, int id_user) {
        this.id_reservation = id_reservation;
        this.nombre_personnes = nombre_personnes;
        this.date_reservation = date_reservation;
        this.id_bonplan = id_bonplan;
        this.id_user = id_user;
    }

    public int getNombre_personnes() {
        return nombre_personnes;
    }

    public void setNombre_personnes(int nombre_personnes) {
        this.nombre_personnes = nombre_personnes;
    }
    
    //getters and setters 
    
    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_bonplan() {
        return id_bonplan;
    }

    public void setId_bonplan(int id_bonplan) {
        this.id_bonplan = id_bonplan;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    
    //ToString

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nombre_personnes=" + nombre_personnes + ", date_reservation=" + date_reservation + ", id_bonplan=" + id_bonplan + ", id_user=" + id_user + '}';
    }

  



}