package com.emergentes.medicapp.clases;

public class Medicamento {

    private int id_cita;
    private int id_medicamento;
    private String medicamento;
    private String dosis;
    private String hora_aplicacion;
    private String descrpcion;

    public Medicamento(){};

    public Medicamento(int id_cita,int id_medicamento, String medicamento, String dosis, String hora_aplicacion, String descrpcion) {
        this.id_cita = id_cita;
        this.id_medicamento = id_medicamento;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.hora_aplicacion = hora_aplicacion;
        this.descrpcion = descrpcion;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public Medicamento setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
        return this;
    }

    public int getId_cita() {
        return id_cita;
    }

    public Medicamento setId_cita(int id_cita) {
        this.id_cita = id_cita;
        return this;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public Medicamento setMedicamento(String medicamento) {
        this.medicamento = medicamento;
        return this;
    }

    public String getDosis() {
        return dosis;
    }

    public Medicamento setDosis(String dosis) {
        this.dosis = dosis;
        return this;
    }

    public String getHora_aplicacion() {
        return hora_aplicacion;
    }

    public Medicamento setHora_aplicacion(String hora_aplicacion) {
        this.hora_aplicacion = hora_aplicacion;
        return this;
    }

    public String getDescrpcion() {
        return descrpcion;
    }

    public Medicamento setDescrpcion(String descrpcion) {
        this.descrpcion = descrpcion;
        return this;
    }
}
