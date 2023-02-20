/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;
import elbaldi.interfaces.InterfaceReservationCrud;
import elbaldi.models.Reservation;
import elbaldi.models.bonplan;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class ReservationCrud implements InterfaceReservationCrud {
    Statement ste;
    Connection connection= MyConnection.getInstance().getConn();
    @Override
    public void ajouterReservation(Reservation R) {
        try {
            String req = "INSERT INTO `reservation`( `id_reservation`, `nombre_personnes`, `date_reservation`, `id_bonplan`, `id_user`) VALUES (?,?,?,?,?)";
           PreparedStatement ps=connection.prepareStatement(req);
          
            ps.setInt(1, R.getId_reservation());
            ps.setInt(2, R.getNombre_personnes());
            ps.setDate(3, (Date) R.getDate_reservation());
            ps.setInt(4, R.getId_bonplan());
            ps.setInt(5, R.getId_user());
          
             ps.executeUpdate();
            System.out.println("reservation ajouté");
        } catch (SQLException ex) {
           System.out.println("reservation non ajouté");                 
        }   
    }

    @Override
    public void modifierReservation(Reservation R) {
        try {
            String req = "UPDATE `Reservation` SET `id_reservation` = '" + R.getId_reservation() + "', `nombre_personnes` = '" + R.getNombre_personnes() + "', `date_reservation` = '" + R.getDate_reservation() + "', `id_bonplan` = '" + R.getId_bonplan() + "', `id_user` = '" + R.getId_user() + "' WHERE `Reservation`.`id_reservation` = " + R.getId_reservation();
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println("Reservation non updated !");
        }
    }
    
    
     @Override
    public List<Reservation> afficherReservation() {
       List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from Reservation";
            Statement st = connection.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reservation R = new Reservation();
             R.setId_reservation(RS.getInt("id_reservation"));
             R.setNombre_personnes(RS.getInt("nombre_personnes"));
             R.setDate_reservation(RS.getDate("date_reservation"));
             R.setId_bonplan(RS.getInt("id_bonplan"));
             R.setId_user(RS.getInt("id_user"));
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    @Override
    public void supprimerReservation(int id) {
        try {
            String req = "DELETE FROM `Reservation` WHERE id_reservation = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation supprimée");
        } catch (SQLException ex) {
            System.out.println("reservation supprimée");
        }
    }
    
    @Override
    public List<Reservation> filtreByDate(Date date_reservation) {
    List<Reservation> reservations = new ArrayList<>();
    try {
        String fil = "SELECT * FROM reservation WHERE date_reservation = ?";
        PreparedStatement ps = connection.prepareStatement(fil);
        ps.setDate(1, date_reservation);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Reservation R = new Reservation();
            R.setId_reservation(result.getInt("id_reservation"));
            R.setNombre_personnes(result.getInt("nombre_personnes"));
            R.setDate_reservation(result.getDate("date_reservation"));
            R.setId_bonplan(result.getInt("id_bonplan"));
            R.setId_user(result.getInt("id_user"));
            reservations.add(R);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return reservations;
}

    
}