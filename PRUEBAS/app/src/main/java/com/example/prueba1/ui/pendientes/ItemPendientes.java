package com.example.prueba1.ui.pendientes;

public class ItemPendientes {
    private String fechaP, horaP, costoP, tipo_citaP;

    public ItemPendientes(){

    }

    public ItemPendientes(String fechaP, String horaP, String costoP, String tipo_citaP) {
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
}
