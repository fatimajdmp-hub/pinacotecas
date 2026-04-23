package modelo;

/**
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
    public Pinacoteca(String nombre, String ciudad, String direccion, String metrosCuadrados) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.metrosCuadrados = metrosCuadrados;
    }

    public Pinacoteca() {
    }
    //Geter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(String metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    //Metodos propios
    @Override
    public String toString() {
        return "nombre =" + nombre + ", ciudad=" + ciudad + ", direccion=" + direccion + ",metrosCuadrados= " + metrosCuadrados;
    }
}
