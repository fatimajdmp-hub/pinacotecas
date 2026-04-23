package modelo;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class Pintor {
    //Atributo
    private String nombre;
    private String pais;
    private String ciudadNacimiento;
    private String fechaNacimiento;
    private String fechaDefuncion;
    private String nombre_escuela;
    private String nombre_maestro;
    //Construcutores

    public Pintor(String nombre, String pais, String ciudadNacimiento, String fechaNacimiento, String fechaDefuncion, String nombre_escuela, String nombre_maestro) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudadNacimiento = ciudadNacimiento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDefuncion = fechaDefuncion;
        this.nombre_escuela = nombre_escuela;
        this.nombre_maestro = nombre_maestro;
    }

    public Pintor() {
    }

    //Geter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public String getNombre_escuela() {
        return nombre_escuela;
    }

    public void setNombre_escuela(String nombre_escuela) {
        this.nombre_escuela = nombre_escuela;
    }

    public String getNombre_maestro() {
        return nombre_maestro;
    }

    public void setNombre_maestro(String nombre_maestro) {
        this.nombre_maestro = nombre_maestro;
    }

    //Metodos propios
    @Override
    public String toString() {
        return "nombre=" + nombre + ", pais=" + pais + ", ciudad nacimiento=" + ciudadNacimiento + ", fecha nacimiento="
                + fechaNacimiento + ", fecha defuncion=" + fechaDefuncion + ", nombre escuela=" + nombre_escuela + ", nombre maestro=" + nombre_maestro;
    }
}
