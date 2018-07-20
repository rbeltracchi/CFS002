/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mil.sda111mil.forms;

import com.mil.sda111mil.ComparatorAlumno;
import com.mil.sda111mil.beans.Alumno;
import com.mil.sda111mil.beans.Asistencia;
import com.mil.sda111mil.beans.Asistencia.ClaveAsistencia;
import com.mil.sda111mil.beans.Curso;
import com.mil.sda111mil.beans.Preceptor;
import com.mil.sda111mil.services.Service;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CF02
 */
public class FormAsistencia extends javax.swing.JInternalFrame {

    Service s;
    Preceptor preceptor;

    /**
     * Creates new form FormAsistencia
     *
     * @param p
     */
    public FormAsistencia(Preceptor p) {

        this.preceptor = p;
        initComponents();
        cboAlumnosAsistencia1.setVisible(false);
        cargarData();
        alertarAsistencias();
        cargarCursos();

        fechaAsistencia.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    cargarCursos();
                }
            }
        });

        tablaListado.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {

                // do some actions here, for example
                // print first column value from selected row
                if (tablaListado.getSelectedRow() >= 0) {
                    String dni = tablaListado.getValueAt(tablaListado.getSelectedRow(), 2).toString();
                    String apellido = tablaListado.getValueAt(tablaListado.getSelectedRow(), 0).toString();
                    String nombre = tablaListado.getValueAt(tablaListado.getSelectedRow(), 1).toString();

                    Alumno elegido = Service.listarPorDni(dni);

                    jList1.removeAll();
                    jLabel3.setText(apellido + ", " + nombre);
                    DefaultListModel<String> model = new DefaultListModel<>();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                    for (Asistencia a : elegido.getAsistencia()) {
                        if (a.getAsistencias() > 0) {
                            String fecha = sdf.format(a.getClaveAsistencia().getFecha());
                            double asist = (double) a.getAsistencias() / 2;
                            model.addElement(fecha + ": " + asist + " falta.");
                        }
                    }
                    jList1.setModel(model);
                }
            }
        });

    }

    public void mostrarListaAsistencias() {

        NoEditableModel modelo;

        String[] titulos = {"Apellido", "Nombre", "DNI", "Inasistencias"};

        modelo = new NoEditableModel(null, titulos);

        Service.actualizarPreceptor(preceptor);
        int indice = cboCurso1.getSelectedIndex();
        if (indice < 0) {
            indice = 0;
        }
        Curso c = preceptor.getCursos().get(indice);

        c.getAlumnos().sort(new ComparatorAlumno());

        for (Alumno a : c.getAlumnos()) {
            Object[] o = new Object[4];

            o[0] = a.getApellido();
            o[1] = a.getNombre();
            o[2] = a.getDni();
            o[3] = a.getAsistenciasTotales(fechaDesde.getDate(), fechaHasta.getDate());

            modelo.addRow(o);
        }
        tablaListado.setModel(modelo);

    }

    public void alertarAsistencias() {

        DefaultTableModel modelo;
        DefaultTableModel modeloUno;
        DefaultTableModel modeloDos;

        String[] titulos = {"Apellido", "Nombre", "Curso", "Responsable", "Tel", "Inasistencias"};

        modelo = new DefaultTableModel(null, titulos);
        modeloUno = new DefaultTableModel(null, titulos);
        modeloDos = new DefaultTableModel(null, titulos);

        List<Alumno> alumnosCasiLibres = Service.getAlumnosLibres(preceptor, false);
        List<Alumno> alumnosLibres = Service.getAlumnosLibres(preceptor, true);
        List<Alumno> alumnosRecurrentes = Service.getAlumnosRecurrentes(3, this.preceptor);

        for (Alumno a : alumnosCasiLibres) {
            Object[] o = new Object[6];

            o[0] = a.getApellido();
            o[1] = a.getNombre();
            o[2] = a.getCurso();
            o[3] = a.getResponsable().getApellido() + ", " + a.getResponsable().getNombre();
            o[4] = a.getResponsable().getTelefonos().get(0).getNumero();
            o[5] = a.getAsistenciasTotales(fechaDesde.getDate(), fechaHasta.getDate());

            modelo.addRow(o);
        }

        for (Alumno a : alumnosLibres) {
            Object[] o = new Object[6];

            o[0] = a.getApellido();
            o[1] = a.getNombre();
            o[2] = a.getCurso();
            o[3] = a.getResponsable().getApellido() + ", " + a.getResponsable().getNombre();
            o[4] = a.getResponsable().getTelefonos().get(0).getNumero();
            o[5] = a.getAsistenciasTotales(fechaDesde.getDate(), fechaHasta.getDate());

            modeloUno.addRow(o);
        }

        for (Alumno a : alumnosRecurrentes) {
            Object[] o = new Object[6];

            o[0] = a.getApellido();
            o[1] = a.getNombre();
            o[2] = a.getCurso();
            o[3] = a.getResponsable().getApellido() + ", " + a.getResponsable().getNombre();
            o[4] = a.getResponsable().getTelefonos().get(0).getNumero();
            o[5] = a.getAsistenciasTotales(fechaDesde.getDate(), fechaHasta.getDate());

            modeloDos.addRow(o);
        }
        
        jTable1.setModel(modeloUno);
        jTable3.setModel(modelo);
        jTable4.setModel(modeloDos);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tomarAsistencias = new javax.swing.JPanel();
        Alumno = new javax.swing.JLabel();
        Curso = new javax.swing.JLabel();
        btnTomarAsistencia = new javax.swing.JButton();
        fechaAsistencia = new com.toedter.calendar.JDateChooser();
        btnEditarAsistencia = new javax.swing.JButton();
        cboTurno = new javax.swing.JComboBox<>();
        Curso2 = new javax.swing.JLabel();
        cboCurso = new javax.swing.JComboBox<>();
        cboAlumnosAsistencia = new javax.swing.JComboBox<>();
        Curso3 = new javax.swing.JLabel();
        cboAlumnosAsistencia1 = new javax.swing.JComboBox<>();
        listaAsistencias = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        fechaDesde = new com.toedter.calendar.JDateChooser();
        cboCurso1 = new javax.swing.JComboBox<>();
        Curso1 = new javax.swing.JLabel();
        FechaDesde = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        fechaHasta = new com.toedter.calendar.JDateChooser();
        Fechahasta = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        alertarAsistencias = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        btn_salir = new javax.swing.JButton();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tomarAsistencias.setBackground(new java.awt.Color(204, 255, 255));
        tomarAsistencias.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Asistencias"));
        tomarAsistencias.setForeground(new java.awt.Color(250, 250, 250));
        tomarAsistencias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Alumno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Alumno.setText("Fecha:");
        Alumno.setMaximumSize(new java.awt.Dimension(60, 15));
        Alumno.setMinimumSize(new java.awt.Dimension(60, 15));
        Alumno.setPreferredSize(new java.awt.Dimension(60, 22));
        tomarAsistencias.add(Alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 30));

        Curso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Curso.setText("Curso:");
        Curso.setMaximumSize(new java.awt.Dimension(60, 15));
        Curso.setMinimumSize(new java.awt.Dimension(60, 15));
        Curso.setPreferredSize(new java.awt.Dimension(60, 22));
        tomarAsistencias.add(Curso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 70, 30));

        btnTomarAsistencia.setBackground(new java.awt.Color(51, 51, 51));
        btnTomarAsistencia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTomarAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        btnTomarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/if_Check_24x24.png"))); // NOI18N
        btnTomarAsistencia.setText("Tomar Asistencia");
        btnTomarAsistencia.setBorder(null);
        btnTomarAsistencia.setPreferredSize(new java.awt.Dimension(120, 30));
        btnTomarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTomarAsistenciaActionPerformed(evt);
            }
        });
        tomarAsistencias.add(btnTomarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 170, -1));

        fechaAsistencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fechaAsistencia.setMaxSelectableDate(new Date());
        fechaAsistencia.setPreferredSize(new java.awt.Dimension(170, 30));
        fechaAsistencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaAsistenciaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fechaAsistenciaMouseReleased(evt);
            }
        });
        tomarAsistencias.add(fechaAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 170, 30));

        btnEditarAsistencia.setBackground(new java.awt.Color(51, 51, 51));
        btnEditarAsistencia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditarAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/24x24/Edit_24x24.png"))); // NOI18N
        btnEditarAsistencia.setText("Editar Asistencia");
        btnEditarAsistencia.setBorder(null);
        btnEditarAsistencia.setPreferredSize(new java.awt.Dimension(120, 30));
        btnEditarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAsistenciaActionPerformed(evt);
            }
        });
        tomarAsistencias.add(btnEditarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 170, -1));

        cboTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mañana", "Tarde" }));
        cboTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTurnoActionPerformed(evt);
            }
        });
        tomarAsistencias.add(cboTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 170, 30));

        Curso2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Curso2.setText("Turno:");
        Curso2.setMaximumSize(new java.awt.Dimension(60, 15));
        Curso2.setMinimumSize(new java.awt.Dimension(60, 15));
        Curso2.setPreferredSize(new java.awt.Dimension(60, 22));
        tomarAsistencias.add(Curso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 70, 30));

        cboCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCursoActionPerformed(evt);
            }
        });
        tomarAsistencias.add(cboCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 170, 30));

        cboAlumnosAsistencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboAlumnosAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAlumnosAsistenciaActionPerformed(evt);
            }
        });
        tomarAsistencias.add(cboAlumnosAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 170, 30));

        Curso3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Curso3.setText("Alumnos:");
        Curso3.setMaximumSize(new java.awt.Dimension(60, 15));
        Curso3.setMinimumSize(new java.awt.Dimension(60, 15));
        Curso3.setPreferredSize(new java.awt.Dimension(60, 22));
        tomarAsistencias.add(Curso3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 70, 30));

        cboAlumnosAsistencia1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboAlumnosAsistencia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAlumnosAsistencia1ActionPerformed(evt);
            }
        });
        tomarAsistencias.add(cboAlumnosAsistencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 170, 30));

        getContentPane().add(tomarAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 210));

        listaAsistencias.setBackground(new java.awt.Color(0, 204, 204));
        listaAsistencias.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Asistencias"));
        listaAsistencias.setAutoscrolls(true);
        listaAsistencias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Listar Por Fecha:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 22));
        listaAsistencias.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 20));

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
        listaAsistencias.add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jScrollPane2.setAutoscrolls(true);

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Apellido", "Nombre", "DNI", "Inasistencias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaListado.setToolTipText("");
        tablaListado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tablaListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaListadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaListado);

        listaAsistencias.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 620, 310));

        fechaDesde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fechaDesde.setPreferredSize(new java.awt.Dimension(170, 30));
        listaAsistencias.add(fechaDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 170, 30));

        cboCurso1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboCurso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCurso1ActionPerformed(evt);
            }
        });
        listaAsistencias.add(cboCurso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 170, 30));

        Curso1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Curso1.setText("Curso:");
        Curso1.setMaximumSize(new java.awt.Dimension(60, 15));
        Curso1.setMinimumSize(new java.awt.Dimension(60, 15));
        Curso1.setPreferredSize(new java.awt.Dimension(60, 22));
        listaAsistencias.add(Curso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, 30));

        FechaDesde.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FechaDesde.setText("Fecha Desde:");
        FechaDesde.setMaximumSize(new java.awt.Dimension(60, 15));
        FechaDesde.setMinimumSize(new java.awt.Dimension(60, 15));
        FechaDesde.setPreferredSize(new java.awt.Dimension(60, 22));
        listaAsistencias.add(FechaDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 30));
        listaAsistencias.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 690, 10));

        fechaHasta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fechaHasta.setPreferredSize(new java.awt.Dimension(170, 30));
        listaAsistencias.add(fechaHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 170, 30));

        Fechahasta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fechahasta.setText("Fecha Hasta:");
        Fechahasta.setMaximumSize(new java.awt.Dimension(60, 15));
        Fechahasta.setMinimumSize(new java.awt.Dimension(60, 15));
        Fechahasta.setPreferredSize(new java.awt.Dimension(60, 22));
        listaAsistencias.add(Fechahasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 100, 30));
        listaAsistencias.add(jScrollBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jList1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sin información" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        listaAsistencias.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 240, 110));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Alumno:");
        listaAsistencias.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 70, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        listaAsistencias.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 170, 20));

        getContentPane().add(listaAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 690, 710));

        alertarAsistencias.setBackground(new java.awt.Color(204, 255, 255));
        alertarAsistencias.setBorder(javax.swing.BorderFactory.createTitledBorder("Alertas de Asistencias"));
        alertarAsistencias.setForeground(new java.awt.Color(250, 250, 250));
        alertarAsistencias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(90);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(1).setMinWidth(90);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(2).setMinWidth(95);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(95);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(95);
            jTable1.getColumnModel().getColumn(3).setMinWidth(140);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(140);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        jTabbedPane1.addTab("LIBRES", jScrollPane1);

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane4.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setMinWidth(90);
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTable3.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable3.getColumnModel().getColumn(1).setMinWidth(90);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTable3.getColumnModel().getColumn(1).setMaxWidth(90);
            jTable3.getColumnModel().getColumn(2).setMinWidth(95);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(95);
            jTable3.getColumnModel().getColumn(2).setMaxWidth(95);
            jTable3.getColumnModel().getColumn(3).setMinWidth(140);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(140);
            jTable3.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        jTabbedPane1.addTab("EN PELIGRO", jScrollPane4);

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane5.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setMinWidth(90);
            jTable4.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTable4.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable4.getColumnModel().getColumn(1).setMinWidth(90);
            jTable4.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTable4.getColumnModel().getColumn(1).setMaxWidth(90);
            jTable4.getColumnModel().getColumn(2).setMinWidth(95);
            jTable4.getColumnModel().getColumn(2).setPreferredWidth(95);
            jTable4.getColumnModel().getColumn(2).setMaxWidth(95);
            jTable4.getColumnModel().getColumn(3).setMinWidth(140);
            jTable4.getColumnModel().getColumn(3).setPreferredWidth(140);
            jTable4.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        jTabbedPane1.addTab("RECURRENTES", jScrollPane5);

        alertarAsistencias.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 600, 410));
        jTabbedPane1.getAccessibleContext().setAccessibleName("LIBRES");

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
        alertarAsistencias.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, -1));

        getContentPane().add(alertarAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 630, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTomarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTomarAsistenciaActionPerformed

        String cursoItem = (String) cboCurso.getSelectedItem();
        String[] cursoArray = cursoItem.split("º");

        Curso seleccionado = preceptor.getCursos().get(0);
        for (Curso c : preceptor.getCursos()) {
            if (c.getCurso() == Integer.parseInt(cursoArray[0]) && c.getDivision().equals(cursoArray[1].trim())) {
                seleccionado = c;
            }
        }

        Date fecha = fechaAsistencia.getDate();
        int turno = cboTurno.getSelectedIndex();
        Object[] opciones = {"Presente", "Tarde", "Ausente"};
        Object[] opcionesTarde = {"Presente", "Ausente"};

        seleccionado.getAlumnos().sort(new ComparatorAlumno());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(fecha);

        for (Alumno a : seleccionado.getAlumnos()) {

            if (a != null) {

                String texto = "Alumno: " + a.getApellido() + " " + a.getNombre();

                ClaveAsistencia asist = new ClaveAsistencia();
                asist.setAlumno(a);
                asist.setFecha(fecha);
                asist.setTurno(turno);

                Asistencia as = new Asistencia();

                if (existeAsistencia(asist)) {
                    int asistencia = JOptionPane.showOptionDialog(this, "La asistencia del día " + date + " del alumno " + a.getApellido() + ", " + a.getNombre() + "\n Ha sido cargada previamente", "Asistencia del día: " + date, JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                    if (asistencia == -1) {
                        break;
                    }
                } else {
                    ImageIcon imageIcon;
                    try {
                        imageIcon = new ImageIcon(getClass().getResource("/fotos/" + a.getDni() + ".jpg"));
                    } catch (Exception e) {
                        imageIcon = new ImageIcon(getClass().getResource("/fotos/no_foto.jpg"));
                    }
                    Image image = imageIcon.getImage(); // transform it 
                    Image newimg = image.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    imageIcon = new ImageIcon(newimg);

                    int asistencia;
                    if (turno == 0) {
                        asistencia = JOptionPane.showOptionDialog(this, texto, "Asistencia del dia: " + date, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon, opciones, opciones[0]);
                    } else {
                        asistencia = JOptionPane.showOptionDialog(this, texto, "Asistencia del dia: " + date, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon, opcionesTarde, opcionesTarde[0]);

                    }

                    if (asistencia < 0) {
                        break;
                    }

                    as.setAsistencias(asistencia);
                    as.setClaveAsistencia(asist);

                    Service.guardarAsistencia(as);
                    cargarAlumnosAsistencias();

                }

            }
        }


    }//GEN-LAST:event_btnTomarAsistenciaActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente deseas salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (opcion == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        this.mostrarListaAsistencias();
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void tablaListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListadoMouseClicked

    }//GEN-LAST:event_tablaListadoMouseClicked

    private void cboCurso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCurso1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCurso1ActionPerformed

    private void btnEditarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAsistenciaActionPerformed

        int indice = cboCurso.getSelectedIndex();
        Curso c = preceptor.getCursos().get(indice);
        Date fecha = fechaAsistencia.getDate();
        int turno = cboTurno.getSelectedIndex();
        Object[] opciones = {"Presente", "Tarde", "Ausente"};
        Object[] opcionesTarde = {"Presente", "Ausente"};

        c.getAlumnos().sort(new ComparatorAlumno());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(fecha);

        String dni = cboAlumnosAsistencia1.getItemAt(cboAlumnosAsistencia.getSelectedIndex());
        String texto = "Actualizar asistencia del alumno: " + cboAlumnosAsistencia.getSelectedItem();


        int asistenciaNueva;

        if (turno == 0) {
            asistenciaNueva = JOptionPane.showOptionDialog(this, texto, " Asistencia del dia: " + date, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        } else {
            asistenciaNueva = JOptionPane.showOptionDialog(this, texto, " Asistencia del dia: " + date, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesTarde, opcionesTarde[0]);
            asistenciaNueva *= 2;
        }
        if (asistenciaNueva >= 0) {
            Service.modificarAsistencia(Integer.parseInt(dni), fecha, turno, asistenciaNueva);
        }

    }//GEN-LAST:event_btnEditarAsistenciaActionPerformed

    private void cboTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTurnoActionPerformed
        cargarCursos();
    }//GEN-LAST:event_cboTurnoActionPerformed

    private void fechaAsistenciaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaAsistenciaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaAsistenciaMouseReleased

    private void fechaAsistenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaAsistenciaMouseClicked
        cargarCursos();

    }//GEN-LAST:event_fechaAsistenciaMouseClicked

    private void cargarAlumnosAsistencias() {
        cboAlumnosAsistencia.removeAllItems();
        cboAlumnosAsistencia1.removeAllItems();
        int indice = cboCurso.getSelectedIndex();

        if (indice >= 0) {
            String cursoItem = (String) cboCurso.getSelectedItem();
            String[] cursoArray = cursoItem.split("º");

            Curso seleccionado = preceptor.getCursos().get(0);
            for (Curso c : preceptor.getCursos()) {
                if (c.getCurso() == Integer.parseInt(cursoArray[0]) && c.getDivision().equals(cursoArray[1].trim())) {
                    seleccionado = c;
                }
            }

            Date fecha = fechaAsistencia.getDate();
            int turno = cboTurno.getSelectedIndex();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(fecha);

            List<Asistencia> asistencias = Service.obtenerAlumnosAsistencia(turno, fecha);

            for (Asistencia a : asistencias) {
                if (a != null && a.getClaveAsistencia().getAlumno().getCurso() == seleccionado) {
                    cboAlumnosAsistencia.addItem(a.getClaveAsistencia().getAlumno().getApellido() + ", " + a.getClaveAsistencia().getAlumno().getNombre());
                    cboAlumnosAsistencia1.addItem(String.valueOf(a.getClaveAsistencia().getAlumno().getDni()));
                }
            }
        }
        if (cboAlumnosAsistencia.getItemCount() > 0) {
            btnEditarAsistencia.setEnabled(true);
        } else {
            btnEditarAsistencia.setEnabled(false);
        }
    }

    /*
    private void cboCursoActionPerformed(java.awt.event.ActionEvent evt) {                                         

        cargarAlumnosAsistencias();


    }                                        
    private void cboCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCursoActionPerformed
cargarAlumnosAsistencias();
    }//GEN-LAST:event_cboCursoActionPerformed
*/
    private void cboCursoActionPerformed(java.awt.event.ActionEvent evt) {
        cargarAlumnosAsistencias();
    }

    private void cboAlumnosAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAlumnosAsistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAlumnosAsistenciaActionPerformed

    private void cboAlumnosAsistencia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAlumnosAsistencia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAlumnosAsistencia1ActionPerformed

    private void cargarCursos() {
        List<Curso> listaCursos = preceptor.mostrarCursosPorTurno(fechaAsistencia.getDate(), cboTurno.getSelectedIndex());
        cboCurso.removeAllItems();
        for (Curso c : listaCursos) {
            if (c != null) {
                cboCurso.addItem(c.toString());
            }
        }
        cargarAlumnosAsistencias();
        if (cboCurso.getItemCount() > 0) {
            btnTomarAsistencia.setEnabled(true);
        } else {
            btnTomarAsistencia.setEnabled(false);

        }

    }
    private static FormAsistencia asistencia;
    private String accion = "guardar";

    /**
     * El método da la fecha en un formato dado.
     */
    public static String fecha2() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }

    public static FormAsistencia getInstance(Preceptor p) {
        if (asistencia == null) {
            asistencia = new FormAsistencia(p);
        }
        return asistencia;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Alumno;
    private javax.swing.JLabel Curso;
    private javax.swing.JLabel Curso1;
    private javax.swing.JLabel Curso2;
    private javax.swing.JLabel Curso3;
    private javax.swing.JLabel FechaDesde;
    private javax.swing.JLabel Fechahasta;
    private javax.swing.JPanel alertarAsistencias;
    private javax.swing.JButton btnEditarAsistencia;
    private javax.swing.JButton btnTomarAsistencia;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cboAlumnosAsistencia;
    private javax.swing.JComboBox<String> cboAlumnosAsistencia1;
    private javax.swing.JComboBox<String> cboCurso;
    private javax.swing.JComboBox<String> cboCurso1;
    private javax.swing.JComboBox<String> cboTurno;
    private com.toedter.calendar.JDateChooser fechaAsistencia;
    private com.toedter.calendar.JDateChooser fechaDesde;
    private com.toedter.calendar.JDateChooser fechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JPanel listaAsistencias;
    private javax.swing.JTable tablaListado;
    private javax.swing.JPanel tomarAsistencias;
    // End of variables declaration//GEN-END:variables

    private void cargarData() {

        for (Curso c : preceptor.getCursos()) {
            if (c != null) {
                //cboCurso.addItem(c.toString());
                cboCurso1.addItem(c.toString());
            }
        }
        Date inicio = Service.getFechaInicio();

        Date date = new Date();
        fechaAsistencia.setDate(date);
        fechaHasta.setDate(date);
        fechaDesde.setDate(inicio);

    }

    private boolean existeAsistencia(ClaveAsistencia asist) {
        Asistencia as = Service.validarAsistencia(asist);
        if (as == null) {
            return false;
        } else {
            return true;
        }
    }
}
