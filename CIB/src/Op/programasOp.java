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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julian C Vargas Q
 */
public class programasOp {
    
    
    Conexion mysql = new Conexion();
    Connection cn = mysql.getconexion();
    
    
    String sSQL = "";
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    String[] registro;
    CallableStatement cs;
    
    
      public void registrarfacultad(String facultad){
        
         try {

             cs = cn.prepareCall("{call ins_facultad(?)}"); 
            
            cs.setString(1, facultad);
     
           int respuesta = cs.executeUpdate();
            if(respuesta > 0){
                JOptionPane.showMessageDialog(null, "Programa registrado Satisfactoriamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
 
    }
      
      
      public int idfacultad(String facultad){
          
            int id = 0 ;
        try {

            /*  PreparedStatement ps = cn.prepareStatement("SELECT idproveedor FROM proveedor WHERE nombre = '" + nombre + "'");
            ResultSet rs = ps.executeQuery();*/
            sSQL ="SELECT idfacultad  FROM facultad WHERE nombfacultad= '" + facultad + "'";
            st = cn.createStatement();
             rs = st.executeQuery(sSQL);
            
            while (rs.next()) {
                id= rs.getInt("idfacultad");
               
            }
        } catch (Exception e) {
            
        }

        return id;
      
          
      }
      
    
    public void registrarprograma(String idprograma,int idfacultad, String programa, String ref){
        
         try {

            cs = cn.prepareCall("{call ins_programa(?,?,?,?)}"); 
            
            cs.setString(1, idprograma);
            cs.setInt(2, idfacultad);
            cs.setString(3, programa);
            cs.setString(4, ref);
            
            int respuesta = cs.executeUpdate();
            if(respuesta > 0){
                JOptionPane.showMessageDialog(null, "Programa registrado Satisfactoriamente");
            }else{               
                JOptionPane.showMessageDialog(null, "Error a guardar");
            }
                
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
 
    }
    
    
     public JComboBox cargarcbo(){
     
         JComboBox lista = null;

        try {

            st = cn.createStatement();
            rs = st.executeQuery("select * from facultad");

            while (rs.next()) {
                lista.addItem(rs.getString("nombfacultad"));
            }

        } catch (Exception e) {

        }
        return lista;
    }
    
    
     public DefaultTableModel verProgramas(int id) {

        String[] titulos = {"COD.", "PROGRAMA","REF."};

        registro = new String[3];
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from programa where idfacultad = "+id;

        try {
             st = cn.createStatement();
             rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idprograma");
                registro[1] = rs.getString("nombreprograma");
                registro[2] = rs.getString("referencia");
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

     }
    
}
