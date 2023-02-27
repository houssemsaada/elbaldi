/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import elbaldi.models.Participation;
import elbaldi.interfaces.InterfaceParticipationCRUD ;
import elbaldi.utils.MyConnection;
/**
 *
 * @author MSI
 */
public class ParticipationService implements InterfaceParticipationCRUD<Participation>{
    Connection cnn
            ;

    public ParticipationService() {
       
            cnn  =MyConnection.getInstance().getcnn
        ();
    }
    

    @Override
    public void ajouter(Participation t) throws SQLException {
 try{ 
     String req = "insert into participation(id_user, id_event,date) VALUES ("
             + "'"+t.getId_user()+ "','"+t.getEv().getId_event()+ "','"+t.getDate()+  "')";
     
     Statement st = cnn
             .createStatement();
     st.executeUpdate(req);
     System.out.println("AJOUT de participation se fait avec succes");
 } catch(SQLException ex ){
     System.out.println("Erreur " + ex.getMessage());
 }
    }

    @Override
    public void modifier(Participation t) throws SQLException {
         try{ 
      String req = "UPDATE  participation SET id_user = ? , id_event=? , date=? WHERE id_participation =? ";
             PreparedStatement ps =cnn
                     .prepareStatement(req);
             ps.setInt(1, t.getId_user());
             ps.setInt(2, t.getEv().getId_event());
             ps.setString(3,t.getDate());
             ps.setInt(4, t.getId_participation());
             
             ps.executeUpdate();
             System.out.println("Modification de participation se fait");
 } catch(SQLException ex ){
     System.out.println("Erreur " + ex.getMessage());
 }
    }

    @Override
    public void supprimer(Participation t) throws SQLException {
          try {
              String req = "DELETE FROM participation where id_participation = ? " ;
              PreparedStatement ps = cnn
                      .prepareStatement(req);
              ps.setInt(1, t.getId_participation());
              ps.executeUpdate();
              System.out.println("Suppression participation se fait");
          }catch(SQLException ex ){
              System.out.println("Erreur" + ex.getMessage());
          }
      }

    @Override
    public List<Participation> recuperer(Participation t) throws SQLException {
      List<Participation> lP = new ArrayList<Participation>();
        try{
          String req = "SELECT * FROM participation ";
          Statement st = cnn
                  .createStatement();
          ResultSet rs = st.executeQuery(req);
          EvenementService es = new EvenementService() ;
          while(rs.next()){
              Participation P = new Participation();
              P.setId_participation(rs.getInt(1));
              P.setId_user(rs.getInt(2));
              P.setEv(es.findByid(rs.getInt(4)));
              
              lP.add(P);
          }
          
      }catch(SQLException ex){
          System.out.println("Erreur" + ex.getMessage());
          
      }
        return  lP;
    }
}
