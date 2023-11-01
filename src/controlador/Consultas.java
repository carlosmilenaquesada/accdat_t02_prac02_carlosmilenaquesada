package controlador;

import modelo.Alumno;
import modelo.Curso;
import java.util.ArrayList;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Examen;
import modelo.VistaMatricula;

public class Consultas {

    public Consultas() {

    }

    public ArrayList<Alumno> obtenerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String ins = "SELECT * FROM ALUMNOS";
        ResultSet rs;
        try {
            Statement st = Conexion.getInstance().createStatement();
            rs = st.executeQuery(ins);

            while (rs.next()) {
                alumnos.add(new Alumno(rs.getString("cCodAlu"), rs.getString("cNomAlu")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    public ArrayList<Curso> obteneCursos() {
        ArrayList<Curso> cursos = new ArrayList<>();
        String ins = "SELECT * FROM CURSOS";
        ResultSet rs;
        try {
            Statement st = Conexion.getInstance().createStatement();
            rs = st.executeQuery(ins);
            while (rs.next()) {
                cursos.add(new Curso(rs.getString("cCodCurso"), rs.getString("cNomCurso"), rs.getInt("nNumExa")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    public ArrayList<VistaMatricula> obtenerVistaMatriculas(String cCodAlu) {
        ArrayList<VistaMatricula> listaMatriculas = new ArrayList<>();
        String ins = "SELECT * FROM V_MATRICULAS WHERE cCodAlu = '" + cCodAlu + "'";
        try {
            Statement st = Conexion.getInstance().createStatement();
            ResultSet rs = st.executeQuery(ins);
            while (rs.next()) {
                listaMatriculas.add(new VistaMatricula(
                        rs.getString("cCodAlu"),
                        rs.getString("cNomAlu"),
                        rs.getString("cCodCurso"),
                        rs.getString("cNomCurso"),
                        rs.getInt("nNotaMedia"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMatriculas;
    }

    public ArrayList<Examen> obtenerExamenes(String cCodAlu, String cCodCurso) {
        ArrayList<Examen> listaExamenes = new ArrayList<>();
        String ins = "SELECT * FROM EXAMENES WHERE cCodAlu = '" + cCodAlu + "' AND cCodCurso = '" + cCodCurso + "'";

        try {
            Statement st = Conexion.getInstance().createStatement();
            ResultSet rs = st.executeQuery(ins);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            while (rs.next()) {               
                    Examen ex = new Examen();

                    ex.setcCodAlu(rs.getString("cCodAlu"));
                    ex.setcCodCurso(rs.getString("cCodCurso"));
                    ex.setnNumExam(rs.getInt("nNumExam"));
                    ex.setnNotaExam(rs.getInt("nNotaExam"));
                    if (rs.getString("dFecExam") != null) {   
                        ex.setdFecExam(sdf.format(rs.getDate("dFecExam")));
                    }
                    listaExamenes.add(ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaExamenes;
    }

    public int procedimientoCrearMatricula(String cCodAlu, String cCodCurso) {
        String ins = "{call sp_AltaMatricula(?, ?, ?)}";
        int codigoRetorno = 0;
        try {
            CallableStatement sentencia = Conexion.getInstance().prepareCall(ins);
            sentencia.setString("xcCodAlu", cCodAlu);
            sentencia.setString("xcCodCurso", cCodCurso);
            sentencia.registerOutParameter("xError", Types.NUMERIC);
            sentencia.executeUpdate();
            codigoRetorno = sentencia.getInt("xError");
        } catch (SQLException ex) {
            codigoRetorno = ex.getErrorCode();
        } finally {
            return codigoRetorno;
        }
    }

    public void actualizarExamen(String cCodAlu, String cCodCurso, int nNumExam, String dFecExam, int nNotaExam) {
        String ins = "UPDATE EXAMENES SET dFecExam = ?, nNotaExam = ? WHERE cCodAlu = ? "
                + "AND cCodCurso = ? AND nNumExam = ?";

        try {
            PreparedStatement ps = Conexion.getInstance().prepareStatement(ins);
            ps.setString(1, dFecExam);
            ps.setInt(2, nNotaExam);
            ps.setString(3, cCodAlu);
            ps.setString(4, cCodCurso);
            ps.setInt(5, nNumExam);
            String ins2 = "UPDATE EXAMENES SET dFecExam = " + dFecExam + ", nNotaExam = " + nNotaExam + " WHERE cCodAlu = " + cCodAlu + " "
                    + "AND cCodCurso = " + cCodCurso + " AND nNumExam = " + nNumExam;
            System.out.println(ins2);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("esta");
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
