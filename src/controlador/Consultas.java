package controlador;

import modelo.Alumno;
import modelo.Curso;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import modelo.Examen;
import modelo.VistaMatricula;

public class Consultas {

    public static ArrayList<Alumno> obtenerAlumnos() throws SQLException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String ins = "SELECT * FROM ALUMNOS";
        ResultSet rs;
        Statement st = Conexion.getConexion().createStatement();
        rs = st.executeQuery(ins);
        while (rs.next()) {
            alumnos.add(new Alumno(rs.getString("cCodAlu"), rs.getString("cNomAlu")));
        }
        return alumnos;
    }

    public static ArrayList<Curso> obteneCursos() throws SQLException {
        ArrayList<Curso> cursos = new ArrayList<>();
        String ins = "SELECT * FROM CURSOS";
        ResultSet rs;
        Statement st = Conexion.getConexion().createStatement();
        rs = st.executeQuery(ins);
        while (rs.next()) {
            cursos.add(new Curso(rs.getString("cCodCurso"), rs.getString("cNomCurso"), rs.getInt("nNumExa")));
        }
        return cursos;
    }

    public static ArrayList<VistaMatricula> obtenerVistaMatriculas(String cCodAlu) throws SQLException {
        ArrayList<VistaMatricula> listaMatriculas = new ArrayList<>();
        String ins = "SELECT * FROM V_MATRICULAS WHERE cCodAlu = '" + cCodAlu + "'";
        Statement st = Conexion.getConexion().createStatement();
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
        return listaMatriculas;
    }

    public static ArrayList<Examen> obtenerExamenes(String cCodAlu, String cCodCurso) throws SQLException {
        ArrayList<Examen> listaExamenes = new ArrayList<>();
        String ins = "SELECT * FROM EXAMENES WHERE cCodAlu = '" + cCodAlu + "' AND cCodCurso = '" + cCodCurso + "'";
        Statement st = Conexion.getConexion().createStatement();
        ResultSet rs = st.executeQuery(ins);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        while (rs.next()) {
            Examen ex = new Examen();
            ex.setcCodAlu(rs.getString("cCodAlu"));
            ex.setcCodCurso(rs.getString("cCodCurso"));
            ex.setnNumExam(rs.getInt("nNumExam"));
            ex.setnNotaExam(rs.getInt("nNotaExam"));
            ex.setdFecExam(rs.getDate("dFecExam"));            
            listaExamenes.add(ex);
        }
        return listaExamenes;
    }

    public static int procedimientoCrearMatricula(String cCodAlu, String cCodCurso) throws SQLException {
        String ins = "{call sp_AltaMatricula(?, ?, ?)}";
        CallableStatement sentencia = Conexion.getConexion().prepareCall(ins);
        sentencia.setString("xcCodAlu", cCodAlu);
        sentencia.setString("xcCodCurso", cCodCurso);
        sentencia.registerOutParameter("xError", Types.NUMERIC);
        sentencia.executeUpdate();
        return sentencia.getInt("xError");
    }

    public static void actualizarExamen(String cCodAlu, String cCodCurso, int nNumExam, java.sql.Date dFecExam, int nNotaExam) throws SQLException {
        String ins = "UPDATE EXAMENES SET dFecExam = ?, nNotaExam = ? WHERE cCodAlu = ? "
                + "AND cCodCurso = ? AND nNumExam = ?";
        PreparedStatement ps = Conexion.getConexion().prepareStatement(ins);
        ps.setDate(1, dFecExam);
        ps.setInt(2, nNotaExam);
        ps.setString(3, cCodAlu);
        ps.setString(4, cCodCurso);
        ps.setInt(5, nNumExam);
        ps.executeUpdate();
    }
}
