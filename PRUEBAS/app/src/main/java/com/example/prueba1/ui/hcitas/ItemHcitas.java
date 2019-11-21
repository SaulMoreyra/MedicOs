package com.example.prueba1.ui.hcitas;

public class ItemHcitas {
    private String fecha, hora, diagnostico, sintomas, costo, tipo_cita;

    public ItemHcitas(){
    }

    public ItemHcitas(String fecha, String hora, String diagnostico, String sintomas, String costo, String tipo_cita) {
        this.fecha = fecha;
        this.hora = hora;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.costo = costo;
        this.tipo_cita = tipo_cita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getTipo_cita() {
        return tipo_cita;
    }

    public void setTipo_cita(String tipo_cita) {
        this.tipo_cita = tipo_cita;
    }
}
