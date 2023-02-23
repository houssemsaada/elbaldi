/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceProduitCRUD;
import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yasmine
 */
public class ProduitCRUD implements InterfaceProduitCRUD {
    
    Connection conn = MyConnection.getInstance().getConn();
    @Override
    public void ajouterProduit(produit p) throws SQLException{
           try {
            String req = "INSERT INTO `produit`(`ref_produit`, `libelle`, `description`, `image`, `prix_vente`, `quantite`, `id_categorie`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(req);
          
            ps.setString(1, p.getRef_produit());
            ps.setString(2, p.getLibelle());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getImage());
            ps.setFloat(5, p.getPrix_vente());
            ps.setInt(6, p.getQuantite());
            ps.setInt(7, p.getCategoriee().getId_categorie());        
            ps.executeUpdate();
            System.out.println("Produit ajoutee avec succes ");
        } catch (SQLException ex) {
            System.out.println("Produit non ajoutee !!"); 
               System.out.println(ex.getMessage());  
        }   
    }


    @Override
    public void modifierProduit(produit p) throws SQLException {
        try {
            String req = "UPDATE `produit` SET `libelle` = '" + p.getLibelle() 
                    + "', `description` = '" + p.getDescription()+ "', `image` = '" + p.getImage()
                    + "', `prix_vente` = '" + p.getPrix_vente()+ "', `quantite` = '" + p.getQuantite()
                    + "', `id_categorie` = '" + p.getCategoriee().getId_categorie()
                    + "' WHERE `produit`.`ref_produit` = '" + p.getRef_produit() + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerProduit(String ref) throws SQLException {
          try {
            String req = "DELETE FROM `produit` WHERE `ref_produit` = '" + ref + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<produit> afficherProduit() throws SQLException{
         List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             produit p = new produit();
             p.setRef_produit(RS.getString(1));
             p.setLibelle(RS.getString(2));
             p.setDescription(RS.getString(3));
             p.setImage(RS.getString(4));
             p.setPrix_vente(RS.getFloat(5));
             p.setQuantite(RS.getInt(6));
             CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
             int categorieId = RS.getInt(7);
             categorie categoriee = categorieCRUD.getCategorieById(categorieId);
             p.setCategoriee(categoriee);
             list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
      
    @Override
    public produit getByRefProduit (String ref) throws SQLException {
        //produit p = new produit();
        produit p = null;
        //String reff="TUN61900"+ref;
        try {
            String req = "SELECT * FROM `produit` WHERE `ref_produit` = '" + ref + "'";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                p=new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
             CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
             int categorieId = RS.getInt(7);
             categorie categoriee = categorieCRUD.getCategorieById(categorieId);
             p.setCategoriee(categoriee);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
   @Override
    public List<produit> filtreByPrixVente(float min, float max) throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit WHERE prix_vente BETWEEN " + min + " AND " + max;
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
             CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
             int categorieId = RS.getInt(7);
             categorie categoriee = categorieCRUD.getCategorieById(categorieId);
             p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    @Override 
    public List<produit> filtrerProduitParQuantite(int quantite) throws SQLException {
    List<produit> list = new ArrayList<>();
    try {
        String req = "Select * from produit where quantite >= ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, quantite);
        ResultSet RS = ps.executeQuery();
        while (RS.next()) {
            produit p = new produit();
            p.setRef_produit(RS.getString(1));
            p.setLibelle(RS.getString(2));
            p.setDescription(RS.getString(3));
            p.setImage(RS.getString(4));
            p.setPrix_vente(RS.getFloat(5));
            p.setQuantite(RS.getInt(6));
            CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
             int categorieId = RS.getInt(7);
             categorie categoriee = categorieCRUD.getCategorieById(categorieId);
             p.setCategoriee(categoriee);
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}
    @Override
   public List<produit> filtreByCategorie(int idCategorie) throws SQLException {
    List<produit> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM produit WHERE id_categorie = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, idCategorie);
        ResultSet RS = ps.executeQuery();
        while (RS.next()) {
            produit p = new produit();
            p.setRef_produit(RS.getString(1));
            p.setLibelle(RS.getString(2));
            p.setDescription(RS.getString(3));
            p.setImage(RS.getString(4));
            p.setPrix_vente(RS.getFloat(5));
            p.setQuantite(RS.getInt(6));
            CategorieCRUD categorieCRUD = new CategorieCRUD();
            categorie categoriee = categorieCRUD.getCategorieById(idCategorie);
            p.setCategoriee(categoriee);
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}



    
}
