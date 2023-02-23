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
public class Participation {
 int id_participation,id_user,id_event;
 String date;

    public Participation() {
    }

    public Participation(int id_participation, int id_user, int id_event, String date) {
        this.id_participation = id_participation;
        this.id_user = id_user;
        this.id_event = id_event;
        this.date = date;
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Participation{" + "id_participation=" + id_participation + ", id_user=" + id_user + ", id_event=" + id_event + ", date=" + date + '}';
    }

     
 
}
