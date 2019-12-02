package com.example.prueba1.ui.hcitas;

public class ItemHcitas {
    private String fecha, hora, diagnostico, sintomas, costo, tipo_cita,nom,tel,esp;
    private int id_cita;
    public ItemHcitas(){
    }

    public ItemHcitas(int id_cita,String fecha, String hora, String diagnostico, String sintomas, String costo, String tipo_cita,String nom,String esp,String tel) {
       this.id_cita=id_cita;
        this.fecha = fecha;
        this.hora = hora;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.costo = costo;
        this.tipo_cita = tipo_cita;
        this.esp=esp;
        this.nom=nom;
        this.tel=tel;
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

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }
}
