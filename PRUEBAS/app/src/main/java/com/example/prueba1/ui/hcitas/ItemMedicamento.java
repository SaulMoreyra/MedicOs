package com.example.prueba1.ui.hcitas;

class ItemMedicamento {

  public int id_cita;
  public String medicamento,dosis,horario_aplicacion,descripcion;

    public ItemMedicamento(int id_cita, String medicamento, String dosis, String horario_aplicacion, String descripcion) {
        this.id_cita = id_cita;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.horario_aplicacion = horario_aplicacion;
        this.descripcion = descripcion;
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

    public String getHorario_aplicacion() {
        return horario_aplicacion;
    }

    public void setHorario_aplicacion(String horario_aplicacion) {
        this.horario_aplicacion = horario_aplicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
