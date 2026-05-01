package dao;

import conexion.ConexionBD;
import modelo.Escuela;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *Gestion de la tabla Escuelas
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class EscuelaDaoImpl implements EscuelaDao {

    /**
     * Crea la tabla escuela.
     * Clave primaria:nombre tipo texto no nulo.
     * Atributo: paisAparicion,fechaDeCreacion tipo texto.
     */
    @Override
    public void crearEscuela() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ESCUELAS (
                    nombre TEXT NOT NULL,
                    paisAparicion TEXT,
                    fechaDeCreacion TEXT,
                    PRIMARY KEY (nombre)
                );
                """;
        try (Connection conexion = ConexionBD.getConnection();
             Statement statement = conexion.createStatement()) {

            statement.execute(sql);
            System.out.println("TABLA DE ESCUELA CREADA");
        } catch (Exception e) {
            System.out.println("Error al crear escuela");
        }
    }

    /**
     * Recibe un objeto escuela e inserta sus datos en la tabla escuela en sus columnas correspondiente.
     * @param escuela
     */
    @Override
    public void insertarEscuela(Escuela escuela) {
        String sql = "INSERT INTO escuelas (nombre, paisAparicion, fechaDeCreacion) VALUES (?, ?, ?);";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, escuela.getNombre());
            preparedStatement.setString(2, escuela.getPaisAparicion());
            preparedStatement.setString(3, escuela.getFechaDeCreacion());
            preparedStatement.executeUpdate();
            System.out.println("TABLA DE ESCUELA CREADA");
        } catch (Exception e) {
            System.out.println("Error al insertar escuela");
        }
    }

    /**
     * Lee fila por fila de la tabla escuela y crea un objeto escuela con los datos de esa fila y lo mete en una lista.
     * @return devuelve una lista de la escuela.
     */
    @Override
    public List<Escuela> listarEscuelas() {
        String sql = "SELECT * FROM escuelas;";
        List<Escuela> escuelas = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Escuela escuela = new Escuela();
                escuela.setNombre(resultSet.getString("nombre"));
                escuela.setPaisAparicion(resultSet.getString("paisAparicion"));
                escuela.setFechaDeCreacion(resultSet.getString("fechaDeCreacion"));
                escuelas.add(escuela);
            }
        } catch (Exception e) {
            System.out.println("Error al listar escuelas");
        }
        return escuelas;
    }

    /**
     * Recibe un objeto escuela con los cambio ya hechos y lo actualiza en la tabla donde estaba la escuela que
     * queriamos actualizar.
     * @param escuela
     */
    @Override
    public void actualizarEscuela(Escuela escuela) {
        String sql = "UPDATE escuelas SET paisAparicion = ?, fechaDeCreacion = ? WHERE nombre = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, escuela.getPaisAparicion());
            preparedStatement.setString(2, escuela.getFechaDeCreacion());
            preparedStatement.setString(3, escuela.getNombre());
            preparedStatement.execute();
            System.out.println("Actualizando ESCUELA: " + escuela.getNombre());
        } catch (Exception e) {
            System.out.println("Error al actualizar escuela");
        }
    }

    /**
     * Elimina la escula de la tabla escuela.
     * @param escuela
     */
    @Override
    public void eliminarEscuela(String escuela) {
        String sql = "DELETE FROM escuelas WHERE nombre = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, escuela);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Error al eliminar escuela");
        }
    }

    /**
     * Busca si tiene pintores asociado a una escuela en la tabla pintores.
     * @param nombreEscuela
     * @return true si tiene pintoresasociados, false si no
     */
    @Override
    public boolean tienePintoresAsociados(String nombreEscuela) {
        String sql = "SELECT COUNT(*) FROM pintores WHERE nombre_escuela = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombreEscuela);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al comprobar los pintores de la escuela: " + e.getMessage());
        }
        return false;
    }

    /**
     * Comprueba si un pintor tiene cuadros asociados en la tabla cuadros.
     *
     * @param nombrePintor Nombre del pintor a comprobar.
     * @return true si tiene cuadros, false si no.
     */
    @Override
    public boolean tieneCuadrosAsociados(String nombrePintor) {
        String sql = "SELECT COUNT(*) FROM cuadros WHERE nombre_pintor = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombrePintor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al comprobar los cuadros: " + e.getMessage());
        }
        return false;
    }

}
