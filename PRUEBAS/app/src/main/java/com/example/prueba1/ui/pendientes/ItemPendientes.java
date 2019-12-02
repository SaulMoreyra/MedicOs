package com.example.prueba1.ui.pendientes;

public class ItemPendientes {
    private String fechaP, horaP, costoP, tipo_citaP;
    private int id_cita;
    public ItemPendientes(){

    }

    public ItemPendientes(int id_cita,String fechaP, String horaP, String costoP, String tipo_citaP) {
        this.id_cita=id_cita;
        this.fechaP = fechaP;
        this.horaP = horaP;
        this.costoP = costoP;
        this.tipo_citaP = tipo_citaP;
    }

    public String getFechaP() {
        return fechaP;
    }

    public void setFechaP(String fechaP) {
        this.fechaP = fechaP;
    }

    public String getHoraP() {
        return horaP;
    }

    public void setHoraP(String horaP) {
        this.horaP = horaP;
    }

    public String getCostoP() {
        return costoP;
    }

    public void setCostoP(String costoP) {
        this.costoP = costoP;
    }

    public String getTipo_citaP() {
        return tipo_citaP;
    }

    public void setTipo_citaP(String tipo_citaP) {
        this.tipo_citaP = tipo_citaP;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }
}
