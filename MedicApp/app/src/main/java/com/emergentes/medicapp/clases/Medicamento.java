package com.emergentes.medicapp.clases;

public class Medicamento {

    private int id_cita;
    private String medicamento;
    private String dosis;
    private String hora_aplicacion;
    private String descrpcion;

    public Medicamento(int id_cita, String medicamento, String dosis, String hora_aplicacion, String descrpcion) {
        this.id_cita = id_cita;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.hora_aplicacion = hora_aplicacion;
        this.descrpcion = descrpcion;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getHora_aplicacion() {
        return hora_aplicacion;
    }

    public void setHora_aplicacion(String hora_aplicacion) {
        this.hora_aplicacion = hora_aplicacion;
    }

    public String getDescrpcion() {
        return descrpcion;
    }

    public void setDescrpcion(String descrpcion) {
        this.descrpcion = descrpcion;
    }
}
