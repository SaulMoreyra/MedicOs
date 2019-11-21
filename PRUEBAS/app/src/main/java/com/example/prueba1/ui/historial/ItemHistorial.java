package com.example.prueba1.ui.historial;

public class ItemHistorial {
    private String tipo, nombreAntecedente, descripcion, antiguedad;

    public ItemHistorial(){
    }

    public ItemHistorial(String nombreAntecedente, String descripcion){
        this.nombreAntecedente = nombreAntecedente;
        this.descripcion = descripcion;
    }

    public ItemHistorial(String tipo, String nombreAntecedente, String descripcion, String antiguedad){
        this.tipo = tipo;
        this.nombreAntecedente = nombreAntecedente;
        this.descripcion = descripcion;
        this.antiguedad = antiguedad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreAntecedente() {
        return nombreAntecedente;
    }

    public void setNombreAntecedente(String nombreAntecedente) {
        this.nombreAntecedente = nombreAntecedente;
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
