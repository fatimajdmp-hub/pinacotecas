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

    /**
     * constructor parametrizado
     *
     * @param nombre del pintor
     * @param pais del pintor
     * @param ciudadNacimiento del pintor
     * @param fechaNacimiento del pintor
     * @param fechaDefuncion del pintor
     * @param nombre_escuela nombre de la escula
     * @param nombre_maestro nombre del maestro(pintor)
     */
    public Pintor(String nombre, String pais, String ciudadNacimiento, String fechaNacimiento, String fechaDefuncion, String nombre_escuela, String nombre_maestro) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudadNacimiento = ciudadNacimiento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDefuncion = fechaDefuncion;
        this.nombre_escuela = nombre_escuela;
        this.nombre_maestro = nombre_maestro;
    }

    /**
     * construcot vacio
     */
    public Pintor() {
    }

    //Geter y Setter

    /**
     * devuelve el nombre del pintor
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modifica el nombre del pintor
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devueklve el nombre del pais del pintor
     * @return
     */
    public String getPais() {
        return pais;
    }

    /**
     * modifica el pais del pintor
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * devuelve la ciudad de nacimiento del pintor
     * @return
     */
    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    /**
     * modifica la ciudad de nacimiento del pintor
     * @param ciudadNacimiento
     */
    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    /**
     * devulve la fehca de nacmiento del pintor
     * @return
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * modifica la fecha de nacimeinto del pintor
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * devuelve la fecha de defuncion del pintor
     * @return
     */
    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    /**
     * modifica la fecha de defuncion del pintor
     * @param fechaDefuncion
     */
    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    /**
     * devuelve el nombre de la escuela
     * @return
     */
    public String getNombre_escuela() {
        return nombre_escuela;
    }

    /**
     * modifica el nombre de la escuela
     * @param nombre_escuela
     */
    public void setNombre_escuela(String nombre_escuela) {
        this.nombre_escuela = nombre_escuela;
    }

    /**
     * devuelve nombre del maestro(pintor)
     * @return
     */
    public String getNombre_maestro() {
        return nombre_maestro;
    }

    /**
     * modifica el nombre del maestro(pintor)
     * @param nombre_maestro
     */
    public void setNombre_maestro(String nombre_maestro) {
        this.nombre_maestro = nombre_maestro;
    }

    //Metodos propios
    @Override
    public String toString() {
        String formato = "%-20s : %s%n";

        return String.format("%n--- DATOS PINTOR ---%n") +
                String.format(formato, "Nombre", nombre) +
                String.format(formato, "País", pais) +
                String.format(formato, "Ciudad Nacimiento", ciudadNacimiento) +
                String.format(formato, "Fecha Nacimiento", fechaNacimiento) +
                String.format(formato, "Fecha Defunción", fechaDefuncion) +
                String.format(formato, "Escuela", nombre_escuela) +
                String.format(formato, "Maestro", nombre_maestro) +
                "-------------------------";
    }
}
