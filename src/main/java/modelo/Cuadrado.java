package modelo;

/**
 * Clase Cuadrado
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.20
 */

public class Cuadrado {
    //Atributo
    private String codigo;
    private String nombre;
    private String dimensiones;
    private String fechaPintado;
    private String tecnica;
    private String nombre_pinacoteca;
    private String nombre_pintor;

    //Construcutores
    /**
     * constructor parametrizado.
     *
     * @param codigo unico para identifiacar el cuadrado
     * @param nombre  del cuadrado
     * @param dimensiones del cuadrado
     * @param fechaPintado fecha cunado se pinto con validacion en el seter
     * @param tecnica del cuadrado
     * @param nombre_pinacoteca nombre de la pinacoteca
     * @param nombre_pintor nombre del pintor que pinto
     */
    public Cuadrado(String codigo, String nombre, String dimensiones, String fechaPintado, String tecnica, String nombre_pinacoteca, String nombre_pintor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dimensiones = dimensiones;
        this.fechaPintado = fechaPintado;
        this.tecnica = tecnica;
        this.nombre_pinacoteca = nombre_pinacoteca;
        this.nombre_pintor = nombre_pintor;
    }
    /**
     * constructor vacio
     */
    public Cuadrado() {
    }

    //Geter y Seter
    /**
     * devuelve el codigo del cuadrado
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * modifica el codigo del cuadrado
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * devuelve el nombre del cuadrado
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modifica el nombre del cuadrado
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve las dimensiones del cuadrado
     * @return
     */
    public String getDimensiones() {
        return dimensiones;
    }

    /**
     * modifica las dimensiones del cuadrado
     * @param dimensiones
     */
    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    /**
     * devuelve la fecha de cuando se pinto el cuadrado
     * @return
     */
    public String getFechaPintado() {
        return fechaPintado;
    }

    /**
     * validacion de la fecha tiene que cumplir con el formato xx/xx/xxxx.
     *
     * @param fechaPintado
     * @throws IllegalAccessException devuelve excepcion si la fecha no cumple el formato
     */
    public void setFechaPintado(String fechaPintado) {
        if (fechaPintado == null || !fechaPintado.matches("\\d{2}/\\d{2}/\\d{4}")){
            throw new IllegalArgumentException("Formato incorrecto. Debe ser xx/xx/xxxx");
        }
        this.fechaPintado = fechaPintado;
    }

    /**
     * devuelve la tecnica que se utilizo para pintar
     * @return
     */
    public String getTecnica() {
        return tecnica;
    }

    /**
     * modifica la tecnica la cual se utilzo para pintar
     * @param tecnica
     */
    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    /**
     * devuelve el nombre de la pinacoteca
     * @return
     */
    public String getNombre_pinacoteca() {
        return nombre_pinacoteca;
    }

    /**
     * modifica el nombre de la pinacoteca
     * @param nombre_pinacoteca
     */
    public void setNombre_pinacoteca(String nombre_pinacoteca) {
        this.nombre_pinacoteca = nombre_pinacoteca;
    }

    /**
     * devuelve el nombre del pintor
     * @return
     */
    public String getNombre_pintor() {
        return nombre_pintor;
    }

    /**
     * modifica el nombre del pintor
     * @param nombre_pintor
     */
    public void setNombre_pintor(String nombre_pintor) {
        this.nombre_pintor = nombre_pintor;
    }

    //Metodos propios
    /**
     * devuelve un toString de la clase cuadrado
     * @return
     */
    @Override
    public String toString() {
        String formato = "%-18s : %s%n";
        return String.format("--- DETALLES DEL CUADRADO ---%n") +
                String.format(formato, "Código", codigo) +
                String.format(formato, "Nombre", nombre) +
                String.format(formato, "Dimensiones", dimensiones) +
                String.format(formato, "Fecha de Pintado", fechaPintado) +
                String.format(formato, "Técnica", tecnica) +
                String.format(formato, "Pinacoteca", nombre_pinacoteca) +
                String.format(formato, "Pintor", nombre_pintor) +
                "-----------------------------";
    }
}
