package dao;

import modelo.Pinacoteca;

import java.util.List;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public interface PinacotecaDao {
    void crearTabla();

    void darAlta(Pinacoteca pinacoteca);

    List<Pinacoteca> listarTodos();

    Pinacoteca buscarPorNombre(String nombre);

    void actualizar(Pinacoteca pinacoteca);

    void eliminar(String nombre);
}
