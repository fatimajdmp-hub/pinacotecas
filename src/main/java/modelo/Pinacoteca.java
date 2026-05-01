package modelo;

/**
 * Clase Pinacoteca
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class Pinacoteca {
    //Atributo
    private String nombre;
    private String ciudad;
    private String direccion;
    private String metrosCuadrados;

    //Construcutores

    /**
     * Constructor parametrizado
     *
     * @param nombre de la pinacoteca
     * @param ciudad de la pinacoteca
     * @param direccion de la pinacoteca
     * @param metrosCuadrados de la pinacoteca
     */
    public Pinacoteca(String nombre, String ciudad, String direccion, String metrosCuadrados) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.metrosCuadrados = metrosCuadrados;
    }

    /**
     * constructor vacio
     */
    public Pinacoteca() {
    }
    //Geter y Setter

    /**
     * devuelve el nombre de la pinacoteca
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modifica el nombre de la pinacoteca
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve el nombre de la ciudad de la pinacoteca
     * @return
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * modifica el nombre de la pinacoteca
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * devuelve la direccion de la pinacoteca
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * modifica la direccion de la pinacoteca
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * devuelve los metros cuadrado de la pinacoteca
     * @return
     */
    public String getMetrosCuadrados() {
        return metrosCuadrados;
    }

    /**
     * modifica los metros cuadrado de la pinacoteca
     * @param metrosCuadrados
     */
    public void setMetrosCuadrados(String metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    //Metodos propios
    @Override
    public String toString() {
        // Usamos un ancho de 18 para que "Metros Cuadrados" encaje bien
        String formato = "%-18s : %s%n";

        return String.format("%n--- DATOS DE LA PINACOTECA---%n") +
                String.format(formato, "Nombre", nombre) +
                String.format(formato, "Ciudad", ciudad) +
                String.format(formato, "Dirección", direccion) +
                String.format(formato, "Metros Cuadrados", metrosCuadrados) +
                "---------------------------------";
    }
}
