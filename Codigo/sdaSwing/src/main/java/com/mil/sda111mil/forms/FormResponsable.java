/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mil.sda111mil.forms;

import com.mil.sda111mil.beans.Alumno;
import com.mil.sda111mil.services.Service;
import com.mil.sda111mil.beans.Responsable;
import com.mil.sda111mil.beans.Telefono;
import com.mil.sda111mil.beans.Telefono.Numero;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CF02
 */
public class FormResponsable extends javax.swing.JInternalFrame {

    /**
     * Creates new form FormAlumno
     */
    public FormResponsable() {
        initComponents();
        inhabilitar();
        mostrarListaResponsables();
    }

    public void mostrarListaResponsables() {
        DefaultTableModel modelo;

        String[] titulos = {"DNI", "Apellido", "Nombre", "Alumno a Cargo", "Teléfono"};

        modelo = new NoEditableModel(null, titulos);

        List<Responsable> lista = Service.listarResponsables();
        jTextCantRespon.setText(String.valueOf(lista.size()));

        for (Responsable r : lista) {
            Object[] o = new Object[5];
            o[0] = r.getDni();
            o[1] = r.getApellido();
            o[2] = r.getNombre();
            String alumno = "";
            for (Alumno a : r.getaCargo()) {
                alumno += a.getApellido() + ", " + a.getNombre() + "/ ";
            }
            if(alumno.length() > 0){
                alumno = alumno.substring(0,alumno.length()-2);
            }
            o[3] = alumno;
            
            String tel = "";
            for (Telefono t : r.getTelefonos()) {
                tel =tel + t.getNumero().getCodigoArea()+ "-" + t.getNumero().getNroTelefono() + "/ " ;
                
            }
            if(tel.length() > 0){
                tel = tel.substring(0, tel.length()-2);
            }
            o[4] = tel;
            modelo.addRow(o);
        }
        tablaListado.setModel(modelo);
        tablaListado.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {

                // do some actions here, for example
                // print first column value from selected row
                if (tablaListado.getSelectedRow() >= 0) {
                    String dni = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
                    cargarFichaResponsable(dni);
                }
            }

        });
    }

    public void mostrarResponsableBuscado() {
        DefaultTableModel modelo;

        String[] titulos = {"DNI", "Apellido", "Nombre", "Alumno a Cargo", "Teléfono"};

        modelo = new DefaultTableModel(null, titulos);

        List<Responsable> lista = Service.encontrarResponsable(txt_buscar_nombre.getText(), txt_buscar_apellido.getText());

        for (Responsable r : lista) {
            Object[] o = new Object[5];
            o[0] = r.getDni();
            o[1] = r.getApellido();
            o[2] = r.getNombre();
            String alumno = "";
            for (Alumno a : r.getaCargo()) {
                alumno += a.getApellido() + ", " + a.getNombre() + "/ ";
            }
            if(alumno.length() > 0){
                alumno = alumno.substring(0,alumno.length()-2);
            }
            o[3] = alumno;
            String tel = "";
            for (Telefono t : r.getTelefonos()) {
                tel += t.getNumero().getCodigoArea() + t.getNumero().getNroTelefono() + "/ " ;
                
            }
            if(tel.length() > 0){
                tel = tel.substring(0, tel.length()-2);
            }
            o[4] = tel;

            
            modelo.addRow(o);
        }

        tablaListado.setModel(modelo);
        tablaListado.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {

                // do some actions here, for example
                // print first column value from selected row
                if (tablaListado.getSelectedRow() >= 0) {
                    String dni = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
                    cargarFichaResponsable(dni);
                }
            }

        });
    }

    private void cargarFichaResponsable(String dni) {
        cleanResponsableForm();
        habilitarEdicion();
        Responsable elegido = Service.listarPorDni3(dni);
        txt_dni_r.setText(dni);
        txt_nombre.setText(elegido.getNombre());
        txt_apellido.setText(elegido.getApellido());
        
        String alumno = "";
        for (Alumno a : elegido.getaCargo()) {
            alumno += a.getApellido() + ", " + a.getNombre() + "/ ";
        }
        if(alumno.length() >0) {
            alumno = alumno.substring(0, alumno.length()-2);
        }
        txt_a_cargo.setText(alumno);
        List<Telefono> listTelefonos = elegido.getTelefonos();

        txt_telefono_area.setText(String.valueOf(listTelefonos.get(0).getNumero().getCodigoArea())); 
        txt_telefono_numero.setText(String.valueOf(listTelefonos.get(0).getNumero().getNroTelefono()));
         if (elegido.getTelefonos().get(0).getTipo() == 0) {
            cboTipoTelefono.getModel().setSelectedItem("Móvil");
        } else {
            cboTipoTelefono.getModel().setSelectedItem("Fijo");
        }
        btn_agregar.setVisible(true);
        if(listTelefonos.size()>1){
            txt_responsable_telefono_area1.setVisible(true);
            txt_responsable_telefono_area1.setEnabled(true);
            txt_responsable_telefono_numero1.setVisible(true);
            txt_responsable_telefono_numero1.setEnabled(true);
            cboTipoTelefono1.setVisible(true);
            cboTipoTelefono1.setEnabled(true);
            btn_agregar.setEnabled(false);
            jLabel7.setVisible(true);
            btn_quitar.setVisible(true);
            btn_agregar1.setVisible(true);
            txt_responsable_telefono_area1.setText(String.valueOf(listTelefonos.get(1).getNumero().getCodigoArea())); 
            txt_responsable_telefono_numero1.setText(String.valueOf(listTelefonos.get(1).getNumero().getNroTelefono()));
             if (elegido.getTelefonos().get(1).getTipo() == 0) {
                cboTipoTelefono1.getModel().setSelectedItem("Móvil");
                jLabel8.setVisible(true);
            } else {
                cboTipoTelefono1.getModel().setSelectedItem("Fijo");
            }
            
            if(listTelefonos.size()>2){
                txt_responsable_telefono_area2.setVisible(true);
                txt_responsable_telefono_area2.setEnabled(true);
                txt_responsable_telefono_numero2.setVisible(true);
                txt_responsable_telefono_numero2.setEnabled(true);
                btn_quitar.setEnabled(false);
                btn_agregar1.setEnabled(false);
                cboTipoTelefono2.setVisible(true);
                cboTipoTelefono2.setEnabled(true);
                btn_quitar1.setVisible(true);
                jLabel10.setVisible(true);
                txt_responsable_telefono_area2.setText(String.valueOf(listTelefonos.get(2).getNumero().getCodigoArea())); 
                txt_responsable_telefono_numero2.setText(String.valueOf(listTelefonos.get(2).getNumero().getNroTelefono()));
                 if (elegido.getTelefonos().get(2).getTipo() == 0) {
                    cboTipoTelefono2.getModel().setSelectedItem("Móvil");
                    jLabel9.setVisible(true);
                } else {
                    cboTipoTelefono2.getModel().setSelectedItem("Fijo");
                }
            }
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registroResponsables = new javax.swing.JPanel();
        RNombre = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        RApellido = new javax.swing.JLabel();
        txt_apellido = new javax.swing.JTextField();
        RDNI = new javax.swing.JLabel();
        txt_dni_r = new javax.swing.JTextField();
        RTelefono = new javax.swing.JLabel();
        txt_telefono_area = new javax.swing.JTextField();
        txt_telefono_numero = new javax.swing.JTextField();
        btn_modificar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        cboTipoTelefono = new javax.swing.JComboBox<>();
        cboTipoTelefono1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_responsable_telefono_area1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_responsable_telefono_numero1 = new javax.swing.JTextField();
        btn_agregar1 = new javax.swing.JButton();
        txt_responsable_telefono_numero2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_responsable_telefono_area2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboTipoTelefono2 = new javax.swing.JComboBox<>();
        RDNI1 = new javax.swing.JLabel();
        btn_quitar = new javax.swing.JButton();
        btn_quitar1 = new javax.swing.JButton();
        txt_a_cargo = new javax.swing.JTextField();
        listaResponsables = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        lblTotalRegistros = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        txt_buscar_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_buscar_apellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        jTextCantRespon = new javax.swing.JTextField();
        btn_refresh = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Responsable");
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registroResponsables.setBackground(new java.awt.Color(204, 255, 204));
        registroResponsables.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Responsables"));
        registroResponsables.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RNombre.setText("Nombre:");
        RNombre.setMaximumSize(new java.awt.Dimension(60, 15));
        RNombre.setMinimumSize(new java.awt.Dimension(60, 15));
        RNombre.setPreferredSize(new java.awt.Dimension(60, 22));
        registroResponsables.add(RNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 70, 30));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombre.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });
        registroResponsables.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 70, 190, 30));

        RApellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RApellido.setText("Apellido:");
        RApellido.setMaximumSize(new java.awt.Dimension(60, 15));
        RApellido.setMinimumSize(new java.awt.Dimension(60, 15));
        RApellido.setPreferredSize(new java.awt.Dimension(60, 22));
        registroResponsables.add(RApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 110, -1, 30));

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_apellido.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });
        txt_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellidoKeyTyped(evt);
            }
        });
        registroResponsables.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 110, 190, 30));

        RDNI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RDNI.setText("A Cargo de:");
        RDNI.setMaximumSize(new java.awt.Dimension(60, 15));
        RDNI.setMinimumSize(new java.awt.Dimension(60, 15));
        RDNI.setPreferredSize(new java.awt.Dimension(60, 22));
        registroResponsables.add(RDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 190, 90, 30));

        txt_dni_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_dni_r.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_dni_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dni_rActionPerformed(evt);
            }
        });
        txt_dni_r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dni_rKeyTyped(evt);
            }
        });
        registroResponsables.add(txt_dni_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 150, 190, 30));

        RTelefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RTelefono.setText("Teléfono:");
        RTelefono.setMaximumSize(new java.awt.Dimension(60, 15));
        RTelefono.setMinimumSize(new java.awt.Dimension(60, 15));
        RTelefono.setPreferredSize(new java.awt.Dimension(60, 22));
        registroResponsables.add(RTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 253, 80, 30));

        txt_telefono_area.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_telefono_area.setMaximumSize(new java.awt.Dimension(100, 25));
        txt_telefono_area.setMinimumSize(new java.awt.Dimension(100, 25));
        txt_telefono_area.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_telefono_area.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefono_areaActionPerformed(evt);
            }
        });
        txt_telefono_area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefono_areaKeyTyped(evt);
            }
        });
        registroResponsables.add(txt_telefono_area, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 254, 50, 30));

        txt_telefono_numero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_telefono_numero.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_telefono_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefono_numeroActionPerformed(evt);
            }
        });
        txt_telefono_numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefono_numeroKeyTyped(evt);
            }
        });
        registroResponsables.add(txt_telefono_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 254, 113, 30));

        btn_modificar.setBackground(new java.awt.Color(51, 51, 51));
        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/Edit_24x24.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(null);
        btn_modificar.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, -1, -1));

        btn_guardar.setBackground(new java.awt.Color(51, 51, 51));
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Save_24x24.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 600, -1, -1));

        btn_cancelar.setBackground(new java.awt.Color(51, 51, 51));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Cancel_24x24.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 600, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("0");
        jLabel4.setIconTextGap(0);
        jLabel4.setPreferredSize(new java.awt.Dimension(50, 30));
        registroResponsables.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 253, 12, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("15");
        jLabel5.setIconTextGap(0);
        jLabel5.setPreferredSize(new java.awt.Dimension(50, 30));
        registroResponsables.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 253, 21, -1));

        btn_agregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Add_24x24.png"))); // NOI18N
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 254, -1, 30));

        cboTipoTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTipoTelefono.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Móvil", "Fijo" }));
        cboTipoTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoTelefonoActionPerformed(evt);
            }
        });
        registroResponsables.add(cboTipoTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 253, 70, 30));

        cboTipoTelefono1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTipoTelefono1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Móvil", "Fijo" }));
        cboTipoTelefono1.setToolTipText("");
        cboTipoTelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoTelefono1ActionPerformed(evt);
            }
        });
        registroResponsables.add(cboTipoTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 295, 70, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("0");
        jLabel7.setIconTextGap(0);
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 30));
        registroResponsables.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 295, 12, -1));

        txt_responsable_telefono_area1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_responsable_telefono_area1.setMaximumSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_area1.setMinimumSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_area1.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_area1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_responsable_telefono_area1ActionPerformed(evt);
            }
        });
        txt_responsable_telefono_area1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_responsable_telefono_area1KeyTyped(evt);
            }
        });
        registroResponsables.add(txt_responsable_telefono_area1, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 295, 50, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("15");
        jLabel8.setIconTextGap(0);
        jLabel8.setPreferredSize(new java.awt.Dimension(50, 30));
        registroResponsables.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 295, 21, -1));

        txt_responsable_telefono_numero1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_responsable_telefono_numero1.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_numero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_responsable_telefono_numero1ActionPerformed(evt);
            }
        });
        txt_responsable_telefono_numero1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_responsable_telefono_numero1KeyTyped(evt);
            }
        });
        registroResponsables.add(txt_responsable_telefono_numero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 295, 113, 30));

        btn_agregar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_agregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Add_24x24.png"))); // NOI18N
        btn_agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar1ActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_agregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 295, -1, 30));

        txt_responsable_telefono_numero2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_responsable_telefono_numero2.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_numero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_responsable_telefono_numero2ActionPerformed(evt);
            }
        });
        txt_responsable_telefono_numero2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_responsable_telefono_numero2KeyTyped(evt);
            }
        });
        registroResponsables.add(txt_responsable_telefono_numero2, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 336, 113, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("15");
        jLabel9.setIconTextGap(0);
        jLabel9.setPreferredSize(new java.awt.Dimension(50, 30));
        registroResponsables.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 336, 21, -1));

        txt_responsable_telefono_area2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_responsable_telefono_area2.setMaximumSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_area2.setMinimumSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_area2.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_responsable_telefono_area2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_responsable_telefono_area2ActionPerformed(evt);
            }
        });
        txt_responsable_telefono_area2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_responsable_telefono_area2KeyTyped(evt);
            }
        });
        registroResponsables.add(txt_responsable_telefono_area2, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 336, 50, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("0");
        jLabel10.setIconTextGap(0);
        jLabel10.setPreferredSize(new java.awt.Dimension(50, 30));
        registroResponsables.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 336, 12, -1));

        cboTipoTelefono2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTipoTelefono2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Móvil", "Fijo" }));
        cboTipoTelefono2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoTelefono2ActionPerformed(evt);
            }
        });
        registroResponsables.add(cboTipoTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 336, 70, 30));

        RDNI1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RDNI1.setText("DNI:");
        RDNI1.setMaximumSize(new java.awt.Dimension(60, 15));
        RDNI1.setMinimumSize(new java.awt.Dimension(60, 15));
        RDNI1.setPreferredSize(new java.awt.Dimension(60, 22));
        registroResponsables.add(RDNI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 150, -1, 30));

        btn_quitar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Remove2_24x24.png"))); // NOI18N
        btn_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_quitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 295, -1, 30));

        btn_quitar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_quitar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Remove2_24x24.png"))); // NOI18N
        btn_quitar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitar1ActionPerformed(evt);
            }
        });
        registroResponsables.add(btn_quitar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 336, -1, 30));

        txt_a_cargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_a_cargo.setPreferredSize(new java.awt.Dimension(100, 25));
        txt_a_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_a_cargoActionPerformed(evt);
            }
        });
        txt_a_cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_a_cargoKeyTyped(evt);
            }
        });
        registroResponsables.add(txt_a_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 190, 190, 30));

        getContentPane().add(registroResponsables, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 670));

        listaResponsables.setBackground(new java.awt.Color(102, 255, 153));
        listaResponsables.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Responsables"));
        listaResponsables.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaListado.setAutoCreateRowSorter(true);
        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Alumno a Cargo", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane1.setViewportView(tablaListado);

        listaResponsables.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 137, 680, 450));

        lblTotalRegistros.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalRegistros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalRegistros.setText("Registros");
        lblTotalRegistros.setPreferredSize(new java.awt.Dimension(60, 22));
        listaResponsables.add(lblTotalRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 600, 70, 30));

        btn_salir.setBackground(new java.awt.Color(51, 51, 51));
        btn_salir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_salir.setForeground(new java.awt.Color(255, 255, 255));
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Log Out_24x24.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setMaximumSize(new java.awt.Dimension(143, 41));
        btn_salir.setMinimumSize(new java.awt.Dimension(143, 41));
        btn_salir.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        listaResponsables.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        txt_buscar_nombre.setMaximumSize(new java.awt.Dimension(6, 21));
        txt_buscar_nombre.setMinimumSize(new java.awt.Dimension(6, 21));
        txt_buscar_nombre.setPreferredSize(new java.awt.Dimension(6, 21));
        listaResponsables.add(txt_buscar_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 38, 110, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 22));
        listaResponsables.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 38, 70, 30));

        txt_buscar_apellido.setMaximumSize(new java.awt.Dimension(6, 21));
        txt_buscar_apellido.setMinimumSize(new java.awt.Dimension(6, 21));
        txt_buscar_apellido.setPreferredSize(new java.awt.Dimension(6, 21));
        txt_buscar_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscar_apellidoActionPerformed(evt);
            }
        });
        listaResponsables.add(txt_buscar_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 38, 110, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Apellido:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 22));
        listaResponsables.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 38, 60, 30));

        btn_buscar.setBackground(new java.awt.Color(51, 51, 51));
        btn_buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Search_24x24.png"))); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.setMaximumSize(new java.awt.Dimension(143, 41));
        btn_buscar.setMinimumSize(new java.awt.Dimension(143, 41));
        btn_buscar.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        listaResponsables.add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        btn_eliminar.setBackground(new java.awt.Color(51, 51, 51));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Remove_24x24.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        listaResponsables.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        btn_nuevo.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_nuevo.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Add_24x24.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setBorder(null);
        btn_nuevo.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        listaResponsables.add(btn_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        jTextCantRespon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCantResponActionPerformed(evt);
            }
        });
        listaResponsables.add(jTextCantRespon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 600, 40, 30));

        btn_refresh.setBackground(new java.awt.Color(51, 51, 51));
        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(255, 255, 255));
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/reload-icon_24x24.png"))); // NOI18N
        btn_refresh.setText("Actualizar");
        btn_refresh.setMaximumSize(new java.awt.Dimension(143, 41));
        btn_refresh.setMinimumSize(new java.awt.Dimension(143, 41));
        btn_refresh.setPreferredSize(new java.awt.Dimension(120, 30));
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        listaResponsables.add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        getContentPane().add(listaResponsables, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 730, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListadoMouseClicked
        btn_guardar.setText("Editar");
    }//GEN-LAST:event_tablaListadoMouseClicked

    private void cboTipoTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoTelefonoActionPerformed
        cboTipoTelefono.transferFocus();
        if (cboTipoTelefono.getSelectedItem() == "Fijo") {
            jLabel5.setVisible(false);
        } else {
            jLabel5.setVisible(true);
        }
    }//GEN-LAST:event_cboTipoTelefonoActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        txt_nombre.setText("");
        txt_buscar_nombre.setText("");
        txt_buscar_apellido.setText("");
        txt_apellido.setText("");
        txt_dni_r.setText("");
        txt_telefono_area.setText("");
        txt_telefono_numero.setText("");
        btn_buscar.setEnabled(true);
        btn_cancelar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_guardar.setEnabled(true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed

        if (txt_nombre.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Nombre para el Responsable");
            txt_nombre.requestFocus();
            return;
        }
        if (txt_apellido.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Apellido para el Responsable");
            txt_apellido.requestFocus();
            return;
        }
        if (txt_dni_r.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un DNI para el Responsable");
            txt_dni_r.requestFocus();
            return;
        }
        if ((txt_telefono_area.getText().length() == 0) || (txt_telefono_numero.getText().length() == 0)) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Telefono para el Responsable");
            txt_telefono_area.requestFocus();
            return;
        }
        Responsable respon = generarResponsable(txt_apellido.getText(), txt_nombre.getText(), Integer.valueOf(txt_dni_r.getText()));
        List<Telefono> listaTelefonos = generarTelefonos(respon);
        if(listaTelefonos.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Debe asignarle al menos un teléfono al Responsable");
        } else {
            Service.crearResponsable(respon);
            for (Telefono t : listaTelefonos) {
                Service.agregarTelefonoResponsable(t);
            }
            JOptionPane.showMessageDialog(rootPane, "El Responsable con DNI = " + txt_dni_r.getText() + " fue Agregado Exitosamente");
            cleanResponsableForm();
            mostrarListaResponsables();
        } 
    }//GEN-LAST:event_btn_guardarActionPerformed

    private Responsable generarResponsable(String apellido, String nombre, int dni){
        Responsable respon = new Responsable();
        respon.setDni(dni);
        respon.setApellido(apellido);
        respon.setNombre(nombre);
        return respon;
    }
    
    private List<Telefono> generarTelefonos(Responsable r){
        List<Telefono> listaTelefonos = new ArrayList<>();
        Numero n = null;
        Telefono t = null;
        if(txt_telefono_area.getText().length()>0 && txt_telefono_numero.getText().length() > 0) {
            t = new Telefono();
            n = new Numero();
            n.setCodigoArea(Integer.valueOf(txt_telefono_area.getText()));
            n.setNroTelefono(Integer.valueOf(txt_telefono_numero.getText()));
            t.setNumero(n);
            t.setTipo(cboTipoTelefono.getSelectedIndex());
            t.setResponsable(r);
            listaTelefonos.add(t);
        }
        if(txt_responsable_telefono_area1.getText().length()>0 && txt_responsable_telefono_numero1.getText().length() > 0) {
           t = new Telefono();
           n = new Numero();
           n.setCodigoArea(Integer.valueOf(txt_responsable_telefono_area1.getText()));
           n.setNroTelefono(Integer.valueOf(txt_responsable_telefono_numero1.getText()));
           t.setNumero(n);
           t.setTipo(cboTipoTelefono1.getSelectedIndex());
           t.setResponsable(r);
           listaTelefonos.add(t);
        }
        if(txt_responsable_telefono_area2.getText().length()>0 && txt_responsable_telefono_numero2.getText().length() > 0) {
            t = new Telefono();
            n = new Numero();
            n.setCodigoArea(Integer.valueOf(txt_responsable_telefono_area2.getText()));
            n.setNroTelefono(Integer.valueOf(txt_responsable_telefono_numero2.getText()));
            t.setNumero(n);
            t.setTipo(cboTipoTelefono2.getSelectedIndex());
            t.setResponsable(r);
            listaTelefonos.add(t);
        } 
        return listaTelefonos;
    }
    
    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        if (tablaListado.getSelectedRow() >= 0) {
            String dni = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
            Responsable respon = generarResponsable(txt_apellido.getText(), txt_nombre.getText(), Integer.valueOf(dni));
            List<Telefono> listaTelefonos = generarTelefonos(respon);
            
            if(listaTelefonos.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Debe asignarle al menos un teléfono al Responsable");
            } else {
                Service.actualizarResponsable(Integer.valueOf(dni), txt_apellido.getText(), txt_nombre.getText());
                Service.borrarTelefonoResponsable(dni);
                for (Telefono t : listaTelefonos) {
                    Service.agregarTelefonoResponsable(t);
                }
                cleanResponsableForm();
                mostrarListaResponsables();
                JOptionPane.showMessageDialog(rootPane, "El Responsable con DNI = " + dni + " fue modificado Exitosamente");
            }   
        }
        
       
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_telefono_numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefono_numeroKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || Character.isSpaceChar(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_telefono_numero.setCursor(null);
        }
    }//GEN-LAST:event_txt_telefono_numeroKeyTyped

    private void txt_telefono_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefono_numeroActionPerformed
        txt_telefono_numero.transferFocus();
    }//GEN-LAST:event_txt_telefono_numeroActionPerformed

    private void txt_telefono_areaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefono_areaKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || Character.isSpaceChar(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_telefono_area.setCursor(null);
        }
    }//GEN-LAST:event_txt_telefono_areaKeyTyped

    private void txt_telefono_areaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefono_areaActionPerformed
        txt_telefono_area.transferFocus();
    }//GEN-LAST:event_txt_telefono_areaActionPerformed

    private void txt_dni_rKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dni_rKeyTyped
        char validar = evt.getKeyChar();
        if ((validar < '0' || validar > '9') && (validar != (char) KeyEvent.VK_BACK_SPACE) && (validar != (char) KeyEvent.VK_ENTER) && (validar < '°' || validar > '°')) {
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_dni_r.setCursor(null);
        }
    }//GEN-LAST:event_txt_dni_rKeyTyped

    private void txt_dni_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dni_rActionPerformed
        txt_dni_r.transferFocus();
    }//GEN-LAST:event_txt_dni_rActionPerformed

    private void txt_apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidoKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Letras", "Advertencia", 2);
            txt_apellido.setCursor(null);
        }
    }//GEN-LAST:event_txt_apellidoKeyTyped

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        txt_apellido.transferFocus();
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Letras", "Advertencia", 2);
            txt_nombre.setCursor(null);
        }
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        txt_nombre.transferFocus();
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void cboTipoTelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoTelefono1ActionPerformed
        cboTipoTelefono1.transferFocus();
        if (cboTipoTelefono1.getSelectedItem() == "Fijo") {
            jLabel8.setVisible(false);
        } else {
            jLabel8.setVisible(true);
        }
    }//GEN-LAST:event_cboTipoTelefono1ActionPerformed

    private void txt_responsable_telefono_area1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_area1ActionPerformed
        txt_responsable_telefono_area1.transferFocus();
    }//GEN-LAST:event_txt_responsable_telefono_area1ActionPerformed

    private void txt_responsable_telefono_area1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_area1KeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || Character.isSpaceChar(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_responsable_telefono_area1.setCursor(null);
        }
    }//GEN-LAST:event_txt_responsable_telefono_area1KeyTyped

    private void txt_responsable_telefono_numero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_numero1ActionPerformed
        txt_responsable_telefono_numero1.transferFocus();
    }//GEN-LAST:event_txt_responsable_telefono_numero1ActionPerformed

    private void txt_responsable_telefono_numero1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_numero1KeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || Character.isSpaceChar(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_responsable_telefono_numero1.setCursor(null);
        }
    }//GEN-LAST:event_txt_responsable_telefono_numero1KeyTyped

    private void txt_responsable_telefono_numero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_numero2ActionPerformed
        txt_responsable_telefono_numero2.transferFocus();
    }//GEN-LAST:event_txt_responsable_telefono_numero2ActionPerformed

    private void txt_responsable_telefono_numero2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_numero2KeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || Character.isSpaceChar(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_responsable_telefono_numero2.setCursor(null);
        }
    }//GEN-LAST:event_txt_responsable_telefono_numero2KeyTyped

    private void txt_responsable_telefono_area2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_area2ActionPerformed
        txt_responsable_telefono_area2.transferFocus();
    }//GEN-LAST:event_txt_responsable_telefono_area2ActionPerformed

    private void txt_responsable_telefono_area2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_responsable_telefono_area2KeyTyped
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || Character.isSpaceChar(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Debes Ingresar Sólo Números", "Advertencia", 2);
            txt_responsable_telefono_area2.setCursor(null);
        }
    }//GEN-LAST:event_txt_responsable_telefono_area2KeyTyped

    private void cboTipoTelefono2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoTelefono2ActionPerformed
        cboTipoTelefono2.transferFocus();
        if (cboTipoTelefono2.getSelectedItem() == "Fijo") {
            jLabel9.setVisible(false);
        } else {
            jLabel9.setVisible(true);
        }
    }//GEN-LAST:event_cboTipoTelefono2ActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente deseas salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (opcion == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        mostrarResponsableBuscado();
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void txt_buscar_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscar_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_apellidoActionPerformed

    private void txt_a_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_a_cargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_a_cargoActionPerformed

    private void txt_a_cargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_a_cargoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_a_cargoKeyTyped

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        habilitar();
        txt_a_cargo.setEnabled(false);
        btn_agregar.setEnabled(true);
        cleanResponsableForm();
        btn_cancelar.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_guardar.setText("Guardar");  
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void jTextCantResponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCantResponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCantResponActionPerformed

    private void btn_quitar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitar1ActionPerformed
        btn_quitar1.transferFocus();
        btn_agregar1.setVisible(true);
        btn_agregar1.setEnabled(true);
        btn_quitar.setVisible(true);
        btn_quitar.setEnabled(true);
        btn_quitar1.setVisible(false);
        cboTipoTelefono2.setVisible(false);
        jLabel9.setVisible(false);
        txt_responsable_telefono_area2.setVisible(false);
        jLabel10.setVisible(false);
        txt_responsable_telefono_numero2.setVisible(false);
    }//GEN-LAST:event_btn_quitar1ActionPerformed

    private void btn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarActionPerformed
        btn_quitar.transferFocus();
        btn_agregar.setVisible(true);
        btn_agregar.setEnabled(true);
        btn_agregar1.setVisible(false);
        btn_quitar.setVisible(false);
        btn_quitar1.setVisible(false);
        cboTipoTelefono1.setVisible(false);
        jLabel7.setVisible(false);
        txt_responsable_telefono_area1.setVisible(false);
        txt_responsable_telefono_area1.setText("");
        jLabel8.setVisible(false);
        txt_responsable_telefono_numero1.setVisible(false);
        txt_responsable_telefono_numero1.setText("");
    }//GEN-LAST:event_btn_quitarActionPerformed

    private void btn_agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar1ActionPerformed
        btn_agregar1.transferFocus();
        btn_agregar.setVisible(false);
        btn_agregar1.setVisible(false);
        btn_quitar.setVisible(true);
        btn_quitar1.setVisible(true);
        cboTipoTelefono2.setVisible(true);
        cboTipoTelefono2.setSelectedIndex(0);
        jLabel9.setVisible(true);
        txt_responsable_telefono_area2.setVisible(true);
        jLabel10.setVisible(true);
        txt_responsable_telefono_numero2.setVisible(true);
        btn_quitar.setEnabled(false);
        btn_agregar1.setEnabled(false);
    }//GEN-LAST:event_btn_agregar1ActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        btn_agregar.transferFocus();
        btn_agregar.setVisible(false);
        btn_agregar1.setVisible(true);
        btn_agregar1.setEnabled(true);
        btn_quitar.setEnabled(true);
        btn_quitar.setVisible(true);
        btn_quitar1.setVisible(false);
        cboTipoTelefono1.setVisible(true);
        cboTipoTelefono1.setSelectedIndex(0);
        jLabel7.setVisible(true);
        txt_responsable_telefono_area1.setVisible(true);
        txt_responsable_telefono_area1.setText("");
        txt_responsable_telefono_area2.setText("");
        jLabel8.setVisible(true);
        txt_responsable_telefono_numero1.setVisible(true);
        txt_responsable_telefono_numero1.setText("");
        txt_responsable_telefono_numero2.setText("");
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        if (tablaListado.getSelectedRow() >= 0) {
            String dni = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
            String alumnosCargo = tablaListado.getValueAt(tablaListado.getSelectedRow(), 3).toString();
            if (!"".equals(alumnosCargo)){
                JOptionPane.showMessageDialog(rootPane, "Para eliminar el Responsable con DNI = " + dni + " NO debe tener Alumnos a cargo");
            } else {
                Service.bajaResponsable(Integer.valueOf(dni));
                cleanResponsableForm();
                mostrarListaResponsables();
                JOptionPane.showMessageDialog(rootPane, "El Responsable con DNI = " + dni + " fue eliminado Exitosamente");
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        mostrarListaResponsables();
        cleanResponsableForm();
    }//GEN-LAST:event_btn_refreshActionPerformed
// metodo para crear solo una instancia del formulario al hacer click en el menu
    private static FormResponsable responsable;

    public static FormResponsable getInstance() {
        if (responsable == null) {
            responsable = new FormResponsable();
        }
        return responsable;
    }

    public void inhabilitar() {
        txt_apellido.setEnabled(false);
        txt_dni_r.setEnabled(false);
        txt_dni_r.setEditable(false);
        txt_nombre.setEnabled(false);
        txt_telefono_area.setEnabled(false);
        txt_telefono_numero.setEnabled(false);
        cboTipoTelefono.setEnabled(false);
        btn_agregar.setVisible(false);
        btn_agregar.setEnabled(false);
        btn_cancelar.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_guardar.setEnabled(false);
        cboTipoTelefono1.setVisible(false);
        jLabel7.setVisible(false);
        txt_responsable_telefono_area1.setVisible(false);
        jLabel8.setVisible(false);
        txt_responsable_telefono_numero1.setVisible(false);
        btn_agregar1.setVisible(false);
        cboTipoTelefono2.setVisible(false);
        jLabel9.setVisible(false);
        txt_responsable_telefono_area2.setVisible(false);
        jLabel10.setVisible(false);
        txt_responsable_telefono_numero2.setVisible(false);
        txt_a_cargo.setEnabled(false);
        btn_quitar.setVisible(false);
        btn_quitar1.setVisible(false);       
        
    }
    
    public void habilitarEdicion() {
        txt_apellido.setEnabled(true);
        txt_dni_r.setEnabled(false);
        txt_nombre.setEnabled(true);
        txt_a_cargo.setEnabled(false);
        txt_telefono_area.setEnabled(true);
        txt_telefono_numero.setEnabled(true);
        cboTipoTelefono.setEnabled(true);
        btn_agregar.setEnabled(true);
        btn_cancelar.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_modificar.setEnabled(true);

    }

    public void habilitar() {
        txt_buscar_nombre.setEnabled(true);
        txt_buscar_apellido.setEnabled(true);
        txt_apellido.setEnabled(true);
        txt_dni_r.setEnabled(true);
        txt_dni_r.setEditable(true);
        txt_nombre.setEnabled(true);
        txt_telefono_area.setEnabled(true);
        txt_telefono_numero.setEnabled(true);
        cboTipoTelefono.setEnabled(true);
        btn_buscar.setEnabled(true);
        tablaListado.setEnabled(true);
        btn_cancelar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_guardar.setEnabled(true);
        txt_a_cargo.setEnabled(true);
        btn_modificar.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RApellido;
    private javax.swing.JLabel RDNI;
    private javax.swing.JLabel RDNI1;
    private javax.swing.JLabel RNombre;
    private javax.swing.JLabel RTelefono;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregar1;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JButton btn_quitar1;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cboTipoTelefono;
    private javax.swing.JComboBox<String> cboTipoTelefono1;
    private javax.swing.JComboBox<String> cboTipoTelefono2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextCantRespon;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JPanel listaResponsables;
    private javax.swing.JPanel registroResponsables;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txt_a_cargo;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_buscar_apellido;
    private javax.swing.JTextField txt_buscar_nombre;
    private javax.swing.JTextField txt_dni_r;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_responsable_telefono_area1;
    private javax.swing.JTextField txt_responsable_telefono_area2;
    private javax.swing.JTextField txt_responsable_telefono_numero1;
    private javax.swing.JTextField txt_responsable_telefono_numero2;
    private javax.swing.JTextField txt_telefono_area;
    private javax.swing.JTextField txt_telefono_numero;
    // End of variables declaration//GEN-END:variables

    private void cleanResponsableForm() {
        txt_dni_r.setText("");
        txt_apellido.setText("");
        txt_buscar_apellido.setText("");
        txt_nombre.setText("");
        txt_dni_r.setEditable(true);
        txt_telefono_area.setText("");
        txt_telefono_numero.setText("");
        cboTipoTelefono1.setVisible(false);
        txt_responsable_telefono_area1.setVisible(false);
        txt_responsable_telefono_numero1.setVisible(false);
        cboTipoTelefono2.setVisible(false);
        txt_responsable_telefono_area2.setVisible(false);
        txt_responsable_telefono_numero2.setVisible(false);
        txt_a_cargo.setText("");
        btn_buscar.setEnabled(true);
        btn_cancelar.setEnabled(false);
        btn_eliminar.setEnabled(true);
        btn_modificar.setEnabled(false);
        btn_guardar.setEnabled(false);
        
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        btn_quitar.setVisible(false);
        btn_quitar1.setVisible(false);
        btn_agregar1.setVisible(false);
    }
}
