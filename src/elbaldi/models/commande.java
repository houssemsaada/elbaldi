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
    private Utilisateur u1 ;

    //constructeur par defaut
    public commande() {
    }
    //constructeur parametre 

    public commande(Utilisateur u1, String etat, Date date_cmd) {
        this.u1 = u1;
        this.etat = etat;
        this.date_cmd = date_cmd;
    }

    public commande(int id_cmd, Utilisateur u1, String etat, Date date_cmd) {
        this.id_cmd = id_cmd;
        this.u1 = u1;
        this.etat = etat;
        this.date_cmd = date_cmd;
    }
    //getters

    public int getId_cmd() {
        return id_cmd;
    }

    public Utilisateur getU1() {
        return u1;
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

    public void setU1(Utilisateur u1) {
        this.u1 = u1;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate_cmd(Date date_cmd) {
        this.date_cmd = date_cmd;
    }

    @Override
    public String toString() {
        return "commande{" + "id_cmd=" + id_cmd + ", user=" + u1 + ", etat=" + etat + ", date_cmd=" + date_cmd + '}';
    }

}
