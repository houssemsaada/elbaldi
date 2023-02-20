/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUDpromotion;
import elbaldi.models.promotion;
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
 * @author selim
 */
public class PromotionCRUD implements InterfaceCRUDpromotion {
    Connection conn = MyConnection.getInstance().getConn();
   
     @Override
     public void ajouterpromotion(promotion p) {
          if (p.isValid()){
    try { 
        String req = "INSERT INTO `promotion`(`code_promo`, `taux`, `date_debut`, `date_fin`) VALUES (?,?,?,?)" ;
        PreparedStatement ps = conn.prepareStatement(req); 
        
        ps.setString(1, p.getCode_promo());
        ps.setFloat(2, p.getTaux());
        ps.setDate(3, java.sql.Date.valueOf(p.getDate_debut()));
        ps.setDate(4, java.sql.Date.valueOf(p.getDate_fin()));
            
        ps.executeUpdate();
            
        System.out.println("Promotion ajoutée!!!");
    } catch (SQLException ex) {
        System.out.println("Promotion non ajoutée");  
    }
    } else {
             System.out.println(" la date de début doit être antérieure à la date de fin");
    
          }}
          
  
     @Override
public void modifierpromotion(promotion p) {
    if (p.isValid()){
    try {
      
       String req = "UPDATE `promotion` SET `code_promo` = ?, `taux` = ?, `date_debut` = ?, `date_fin` = ?  WHERE `id_promotion` = ?";
 
       PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, p.getCode_promo());
        ps.setFloat(2, p.getTaux());
        ps.setDate(3, java.sql.Date.valueOf(p.getDate_debut()));
        ps.setDate(4, java.sql.Date.valueOf(p.getDate_fin()));
        ps.setInt(5, p.getId_promotion());
        ps.executeUpdate();
        System.out.println("promotion modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}else {
             System.out.println(" la date de début doit être antérieure à la date de fin");
} }



  @Override
    public void supprimerpromotion(int id) {
        try {
            String req = "DELETE FROM `promotion` WHERE `id_promotion` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
  @Override
   public List<promotion> afficherpromotion() {
   List<promotion> listpromotion = new ArrayList<>();
    try {
        String req = "Select * from promotion";
        Statement st = conn.createStatement();
       
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
         promotion p = new promotion();
         p.setId_promotion(RS.getInt("id_promotion"));
         p.setCode_promo(RS.getString(2));
        p.setDate_debut(RS.getDate("date_debut").toLocalDate());
          p.setDate_fin(RS.getDate("date_fin").toLocalDate());
         
         
         listpromotion.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

       return listpromotion;
}
    
      @Override
   public promotion getById(int id) {
      promotion p = new promotion();
    try {
        String req = "SELECT * FROM promotion WHERE id_promotion = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            
            p.setId_promotion(rs.getInt("id_promotion"));
            p.setCode_promo(rs.getString("code_promo"));
            p.setTaux(rs.getFloat("taux"));
            p.setDate_debut(rs.getDate("date_debut").toLocalDate());
             p.setDate_fin(rs.getDate("date_fin").toLocalDate());
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return p ;
}
   
   
    @Override
   public List<promotion> filtreBytaux(float taux) {
    List<promotion> list = new ArrayList<>();
    try {
        String req = "Select * from promotion where taux = '" + taux + "'";
        Statement st = conn.createStatement();
       
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            promotion p = new promotion();
            p.setId_promotion(rs.getInt("id_promotion"));
           
            p.setCode_promo(rs.getString("code_promo"));
            p.setTaux(rs.getFloat("taux"));
            p.setDate_debut(rs.getDate("date_debut").toLocalDate());
            p.setDate_fin(rs.getDate("date_fin").toLocalDate());
            
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
}
