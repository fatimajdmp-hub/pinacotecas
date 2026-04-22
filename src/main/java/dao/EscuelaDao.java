package dao;

import modelo.Escuela;

import java.util.List;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.21
 */

public interface EscuelaDao {
    void crearEscuela();

    void insertarEscuela(Escuela escuela);

    List<Escuela> listarEscuelas();

    void actualizarEscuela(Escuela escuela);

    void eliminarEscuela(Escuela escuela);

}
