/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceBonplanCrud;
import elbaldi.models.bonplan;
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
 * @author user
 */
public class BonplanCrud implements InterfaceBonplanCrud {
    Statement ste;
    Connection connection= MyConnection.getInstance().getConn();
    @Override
    public void ajouterBonplan(bonplan B) {
        try {
            String req = "INSERT INTO `bonplan`( `id_bonplan`, `titre_bonplan`, `description_bonplan`, `type_bonplan` , `image_bonplan`, `id_user`) VALUES (?,?,?,?,?,?)";
           PreparedStatement ps=connection.prepareStatement(req);
          
            ps.setInt(1, B.getId_bonplan());
            ps.setString(2, B.getTitre_bonplan());
            ps.setString(3, B.getDescription_bonplan());
            ps.setString(4, B.getType_bonplan());
            ps.setString(5, B.getImage_bonplan());
            ps.setInt(6, B.getId_user());
             ps.executeUpdate();
            System.out.println("Bonplan ajouté");
        } catch (SQLException ex) {
        System.out.println("Bonplan non  ajouté");                    
        }   
    }
    
    @Override
    public void modifierBonplan(bonplan B) {
        try {
            String req = "UPDATE `bonplan` SET `id_bonplan` = '" + B.getId_bonplan() + "', `titre_bonplan` = '" + B.getTitre_bonplan() + "', `description_bonplan` = '" + B.getDescription_bonplan() + "', `type_bonplan` = '" + B.getType_bonplan() + "', `image_bonplan` = '" + B.getImage_bonplan() + "', `id_user` = '" + B.getId_user() + "' WHERE `bonplan`.`id_bonplan` = " + B.getId_bonplan();
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Bonplan updated !");
        } catch (SQLException ex) {
           System.out.println("Bonplan non updated !");
        }
    }
    
    
     @Override
    public List<bonplan> afficherBonplan() {
       List<bonplan> list = new ArrayList<>();
        try {
            String req = "Select * from bonplan";
            Statement st = connection.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             bonplan B = new bonplan();
             B.setId_bonplan(RS.getInt("id_bonplan"));
             B.setTitre_bonplan(RS.getString("titre_bonplan"));
             B.setDescription_bonplan(RS.getString("description_bonplan"));
             B.setType_bonplan(RS.getString("type_bonplan"));
             B.setImage_bonplan(RS.getString("image_bonplan"));
             B.setId_user(RS.getInt("id_user"));
             list.add(B);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
    
    @Override
    public void supprimerbonplan(int id) {
        try {
            String req = "DELETE FROM `bonplan` WHERE id_bonplan = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("bonplan supprime");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    }
    
    
    
    

 
 