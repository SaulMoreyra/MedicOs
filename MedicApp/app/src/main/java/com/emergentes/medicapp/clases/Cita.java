package com.emergentes.medicapp.clases;

public class Cita{
    private String nombre;
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
    private String doctor;
    private String cedula_doc;
    private String all;

    public Cita(){}

    public Cita(int idcita, int idpaciente, int idmedico, String nombre, String fecha, String hora,
                double latitud, double longitud, String diagnostico, String sintomas, int costo, char tipo_cita, char status) {
        this.nombre = nombre;
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

    public void setAll(String all){
        this.all = all;
    }

    public String getAll(){
        return all;
    }

    public Cita setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Cita setIdcita(int idcita) {
        this.idcita = idcita;
        return this;
    }

    public Cita setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
        return this;
    }

    public Cita setIdmedico(int idmedico) {
        this.idmedico = idmedico;
        return this;
    }

    public Cita setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public Cita setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public Cita setLatitud(double latitud) {
        this.latitud = latitud;
        return this;
    }

    public Cita setLongitud(double longitud) {
        this.longitud = longitud;
        return this;
    }

    public Cita setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
        return this;
    }

    public Cita setSintomas(String sintomas) {
        this.sintomas = sintomas;
        return this;
    }

    public Cita setCosto(int costo) {
        this.costo = costo;
        return this;
    }

    public Cita setTipo_cita(char tipo_cita) {
        this.tipo_cita = tipo_cita;
        return this;
    }

    public Cita setStatus(char status) {
        this.status = status;
        return this;
    }

    public Cita setDoctor(String doctor) {
        this.doctor = doctor;
        return this;
    }

    public Cita setCedula_doc(String cedula_doc) {
        this.cedula_doc = cedula_doc;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdcita() {
        return idcita;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public int getIdmedico() {
        return idmedico;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public int getCosto() {
        return costo;
    }

    public char getTipo_cita() {
        return tipo_cita;
    }

    public char getStatus() {
        return status;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getCedula_doc() {
        return cedula_doc;
    }
}