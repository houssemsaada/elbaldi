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
                String req = "INSERT INTO `panier` (`id_panier`,`ref_produit`,`id_user`, `nombre_article`, `quantite_produit`, `total_panier`) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, p.getId_panier());
                ps.setString(2, p.getP1().getRef_produit());
                ps.setInt(3, p.getU1().getid_user());
                ps.setInt(4, p.getNombre_article());

                ps.setInt(5, p.getQuantite_produit());
                ps.setFloat(6, p.getTotal_panier());
                ps.executeUpdate();
                System.out.println("panier ajouté!!!");
            } catch (SQLException ex) {
                System.out.println("panier non ajouté!!!");
            }
        } else {
            try {
                String req = "INSERT INTO `panier` (`ref_produit`,`id_user`, `nombre_article`, `quantite_produit`, `total_panier`) VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setString(1, p.getP1().getRef_produit());
                ps.setInt(2, p.getU1().getid_user());
                ps.setInt(3, p.getNombre_article());
                ps.setInt(4, p.getQuantite_produit());
                ps.setFloat(5, p.getTotal_panier());
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
            ps.setInt(3, p.getQuantite_produit());
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
    public void afficherPanier() {
        List<panier> list = new ArrayList<>();
        List<produit> list_p = new ArrayList<>();
        try {
            String req = "Select * from panier";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {

                panier p = new panier();

                Utilisateur u = new Utilisateur();
                p.setId_panier(RS.getInt(1));
                // list des produits
                String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
                PreparedStatement psp = conn.prepareStatement(produitfilter);
                psp.setInt(1, RS.getInt(1));
                ResultSet rp = psp.executeQuery();

                while (rp.next()) {

//                    // Get the values of each column and print them
//                    int id = rp.getInt(1);
//                    String name = rp.getString(2);
//                    int quantite = rp.getInt(3);
//                    System.out.println(id + ", " + name + ", " + quantite);
//                    System.out.println("*******************");
                    //produit
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
                        p1.setQuantite(rp.getInt(3));
                        p1.setId_categorie(rr.getInt(10));
                        list_p.add(p1);
                    }

                }

                //utilisateur
                String filter2 = "SELECT *  FROM utilisateur WHERE `id_user`= ?";
                PreparedStatement psu = conn.prepareStatement(filter2);
                psu.setInt(1, RS.getInt(3));
                ResultSet ru = psu.executeQuery();
                while (ru.next()) {
                    u.setid_user(ru.getInt(1));
                    u.setNom(ru.getString(2));
                    u.setPrenom(ru.getString(3));
                    u.setEmail(ru.getString(4));
                    u.setDateNaissance(ru.getString(5));
                    u.setNumTel(ru.getInt(6));
                    u.setVille(ru.getString(7));
                    u.setLogin(ru.getString(8));
                    u.setMdp(ru.getString(9));
                    u.setRole(Role.valueOf(ru.getString(10)));
                }

                p.setU1(u);

                p.setNombre_article(RS.getInt(4));
                p.setQuantite_produit(RS.getInt(5));
                p.setTotal_panier(RS.getFloat(6));
                list.add(p);
                for (panier item : list) {
                    System.out.println(item);
                    System.out.println("list of products");
                    for (produit prod : list_p) {
                        System.out.println(prod);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // return list;
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

                panier p = new panier();
                produit p1 = new produit();
                Utilisateur u = new Utilisateur();
                p.setId_panier(RS.getInt(1));
                //produit
                String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                PreparedStatement pss = conn.prepareStatement(filter);
                pss.setString(1, RS.getString(2));
                ResultSet rr = pss.executeQuery();
                while (rr.next()) {
                    p1.setRef_produit(rr.getString(1));
                    p1.setLibelle(rr.getString(2));
                    p1.setDescription(rr.getString(3));
                    p1.setImage(rr.getString(4));
                    p1.setPrix_achat(rr.getFloat(5));
                    p1.setMarge(rr.getFloat(6));
                    p1.setPrix_vente(rr.getFloat(7));
                    p1.setQuantite(rr.getInt(8));
                    p1.setId_user(rr.getInt(9));
                    p1.setId_categorie(rr.getInt(10));
                }
                p.setP1(p1);
                //utilisateur
                String filter2 = "SELECT *  FROM utilisateur WHERE `id_user`= ?";
                PreparedStatement psu = conn.prepareStatement(filter2);
                psu.setInt(1, RS.getInt(3));
                ResultSet ru = psu.executeQuery();
                while (ru.next()) {
                    u.setid_user(ru.getInt(1));
                    u.setNom(ru.getString(2));
                    u.setPrenom(ru.getString(3));
                    u.setEmail(ru.getString(4));
                    u.setDateNaissance(ru.getString(5));
                    u.setNumTel(ru.getInt(6));
                    u.setVille(ru.getString(7));
                    u.setLogin(ru.getString(8));
                    u.setMdp(ru.getString(9));
                    u.setRole(Role.valueOf(ru.getString(10)));
                }

                p.setU1(u);

                p.setNombre_article(RS.getInt(4));
                p.setQuantite_produit(RS.getInt(5));
                p.setTotal_panier(RS.getFloat(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
