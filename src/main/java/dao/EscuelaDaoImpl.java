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
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class EscuelaDaoImpl implements EscuelaDao {

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

    // Método para comprobar si una escuela tiene pintores
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
     * Comprueba si un pintor tiene cuadros asociados en la tabla CUADROS.
     *
     * @param nombrePintor Nombre del pintor a comprobar.
     * @return true si tiene cuadros, false si no.
     */
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
