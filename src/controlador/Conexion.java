package controlador;

import java.sql.*;

public class Conexion {

    private static Connection connection = null;
    private static final String SERVER = "localhost";
    private static final String PORT = "1521";
    private static final String USER = "AD_TEMA02";
    private static final String PASS = "AD_TEMA02";

    public static Connection getConexion() {
        if (Conexion.connection == null) {
            try {
                Conexion.connection = DriverManager.getConnection("jdbc:oracle:thin:@" + Conexion.SERVER + ":" + Conexion.PORT + ":" + "xe", Conexion.USER, Conexion.PASS);
            } catch (SQLException ex) {
                Conexion.connection = null;
            }
        }
        return Conexion.connection;
    }

    public static void cerrarConexion() {
        try {
            if (Conexion.connection != null && !Conexion.connection.isClosed()) {
                Conexion.connection.close();
            }
        } catch (SQLException ex) {
        }

    }
}
