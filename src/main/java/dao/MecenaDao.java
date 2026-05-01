package dao;

import modelo.Mecena;

import java.util.List;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.21
 */

public interface MecenaDao {

    void crearTabla();

    void insertarMecena(Mecena mecena);

    List<Mecena> listarMecenas();

    void actualizarMecena(Mecena mecena);

    void eliminarMecena(String nombre);

    void asociarMecenaConPintor(String nombreMecena, String nombrePintor, String relacion);

    void desvincularMecenaDePintor(String nombreMecena, String nombrePintor);

}
