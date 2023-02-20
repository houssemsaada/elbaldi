/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author user
 */
public class bonplan {
    

    private int id_bonplan;
    private String titre_bonplan;
    private String description_bonplan;
    private String type_bonplan;
    private String image_bonplan;
    private int id_user;

    public bonplan() {
    }

    public bonplan(int id_bonplan, String titre_bonplan, String description_bonplan, String type_bonplan, String image_bonplan, int id_user) {
        this.id_bonplan = id_bonplan;
        this.titre_bonplan = titre_bonplan;
        this.description_bonplan = description_bonplan;
        this.type_bonplan = type_bonplan;
        this.image_bonplan = image_bonplan;
        this.id_user = id_user;
    }

    public bonplan(int id_bonplan, String titre_bonplan, String description_bonplan, String type_bonplan, String image_bonplan) {
        this.id_bonplan = id_bonplan;
        this.titre_bonplan = titre_bonplan;
        this.description_bonplan = description_bonplan;
        this.type_bonplan = type_bonplan;
        this.image_bonplan = image_bonplan;
    }
    

    public int getId_bonplan() {
        return id_bonplan;
    }

    public void setId_bonplan(int id_bonplan) {
        this.id_bonplan = id_bonplan;
    }

    public String getTitre_bonplan() {
        return titre_bonplan;
    }

    public void setTitre_bonplan(String titre_bonplan) {
        this.titre_bonplan = titre_bonplan;
    }

    public String getDescription_bonplan() {
        return description_bonplan;
    }

    public void setDescription_bonplan(String description_bonplan) {
        this.description_bonplan = description_bonplan;
    }

    public String getType_bonplan() {
        return type_bonplan;
    }

    public void setType_bonplan(String type_bonplan) {
        this.type_bonplan = type_bonplan;
    }

    public String getImage_bonplan() {
        return image_bonplan;
    }

    public void setImage_bonplan(String image_bonplan) {
        this.image_bonplan = image_bonplan;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "bonplan{" + "id_bonplan=" + id_bonplan + ", titre_bonplan=" + titre_bonplan + ", description_bonplan=" + description_bonplan + ", type_bonplan=" + type_bonplan + ", image_bonplan=" + image_bonplan + ", id_user=" + id_user + '}';
    }

    
}