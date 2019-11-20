package com.emergentes.medicapp.clases;

public class Cita {
    private int idcita;
    private int idpaciente;
    private int idmedico;
    private String fecha;
    private String hora;
    private double latitud;
    private double longitud;
    private String diagnostico;
    private String sintomas;
    private int costo;
    private char tipo_cita;
    private char status;

    public Cita(int idcita, int idpaciente, int idmedico, String fecha, String hora, double latitud, double longitud, String diagnostico, String sintomas, int costo, char tipo_cita, char status) {
        this.idcita = idcita;
        this.idpaciente = idpaciente;
        this.idmedico = idmedico;
        this.fecha = fecha;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.costo = costo;
        this.tipo_cita = tipo_cita;
        this.status = status;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public int getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(int idmedico) {
        this.idmedico = idmedico;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public char getTipo_cita() {
        return tipo_cita;
    }

    public void setTipo_cita(char tipo_cita) {
        this.tipo_cita = tipo_cita;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
