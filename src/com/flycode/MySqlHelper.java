/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flycode;
import java.sql.*;

/**
 *
 * @author erikn
 */
public class MySqlHelper {
    private String Username;
    private String pass;
    private String link = "jdbc:mysql://localhost:3306/PadSearch";
    private Connection con;
    public MySqlHelper(String Username, String pass){
        this.setUsername(Username);
        this.setPass(pass);
    }
    public void getConnection(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection(  
            link,Username,pass); 
             
        }catch(Exception e){ 
            System.out.println(e);
        }      
    }
    
    //CRUD
    public void createRecord(DataParser data){
        try{
            Statement stmt=con.createStatement();
           
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    //Setters
    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
