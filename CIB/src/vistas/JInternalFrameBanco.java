/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Op.bancosOp;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julian C Vargas Q
 */
public class JInternalFrameBanco extends javax.swing.JInternalFrame {

    /**
     * Creates new form JInternalFrameBanco
     */
    
    bancosOp b = new bancosOp();
    
    public JInternalFrameBanco() {
      
        initComponents();
         verbancos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnguardarbanco = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablebancos = new javax.swing.JTable();
        txtnombreb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setFocusTraversalPolicyProvider(true);

        btnguardarbanco.setFont(new java.awt.Font("Bookman Old Style", 3, 16)); // NOI18N
        btnguardarbanco.setText("Guardar");
        btnguardarbanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarbancoActionPerformed(evt);
            }
        });

        jTablebancos.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jTablebancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BANCO"
            }
        ));
        jScrollPane1.setViewportView(jTablebancos);

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 3, 16)); // NOI18N
        jLabel2.setText("Banco");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombreb, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnguardarbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnguardarbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnombreb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarbancoActionPerformed

        String banco = txtnombreb.getText();

        b.registrarbanco(banco);

        verbancos();

    }//GEN-LAST:event_btnguardarbancoActionPerformed

     public void verbancos(){
        
        DefaultTableModel modelo ;
        modelo = b.verBancos();
        jTablebancos.setModel(modelo);
      //  imagensalir();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardarbanco;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablebancos;
    private javax.swing.JTextField txtnombreb;
    // End of variables declaration//GEN-END:variables
}