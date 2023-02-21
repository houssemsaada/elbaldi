/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.panierInterfaceCRUD;
import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author houss
 */
public class panierCRUD implements panierInterfaceCRUD {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterPanier(panier p) {
        if (p.getId_panier() != 0) {
            try {
                String req = "INSERT INTO `panier` (`id_panier`,`id_user`, `nombre_article`,`total_panier`) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, p.getId_panier());
                ps.setInt(2, p.getU1().getid_user());
                ps.setInt(3, p.getNombre_article());
                ps.setFloat(4, p.getTotal_panier());
                ps.executeUpdate();
                System.out.println("panier ajouté!!!");
            } catch (SQLException ex) {
                System.out.println("panier non ajouté!!!");
            }
        } else {
            try {
                String req = "INSERT INTO `panier` (`id_user`, `nombre_article`, `total_panier`) VALUES (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);

                ps.setInt(1, p.getU1().getid_user());
                ps.setInt(2, p.getNombre_article());
                ps.setFloat(3, p.getTotal_panier());
                ps.executeUpdate();
                System.out.println("panier ajouté!!!");
            } catch (SQLException ex) {
                System.out.println("panier non ajouté!!!");
            }
        }
    }

    @Override
    public void ajouterProdPanier(panier p, produit p1, int quantite) {
        try {
            String req = "INSERT INTO `panier_produit`(`id_panier`, `ref_produit`,`Quantite`) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, p.getId_panier());
            ps.setString(2, p1.getRef_produit());
            ps.setInt(3, quantite);
            ps.executeUpdate();
            System.out.println("produit ajouté au panier!!!");
        } catch (SQLException ex) {
            System.out.println("produit non ajouté au panier!!!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierPanier(panier p, produit p1) {
        try {
            String req = "UPDATE `panier` SET `ref_produit` = ?, `nombre_article` = ? , `quantite_produit` = ?, `total_panier` = ? WHERE id_panier  = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p1.getRef_produit());
            ps.setInt(2, p.getNombre_article());
            ps.setFloat(4, p.getTotal_panier());
            ps.setInt(5, p.getId_panier());

            ps.executeUpdate();
            System.out.println("panier updated !");
        } catch (SQLException ex) {
            System.out.println("panier non updated !");
        }
    }

    @Override
    public void supprimerPanier(panier p) {
        try {
            String req = "DELETE FROM `panier` WHERE id_panier = " + p.getId_panier();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("panier deleted !");
        } catch (SQLException ex) {
            System.out.println("panier non deleted !");
        }
    }

    @Override
    public List<panier> afficherPanier() {
        List<panier> list = new ArrayList<>();

        try {
            String req = "Select * from panier";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                List<produit> list_p = new ArrayList<>();
                panier p = new panier();

                p.setId_panier(RS.getInt(1));
                // list des produits
                String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
                PreparedStatement psp = conn.prepareStatement(produitfilter);
                psp.setInt(1, RS.getInt(1));
                ResultSet rp = psp.executeQuery();

                while (rp.next()) {
                    
                    //produit;
//                    ProduitCRUD prod = new ProduitCRUD();
//                    String ref = rp.getString(2);
                    int quantite = rp.getInt(3);
//                    produit proda = prod.getByRefProduit(ref);
//                    produitMap.put(quantite, proda);
                    // list_p.add(produit);
                    String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                    PreparedStatement pss = conn.prepareStatement(filter);
                    pss.setString(1, rp.getString(2));
                    ResultSet rr = pss.executeQuery();
                    while (rr.next()) {
                        produit p1 = new produit();
                        p1.setRef_produit(rr.getString(1));
                        p1.setLibelle(rr.getString(2));
                        p1.setDescription(rr.getString(3));
                        p1.setImage(rr.getString(4));
                        p1.setPrix_vente(rr.getFloat(7));
                        p1.setQuantite(quantite);
                        p1.setId_categorie(rr.getInt(10));
                        list_p.add(p1);
                    }
                    p.setList(list_p);
                }
                //utilisateur
                UtilisateurCRUD uti = new UtilisateurCRUD();
                int user_id = RS.getInt(3);
                Utilisateur u = uti.getUserByID(user_id);
                p.setU1(u);
                int  numarticle=p.numArticle(list_p);
                p.setNombre_article(numarticle);
                float somme = p.sommePanier(list_p);
                p.setTotal_panier(somme);
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<panier> filtreByuser(Utilisateur u1) {
        List<panier> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM panier WHERE id_user = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, u1.getid_user());
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                List<produit> list_p = new ArrayList<>();
                panier p = new panier();

                p.setId_panier(RS.getInt(1));
                // list des produits
                String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
                PreparedStatement psp = conn.prepareStatement(produitfilter);
                psp.setInt(1, RS.getInt(1));
                ResultSet rp = psp.executeQuery();

                while (rp.next()) {
                    
                    //produit;
//                    ProduitCRUD prod = new ProduitCRUD();
//                    String ref = rp.getString(2);
                    int quantite = rp.getInt(3);
//                    produit proda = prod.getByRefProduit(ref);
//                    produitMap.put(quantite, proda);
                    // list_p.add(produit);
                    String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                    PreparedStatement pss = conn.prepareStatement(filter);
                    pss.setString(1, rp.getString(2));
                    ResultSet rr = pss.executeQuery();
                    while (rr.next()) {
                        produit p1 = new produit();
                        p1.setRef_produit(rr.getString(1));
                        p1.setLibelle(rr.getString(2));
                        p1.setDescription(rr.getString(3));
                        p1.setImage(rr.getString(4));
                        p1.setPrix_vente(rr.getFloat(7));
                        p1.setQuantite(quantite);
                        p1.setId_categorie(rr.getInt(10));
                        list_p.add(p1);
                    }
                    p.setList(list_p);
                }
                //utilisateur
                UtilisateurCRUD uti = new UtilisateurCRUD();
                int user_id = RS.getInt(3);
                Utilisateur u = uti.getUserByID(user_id);
                p.setU1(u);

                p.setNombre_article(RS.getInt(4));
                p.setTotal_panier(RS.getFloat(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
