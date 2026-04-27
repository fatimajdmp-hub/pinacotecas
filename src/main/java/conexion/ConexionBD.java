package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexion de la base de datos de Pinacoteca con SQLite.
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class ConexionBD {
    /**
     * La url: determina donde esta el archivo de la base de datos.
     */
    private static final String URL = "jdbc:sqlite:Pinacoteca.db";

    /**
     * La variable que guarda la conexion de la base de datos
     */
    private static Connection conexion;

    /**
     * construcot privado para que no puedan llamarlo de otras clase
     */
    private ConexionBD() {}

    /**
     * Proporciona la conexion de la base de datos, verifica si la concexion es nula o ha sido cerrada anteriormente.En
     * caso que sea correcto crea una nueca conexion
     * @return devuelve el objeto que realiza las operaciones en la base de datos
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
            try {
                if(conexion == null || conexion.isClosed()) {
                    conexion = DriverManager.getConnection(URL);
                    System.out.println("Conexion establecida");
                }
            }catch(SQLException e){
                System.out.println("Error al conectar");
            }
        return conexion;
    }
}
