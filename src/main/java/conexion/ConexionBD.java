package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class ConexionBD {
    private static final String URL = "jdbc:sqlite:Pinacoteca.db";


    private static Connection conexion;

    private ConexionBD() {}

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
