package com.emergentes.medicapp.clases;

public class Observacion {

    private int id_observacion;
    private int id_paciente;
    private String tipo;
    private String descripcion;
    private String antiguedad;

    public Observacion(int id_observacion, int id_paciente,String tipo, String descripcion, String antiguedad) {
        this.id_observacion = id_observacion;
        this.id_paciente = id_paciente;
        this.descripcion = descripcion;
        this.antiguedad = antiguedad;
        this.tipo=tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId_observacion() {
        return id_observacion;
    }

    public void setId_observacion(int id_observacion) {
        this.id_observacion = id_observacion;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }
}
