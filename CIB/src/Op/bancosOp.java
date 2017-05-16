/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Op;

import CIB.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julian C Vargas Q
 */
public class bancosOp {
 
    Conexion mysql = new Conexion();
    Connection cn = mysql.getconexion();
    
    
    String sSQL = "";
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    String[] registro;
    CallableStatement cs;
    
    public void registrarbanco( String banco){
        
      
        
         try {

             cs = cn.prepareCall("{call ins_banco(?)}"); 
            
       
            cs.setString(1, banco);
           
            int respuesta = cs.executeUpdate();
            if(respuesta>0){
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            }else{
                 JOptionPane.showMessageDialog(null, "Error");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
 
    }
    
    
     public DefaultTableModel verBancos() {

        String[] titulos = {"BANCO"};

        registro = new String[1];
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from banco";

        try {
             st = cn.createStatement();
             rs = st.executeQuery(sSQL);
             
            while (rs.next()) {
                registro[0] = rs.getString("nombrebanco");
             
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

     }
    
}
