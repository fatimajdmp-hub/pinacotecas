package modelo;

/**
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
    public Cuadrado(String codigo, String nombre, String dimensiones, String fechaPintado, String tecnica, String nombre_pinacoteca, String nombre_pintor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dimensiones = dimensiones;
        this.fechaPintado = fechaPintado;
        this.tecnica = tecnica;
        this.nombre_pinacoteca = nombre_pinacoteca;
        this.nombre_pintor = nombre_pintor;
    }

    public Cuadrado() {
    }

    //Geter y Seter
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getFechaPintado() {
        return fechaPintado;
    }

    public void setFechaPintado(String fechaPintado) {
        this.fechaPintado = fechaPintado;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getNombre_pinacoteca() {
        return nombre_pinacoteca;
    }

    public void setNombre_pinacoteca(String nombre_pinacoteca) {
        this.nombre_pinacoteca = nombre_pinacoteca;
    }

    public String getNombre_pintor() {
        return nombre_pintor;
    }

    public void setNombre_pintor(String nombre_pintor) {
        this.nombre_pintor = nombre_pintor;
    }

    //Metodos propios
    @Override
    public String toString() {
        return "codigo=" + codigo + ", nombre=" + nombre + ", dimensiones=" + dimensiones + ", fecha de pintado=" + fechaPintado + ", tecnica=" + tecnica + ", nombre_pinacoteca=" + nombre_pinacoteca + ", nombre_pinacoteca=" + nombre_pinacoteca;
    }
}
