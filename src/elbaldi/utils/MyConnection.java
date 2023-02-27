/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class MyConnection {
    
    //DB PARAM
    static final String URL ="jdbc:mysql://localhost:3306/eratech";
    static final String USER ="root";
    static final String PASSWORD ="";
    
    //var
    private Connection cnn
            ;
    //1
    static MyConnection instance;
    
    //const
    //2
    public MyConnection(){
        try {
            cnn
                    = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connection etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public Connection getcnn
        () {
        return cnn
                ;
    }

    
    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        
        return instance;
    }
   
    
    
}

