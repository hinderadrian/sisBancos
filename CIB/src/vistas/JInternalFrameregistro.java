/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import CIB.Conexion;
import Op.registroOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julian C Vargas Q
 */
public class JInternalFrameregistro extends javax.swing.JInternalFrame {

    Conexion mysql = new Conexion();
    Connection cn = mysql.getconexion();

    String sSQL = "";
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;

    int idbanco;
    String idprograma;
    int idfecha;
    String fechaR;
    public int validacionparaeditar;
    int idregistro;

    ArrayList cod = new ArrayList();
    ArrayList num_r = new ArrayList();
    ArrayList valor = new ArrayList();

    String letra;
    int ordenVariable = 0;
    String texto;
    File file;

    registroOp r = new registroOp();

    /**
     * Creates new form JInternalFrameregistro
     */
    public JInternalFrameregistro() {

        initComponents();
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        txtfechar.setText(date.format(now));

        txtfechar.setVisible(false);
        btnactualizar.setEnabled(false);
        txtidprograma.setEditable(false);
        cargarbancos();
        cargarprogramas();
    }

    public void cargarbancos() {

        this.cbobanco.removeAllItems();

        try {

            st = cn.createStatement();
            rs = st.executeQuery("select * from banco");
            cbobanco.addItem("");
            while (rs.next()) {
                this.cbobanco.addItem(rs.getString("nombrebanco"));
            }

        } catch (Exception e) {

        }

    }

    public void cargarprogramas() {

        this.cboprogram.removeAllItems();

        try {

            st = cn.createStatement();
            rs = st.executeQuery("select * from programa");
            cboprogram.addItem("");
            while (rs.next()) {
                this.cboprogram.addItem(rs.getString("nombreprograma"));
            }

        } catch (Exception e) {

        }

    }

    public String retornarString(Calendar fecha) {
        //Método que retorna un String de fecha pasándole como argumento un objeto Calendar.
        String retorno = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (fecha != null) {
            retorno = sdf.format(fecha.getTime());
        }
        return retorno;
    }

    public void ocultarid() {
        jTableregistro.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableregistro.getColumnModel().getColumn(0).setMinWidth(0);
        jTableregistro.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableregistro.getColumnModel().getColumn(0).setResizable(false);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbobanco = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jDfecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtidprograma = new javax.swing.JTextField();
        cboprogram = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableregistro = new javax.swing.JTable();
        txtcodestudiante = new javax.swing.JTextField();
        txtnum_recibo = new javax.swing.JTextField();
        txtvalor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        txtfechar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnimport = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jLabel1.setText("Banco");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 115, -1));

        cbobanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbobanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbobancoActionPerformed(evt);
            }
        });
        getContentPane().add(cbobanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 173, -1));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jLabel2.setText("Fecha");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, -1, -1));

        jDfecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDfechaMouseClicked(evt);
            }
        });
        jDfecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDfechaPropertyChange(evt);
            }
        });
        getContentPane().add(jDfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 170, -1));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jLabel3.setText("Programa");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 124, -1));

        txtidprograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidprogramaActionPerformed(evt);
            }
        });
        getContentPane().add(txtidprograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 50, 30));

        cboprogram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboprogram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboprogramActionPerformed(evt);
            }
        });
        getContentPane().add(cboprogram, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 280, -1));

        jTableregistro =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jTableregistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "N. RECIBO", "VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableregistro.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableregistro);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 620, 270));
        getContentPane().add(txtcodestudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, -1));
        getContentPane().add(txtnum_recibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 150, -1));

        txtvalor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorActionPerformed(evt);
            }
        });
        getContentPane().add(txtvalor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 150, -1));

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jLabel4.setText("Cod. estudiante");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jLabel5.setText("N. Recibo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jLabel6.setText("Valor");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        btneditar.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        btneditar.setText("Editar");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });
        getContentPane().add(btneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 130, 48));

        btnactualizar.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, 130, 52));
        getContentPane().add(txtfechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, -1));

        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 890, 90));

        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 620, 90));

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 380, 130, 60));

        btnimport.setFont(new java.awt.Font("Bookman Old Style", 3, 15)); // NOI18N
        btnimport.setText("IMPORTAR .TXT");
        btnimport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportActionPerformed(evt);
            }
        });
        getContentPane().add(btnimport, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 170, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbobancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbobancoActionPerformed

        try {

            idbanco = r.idbanco(cbobanco.getSelectedItem().toString());

            modelo = r.verRegistros(idbanco, txtidprograma.getText(), idfecha);
            jTableregistro.setModel(modelo);
            ocultarid();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbobancoActionPerformed

    private void jDfechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDfechaMouseClicked
        try {
            String fecha = retornarString(jDfecha.getCalendar());
            idfecha = buscaridfecha(fecha);
            modelo = r.verRegistros(idbanco, txtidprograma.getText(), idfecha);
            jTableregistro.setModel(modelo);
            ocultarid();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jDfechaMouseClicked

    private void jDfechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDfechaPropertyChange

        
      
        //  JOptionPane.showMessageDialog(null, "ha cambiado la fecha");
        try {
            String fecha = retornarString(jDfecha.getCalendar());
            idfecha = buscaridfecha(fecha);
            modelo = r.verRegistros(idbanco, txtidprograma.getText(), idfecha);
       
                  jTableregistro.setModel(new DefaultTableModel());
                 jTableregistro.setModel(modelo);
                 ocultarid();
 
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jDfechaPropertyChange

    private void txtidprogramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidprogramaActionPerformed

    }//GEN-LAST:event_txtidprogramaActionPerformed

    private void cboprogramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboprogramActionPerformed
        jTableregistro.setModel(new DefaultTableModel());
        try {
            idprograma = r.idprograma(cboprogram.getSelectedItem().toString());
            txtidprograma.setText(String.valueOf(idprograma));
            modelo = r.verRegistros(idbanco, txtidprograma.getText(), idfecha);
            jTableregistro.setModel(modelo);
            ocultarid();
            System.out.println("idp " + idprograma);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cboprogramActionPerformed

    private void txtvalorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorActionPerformed

        if (cbobanco.getSelectedItem().toString().equals("") && cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) == null) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado banco,programa y fecha");
        } else if (!cbobanco.getSelectedItem().toString().equals("") && cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un programa y fecha");
        } else if (cbobanco.getSelectedItem().toString().equals("") && !cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) == null) {
            JOptionPane.showMessageDialog(null, "seleccione un banco y fecha ");
        } else if (cbobanco.getSelectedItem().toString().equals("") && cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) != null) {
            JOptionPane.showMessageDialog(null, "seleccione un banco y programa ");

        } else if (cbobanco.getSelectedItem().toString().equals("") && !cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) != null) {
            JOptionPane.showMessageDialog(null, "seleccione un banco  ");
        } else if (!cbobanco.getSelectedItem().toString().equals("") && cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) != null) {
            JOptionPane.showMessageDialog(null, "seleccione un programa  ");
        } else if (!cbobanco.getSelectedItem().toString().equals("") && !cboprogram.getSelectedItem().toString().equals("")
                && retornarString(jDfecha.getCalendar()) == null) {
            JOptionPane.showMessageDialog(null, "seleccione una fecha ");

        } else {

            String banco = cbobanco.getSelectedItem().toString();
            String fecha = retornarString(jDfecha.getCalendar());
            idfecha = buscaridfecha(fecha);
            fechaR = txtfechar.getText();
            String codigo = txtcodestudiante.getText();
            String num_recibo = txtnum_recibo.getText();
            String valor = txtvalor.getText();

            /*  JOptionPane.showMessageDialog(null, ""+idbanco+" "+banco+" "+idfecha+" "+fecha+" "+fechaR+" "+idprograma+" "
                + ""+programa+" "+codigo+" "+num_recibo+" "+valor);
             */
            try {
                r.registro(idbanco, idfecha, fecha, fechaR, idprograma, codigo, num_recibo, valor);
                txtcodestudiante.setText("");
                txtnum_recibo.setText("");
                txtvalor.setText("");
                txtcodestudiante.requestFocus();
            } catch (Exception e) {
            }

        }

    }//GEN-LAST:event_txtvalorActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

        int fila = jTableregistro.getSelectedRow();
        if (fila != -1) {

            if (r.opcion == 0) {
                idregistro = Integer.parseInt(jTableregistro.getValueAt(fila, 0).toString());
                txtcodestudiante.setText(jTableregistro.getValueAt(fila, 4).toString());
                txtnum_recibo.setText(jTableregistro.getValueAt(fila, 5).toString());
                txtvalor.setText(jTableregistro.getValueAt(fila, 6).toString());
                btnactualizar.setEnabled(true);
            } else if (r.opcion == 1) {
                idregistro = Integer.parseInt(jTableregistro.getValueAt(fila, 0).toString());
                txtcodestudiante.setText(jTableregistro.getValueAt(fila, 2).toString());
                txtnum_recibo.setText(jTableregistro.getValueAt(fila, 3).toString());
                txtvalor.setText(jTableregistro.getValueAt(fila, 4).toString());
                btnactualizar.setEnabled(true);
            } else if (r.opcion == 2) {
                idregistro = Integer.parseInt(jTableregistro.getValueAt(fila, 0).toString());
                txtcodestudiante.setText(jTableregistro.getValueAt(fila, 1).toString());
                txtnum_recibo.setText(jTableregistro.getValueAt(fila, 2).toString());
                txtvalor.setText(jTableregistro.getValueAt(fila, 3).toString());
                btnactualizar.setEnabled(true);
            }

            btnactualizar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        }

    }//GEN-LAST:event_btneditarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed

        try {
            String codigo = txtcodestudiante.getText();
            String num_recibo = txtnum_recibo.getText();
            String valor = txtvalor.getText();

            r.actualizarR(idregistro, codigo, num_recibo, valor);

            String fecha = retornarString(jDfecha.getCalendar());
            idfecha = buscaridfecha(fecha);
            modelo = r.verRegistros(idbanco, txtidprograma.getText(), idfecha);
            jTableregistro.setModel(modelo);
            ocultarid();

            txtcodestudiante.setText("");
            txtnum_recibo.setText("");
            txtvalor.setText("");
            txtcodestudiante.requestFocus();
            btnactualizar.setEnabled(false);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnactualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int fila = jTableregistro.getSelectedRow();
        if (fila != -1) {
            int resp = JOptionPane.showConfirmDialog(null, "Confirmacion para eliminar el registro ", "Seleccionar opcion", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                idregistro = Integer.parseInt(jTableregistro.getValueAt(fila, 0).toString());
                r.eliminarR(idregistro);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnimportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportActionPerformed

        if (cbobanco.getSelectedItem().toString().equals("") && retornarString(jDfecha.getCalendar()) == null) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado banco y fecha");
        } else if (cbobanco.getSelectedItem().toString().equals("") && retornarString(jDfecha.getCalendar()) != null) {
            JOptionPane.showMessageDialog(null, "seleccione un banco ");
        } else if (!cbobanco.getSelectedItem().toString().equals("") && retornarString(jDfecha.getCalendar()) == null) {
            JOptionPane.showMessageDialog(null, "seleccione una fecha ");
        } else {

            JFileChooser archivo = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(".txt", "txt");
            archivo.setFileFilter(filtro);
            archivo.setAcceptAllFileFilterUsed(false);
            // archivo.addChoosableFileFilter(new FileNameExtensionFilter(".txt", ".txt"));

            if (archivo.showDialog(null, "Importar") == JFileChooser.APPROVE_OPTION) {
                file = archivo.getSelectedFile();
                if (file.exists()) {
                    try {
                        FileReader lector = new FileReader(file);
                        BufferedReader bufer = new BufferedReader(lector);

                        while ((texto = bufer.readLine()) != null) {

                            if (!Character.isWhitespace(texto.charAt(0))) {
                                ordenVariable = 1;
                            }

                            for (int x = 0; x < texto.length(); x++) {

                                letra = String.valueOf(texto.charAt(x));

                                if (ordenVariable < 4 && !Character.isWhitespace(texto.charAt(x))) {
                                    switch (ordenVariable) {
                                        case 1:
                                            cod.add(letra);
                                            break;
                                        case 2:
                                            num_r.add(letra);
                                            break;
                                        case 3:
                                            valor.add(letra);
                                            break;

                                    }
                                }

                                if (Character.isWhitespace(texto.charAt(x)) && !Character.isWhitespace(texto.charAt(x + 1))) {
                                    System.out.println("condicion verdadera");
                                    if (ordenVariable < 4) {
                                        ordenVariable++;
                                        System.out.println("orden " + ordenVariable);
                                    } else if (ordenVariable == 4) {
                                        ordenVariable = 0;

                                    }

                                }

                            }

                            ordenVariable = 0;
                            System.out.println("" + cod);
                            System.out.println("" + num_r);
                            System.out.println("" + valor);
                            if (cod.size() != 0 && num_r.size() != 0 && valor.size() != 0) {
                                String codigoestudiante = "";
                                String numerorecibo = "";
                                String valor2 = "";
                                String ref = "";
                                int contador = 0;
                                for (int i = 0; i < cod.size(); i++) {
                                    codigoestudiante = codigoestudiante + cod.get(i);
                                    if (contador < 4) {
                                        contador++;
                                        ref = ref + cod.get(i);
                                    }
                                }
       
                                for (int i = 0; i < num_r.size(); i++) {
                                    numerorecibo = numerorecibo + num_r.get(i);
                                }
                                for (int i = 0; i < valor.size(); i++) {
                                    valor2 = valor2 + valor.get(i);
                                }

                                String fecha = retornarString(jDfecha.getCalendar());
                                idfecha = buscaridfecha(fecha);
                                fechaR = txtfechar.getText();
                                try {

                                    idprograma = r.idprograma2(ref);
                                    if (idprograma == null) {

                                        String ref2 = ref.substring(0, ref.length() - 1);
                                        System.out.println(ref2);
                                        idprograma = r.idprograma2(ref2);
                                        r.registro(idbanco, idfecha, fecha, fechaR, idprograma, codigoestudiante, numerorecibo, valor2);
                                    
                                    } else {
                                        r.registro(idbanco, idfecha, fecha, fechaR, idprograma, codigoestudiante, numerorecibo, valor2);
                                    
                                    } 
                               
                                } catch (Exception e) {
                                }

                            }

                            cod = new ArrayList();
                            num_r = new ArrayList();
                            valor = new ArrayList();

                        }
                        JOptionPane.showMessageDialog(null, "Informacion cargada exitosamente");
                    } catch (Exception e) {
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no existe ");
                }
            }

        }

        try {
            modelo = r.verRegistros(idbanco, txtidprograma.getText(), idfecha);
            jTableregistro.setModel(modelo);
            ocultarid();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnimportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnimport;
    private javax.swing.JComboBox<String> cbobanco;
    private javax.swing.JComboBox<String> cboprogram;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDfecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableregistro;
    private javax.swing.JTextField txtcodestudiante;
    private javax.swing.JTextField txtfechar;
    private javax.swing.JTextField txtidprograma;
    private javax.swing.JTextField txtnum_recibo;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
}