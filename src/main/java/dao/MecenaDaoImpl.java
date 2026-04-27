package dao;

import conexion.ConexionBD;
import modelo.Mecena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestion de la tabla Mecenas
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class MecenaDaoImpl implements MecenaDao {
    /**
     * Tabla Mecenas:
     * Crea la tabla mecenas.
     * Clave primaria: nombre tipo texto no nulo.
     * Atributo: fecha,pais,ciudadNacimiento,fechaDeFunccion tipo texto.
     *
     * Tabla relacion pintor_mecenas:
     * Crea la tabla pintor_mecenas
     * Clave primaria: nombre_pintor, nombre_mecenas tipo texto no nulo.
     * Atributo: tipoRelacion tipo texto.
     * Clave foranea: nombre_pintor(Tabla Pintores),nombre_mecenas(Tabla Mecenas).
     */
    @Override
    public void crearTabla() {
        String sqlMecenas = """ 
                CREATE TABLE IF NOT EXISTS MECENAS (
                    nombre TEXT NOT NULL,
                    fecha TEXT,
                    pais TEXT,
                    ciudadNacimiento TEXT,
                    fechaDeFuncion TEXT,
                    PRIMARY KEY (nombre)
                );
                """;
        String sqlRelacionPintorMecenas = """ 
                CREATE TABLE IF NOT EXISTS PINTOR_MECENAS (
                    nombre_pintor TEXT NOT NULL,
                    nombre_mecenas TEXT NOT NULL,
                    tipoRelacion TEXT,
                    PRIMARY KEY (nombre_pintor, nombre_mecenas),
                    CONSTRAINT fk_pm_pintor
                        FOREIGN KEY (nombre_pintor) REFERENCES PINTORES(nombre)
                        ON DELETE CASCADE ON UPDATE CASCADE,
                    CONSTRAINT fk_pm_mecenas
                        FOREIGN KEY (nombre_mecenas) REFERENCES MECENAS(nombre)
                        ON DELETE CASCADE ON UPDATE CASCADE
                );
                """;
        try (Connection conexion = ConexionBD.getConnection();
             Statement statement = conexion.createStatement()) {
            statement.execute(sqlMecenas);
            statement.execute(sqlRelacionPintorMecenas);

            System.out.println("TABLA DE MECENAS Y RELACION PINTOR-MECENAS CREADA");
        } catch (Exception e) {
            System.out.println("Error al crear tabla MECENAS Y RELACION PINTOR-MECENAS" + e.getMessage());
        }
    }

    /**
     * Recibe un objeto Mecena y inserta los datos de mecena en la tabla mecenas en sus columna correspondientes
     * @param mecena
     */
    @Override
    public void insertarMecena(Mecena mecena) {
        String sql = "INSERT INTO mecenas (nombre,fecha,pais,ciudadNacimiento,fechaDeFuncion) VALUES (?,?,?,?,?)";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, mecena.getNombre());
            preparedStatement.setString(2, mecena.getFecha());
            preparedStatement.setString(3, mecena.getPais());
            preparedStatement.setString(4, mecena.getCiudadNacimiento());
            preparedStatement.setString(5, mecena.getFechaDeFuncion());
            preparedStatement.executeUpdate();

            System.out.println("Insertado mecena");
        } catch (Exception e) {
            System.out.println("Error al crear tabla MECENAS" + e.getMessage());
        }
    }

    /**
     * Lee fila por fila de mecenas y crea un objeto mecena con sus datos y lo guarda en una lista.
     * @return devuelve la lista de mecenas
     */
    @Override
    public List<Mecena> listarMecenas() {
        String sql = "SELECT * FROM mecenas";
        List<Mecena> mecenas = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConnection();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            while (resultSet.next()) {
                Mecena mecena = new Mecena();
                mecena.setNombre(resultSet.getString("nombre"));
                mecena.setFecha(resultSet.getString("fecha"));
                mecena.setPais(resultSet.getString("pais"));
                mecena.setCiudadNacimiento(resultSet.getString("ciudadNacimiento"));
                mecena.setFechaDeFuncion(resultSet.getString("fechaDeFuncion"));
                mecenas.add(mecena);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tabla MECENAS" + e.getMessage());
        }
        return mecenas;
    }

    /**
     * Recibe un objeto mecena con los cambio ya hechos y lo actualiza en la tabla donde estaba mecena que queriamos
     * actualizar.
     * @param mecena
     */
    @Override
    public void actualizarMecena(Mecena mecena) {
        String sql = "UPDATE mecenas SET fecha =?, pais=?, ciudadNacimiento=?, fechaDeFuncion = ? WHERE nombre=?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, mecena.getFecha());
            preparedStatement.setString(2, mecena.getPais());
            preparedStatement.setString(3, mecena.getCiudadNacimiento());
            preparedStatement.setString(4, mecena.getFechaDeFuncion());
            preparedStatement.setString(5, mecena.getNombre());
            preparedStatement.execute();
            System.out.println("Actualizando MECENA: " + mecena.getNombre());
        } catch (Exception e) {
            System.out.println("Error al actualizar tabla MECENAS" + e.getMessage());
        }
    }

    /**
     * Elimina la mecena de la tabla mecenas.
     * @param nombre
     */
    @Override
    public void eliminarMecena(String nombre) {
        String sql = "DELETE FROM mecenas WHERE nombre=?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.execute();

            System.out.println("Eliminado MECENA: " + nombre);
        } catch (Exception e) {
            System.out.println("Error al eliminar tabla MECENAS" + e.getMessage());
        }
    }

    /**
     * Asocia las claves foranea nombre_mecenas y nombre_pintor y lo guarda en la tabla pintor_mecenas.
     *
     * @param nombreMecena tabla mecenas
     * @param nombrePintor tabla pintores
     * @param relacion
     */
    @Override
    public void asociarMecenaConPintor(String nombreMecena, String nombrePintor, String relacion) {
        String sql = "INSERT OR REPLACE INTO pintor_mecenas(nombre_mecenas, nombre_pintor, tipoRelacion) VALUES(?, ?, ?)";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreMecena);
            preparedStatement.setString(2, nombrePintor);
            preparedStatement.setString(3, relacion);
            preparedStatement.execute();

            System.out.println("Relacion MECENAS-PINTOR insertada");
        } catch (Exception e) {
            System.out.println("Error al asociar MECENAS con PINTOR: " + e.getMessage());
        }
    }

    /**
     * Descvincula las claves foranea nombre_mecenas y nombre_pintor y lo elimina de la tabla .
     *
     * @param nombreMecena tabla mecenas
     * @param nombrePintor tabla pintores
     */
    @Override
    public void desvincularMecenaDePintor(String nombreMecena, String nombrePintor) {
        String sql = "DELETE FROM pintor_mecenas WHERE nombre_mecenas = ? AND nombre_pintor = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreMecena);
            preparedStatement.setString(2, nombrePintor);
            preparedStatement.execute();
            System.out.println("relacion MECENAS-PINTOR eliminada");
        } catch (Exception e) {
            System.out.println("Error al desvincular MECENAS de PINTOR: " + e.getMessage());
        }
    }

}
