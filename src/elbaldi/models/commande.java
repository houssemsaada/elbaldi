/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.time.LocalDate;

import java.sql.Date ; 
/**
 *
 * @author houss
 */
public class commande {

    private int id_cmd, id_user;
    private String etat;
    private Date  date_cmd;

    //constructeur par defaut
    public commande() {
    }
    //constructeur parametre 

    public commande(int id_user, String etat, Date  date_cmd) {
        this.id_user = id_user;
        this.etat = etat;
        this.date_cmd = date_cmd;
    }

    public commande(int id_cmd, int id_user, String etat, Date  date_cmd) {
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.etat = etat;
        this.date_cmd = date_cmd;
    }
    //getters

    public int getId_cmd() {
        return id_cmd;
    }

    public int getId_user() {
        return id_user;
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

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate_cmd(Date date_cmd) {
        this.date_cmd = date_cmd;
    }

    @Override
    public String toString() {
        return "commande{" + "id_cmd=" + id_cmd + ", id_user=" + id_user + ", etat=" + etat + ", date_cmd=" + date_cmd + '}';
    }

}
