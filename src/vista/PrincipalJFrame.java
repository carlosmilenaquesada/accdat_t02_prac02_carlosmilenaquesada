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

public class PrincipalJFrame extends javax.swing.JFrame {

    Consultas consulta;
    DefaultTableModel dtmAlumno;
    DefaultTableModel dtmCursos;
    DefaultTableModel dtmMatriculas;
    DefaultTableModel dtmExamenes;
    ArrayList<Alumno> listaAlumnos;
    ArrayList<Curso> listaCursos;

    public PrincipalJFrame() {

        if (Conexion.getInstance() == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            System.exit(0);
        }

        initComponents();
        initConfiguracion();

    }

    private void initConfiguracion() {
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

        JTextFieldDateEditor e = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        e.setEditable(false);

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
                    jTextFieldNota.setText(Integer.toString(nota));

                }
            }
        });

    }

    private void rellenarTablaDeAlumnos() {
        for (Alumno a : listaAlumnos) {
            dtmAlumno.addRow(new Object[]{a.getcCodALu(), a.getcNomAlu()});
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

    public static void limpiarTabla(DefaultTableModel dtm) {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPanelAlumnos = new javax.swing.JScrollPane();
        jTableAlumnos = new javax.swing.JTable();
        jScrollPaneCursos = new javax.swing.JScrollPane();
        jTableTablaCursos = new javax.swing.JTable();
        jButtonMatricular = new javax.swing.JButton();
        jScrollPaneMatriculas = new javax.swing.JScrollPane();
        jTableMatriculas = new javax.swing.JTable();
        jScrollPaneExamenes = new javax.swing.JScrollPane();
        jTableExamenes = new javax.swing.JTable();
        jLabelFechaExamen = new javax.swing.JLabel();
        jLabelNota = new javax.swing.JLabel();
        jTextFieldNota = new javax.swing.JTextField();
        jButtonActualizar = new javax.swing.JButton();
        jButtonBoletinJson = new javax.swing.JButton();
        jButtonListadoMatricula = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión Academia de Indiomas");
        setMinimumSize(new java.awt.Dimension(1075, 841));
        getContentPane().setLayout(null);

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

        getContentPane().add(jScrollPanelAlumnos);
        jScrollPanelAlumnos.setBounds(12, 33, 420, 190);

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

        getContentPane().add(jScrollPaneCursos);
        jScrollPaneCursos.setBounds(462, 30, 430, 200);

        jButtonMatricular.setText("Martricular Alumno en Curso");
        jButtonMatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMatricularActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMatricular);
        jButtonMatricular.setBounds(420, 250, 250, 25);

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

        getContentPane().add(jScrollPaneMatriculas);
        jScrollPaneMatriculas.setBounds(22, 290, 900, 170);

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

        getContentPane().add(jScrollPaneExamenes);
        jScrollPaneExamenes.setBounds(50, 520, 452, 190);

        jLabelFechaExamen.setText("Fecha Examen");
        getContentPane().add(jLabelFechaExamen);
        jLabelFechaExamen.setBounds(550, 600, 100, 25);

        jLabelNota.setText("Nota");
        getContentPane().add(jLabelNota);
        jLabelNota.setBounds(540, 690, 100, 25);
        getContentPane().add(jTextFieldNota);
        jTextFieldNota.setBounds(630, 710, 150, 25);

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonActualizar);
        jButtonActualizar.setBounds(670, 750, 150, 25);

        jButtonBoletinJson.setText("Boletín JSON");
        jButtonBoletinJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBoletinJsonActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBoletinJson);
        jButtonBoletinJson.setBounds(600, 800, 250, 25);

        jButtonListadoMatricula.setText("Listado Matrícula XML");
        jButtonListadoMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListadoMatriculaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonListadoMatricula);
        jButtonListadoMatricula.setBounds(290, 790, 250, 25);
        getContentPane().add(jDateChooser1);
        jDateChooser1.setBounds(650, 600, 210, 20);

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

            int nota = Integer.parseInt(jTextFieldNota.getText());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
            String resultado = consulta.actualizarExamen(alumno, curso, numExam, fecha.format(formato), nota);
            if (!resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error al completar la acción.\n" + resultado);
            } else {
                dtmExamenes.setValueAt(fecha, rowExamen, 1);
                dtmExamenes.setValueAt(nota, rowExamen, 2);
            }

        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonBoletinJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBoletinJsonActionPerformed
        File file = Herramientas.obtenerFileParaGuardar();
        if (file != null && file.exists() && file.isFile() && file.canWrite()) {
            String alumno = ((String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 0));
            String curso = ((String) jTableMatriculas.getValueAt(jTableMatriculas.getSelectedRow(), 2));
            Herramientas.exportarArchivoJSON(file, consulta.obtenerExamenes(alumno, curso));
        }
    }//GEN-LAST:event_jButtonBoletinJsonActionPerformed

    private void jButtonListadoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListadoMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonListadoMatriculaActionPerformed

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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabelFechaExamen;
    private javax.swing.JLabel jLabelNota;
    private javax.swing.JScrollPane jScrollPaneCursos;
    private javax.swing.JScrollPane jScrollPaneExamenes;
    private javax.swing.JScrollPane jScrollPaneMatriculas;
    private javax.swing.JScrollPane jScrollPanelAlumnos;
    private javax.swing.JTable jTableAlumnos;
    private javax.swing.JTable jTableExamenes;
    private javax.swing.JTable jTableMatriculas;
    private javax.swing.JTable jTableTablaCursos;
    private javax.swing.JTextField jTextFieldNota;
    // End of variables declaration//GEN-END:variables

}
