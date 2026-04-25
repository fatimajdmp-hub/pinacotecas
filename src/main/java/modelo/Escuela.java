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

    /**
     * constructor parametrizado
     *
     * @param nombre de la escuela
     * @param paisAparicion de donde es
     * @param fechaDeCreacion cunado se creo
     */
    public Escuela(String nombre, String paisAparicion, String fechaDeCreacion) {
        this.nombre = nombre;
        this.paisAparicion = paisAparicion;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    /**
     * constructor vacio
     */
    public Escuela() {
    }

    //Geter y Seter

    /**
     * devuelve el nombre de la escuela
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modifica el nombre de la escuela
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve el pasi de creacion de la escuela
     * @return
     */
    public String getPaisAparicion() {
        return paisAparicion;
    }

    /**
     * modifica el pais de creacion de la escuela
     * @param paisAparicion
     */
    public void setPaisAparicion(String paisAparicion) {
        this.paisAparicion = paisAparicion;
    }

    /**
     * devuelve la fecha de cuando creo de la escuela
     * @return
     */
    public String getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    /**
     * modifica la fecha de creacion de la escuela
     * @param fechaDeCreacion
     */
    public void setFechaDeCreacion(String fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }


    //Metodos propios
    @Override
    public String toString() {
        return "nombre=" + nombre + ", PaisAparicion=" + paisAparicion + ", fechaDeCreacion=" + fechaDeCreacion;
    }
}
