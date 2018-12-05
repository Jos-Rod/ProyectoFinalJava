package sample.model;

import java.sql.Date;

public class Tarea {

    private int idTarea;
    private String titulo;
    private String descripcion;
    private String estado;
    private int idUsuario;
    private String fechaDeAsignacion;

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, String estado, int idUsuario, String fechaDeAsignacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.fechaDeAsignacion = fechaDeAsignacion;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaDeAsignacion() {
        return fechaDeAsignacion;
    }

    public void setFechaDeAsignacion(String fechaDeAsignacion) {
        this.fechaDeAsignacion = fechaDeAsignacion;
    }
}
