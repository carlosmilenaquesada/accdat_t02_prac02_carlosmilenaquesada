package controlador;

import modelo.Alumno;
import modelo.Curso;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String ins = "SELECT * FROM V_MATRICULAS WHERE cCodAlu = " + cCodAlu;
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

    public ArrayList<Object> obtenerExamenes(String cCodAlu, String cCodCurso) {
        ArrayList<Object> listaExamenes = new ArrayList<>();

        return listaExamenes;
    }

    public int procedimientoCrearMatricula(String cCodAlu, String cCodCurso) {
        String ins = "{call sp_AltaMatricula(?, ?, ?)}";
        int codigoRetorno = 0;
        try {
            CallableStatement sentencia = Conexion.getInstance().prepareCall(ins);
            sentencia.setString("xcCodAlu", "rosetas");
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
}
