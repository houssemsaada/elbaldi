/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author Yasmine
 */
public class produit {
    private String ref_produit;
    private String libelle;
    private String description;
    private String image;
    private float prix_vente;
    private int quantite;
    private categorie categoriee;

    public produit(){
        
    }
    public produit(String ref_produit){
        this.ref_produit=ref_produit;
    }

    public produit(String ref_produit, String libelle, String description, String image, float prix_vente, int quantite,categorie categoriee) {
        this.ref_produit = ref_produit;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.prix_vente = prix_vente;
        this.quantite = quantite;
        this.categoriee=categoriee;
       
    }

    public String getRef_produit() {
        return "TUN61900"+ref_produit;
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
    
    public categorie getCategoriee() {
        return categoriee;
    }

    public void setCategoriee(categorie categoriee) {
        this.categoriee = categoriee;
    }

    @Override
    public String toString() {
        return "produit{" + "ref_produit=" + ref_produit + ", libelle=" + libelle + ", description=" + description + ", image=" + image + ", prix_vente=" + prix_vente + ", quantite=" + quantite + ", categoriee=" + categoriee + '}';
    }
   
    
    
}
