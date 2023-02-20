/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import elbaldi.models.produit;
import java.util.List;

/**
 *
 * @author USER
 */
public interface InterfaceProduitCRUD {
    public void ajouterProduit(produit p);
    public void modifierProduit(produit p);
    public void supprimerProduit(String ref_produit) ;
    public List<produit> afficherProduit();
    public produit getByRefProduit(String ref);
    public List<produit> filtreByPrixVente(float min, float max);
    public List<produit> filtrerProduitParQuantite(int quantite);
}
