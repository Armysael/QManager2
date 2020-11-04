package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Equipo;

import java.io.Serializable;

public class EquipoDto implements Serializable {

    private long id;
    private String nombre;

    public EquipoDto() {
    }

    public EquipoDto(Equipo equipo) {
        this.id = equipo.getId();
        this.nombre = equipo.getNombre();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
