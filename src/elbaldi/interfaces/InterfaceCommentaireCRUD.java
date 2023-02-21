/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.commentaire;
import elbaldi.models.produit;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Yasmine
 */
public interface InterfaceCommentaireCRUD {
    public void ajouterCommentaire(commentaire c);
    public void modifierCommentaire(commentaire c);
    public void supprimerCommentaire(commentaire c) ;
    public List<commentaire> afficherCommentaire();
    public commentaire getCommentaireById(int id_commentaire);
    public List<commentaire> getCommentairesByArticle(produit p);
    public List<commentaire> filterParDate(Date date);
}
