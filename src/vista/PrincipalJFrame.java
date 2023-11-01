package vista;

import controlador.Conexion;
import controlador.Consultas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Curso;
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
        dtmAlumno = (DefaultTableModel) jTableTablaAlumnos.getModel();
        dtmCursos = (DefaultTableModel) jTableTablaCursos.getModel();
        dtmMatriculas = (DefaultTableModel) jTableMatriculas.getModel();
        dtmExamenes = (DefaultTableModel) jTableExamenes.getModel();
        jTableTablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableTablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableMatriculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaAlumnos = consulta.obtenerAlumnos();
        rellenarTablaDeAlumnos();
        listaCursos = consulta.obteneCursos();
        rellenarTablaDeCursos();
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

    private void rellenarTablaDeMatriculas(ArrayList<VistaMatricula> vistaMatriculas) {
        for (VistaMatricula vm : vistaMatriculas) {
            dtmMatriculas.addRow(new Object[]{vm.getcCodAlu(), vm.getcNomAlu(), vm.getcCodCurso(), vm.getcNomCurso(), vm.getnNotaMedia()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPanelTablaAlumnos = new javax.swing.JScrollPane();
        jTableTablaAlumnos = new javax.swing.JTable();
        jScrollPaneTablaCursos = new javax.swing.JScrollPane();
        jTableTablaCursos = new javax.swing.JTable();
        jButtonMatricular = new javax.swing.JButton();
        jScrollPaneMatriculas = new javax.swing.JScrollPane();
        jTableMatriculas = new javax.swing.JTable();
        jScrollPaneExamenes = new javax.swing.JScrollPane();
        jTableExamenes = new javax.swing.JTable();
        jLabelFechaExamen = new javax.swing.JLabel();
        jLabelNota = new javax.swing.JLabel();
        jTextFieldFechaExamen = new javax.swing.JTextField();
        jTextFieldNota = new javax.swing.JTextField();
        jButtonActualizar = new javax.swing.JButton();
        jButtonBoletinJson = new javax.swing.JButton();
        jButtonListadoMatricula = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión Academia de Indiomas");
        setMinimumSize(new java.awt.Dimension(1075, 841));
        getContentPane().setLayout(null);

        jTableTablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableTablaAlumnos.getTableHeader().setReorderingAllowed(false);
        jScrollPanelTablaAlumnos.setViewportView(jTableTablaAlumnos);
        jTableTablaAlumnos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableTablaAlumnos.getColumnModel().getColumnCount() > 0) {
            jTableTablaAlumnos.getColumnModel().getColumn(0).setResizable(false);
            jTableTablaAlumnos.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPanelTablaAlumnos);
        jScrollPanelTablaAlumnos.setBounds(12, 33, 420, 190);

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
        jScrollPaneTablaCursos.setViewportView(jTableTablaCursos);
        jTableTablaCursos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableTablaCursos.getColumnModel().getColumnCount() > 0) {
            jTableTablaCursos.getColumnModel().getColumn(0).setResizable(false);
            jTableTablaCursos.getColumnModel().getColumn(1).setResizable(false);
            jTableTablaCursos.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPaneTablaCursos);
        jScrollPaneTablaCursos.setBounds(462, 30, 430, 200);

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
        jLabelFechaExamen.setBounds(530, 520, 100, 25);

        jLabelNota.setText("Nota");
        getContentPane().add(jLabelNota);
        jLabelNota.setBounds(540, 580, 100, 25);
        getContentPane().add(jTextFieldFechaExamen);
        jTextFieldFechaExamen.setBounds(670, 520, 150, 25);
        getContentPane().add(jTextFieldNota);
        jTextFieldNota.setBounds(670, 580, 150, 25);

        jButtonActualizar.setText("Actualizar");
        getContentPane().add(jButtonActualizar);
        jButtonActualizar.setBounds(680, 630, 150, 25);

        jButtonBoletinJson.setText("Boletín JSON");
        getContentPane().add(jButtonBoletinJson);
        jButtonBoletinJson.setBounds(600, 680, 250, 25);

        jButtonListadoMatricula.setText("Listado Matrícula XML");
        getContentPane().add(jButtonListadoMatricula);
        jButtonListadoMatricula.setBounds(610, 740, 250, 25);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMatricularActionPerformed
        int rowAlumno = jTableTablaAlumnos.getSelectedRow();
        int rowCurso = jTableTablaCursos.getSelectedRow();
        if (rowAlumno != -1 && rowCurso != -1) {
            String alumno = ((String) jTableTablaAlumnos.getValueAt(rowAlumno, 0));
            String curso = ((String) jTableTablaCursos.getValueAt(rowCurso, 0));
            int resultado = consulta.procedimientoCrearMatricula(alumno, curso);
            if (resultado != 0) {
                JOptionPane.showMessageDialog(null, "No se pudo matricular al alumno."
                        + "\nError: " + resultado);
            } else {
                rellenarTablaDeMatriculas(consulta.obtenerVistaMatriculas(alumno));
            }
        }
    }//GEN-LAST:event_jButtonMatricularActionPerformed

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
    private javax.swing.JLabel jLabelFechaExamen;
    private javax.swing.JLabel jLabelNota;
    private javax.swing.JScrollPane jScrollPaneExamenes;
    private javax.swing.JScrollPane jScrollPaneMatriculas;
    private javax.swing.JScrollPane jScrollPaneTablaCursos;
    private javax.swing.JScrollPane jScrollPanelTablaAlumnos;
    private javax.swing.JTable jTableExamenes;
    private javax.swing.JTable jTableMatriculas;
    private javax.swing.JTable jTableTablaAlumnos;
    private javax.swing.JTable jTableTablaCursos;
    private javax.swing.JTextField jTextFieldFechaExamen;
    private javax.swing.JTextField jTextFieldNota;
    // End of variables declaration//GEN-END:variables
}
