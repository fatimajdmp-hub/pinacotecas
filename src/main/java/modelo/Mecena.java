package modelo;

/**
 * Clase Mecena
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

    /**
     * Constructor parametrizado
     *
     * @param nombre del mecenas
     * @param fecha de la mecena
     * @param pais de la mecena
     * @param ciudadNacimiento de la mecena
     * @param fechaDeFuncion de la mecena
     */
    public Mecena(String nombre, String fecha, String pais, String ciudadNacimiento, String fechaDeFuncion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.pais = pais;
        this.ciudadNacimiento = ciudadNacimiento;
        this.fechaDeFuncion = fechaDeFuncion;
    }

    /**
     * constructor vacio
     */
    public Mecena() {
    }
    //Geter y Seter

    /**
     * devuelve el nombre de la mecena
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modifica el nombre de la mecena
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve la fecha de la mecena
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * modifica la fecha de la mecena
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * devuelve el pais de la mecena
     * @return
     */
    public String getPais() {
        return pais;
    }

    /**
     * modifica el pais de la mecena
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * devuelve la ciudad de nacimiento del mecena
     * @return
     */
    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    /**
     * modifica la ciudad de nacimiento de la mecena
     * @param ciudadNacimiento
     */
    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    /**
     * devuelde la fecha de defuncion
     * @return
     */
    public String getFechaDeFuncion() {
        return fechaDeFuncion;
    }

    /**
     * modifica la fecha de defuncion
     * @param fecahDeFuncion
     */
    public void setFechaDeFuncion(String fecahDeFuncion) {
        if (fechaDeFuncion == null || !fechaDeFuncion.matches("\\d{2}/\\d{2}/\\d{4}")){
            throw new IllegalArgumentException("Formato incorrecto. Debe ser xx/xx/xxxx");
        }
        this.fechaDeFuncion = fechaDeFuncion;
    }

    //Metodos propios
    @Override
    public String toString() {
        String formato = "%-20s : %s%n";

        return String.format("%n--- DATOS MECENAS ---%n") +
                String.format(formato, "Nombre", nombre) +
                String.format(formato, "Fecha", fecha) +
                String.format(formato, "País", pais) +
                String.format(formato, "Ciudad Nacimiento", ciudadNacimiento) +
                String.format(formato, "Fecha de Defunción", fechaDeFuncion) +
                "----------------------";
    }
}
