package dao;

import conexion.ConexionBD;
import modelo.Pinacoteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestion de la tabla Pinacotecas
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class PinacotecaDaoImpl implements PinacotecaDao {

    /**
     * Crear tabla pinacotecas.
     * Clave primaria: nombre tipo texto no nulo.
     * Atributos: ciudad (tipo texto), direccion (tipo texto), metrosCuadrados (Tipo numeric).
     */
    @Override
    public void crearTabla() {
        String sql = """ 
                CREATE TABLE IF NOT EXISTS PINACOTECAS (
                nombre TEXT NOT NULL,
                ciudad TEXT NOT NULL,
                direccion TEXT,
                metrosCuadrados NUMERIC(10,2),
                PRIMARY KEY (nombre)
                );
                """;
        try (Connection conexion = ConexionBD.getConnection();
             Statement statement = conexion.createStatement()) {
            statement.execute(sql);
            System.out.println("TABLA DE PINACOTECA CREADA");
        } catch (Exception e) {
            System.out.println("Error al crear tabla PINACOTECA" + e.getMessage());
        }
    }

    /**
     * Recibe un objeto tipo pinacoteca y lo inserta en la tabla Pinacotecas en sus columnas correspondientes.
     * @param pinacoteca
     */
    @Override
    public void darAlta(Pinacoteca pinacoteca) {
        String sql = "INSERT INTO pinacotecas (nombre, ciudad, direccion, metrosCuadrados) VALUES (?,?,?,?)";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pinacoteca.getNombre());
            preparedStatement.setString(2, pinacoteca.getCiudad());
            preparedStatement.setString(3, pinacoteca.getDireccion());
            preparedStatement.setString(4, pinacoteca.getMetrosCuadrados());
            preparedStatement.executeUpdate();

            System.out.println("Dar de alta Pinacoteca correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear tabla PINACOTECA" + e.getMessage());
        }
    }

    /**
     * Lee fila por fila de pinacotecas y crea un objeto pinacoteca con sus datos y lo guarda en una lista.
     * @return devuelve una lista de pinacoteca
     */
    @Override
    public List<Pinacoteca> listarTodos() {
        String sql = "SELECT * FROM pinacotecas";
        List<Pinacoteca> listaPinacoteca = new ArrayList<>();

        try (Connection connection = ConexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            while (resultSet.next()) {
                Pinacoteca pinacoteca = new Pinacoteca();
                pinacoteca.setNombre(resultSet.getString("nombre"));
                pinacoteca.setCiudad(resultSet.getString("ciudad"));
                pinacoteca.setDireccion(resultSet.getString("direccion"));
                pinacoteca.setMetrosCuadrados(resultSet.getString("metrosCuadrados"));
                listaPinacoteca.add(pinacoteca);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tabla PINACOTECA" + e.getMessage());
        }
        return listaPinacoteca;
    }

    /**
     * Busca en la tabla pinacotecas el nombre de la pinacoteca y si lo encuentra crea un objeto con sus datos.
     * @param nombre
     * @return devuelve un objeto pinacoteca
     */
    @Override
    public Pinacoteca buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM pinacotecas WHERE nombre = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pinacoteca pinacoteca = new Pinacoteca();
                    pinacoteca.setNombre(resultSet.getString("nombre"));
                    pinacoteca.setCiudad(resultSet.getString("ciudad"));
                    pinacoteca.setDireccion(resultSet.getString("direccion"));
                    pinacoteca.setMetrosCuadrados(resultSet.getString("metrosCuadrados"));
                    return pinacoteca;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar tabla PINACOTECA" + e.getMessage());
        }
        return null;
    }

    /**
     * Recibe un objeto pinacoteca con los cambio ya hechos y lo actualiza en la tabla donde estaba la pinacoteca
     * que queriamos actualizar.
     * @param pinacoteca
     */
    @Override
    public void actualizar(Pinacoteca pinacoteca) {
        String sql = "UPDATE pinacotecas SET ciudad = ?,direccion=?, metrosCuadrados=? WHERE nombre = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, pinacoteca.getCiudad());
            preparedStatement.setString(2, pinacoteca.getDireccion());
            preparedStatement.setString(3, pinacoteca.getMetrosCuadrados());
            preparedStatement.setString(4, pinacoteca.getNombre());
            preparedStatement.execute();

            System.out.println("Actualizando PINACOTECA: " + pinacoteca.getNombre());
        } catch (Exception e) {
            System.out.println("Error al actualizar tabla PINACOTECA" + e.getMessage());
        }
    }

    /**
     * Elimina la pinacoteca de la tabla pinacotecas.
     * @param nombre
     */
    @Override
    public void eliminar(String nombre) {
        String sql = "DELETE FROM pinacotecas WHERE nombre = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.execute();

            System.out.println("Eliminando PINACOTECA: " + nombre);
        } catch (Exception e) {
            System.out.println("Error al eliminar tabla PINACOTECA" + e.getMessage());
        }
    }
}
