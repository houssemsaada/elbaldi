/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author USER
 */
public class produit {
    private String ref_produit;
    private String libelle;
    private String description;
    private String image;
    private float prix_achat;
    private float marge;
    private float prix_vente;
    private int quantite;
    private int id_user;
    private int id_categorie;

    public produit(){
        
    }

    public produit(String ref_produit, String libelle, String description, String image, float prix_achat, float marge, float prix_vente, int quantite, int id_user, int id_categorie) {
        this.ref_produit = ref_produit;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.prix_achat = prix_achat;
        this.marge = marge;
        this.prix_vente = prix_vente;
        this.quantite = quantite;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
    }

    public produit(String libelle, float prix_achat) {
        this.libelle = libelle;
        this.prix_achat = prix_achat;
    }
    
    

    public String getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(String ref_produit) {
        this.ref_produit = ref_produit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(float prix_achat) {
        this.prix_achat = prix_achat;
    }

    public float getMarge() {
        return marge;
    }

    public void setMarge(float marge) {
        this.marge = marge;
    }

    public float getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.prix_vente = prix_vente;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "produit{" + "ref_produit=" + ref_produit + ", libelle=" + libelle + ", description=" + description + ", image=" + image + ", prix_achat=" + prix_achat + ", marge=" + marge + ", prix_vente=" + prix_vente + ", quantite=" + quantite + ", id_user=" + id_user + ", id_categorie=" + id_categorie + '}';
    }
    
    
    
}
