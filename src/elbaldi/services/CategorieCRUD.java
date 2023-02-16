/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCategorieCRUD;
import elbaldi.models.categorie;
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
 * @author USER
 */
public class CategorieCRUD implements InterfaceCategorieCRUD {
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterCategorie(categorie c) {
       try {
            String req = "INSERT INTO `categorie`(`id_categorie`, `nom_categorie`) VALUES (?,?)";
            PreparedStatement ps=conn.prepareStatement(req);
          
            ps.setInt(1,c.getId_categorie());
            ps.setString(2,c.getNom_categorie());
            ps.executeUpdate();
            System.out.println("categorie ajoutee avec succes");
        } catch (SQLException ex) {
            System.out.println("categorie non ajoutee !!");                  
        }  
    }

    @Override
    public void modifierCategorie(categorie c) {
        try {
            String req = "UPDATE `categorie` SET `nom_categorie` = '" + c.getNom_categorie() + "' WHERE `categorie`.`id_categorie` = " + c.getId_categorie();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCategorie(int id) {
          try {
            String req = "DELETE FROM `categorie` WHERE id_categorie = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<categorie> affichercategorie() {
          List<categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             categorie c = new categorie();
             c.setId_categorie(RS.getInt("id_categorie"));
             c.setNom_categorie(RS.getString(2));
             list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }
    
    @Override
    public categorie getCategorieById(int id) {
    categorie c = null;
    try {
        String req = "SELECT * FROM categorie WHERE id_categorie = " + id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
            c = new categorie();
            c.setId_categorie(rs.getInt("id_categorie"));
            c.setNom_categorie(rs.getString("nom_categorie"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
}
 @Override
public List<categorie> filtrerCategorie(String nomCategorie) {
    List<categorie> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM categorie WHERE nom_categorie like '%" + nomCategorie + "%'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            categorie c = new categorie();
            c.setId_categorie(rs.getInt("id_categorie"));
            c.setNom_categorie(rs.getString("nom_categorie"));
            list.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}

    
}
