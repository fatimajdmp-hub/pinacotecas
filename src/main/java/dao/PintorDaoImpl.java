package dao;

import conexion.ConexionBD;
import modelo.Pintor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class PintorDaoImpl implements PintorDao {
    final static Scanner SC = new Scanner(System.in);

    @Override
     public void crearTabla(){
        String sql = """ 
               
                CREATE TABLE IF NOT EXISTS PINTORES (
                   nombre TEXT NOT NULL,
                   pais TEXT,
                   ciudadNacimiento TEXT,
                   fechaNacimiento TEXT, 
                   fechaDefuncion TEXT,
                   nombre_escuela TEXT,
                   nombre_maestro TEXT,
                   PRIMARY KEY (nombre),
                   CONSTRAINT fk_pintor_escuela
                       FOREIGN KEY (nombre_escuela) REFERENCES ESCUELAS(nombre)
                       ON DELETE SET NULL ON UPDATE CASCADE,
                   CONSTRAINT fk_pintor_maestro
                       FOREIGN KEY (nombre_maestro) REFERENCES PINTORES(nombre)
                       ON DELETE SET NULL ON UPDATE CASCADE
                );
                """;
        try(Connection conexion = ConexionBD.getConnection();
            Statement preparedStatement =  conexion.createStatement()) {
            preparedStatement.execute(sql);
            System.out.println("TABLA DE PINTORES CREADO");
        }catch (Exception e) {
            System.out.println("Error al crear tabla PINTOR" + e.getMessage());
        }
    }

    @Override
    public void insertarPintor(Pintor pintor){
        String sql = "INSERT INTO pintores(nombre,pais,ciudadNacimiento,fechaNacimiento,fechaDefuncion,nombre_escuela,nombre_maestro) VALUES(?,?,?,?,?,?,?)";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,pintor.getNombre());
            preparedStatement.setString(2,pintor.getPais());
            preparedStatement.setString(3,pintor.getCiudadNacimiento());
            preparedStatement.setString(4,pintor.getFechaNacimiento());
            preparedStatement.setString(5,pintor.getFechaDefuncion());
            preparedStatement.setString(6,pintor.getNombre_escuela());
            preparedStatement.setString(7,pintor.getNombre_maestro());
            preparedStatement.execute();
            System.out.println("INSERTADO PINTOR");
        }catch (Exception e) {
            System.out.println("Error al insertar tabla PINTOR" + e.getMessage());
        }
    }

    @Override
    public void actualizarDatosPintor(Pintor pintor){
        String sql = "UPDATE pintores SET pais = ?,ciudadNacimiento = ?,fechaNacimiento = ?,fechaDefuncion = ?,nombre_escuela = ?,nombre_maestro = ? WHERE nombre=?";

        try(Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,pintor.getPais());
            preparedStatement.setString(2,pintor.getCiudadNacimiento());
            preparedStatement.setString(3,pintor.getFechaNacimiento());
            preparedStatement.setString(4,pintor.getFechaDefuncion());
            preparedStatement.setString(5,pintor.getNombre_escuela());
            preparedStatement.setString(6,pintor.getNombre_maestro());
            preparedStatement.setString(7,pintor.getNombre());
            preparedStatement.execute();
            System.out.println("actualizando tabla PINTOR correctamente...");
        } catch (Exception e) {
            System.out.println("Error al actualizar tabla PINTOR" + e.getMessage());
        }
    }

    @Override
    public boolean isMaestro(Pintor pintor) {
        String sql = "SELECT COUNT(*) FROM pintores WHERE nombre_maestro = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            preparedStatement.setString(1, pintor.getNombre());
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (Exception e) {
            System.out.println("Error al comprobar si es MAESTRO: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void eliminarPintor(Pintor pintor) {
        String sql = "DELETE FROM pintores WHERE nombre=?";

        try (Connection connection = ConexionBD.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,pintor.getNombre());
            preparedStatement.execute();
            System.out.println("Eliminando PINTOR correctamente...");
        } catch (SQLException e) {
            System.out.println("Error al eliminar tabla PINTOR" + e.getMessage());
        }

    }

    @Override
    public List<Pintor> listarPintor() {
        List<Pintor> listaPintor = new ArrayList<>();
        String sql = "SELECT * FROM pintores";
        try (Connection connection = ConexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Pintor pintor = new Pintor();
                pintor.setNombre(resultSet.getString("nombre"));
                pintor.setPais(resultSet.getString("pais"));
                pintor.setCiudadNacimiento(resultSet.getString("ciudadNacimiento"));
                pintor.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                pintor.setFechaDefuncion(resultSet.getString("fechaDefuncion"));
                pintor.setNombre_escuela(resultSet.getString("nombre_escuela"));
                pintor.setNombre_maestro(resultSet.getString("nombre_maestro"));
                listaPintor.add(pintor);
            }
        }catch (Exception e) {
            System.out.println("Error al listar tabla PINTOR" + e.getMessage());
        }
        return listaPintor;
    }

}
