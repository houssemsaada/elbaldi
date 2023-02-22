/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.time.LocalDate;

import java.sql.Date;

/**
 *
 * @author houss
 */
public class commande {

    private int id_cmd;
    private String etat;
    private Date date_cmd;

    private panier pan ; 
    
    //constructeur par defaut
    public commande() {
    }
    //constructeur parametre 

    public commande(panier pan, String etat, Date date_cmd) {
        this.pan = pan;
        this.etat = etat;
        this.date_cmd = date_cmd;
    }

    public commande(int id_cmd, panier pan, String etat, Date date_cmd) {
        this.id_cmd = id_cmd;
        this.pan = pan;
        this.etat = etat;
        this.date_cmd = date_cmd;
    }
    //getters

    public int getId_cmd() {
        return id_cmd;
    }

    public panier getPan() {
        return pan;
    }
    


    public String getEtat() {
        return etat;
    }

    public Date getDate_cmd() {
        return date_cmd;
    }
    //setters

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setPan(panier pan) {
        this.pan = pan;
    }

 

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate_cmd(Date date_cmd) {
        this.date_cmd = date_cmd;
    }

    @Override
    public String toString() {
        return "commande{" + "id_cmd=" + id_cmd + ", etat=" + etat + ", date_cmd=" + date_cmd + ", panier =" + pan + '}';
    }



}
