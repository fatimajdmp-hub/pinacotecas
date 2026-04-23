package dao;

import modelo.Pintor;

import java.util.List;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public interface PintorDao {

    void crearTabla();

    void insertarPintor(Pintor pintor);

    void actualizarDatosPintor(Pintor pintor);

    boolean isMaestro(Pintor pintor);

    void eliminarPintor(String nombre);

    List<Pintor> listarPintor();

}
