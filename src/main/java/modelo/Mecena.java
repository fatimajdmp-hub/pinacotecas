package modelo;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class Mecena {
    //Atributo
    private String nombre;
    private String fecha;
    private String pais;
    private String ciudadNacimiento;
    private String fechaDeFuncion;

    //Construcutores

    public Mecena(String nombre, String fecha, String pais, String ciudadNacimiento, String fechaDeFuncion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.pais = pais;
        this.ciudadNacimiento = ciudadNacimiento;
        this.fechaDeFuncion = fechaDeFuncion;
    }

    public Mecena() {
    }
    //Geter y Seter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getFechaDeFuncion() {
        return fechaDeFuncion;
    }

    public void setFechaDeFuncion(String fecahDeFuncion) {
        this.fechaDeFuncion = fecahDeFuncion;
    }

    //Metodos propios
    @Override
    public String toString() {
        return "nombre=" + nombre + ", fecha=" + fecha + ", pais=" + pais + ", ciudadNacimiento=" + ciudadNacimiento + ", fechaDeFuncion=" + fechaDeFuncion;
    }
}
