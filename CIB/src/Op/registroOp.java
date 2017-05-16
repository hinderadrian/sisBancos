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
import vistas.JInternalFrameregistro;

/**
 *
 * @author Julian C Vargas Q
 */
public class registroOp {

    Conexion mysql = new Conexion();
    Connection cn = mysql.getconexion();

    JInternalFrameregistro reg;

    String sSQL = "";
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    String[] registro;
    public int opcion;

    CallableStatement cs;

    public void registro(int idbanco, int idfecha, String fecha, String fechaR, String idprograma,
            String codigo, String num_recibo, String valor) {

        if (idfecha == 0) {
            try {

                cs = cn.prepareCall("{call ins_fecha(?,?)}");

                cs.setInt(1, idbanco);
                cs.setString(2, fecha);

                int respuesta = cs.executeUpdate();
                if (respuesta > 0) {
                    JOptionPane.showMessageDialog(null, "registrado añadido");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            int idf = buscaridfecha(fecha);

            try {

                cs = cn.prepareCall("{call ins_registro(?,?,?,?,?,?)}");

                cs.setInt(1, idf);
                cs.setString(2, fechaR);
                cs.setString(3, idprograma);
                cs.setString(4, codigo);
                cs.setString(5, num_recibo);
                cs.setString(6, valor);

                int respuesta = cs.executeUpdate();
                if (respuesta > 0) {
                    JOptionPane.showMessageDialog(null, "registrado añadido");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {

            try {

                cs = cn.prepareCall("{call ins_registro(?,?,?,?,?,?)}");

                cs.setInt(1, idfecha);
                cs.setString(2, fechaR);
                cs.setString(3, idprograma);
                cs.setString(4, codigo);
                cs.setString(5, num_recibo);
                cs.setString(6, valor);

                int respuesta = cs.executeUpdate();
                /* if (respuesta > 0) {
                    JOptionPane.showMessageDialog(null, "registro exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }*/

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }

    public int buscaridfecha(String fecha) {

        int respuesta = 0;

        try {

            st = cn.createStatement();
            rs = st.executeQuery("select idfecha from fecha where fecha = ' " + fecha + "'");
            while (rs.next()) {
                respuesta = rs.getInt("idfecha");
            }

        } catch (Exception e) {
        }

        return respuesta;
    }

    
    public int idbanco(String banco) {

        int id = 0;
        try {

            /*  PreparedStatement ps = cn.prepareStatement("SELECT idproveedor FROM proveedor WHERE nombre = '" + nombre + "'");
            ResultSet rs = ps.executeQuery();*/
            sSQL = "SELECT idbanco FROM banco WHERE nombrebanco = '" + banco + "'";
            st = cn.createStatement();
            rs = st.executeQuery(sSQL);

            while (rs.next()) {
                id = rs.getInt("idbanco");

            }
        } catch (Exception e) {

        }

        return id;

    }

    public String idprograma(String programa) {

        String id = null;
        try {

            /*  PreparedStatement ps = cn.prepareStatement("SELECT idproveedor FROM proveedor WHERE nombre = '" + nombre + "'");
            ResultSet rs = ps.executeQuery();*/
            sSQL = "SELECT idprograma  FROM programa WHERE nombreprograma= '" + programa + "'";
            st = cn.createStatement();
            rs = st.executeQuery(sSQL);

            while (rs.next()) {
                id = rs.getString("idprograma");

            }
        } catch (Exception e) {

        }

        return id;

    }
    
    public String idprograma2(String ref) {

        String id = null;
        try {

            /*  PreparedStatement ps = cn.prepareStatement("SELECT idproveedor FROM proveedor WHERE nombre = '" + nombre + "'");
            ResultSet rs = ps.executeQuery();*/
            sSQL = "SELECT idprograma  FROM programa WHERE referencia = '" + ref + "'";
            st = cn.createStatement();
            rs = st.executeQuery(sSQL);

            while (rs.next()) {
                id = rs.getString("idprograma");

            }
        } catch (Exception e) {

        }

        return id;

    }
    

    public DefaultTableModel verRegistros(int idbanco, String idprograma, int idfecha) {

        if (idbanco != 0 && idprograma.equals("null") && idfecha == 0) {

            sSQL = "select r.idregistro, p.idprograma, p.nombreprograma, f.fecha, r.codigo, r.num_recibo, r.valor from registro r "
                    + " inner join fecha f on r.idfecha=f.idfecha inner join programa p on r.idprograma= p.idprograma"
                    + "  where  f.idbanco = " + idbanco + "";
            String[] titulos = {"IDr", "COD. PROGRAMA", "PROGRAMA", "FECHA", "CODIGO", "N. RECIBO", "VALOR"};
            registro = new String[7];
            opcion = 0;
            //  reg.validacionparaeditar = 0;
            modelo = new DefaultTableModel(null, titulos);

        } else if (idbanco != 0 && !idprograma.equals("null") && idfecha == 0) {
            sSQL = "select r.idregistro, f.fecha, r.codigo, r.num_recibo, r.valor from registro r "
                    + " inner join fecha f on r.idfecha=f.idfecha inner join programa p on r.idprograma= p.idprograma"
                    + "  where  f.idbanco = " + idbanco + " and p.idprograma ='" + idprograma + "'";
            String[] titulos = {"IDr", "FECHA", "CODIGO", "N. RECIBO", "VALOR"};
            registro = new String[5];
            opcion = 1;
            // reg.validacionparaeditar = 1;
            modelo = new DefaultTableModel(null, titulos);

        } else if (idbanco != 0 && !idprograma.equals("null") && idfecha != 0) {
            sSQL = "select r.idregistro, r.codigo, r.num_recibo, r.valor from registro r "
                    + " inner join fecha f on r.idfecha=f.idfecha inner join programa p on r.idprograma= p.idprograma"
                    + "  where  f.idbanco = " + idbanco + " and p.idprograma ='" + idprograma + "'  and f.idfecha = " + idfecha + "";
            String[] titulos = {"IDr", "CODIGO", "N. RECIBO", "VALOR"};
            registro = new String[4];
            opcion = 2;
            // reg.validacionparaeditar = 2;
            modelo = new DefaultTableModel(null, titulos);
        }else if(idbanco!=0 && idprograma.equals("null") && idfecha != 0){
           
            sSQL = "select r.idregistro, p.idprograma, p.nombreprograma, f.fecha, r.codigo, r.num_recibo, r.valor from registro r "
                    + " inner join fecha f on r.idfecha=f.idfecha inner join programa p on r.idprograma= p.idprograma"
                    + "  where  f.idbanco = " + idbanco + " and f.idfecha = " + idfecha + "";
            String[] titulos = {"IDr", "COD. PROGRAMA", "PROGRAMA", "FECHA", "CODIGO", "N. RECIBO", "VALOR"};
            registro = new String[7];
            opcion = 3;
            // reg.validacionparaeditar = 2;
            modelo = new DefaultTableModel(null, titulos);
        }

        int tamano = registro.length;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sSQL);

            while (rs.next()) {

                if (opcion == 0) {

                    registro[0] = rs.getString("idregistro");
                    registro[1] = rs.getString("idprograma");
                    registro[2] = rs.getString("nombreprograma");
                    registro[3] = rs.getString("fecha");
                    registro[4] = rs.getString("codigo");
                    registro[5] = rs.getString("num_recibo");
                    registro[6] = rs.getString("valor");

                    modelo.addRow(registro);
                } else if (opcion == 1) {

                    registro[0] = rs.getString("idregistro");
                    registro[1] = rs.getString("fecha");
                    registro[2] = rs.getString("codigo");
                    registro[3] = rs.getString("num_recibo");
                    registro[4] = rs.getString("valor");
                    modelo.addRow(registro);
                } else if (opcion == 2) {

                    registro[0] = rs.getString("idregistro");
                    registro[1] = rs.getString("codigo");
                    registro[2] = rs.getString("num_recibo");
                    registro[3] = rs.getString("valor");

                    modelo.addRow(registro);

                }else if(opcion == 3){
                    registro[0] = rs.getString("idregistro");
                    registro[1] = rs.getString("idprograma");
                    registro[2] = rs.getString("nombreprograma");
                    registro[3] = rs.getString("fecha");
                    registro[4] = rs.getString("codigo");
                    registro[5] = rs.getString("num_recibo");
                    registro[6] = rs.getString("valor");

                    modelo.addRow(registro);
                }

            }
            return modelo;

        } catch (Exception e) {
            return null;
        }

    }

    public void actualizarR(int idr, String codigo, String num_r, String valor) {

        try {

            cs = cn.prepareCall("{call upd_registro(?,?,?,?)}");

            cs.setInt(1, idr);
            cs.setString(2, codigo);
            cs.setString(3, num_r);
            cs.setString(4, valor);

            int respuesta = cs.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void eliminarR(int idr) {

        try {

            cs = cn.prepareCall("{call elim_registro(?)}");

            cs.setInt(1, idr);

            int respuesta = cs.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
