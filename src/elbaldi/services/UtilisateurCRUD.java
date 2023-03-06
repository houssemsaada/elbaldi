package elbaldi.services;


import elbaldi.models.Utilisateur;
import elbaldi.utils.MyConnection;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtilisateurCRUD  {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    PreparedStatement stee;
    public String n, m;
    public String passwordF;



    public UtilisateurCRUD() {
        conn = MyConnection.getInstance().getcnn();

    }
    public Utilisateur getUserByID(int id_user ) throws SQLException {
       String querry="SELECT *  FROM utilisateur WHERE `id_user`="+id_user;
       Statement stm=conn.createStatement();
       ResultSet RS=stm.executeQuery(querry);
       
       Utilisateur u=new Utilisateur ();
        while (RS.next()) {            
            
           
              u.setid_user(RS.getInt(1));
              u.setNom(RS.getString(2));
              u.setPrenom(RS.getString(3));
              u.setEmail(RS.getString(4));
                u.setNumTel(RS.getInt(6));
                 u.setVille(RS.getString(7));
                   u.setMdp(RS.getString(8));
                   
            
        }
    return u;
    }
}