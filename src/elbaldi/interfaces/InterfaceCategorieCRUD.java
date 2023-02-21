/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import elbaldi.models.categorie;
import java.util.List;
/**
 *
 * @author Yasmine
 */
public interface InterfaceCategorieCRUD {
    public void ajouterCategorie(categorie c);
    public void modifierCategorie(categorie c);
    public void supprimerCategorie(categorie c) ;
    public List<categorie> affichercategorie();
    public categorie getCategorieById(int id);
    public List<categorie> filtrerCategorie(String nomCategorie);
    
}
