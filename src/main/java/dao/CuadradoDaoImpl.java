package dao;

import conexion.ConexionBD;
import modelo.Cuadrado;

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

public class CuadradoDaoImpl implements CuadradoDAO {

    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS CUADRAROS (
                    codigo TEXT NOT NULL,
                    nombre TEXT NOT NULL,
                    dimensiones TEXT,
                    fechaPintado TEXT,
                    tecnica TEXT,
                    nombre_pinacoteca TEXT,
                    nombre_pintor TEXT,
                    PRIMARY KEY (codigo),
                    CONSTRAINT fk_cuadro_pinacoteca
                        FOREIGN KEY (nombre_pinacoteca) REFERENCES PINACOTECAS(nombre)
                        ON DELETE CASCADE ON UPDATE CASCADE,
                    CONSTRAINT fk_cuadro_pintor
                        FOREIGN KEY (nombre_pintor) REFERENCES PINTORES(nombre)
                        ON DELETE CASCADE ON UPDATE CASCADE
                );
                """;
        try (Connection conexion = ConexionBD.getConnection();
             Statement statement = conexion.createStatement()) {

            statement.execute(sql);
            System.out.println("TABLA DE CUADRADO CREADA");
        } catch (Exception e) {
            System.out.println("Error al crear tabla Cuadrado" + e.getMessage());
        }
    }

    @Override
    public void insertarCuadrado(Cuadrado cuadrado) {
        String sql = "INSERT INTO cuadrados(codigo,nombre,dimensiones,fechaPintado,tecnica,tecnicanombre_pinacoteca,nombre_pintor) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cuadrado.getCodigo());
            preparedStatement.setString(2, cuadrado.getNombre());
            preparedStatement.setString(3, cuadrado.getDimensiones());
            preparedStatement.setString(4, cuadrado.getFechaPintado());
            preparedStatement.setString(5, cuadrado.getTecnica());
            preparedStatement.setString(6, cuadrado.getNombre_pinacoteca());
            preparedStatement.setString(7, cuadrado.getNombre_pintor());
            preparedStatement.executeUpdate();
            System.out.println("INSERTADO CORRECTAMENTE");
        } catch (Exception e) {
            System.out.println("Error al insertar cuadrado" + e.getMessage());
        }
    }

    @Override
    public List<Cuadrado> listarCuadrado() {
        String sql = "SELECT * FROM cuadrados";
        List<Cuadrado> listaCuadrado = new ArrayList<>();

        try(Connection connection = ConexionBD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);){

            while (resultSet.next()) {
                Cuadrado cuadrado = new Cuadrado();
                cuadrado.setCodigo(resultSet.getString("codigo"));
                cuadrado.setNombre(resultSet.getString("nombre"));
                cuadrado.setDimensiones(resultSet.getString("dimensiones"));
                cuadrado.setFechaPintado(resultSet.getString("fechaPintado"));
                cuadrado.setTecnica(resultSet.getString("tecnica"));
                cuadrado.setNombre_pinacoteca(resultSet.getString("nombre_pinacoteca"));
                cuadrado.setNombre_pintor(resultSet.getString("nombre_pintor"));
                listaCuadrado.add(cuadrado);
            }
        }catch(Exception e){
            System.out.println("Error al listar cuadrado" + e.getMessage());
        }
        return listaCuadrado;
    }

    @Override
    public void actualizarCuadrado(Cuadrado cuadrado) {
        String sql = "UPDATE cuadrados SET nombre=?, dimensiones=?, fechaPintado=?, tecnica=?, nombre_pinacoteca=?, nombre_pintor=? WHERE codigo=?";

        try (Connection connection = ConexionBD.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, cuadrado.getNombre());
            preparedStatement.setString(2, cuadrado.getDimensiones());
            preparedStatement.setString(3, cuadrado.getFechaPintado());
            preparedStatement.setString(4, cuadrado.getTecnica());
            preparedStatement.setString(5, cuadrado.getNombre_pinacoteca());
            preparedStatement.setString(6, cuadrado.getNombre_pintor());
            preparedStatement.setString(7, cuadrado.getCodigo());
            preparedStatement.execute();

        }catch (Exception e){
            System.out.println("Error al actualizar cuadrado" + e.getMessage());
        }
    }

    @Override
    public void eliminarCuadrado(String codigo) {
        String sql = "DELETE FROM cuadrados WHERE codigo = ?";

        try(Connection connection = ConexionBD.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,codigo);

            preparedStatement.execute();
            System.out.println("Eliminando Cuadrado por codigo "+codigo);
        }catch(Exception e){
            System.out.println("Error al eliminar cuadrado" + e.getMessage());
        }
    }
}
