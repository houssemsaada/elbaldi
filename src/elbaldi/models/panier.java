/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author houss
 */
public class panier {

    private int id_panier, nombre_article, quantite_produit;
    private produit p1;
    private Utilisateur u1;
    private float total_panier;
    //constructeur par defaut

    public panier() {
    }
    ///constructeur parametre 

    public panier(int id_panier, produit p1, Utilisateur u1, int nombre_article, int quantite_produit, float total_panier) {
        this.id_panier = id_panier;
        this.u1 = u1;
        this.p1 = p1;
        this.nombre_article = nombre_article;
        this.quantite_produit = quantite_produit;
        this.total_panier = total_panier;
    }

    public panier(produit p1, Utilisateur u1, int nombre_article, int quantite_produit, float total_panier) {
        this.p1 = p1;
        this.u1 = u1;
        this.nombre_article = nombre_article;
        this.quantite_produit = quantite_produit;
        this.total_panier = total_panier;
    }
    //getters

    public int getId_panier() {
        return id_panier;
    }

    public produit getP1() {
        return p1;
    }

    public int getNombre_article() {
        return nombre_article;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public float getTotal_panier() {
        return total_panier;
    }

    public Utilisateur getU1() {
        return u1;
    }
    
    
    //setters

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setP1(produit p1) {
        this.p1 = p1;
    }

    public void setNombre_article(int nombre_article) {
        this.nombre_article = nombre_article;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public void setTotal_panier(float total_panier) {
        this.total_panier = total_panier;
    }

    public void setU1(Utilisateur u1) {
        this.u1 = u1;
    }

    @Override
    public String toString() {
        return "panier{" + "id_panier=" + id_panier + ", nombre_article=" + nombre_article + ", quantite_produit=" + quantite_produit + ", produit=" + p1 + ", utilisateur=" + u1 + ", total_panier=" + total_panier + '}';
    }

   

}
