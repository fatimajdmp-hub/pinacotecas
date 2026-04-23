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
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class MecenaDaoImpl implements MecenaDao {

    @Override
    public void crearTabla() {
        String sqlMecenas = """ 
                CREATE TABLE IF NOT EXISTS MECENAS (
                    nombre TEXT NOT NULL,
                    fecha TEXT,
                    pais TEXT,
                    ciudadNacimiento TEXT,
                    fechaDefuncion TEXT,
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

    @Override
    public void insertarMecena(Mecena mecena) {
        String sql = "INSERT INTO mecenas (nombre,fecha,pais,ciudadNacimiento,fechaDefuncion) VALUES (?,?,?,?)";
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

    @Override
    public void actualizarMecena(Mecena mecena) {
        String sql = "UPDATE mecenas SET fecha =?, pais=?, ciudadNacimiento=?, fechaDefuncion = ? WHERE nombre=?";
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

    @Override
    public void asociarMecenaConPintor(String nombreMecena, String nombrePintor, String relacion) {
        String sql = "INSERT OR REPLACE INTO pintor_mecenas(nombre_mecena, nombre_pintor, relacion) VALUES(?, ?, ?)";
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

    @Override
    public void desvincularMecenaDePintor(String nombreMecena, String nombrePintor) {
        String sql = "DELETE FROM pintor_mecenas WHERE nombre_mecena = ? AND nombre_pintor = ?";
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
