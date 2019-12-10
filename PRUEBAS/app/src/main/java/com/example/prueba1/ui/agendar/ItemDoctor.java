package com.example.prueba1.ui.agendar;

public class ItemDoctor {
    private int id_medico;

    private String corre,nombre,especialidad,cedula,procedencia,telefono,costoxconsulta,direccion,latitud,longitud;

    public ItemDoctor(String corre, String nombre, String especialidad, String cedula, String procedencia, String telefono, String costoxconsulta) {
        this.corre = corre;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.procedencia = procedencia;
        this.telefono = telefono;
        this.costoxconsulta = costoxconsulta;
    }

    public ItemDoctor(int id_medico, String corre, String nombre, String especialidad, String cedula, String procedencia, String telefono, String costoxconsulta,String latitud,String longitud) {
        this.id_medico = id_medico;
        this.corre = corre;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.procedencia = procedencia;
        this.telefono = telefono;
        this.costoxconsulta = costoxconsulta;
        this.latitud = latitud;
        this.longitud=longitud;
    }

    public int getId_medico() {
        return id_medico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getCorre() {
        return corre;
    }

    public void setCorre(String corre) {
        this.corre = corre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCostoxconsulta() {
        return costoxconsulta;
    }

    public void setCostoxconsulta(String costoxconsulta) {
        this.costoxconsulta = costoxconsulta;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
