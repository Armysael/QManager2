package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Equipo;

import java.io.Serializable;

public class EquipoDto implements Serializable {

    private String id;
    private String nombre;

    public EquipoDto(Equipo equipo) {
        this.id = equipo.getId();
        this.nombre = equipo.getNombre();
    }

    public EquipoDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
