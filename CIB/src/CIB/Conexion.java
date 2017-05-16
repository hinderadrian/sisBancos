/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIB;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Julian camilo
 */
public class Conexion {
    
  public  String db ="cib"; 
  public  String url ="jdbc:mysql://localhost:3306/"+db;
  public  String user="root";
  public  String pass="";
  
  
  public Conexion(){
    
}
  
  
  public Connection getconexion(){
      Connection link = null;
    
      try {
          
          Class.forName("org.gjt.mm.mysql.Driver");
          link = DriverManager.getConnection(this.url,this.user,this.pass);
        
          
      } catch (ClassNotFoundException | SQLException e) {
          JOptionPane.showConfirmDialog(null, e);
      
      }
      
      return link;
  }
  
}
