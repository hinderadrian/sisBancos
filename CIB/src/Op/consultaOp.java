/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Op;

import CIB.Conexion;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Julian C Vargas Q
 */
public class consultaOp {

    Conexion mysql = new Conexion();
    Connection cn = mysql.getconexion();

    String sSQL = "";
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    String[] registro;
    CallableStatement cs;
    String idprog[];
    int idfacult[];
    String facultades[];
    int subtotal;
    int totalpresencial;
    int grantotal;
    String facultad;
    Workbook wb;
    Cell celda;
    int subirRow = 1;
    Sheet hoja;
    Row fila;

    public DefaultTableModel cargarReporte(int id, String f1, String f2, File file, String nomb_hoja) {

        DefaultTableModel modelo;

        if (file.getName().endsWith("xls")) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }

        if (nomb_hoja.equals("")) {
            hoja = wb.createSheet("Hoja1");
        } else {
            hoja = wb.createSheet(nomb_hoja);
        }

        String[] titulos = {"FACULTAD", "COD.", "PROGRAMA", "VALOR"};

        registro = new String[4];
        modelo = new DefaultTableModel(null, titulos);

        //int idfacult[] = new int[buscarfacultades().length];
        buscarfacultades();
        int idfacultad;


        try {
            for (int f = 0; f < idfacult.length; f++) {
                idfacultad = idfacult[f];

        generarfilas();
        celda = fila.createCell(0);
        celda.setCellValue(facultades[f]);
                System.out.println("");
                
                idprogramas(idfacultad);

                for (int p = 0; p < idprog.length; p++) {

                    try {

                        if (id == 0) {
                            sSQL = "select facult.nombfacultad, p.idprograma, p.nombreprograma, sum(r.valor) as subtotal "
                                    + "from registro r  inner join fecha f on r.idfecha=f.idfecha inner join programa p "
                                    + " on r.idprograma= p.idprograma inner join facultad facult on p.idfacultad = facult.idfacultad where p.idfacultad = "
                                    + idfacultad + " and p.idprograma ='" + idprog[p] + "'  "
                                    + " and f.fecha  between '" + f1 + "' and '" + f2 + "'";

                        } else if (id != 0) {
                            sSQL = "select facult.nombfacultad, p.idprograma, p.nombreprograma, sum(r.valor) as subtotal "
                                    + "from registro r  inner join fecha f on r.idfecha=f.idfecha inner join programa p "
                                    + " on r.idprograma= p.idprograma inner join facultad facult on p.idfacultad = facult.idfacultad where  f.idbanco = " + id + " and p.idfacultad = "
                                    + idfacultad + " and p.idprograma ='" + idprog[p] + "'  "
                                    + " and f.fecha  between '" + f1 + "' and '" + f2 + "'";
                        }

                        st = cn.createStatement();
                        rs = st.executeQuery(sSQL);

                        while (rs.next()) {

                            registro[0] = rs.getString("nombfacultad");
                            facultad = registro[0];
                            registro[1] = rs.getString("idprograma");
                            registro[2] = rs.getString("nombreprograma");
                            registro[3] = rs.getString("subtotal");

                            subtotal = subtotal + Integer.parseInt(registro[3]);

                            generarfilas();

                            celda = fila.createCell(0);
                            celda.setCellValue(registro[1]);
                            celda = fila.createCell(1);
                            celda.setCellValue(registro[2]);
                            celda = fila.createCell(2);
                            celda.setCellValue(registro[3]);

                            modelo.addRow(registro);

                        }

                    } catch (Exception e) {
                            generarfilas();
                            
                    }

                }
                //salida del for de programas 
                generarfilas();
                celda = fila.createCell(1);
                celda.setCellValue("TOTAL");
                celda = fila.createCell(2);
                celda.setCellValue(subtotal);
                grantotal = grantotal + subtotal;
                subtotal = 0;

                generarfilas();
                
                /*fila = hoja.createRow(subirRow);
                celda = fila.createCell(0);
                celda.setCellValue(facultades[f]);*/

                /* if(facultades[f].equals("ESPECIALIZACIONES")){
                subirRow++;
                fila = hoja.createRow(subirRow);
                celda = fila.createCell(1);
                celda.setCellValue("TOTAL");
                celda = fila.createCell(2);
                celda.setCellValue(grantotal);
                }*/
            }

            // salida del for  de  facultades
            wb.write(new FileOutputStream(file));

            System.out.println("exportacion exitosa");
            wb.close();
            subirRow = 1;
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
            return null;
        }

    }

    public void generarfilas() {
        subirRow++;
        fila = hoja.createRow(subirRow);

    }

    public void buscarfacultades() {

        try {

            int i = 0;
            int cantidad = 0;

            st = cn.createStatement();
            rs = st.executeQuery("select count(idfacultad) as cantidad from facultad");
            while (rs.next()) {
                cantidad = rs.getInt("cantidad");
            }
            idfacult = new int[cantidad];
            facultades = new String[cantidad];
            st = cn.createStatement();
            rs = st.executeQuery("select idfacultad, nombfacultad from facultad");
            while (rs.next()) {
                idfacult[i] = rs.getInt("idfacultad");
                facultades[i] = rs.getString("nombfacultad");
            
                i++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }

    }

    public void idprogramas(int idfacultad) {

        //String[] idp = null;
        int i = 0;
        int cantidad = 0;
        try {

            st = cn.createStatement();
            rs = st.executeQuery("select count(idprograma) as cantidad from programa");
            while (rs.next()) {
                cantidad = rs.getInt("cantidad");
            }
            idprog = new String[cantidad];
            st = cn.createStatement();
            rs = st.executeQuery("select idprograma from programa where idfacultad =" + idfacultad);
            while (rs.next()) {
                idprog[i] = rs.getString("idprograma");
                i++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }
    }

}
