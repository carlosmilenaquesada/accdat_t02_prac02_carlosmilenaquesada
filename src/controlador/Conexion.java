package controlador;

import java.sql.*;

public class Conexion {

    /**
     * Conexión a la base de datos es estática (solo puede haber una en todo el
     * programa) y privada (accedemos a ella solo a través de getInstance()).
     *
     * Los atributos son privados, ya que nos interesa que solamente se puedan
     * inicializar a del constructor por parámetros.
     */
    private static Connection connection = null;
    private static final String server = "localhost";
    private static final String port = "1521";
    private static final String user = "AD_TEMA02";
    private static final String pass = "AD_TEMA02";

    /**
     * Mediante el constructor por parámetros, se inicializan los atributos que
     * recibirá la Connection.
     */
    /**
     * El método getInstance permite obtener un objeto tipo Connection, que
     * obligatoriamente será instancia única, y además solamente se puede crear
     * cuando sea null.
     *
     * Unos atributos no válidos harán que connection siga siendo null (por
     * exception o por no inicialización), lo que nos permite reintentar la
     * creación. Una una vez creada, no se podrá sobreescribir.
     */
    public static Connection getInstance() {
        if (Conexion.connection == null) {
            try {
                Conexion.connection = DriverManager.getConnection("jdbc:oracle:thin:@" + Conexion.server + ":" + Conexion.port + ":" + "xe", Conexion.user, Conexion.pass);
            } catch (SQLException ex) {
                Conexion.connection = null;
            }
        }
        return Conexion.connection;
    }

    public static void cerrarConexion() {
        try {
            if (!Conexion.connection.isClosed()) {
                Conexion.connection.close();
            }
        } catch (SQLException ex) {
        }

    }
}
