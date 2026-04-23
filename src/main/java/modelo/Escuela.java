package modelo;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class Escuela {
    //Atributo
    private String nombre;
    private String paisAparicion;
    private String fechaDeCreacion;
    //Construcutores

    public Escuela(String nombre, String paisAparicion, String fechaDeCreacion) {
        this.nombre = nombre;
        this.paisAparicion = paisAparicion;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Escuela() {
    }

    //Geter y Seter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisAparicion() {
        return paisAparicion;
    }

    public void setPaisAparicion(String paisAparicion) {
        this.paisAparicion = paisAparicion;
    }

    public String getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(String fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }


    //Metodos propios
    @Override
    public String toString() {
        return "nombre=" + nombre + ", PaisAparicion=" + paisAparicion + ", fechaDeCreacion=" + fechaDeCreacion;
    }
}
