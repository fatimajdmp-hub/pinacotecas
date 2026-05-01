package dao;

import modelo.Cuadrado;

import java.util.List;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public interface CuadradoDAO {
    void crearTabla();

    void insertarCuadrado(Cuadrado cuadrado);

    List<Cuadrado> listarCuadrado();

    void actualizarCuadrado(Cuadrado cuadrado);

    void eliminarCuadrado(String codigo);

}
