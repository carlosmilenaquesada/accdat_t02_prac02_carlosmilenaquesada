package vista;

import com.toedter.calendar.JTextFieldDateEditor;
import controlador.Conexion;
import controlador.Consultas;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Curso;
import modelo.Examen;
import modelo.VistaMatricula;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import static vista.Herramientas.obtenerFileParaGuardar;

public class PrincipalJFrame extends javax.swing.JFrame {

    Consultas consulta;
    DefaultTableModel dtmAlumno;
    DefaultTableModel dtmCursos;
    DefaultTableModel dtmMatriculas;
    DefaultTableModel dtmExamenes;
    ArrayList<Alumno> listaAlumnos;
    ArrayList<Curso> listaCursos;

    public PrincipalJFrame() {

        if (Conexion.getConexion() == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            System.exit(0);
        }
        initComponents();
        initConfiguracion();

    }

    private void initConfiguracion() {
        //Inicialización de mis componentes
        consulta = new Consultas();
        dtmAlumno = (DefaultTableModel) jTableAlumnos.getModel();
        dtmCursos = (DefaultTableModel) jTableTablaCursos.getModel();
        dtmMatriculas = (DefaultTableModel) jTableMatriculas.getModel();
        dtmExamenes = (DefaultTableModel) jTableExamenes.getModel();
        jTableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableTablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableMatriculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaAlumnos = consulta.obtenerAlumnos();
        rellenarTablaDeAlumnos();
        listaCursos = consulta.obteneCursos();
        rellenarTablaDeCursos();

        //Creación y configuración de listener para las tablas
        JTextFieldDateEditor e = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        e.setEditable(false);
        jDateChooser1.setDate(new Date());
        jTableAlumnos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String alumno = ((String) jTableAlumnos.getValueAt(jTableAlumnos.getSelectedRow(), 0));
                    refrescarTablaDeMatriculas(consulta.obtenerVistaMatriculas(alumno));
                }
            }
        });

        jTableMatriculas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String alumno = ((String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 0));
                    String curso = ((String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 2));
                    refrescarTablaDeExamenes(consulta.obtenerExamenes(alumno, curso));
                }
            }
        });

        jTableExamenes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String fecha = ((String) jTableExamenes.getValueAt(jTableExamenes.getSelectedRow(), 1));
                    try {
                        if (fecha != null) {
                            jDateChooser1.setDate(new SimpleDateFormat("dd-MM-yy").parse(fecha));
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PrincipalJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int nota = ((Integer) jTableExamenes.getValueAt(jTableExamenes.getSelectedRow(), 2));
                    if (nota >= 0 && nota <= 10) {
                        jComboBox1.setSelectedItem(String.valueOf(nota));
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
            }
        });

    }

    //Métodos auxiliares de gestión de contenido de tablas
    public static void limpiarTabla(DefaultTableModel dtm) {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    private void rellenarTablaDeAlumnos() {
        for (Alumno a : listaAlumnos) {
            dtmAlumno.addRow(new Object[]{a.getcCodAlu(), a.getcNomAlu()});
        }
    }

    private void rellenarTablaDeCursos() {
        for (Curso c : listaCursos) {
            dtmCursos.addRow(new Object[]{c.getcCodCurso(), c.getcNomCurso(), c.getnNumExa()});
        }
    }

    private void refrescarTablaDeMatriculas(ArrayList<VistaMatricula> vistaMatriculas) {
        limpiarTabla(dtmMatriculas);
        for (VistaMatricula vm : vistaMatriculas) {
            dtmMatriculas.addRow(new Object[]{vm.getcCodAlu(), vm.getcNomAlu(), vm.getcCodCurso(), vm.getcNomCurso(), vm.getnNotaMedia()});
        }
    }

    private void refrescarTablaDeExamenes(ArrayList<Examen> examenes) {
        limpiarTabla(dtmExamenes);
        for (Examen e : examenes) {
            dtmExamenes.addRow(new Object[]{e.getnNumExam(), e.getdFecExam(), e.getnNotaExam()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonMatricular = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPanelAlumnos = new javax.swing.JScrollPane();
        jTableAlumnos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneCursos = new javax.swing.JScrollPane();
        jTableTablaCursos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPaneMatriculas = new javax.swing.JScrollPane();
        jTableMatriculas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPaneExamenes = new javax.swing.JScrollPane();
        jTableExamenes = new javax.swing.JTable();
        jLabelFechaExamen = new javax.swing.JLabel();
        jLabelNota = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButtonActualizar = new javax.swing.JButton();
        jButtonBoletinJson = new javax.swing.JButton();
        jButtonListadoMatricula = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión Academia de Indiomas");
        setPreferredSize(new java.awt.Dimension(800, 785));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jButtonMatricular.setText("Martricular Alumno en Curso");
        jButtonMatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMatricularActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMatricular);
        jButtonMatricular.setBounds(260, 230, 220, 25);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(null);

        jScrollPanelAlumnos.setBorder(null);

        jTableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Alumno", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlumnos.getTableHeader().setReorderingAllowed(false);
        jScrollPanelAlumnos.setViewportView(jTableAlumnos);
        jTableAlumnos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableAlumnos.getColumnModel().getColumnCount() > 0) {
            jTableAlumnos.getColumnModel().getColumn(0).setResizable(false);
            jTableAlumnos.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jScrollPanelAlumnos);
        jScrollPanelAlumnos.setBounds(20, 20, 300, 160);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 20, 340, 200);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de cursos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setLayout(null);

        jTableTablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Curso", "Nombre Curso", "Nº Exámenes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTablaCursos.getTableHeader().setReorderingAllowed(false);
        jScrollPaneCursos.setViewportView(jTableTablaCursos);
        jTableTablaCursos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableTablaCursos.getColumnModel().getColumnCount() > 0) {
            jTableTablaCursos.getColumnModel().getColumn(0).setResizable(false);
            jTableTablaCursos.getColumnModel().getColumn(1).setResizable(false);
            jTableTablaCursos.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPaneCursos);
        jScrollPaneCursos.setBounds(20, 20, 360, 160);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(380, 20, 400, 200);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Matriculaciones del alumno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel3.setLayout(null);

        jTableMatriculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Alumno", "Nombre Alumno", "Código Curso", "Nombre Curso", "Nota Media"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMatriculas.getTableHeader().setReorderingAllowed(false);
        jScrollPaneMatriculas.setViewportView(jTableMatriculas);
        jTableMatriculas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableMatriculas.getColumnModel().getColumnCount() > 0) {
            jTableMatriculas.getColumnModel().getColumn(0).setResizable(false);
            jTableMatriculas.getColumnModel().getColumn(1).setResizable(false);
            jTableMatriculas.getColumnModel().getColumn(2).setResizable(false);
            jTableMatriculas.getColumnModel().getColumn(3).setResizable(false);
            jTableMatriculas.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel3.add(jScrollPaneMatriculas);
        jScrollPaneMatriculas.setBounds(20, 20, 720, 160);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(20, 265, 760, 200);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Exámenes del alumno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel4.setLayout(null);

        jTableExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número Examen", "Fecha Examen", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableExamenes.getTableHeader().setReorderingAllowed(false);
        jScrollPaneExamenes.setViewportView(jTableExamenes);
        jTableExamenes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableExamenes.getColumnModel().getColumnCount() > 0) {
            jTableExamenes.getColumnModel().getColumn(0).setResizable(false);
            jTableExamenes.getColumnModel().getColumn(1).setResizable(false);
            jTableExamenes.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel4.add(jScrollPaneExamenes);
        jScrollPaneExamenes.setBounds(20, 20, 460, 160);

        jLabelFechaExamen.setText("Fecha Examen");
        jPanel4.add(jLabelFechaExamen);
        jLabelFechaExamen.setBounds(490, 20, 100, 25);

        jLabelNota.setText("Nota");
        jPanel4.add(jLabelNota);
        jLabelNota.setBounds(490, 55, 50, 25);
        jPanel4.add(jDateChooser1);
        jDateChooser1.setBounds(590, 20, 150, 25);

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonActualizar);
        jButtonActualizar.setBounds(640, 55, 100, 25);

        jButtonBoletinJson.setText("Boletín JSON");
        jButtonBoletinJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBoletinJsonActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonBoletinJson);
        jButtonBoletinJson.setBounds(520, 125, 200, 25);

        jButtonListadoMatricula.setText("Listado Matrícula XML");
        jButtonListadoMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListadoMatriculaActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonListadoMatricula);
        jButtonListadoMatricula.setBounds(520, 160, 200, 25);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jPanel4.add(jComboBox1);
        jComboBox1.setBounds(550, 55, 50, 25);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(20, 475, 760, 200);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMatricularActionPerformed
        int rowAlumno = jTableAlumnos.getSelectedRow();
        int rowCurso = jTableTablaCursos.getSelectedRow();
        if (rowAlumno != -1 && rowCurso != -1) {
            String alumno = ((String) jTableAlumnos.getValueAt(rowAlumno, 0));
            String curso = ((String) jTableTablaCursos.getValueAt(rowCurso, 0));

            int resultado = consulta.procedimientoCrearMatricula(alumno, curso);
            if (resultado != 0) {
                JOptionPane.showMessageDialog(null, "No se pudo matricular al alumno."
                        + "\nError: " + resultado);
            } else {
                refrescarTablaDeMatriculas(consulta.obtenerVistaMatriculas(alumno));
            }
        }
    }//GEN-LAST:event_jButtonMatricularActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        int rowExamen = jTableExamenes.getSelectedRow();
        if (rowExamen >= 0) {
            String alumno = (String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 0);
            String curso = (String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 2);
            int numExam = (Integer) jTableExamenes.getValueAt(rowExamen, 0);
            LocalDate fecha = LocalDate.ofInstant(jDateChooser1.getCalendar().toInstant(), ZoneId.systemDefault());
            int nota = Integer.parseInt((String) jComboBox1.getSelectedItem());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-uu");
            String resultado = consulta.actualizarExamen(alumno, curso, numExam, fecha.format(formato), nota);
            if (!resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, resultado);
            } else {
                dtmExamenes.setValueAt(fecha.format(formato), rowExamen, 1);
                dtmExamenes.setValueAt(nota, rowExamen, 2);
            }

        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonBoletinJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBoletinJsonActionPerformed
        if (jTableMatriculas.getRowCount() > 0) {
            File file = obtenerFileParaGuardar("Archivo JavaScript Object Notation JSON (*.json)", "json");
            if (file != null && file.exists() && file.isFile() && file.canWrite()) {
                String alumno = ((String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 0));
                String curso = ((String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 2));
                Herramientas.exportarArchivoJSON(file, consulta.obtenerExamenes(alumno, curso));
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla de exámenes está vacía, no hay nada que exportar."
                    + "\nSeleccione una matrícula para mostrar en la tabla");
        }
    }//GEN-LAST:event_jButtonBoletinJsonActionPerformed

    private void jButtonListadoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListadoMatriculaActionPerformed
        try {
            //Creación del archivo XML
            File archivoXML = obtenerFileParaGuardar("Archivo Extensible Markup Language XML (*.xml)", "xml");
            if (archivoXML != null && archivoXML.exists() && archivoXML.isFile() && archivoXML.canWrite()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document d = db.newDocument();

                //Configuración del tranformer
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                //Asignacición del documento al transformer y configuración de entrada-salida
                Source source = new DOMSource(Herramientas.rellenarDocument(d));
                Result result = new StreamResult(archivoXML);
                transformer.transform(source, result);
            }
        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error."
                    + "\nDescripción error: " + ex.getMessage());
        }

    }//GEN-LAST:event_jButtonListadoMatriculaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Conexion.cerrarConexion();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBoletinJson;
    private javax.swing.JButton jButtonListadoMatricula;
    private javax.swing.JButton jButtonMatricular;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabelFechaExamen;
    private javax.swing.JLabel jLabelNota;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPaneCursos;
    private javax.swing.JScrollPane jScrollPaneExamenes;
    private javax.swing.JScrollPane jScrollPaneMatriculas;
    private javax.swing.JScrollPane jScrollPanelAlumnos;
    private javax.swing.JTable jTableAlumnos;
    private javax.swing.JTable jTableExamenes;
    private javax.swing.JTable jTableMatriculas;
    private javax.swing.JTable jTableTablaCursos;
    // End of variables declaration//GEN-END:variables

}
