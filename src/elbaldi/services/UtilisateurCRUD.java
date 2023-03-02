


package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUD;
import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.utils.MyConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class UtilisateurCRUD implements InterfaceCRUD<Utilisateur> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public UtilisateurCRUD() {
        conn = MyConnection.getInstance().getConn();

    }
    @Override
    public boolean ajouterUtlisateur(Utilisateur user) {
        Statement stmt;

        try {
            String requete = "INSERT INTO `utilisateur` (`nom`,`prenom`,`email`,`dateDeNaissance`,`numTel`,`ville`,`mdp`,`role`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());
            pst.setString(3, user.getEmail());
            pst.setDate(4, user.getDateNaissance());
            pst.setInt(5, user.getNumTel());
            pst.setString(6, user.getVille());
            pst.setString(7, BCrypt.hashpw(user.getMdp(), BCrypt.gensalt() ));
            pst.setString(8, user.getRole().toString());

            if (pst.executeUpdate() > 0) {
                System.out.println("You have registered successfully.");
                return true;
            } else {
                System.out.println("Something went wrong.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    @Override
    public void modifierUtilisateur(Utilisateur u ,int id_user) {{
        try {
            String req = "UPDATE `utilisateur` SET `nom` = '" + u.getNom() + "', `prenom` = '" + u.getPrenom() +"',`email` = '" + u.getEmail()+ "',`dateDeNaissance` = '" + u.getDateNaissance()+ "',`numTel` = '" + u.getNumTel()+ "',`ville` = '" + u.getVille()+ "',`login` = '" + "',`mdp` = '" + u.getMdp()+ "',`role` = '" + u.getRole()+ "' WHERE `id_user` = " + id_user;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    }

   

    
    @Override
    public List<Utilisateur> afficherUtilisateur() {
       List<Utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from utilisateur";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Utilisateur u = new Utilisateur();
            
             u.setid_user(RS.getInt(1));
              u.setNom(RS.getString(2));
             u.setPrenom(RS.getString(3));
              u.setEmail(RS.getString(4));
               u.setDateNaissance(RS.getDate(5));
                u.setNumTel(RS.getInt(6));
                 u.setVille(RS.getString(7));
                   u.setMdp(RS.getString(9));
                    u.setRole(Role.valueOf(RS.getString(10)));
             list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void supprimerUtilisateur(int id_user) {
        try {
            String req = "DELETE FROM `utilisateur` WHERE id_user = " + id_user;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
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
               u.setDateNaissance(RS.getDate(5));
                u.setNumTel(RS.getInt(6));
                 u.setVille(RS.getString(7));
                   u.setMdp(RS.getString(8));
                    u.setRole(Role.valueOf(RS.getString(9)));
            
        }
    return u;
    }

    
    
    
    @Override
    public List<Utilisateur> Filter_utilisateur(String S, String SS) {
        List<Utilisateur> list = new ArrayList<>();
        try {
            if (S.equals("id_user") ) {
                int temp = Integer.parseInt(SS);
                String req = "SELECT * FROM utilisateur WHERE " + S + " =" + temp;
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setid_user(RS.getInt(1));
              u.setNom(RS.getString(2));
              u.setPrenom(RS.getString(3));
              u.setEmail(RS.getString(4));
               u.setDateNaissance(RS.getDate(5));
                u.setNumTel(RS.getInt(6));
                 u.setVille(RS.getString(7));
                   u.setMdp(RS.getString(8));
                    u.setRole(Role.valueOf(RS.getString(9)));
                

                    list.add(u);
                }
            } else {
                String req = "SELECT * FROM utilisateur WHERE " + S + " =" + "\"" + SS + "\"";
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                   Utilisateur u  = new Utilisateur();
                u.setid_user(RS.getInt(1));
              u.setNom(RS.getString(2));
              u.setPrenom(RS.getString(3));
              u.setEmail(RS.getString(4));
               u.setDateNaissance(RS.getDate(5));
                u.setNumTel(RS.getInt(6));
                 u.setVille(RS.getString(7));
                   u.setMdp(RS.getString(8));
                    u.setRole(Role.valueOf(RS.getString(9)));
                    list.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public boolean afficherReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getReclamationByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Filter_Reclamation(String nom, String iheb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
} 
 
