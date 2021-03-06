package com.mil.sda111mil.forms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mil.sda111mil.beans.Calendario;
import com.mil.sda111mil.beans.Calendario.DiasNoHabiles;
import com.mil.sda111mil.services.Service;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CF02
 */
public class FormFeriado extends javax.swing.JInternalFrame {

    public Integer totalregistros;

    /**
     * Creates new form FormAlumno
     */
    public FormFeriado() {
        initComponents();
        mostrarListaFeriados();
        btn_nuevo.setVisible(false);
        
        txt_desde.getDateEditor().addPropertyChangeListener(
        new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                txt_hasta.setDate(txt_desde.getDate());
            }
        });
        
    }

    public void mostrarListaFeriados() {
        DefaultTableModel modelo;

        String[] titulos = {"Fecha Desde", "Fecha Hasta", "Descripción"};
        modelo = new NoEditableModel(null, titulos);
        List<Calendario> feriados = Service.getFeriados();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Calendario c : feriados) {
            Object[] o = new Object[3];
            DiasNoHabiles dia =  c.getDiasNoHabiles();
            o[0] = df.format(dia.getFechaDesde());
            o[1] = df.format(dia.getFechaHasta());
            o[2] = c.getDescripcion();

            modelo.addRow(o);
        }

        tablaListado.setModel(modelo);

//        tablaListado.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent event) {
//
//                // do some actions here, for example
//                // print first column value from selected row
//                if (tablaListado.getSelectedRow() >= 0) {
//                    String fechaDesde= tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
//                    cargarFichaFeriado(fechaDesde);
//                }
//            }
//        });
    }
    public void mostrarFeriado() {
        DefaultTableModel modelo;

        String[] titulos = {"Fecha Desde", "Fecha Hasta", "Descripción"};

        modelo = new DefaultTableModel(null, titulos);
        
      
        tablaListado.setModel(modelo);

        tablaListado.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {

                // do some actions here, for example
                // print first column value from selected row
                if (tablaListado.getSelectedRow() >= 0) {
                    String fechaDesde = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
                    cargarFichaFeriado(fechaDesde);
                }
            }
        });
    }
    private void cargarFichaFeriado(String fechaDesde) {
     
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registroFeriados1 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_nuevo1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txt_hasta = new com.toedter.calendar.JDateChooser();
        txt_desde = new com.toedter.calendar.JDateChooser();
        registroFeriados2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        btn_nuevo2 = new javax.swing.JButton();
        btn_nuevo3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Días no hábiles");
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registroFeriados1.setBackground(new java.awt.Color(204, 255, 204));
        registroFeriados1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        registroFeriados1.setForeground(new java.awt.Color(250, 250, 250));
        registroFeriados1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_nuevo.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_nuevo.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Cancel_24x24.png"))); // NOI18N
        btn_nuevo.setText("Cancelar");
        btn_nuevo.setBorder(null);
        btn_nuevo.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        registroFeriados1.add(btn_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, -1, -1));

        btn_nuevo1.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_nuevo1.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Save_24x24.png"))); // NOI18N
        btn_nuevo1.setText("Guardar");
        btn_nuevo1.setBorder(null);
        btn_nuevo1.setEnabled(false);
        btn_nuevo1.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_nuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevo1ActionPerformed(evt);
            }
        });
        registroFeriados1.add(btn_nuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Desde:");
        registroFeriados1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Hasta:");
        registroFeriados1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Descripcion:");
        registroFeriados1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        registroFeriados1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 220, 30));

        txt_hasta.setEnabled(false);
        txt_hasta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hasta.setPreferredSize(new java.awt.Dimension(170, 30));
        registroFeriados1.add(txt_hasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 190, 30));

        txt_desde.setEnabled(false);
        txt_desde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_desde.setPreferredSize(new java.awt.Dimension(170, 30));
        registroFeriados1.add(txt_desde, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 190, 30));

        getContentPane().add(registroFeriados1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 420, 360));

        registroFeriados2.setBackground(new java.awt.Color(255, 204, 204));
        registroFeriados2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        registroFeriados2.setForeground(new java.awt.Color(250, 250, 250));
        registroFeriados2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fecha Desde", "Fecha Hasta", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaListado.getTableHeader().setReorderingAllowed(false);
        tablaListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaListadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaListado);
        if (tablaListado.getColumnModel().getColumnCount() > 0) {
            tablaListado.getColumnModel().getColumn(0).setMinWidth(100);
            tablaListado.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaListado.getColumnModel().getColumn(0).setMaxWidth(100);
            tablaListado.getColumnModel().getColumn(1).setMinWidth(100);
            tablaListado.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaListado.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        registroFeriados2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 430, 260));

        btn_nuevo2.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_nuevo2.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Delete_24x24.png"))); // NOI18N
        btn_nuevo2.setText("Eliminar");
        btn_nuevo2.setBorder(null);
        btn_nuevo2.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_nuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevo2ActionPerformed(evt);
            }
        });
        registroFeriados2.add(btn_nuevo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        btn_nuevo3.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_nuevo3.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Add_24x24.png"))); // NOI18N
        btn_nuevo3.setText("Nuevo");
        btn_nuevo3.setBorder(null);
        btn_nuevo3.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_nuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevo3ActionPerformed(evt);
            }
        });
        registroFeriados2.add(btn_nuevo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        getContentPane().add(registroFeriados2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 360));

        getAccessibleContext().setAccessibleName("Días No Habiles");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed

    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_nuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevo1ActionPerformed
          
        
        if (txt_desde.getDate() != null && txt_hasta.getDate() != null && 
                !jTextField1.getText().isEmpty() && !txt_desde.getDate().toString().isEmpty() && !txt_hasta.getDate().toString().isEmpty()) {
            Service.crearFeriado(txt_desde.getDate(), txt_hasta.getDate(), jTextField1.getText());
            mostrarListaFeriados();
            
            this.txt_desde.setDate(null);
            this.txt_hasta.setDate(null);
            this.jTextField1.setText("");
            this.txt_desde.setEnabled(false);
            this.txt_hasta.setEnabled(false);
            this.jTextField1.setEnabled(false);
            this.btn_nuevo1.setEnabled(false);
            
        } else {
            if (txt_desde.getDate()==null || txt_desde.getDate().toString().isEmpty()) {
                 JOptionPane.showMessageDialog(rootPane, "Ingresar fecha desde.");
                 txt_desde.requestFocus();
            } else if (txt_hasta.getDate()==null || txt_hasta.getDate().toString().isEmpty()) {
                 JOptionPane.showMessageDialog(rootPane, "Ingresar fecha hasta.");
                 txt_hasta.requestFocus();
            } else if (jTextField1.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(rootPane, "Ingresar descripción.");
                 jTextField1.requestFocus();
            }
        }

    }//GEN-LAST:event_btn_nuevo1ActionPerformed

    private void tablaListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaListadoMouseClicked

    private void btn_nuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevo2ActionPerformed
        if (tablaListado.getSelectedRow() >= 0) {
              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
              String desde = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
             try {
              Date desdeDate = sdf.parse(desde);
              Date today = new Date();
              if (desdeDate.before(today)) {
                JOptionPane.showMessageDialog(rootPane, "No se pueden eliminar feriados pasados.");
                return ;
              }
              
              String hasta = tablaListado.getValueAt(tablaListado.getSelectedRow(), 1).toString();
           
                Service.eliminarFeriado(desdeDate,sdf.parse(hasta));
                mostrarListaFeriados();
            } catch (ParseException ex) {
                Logger.getLogger(FormFeriado.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debes seleccionar algún feriado para eliminar.");
            }
   
    }//GEN-LAST:event_btn_nuevo2ActionPerformed

    private void btn_nuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevo3ActionPerformed
        this.txt_desde.setEnabled(true);
        this.txt_hasta.setEnabled(true);
        this.jTextField1.setEnabled(true);
        this.btn_nuevo1.setEnabled(true);

    }//GEN-LAST:event_btn_nuevo3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    // metodo para crear solo una instancia del formulario al hacer click en el menu
    private static FormFeriado feriado;

    public static FormFeriado getInstance() {
        if (feriado == null) {
            feriado = new FormFeriado();
        }
        return feriado;
    }

    public void inhabilitar() {

    }

    public void habilitar() {

    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_nuevo1;
    private javax.swing.JButton btn_nuevo2;
    private javax.swing.JButton btn_nuevo3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel registroFeriados1;
    private javax.swing.JPanel registroFeriados2;
    private javax.swing.JTable tablaListado;
    private com.toedter.calendar.JDateChooser txt_desde;
    private com.toedter.calendar.JDateChooser txt_hasta;
    // End of variables declaration//GEN-END:variables
}
