package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Pool;

import java.io.Serializable;
import java.util.List;

public class PoolDto implements Serializable {

    private long id;
    private String nombre;
    private List<EquipoDto> listaEquipo;

    public PoolDto() {
    }

    public PoolDto(Pool pool) {
        this.id = pool.getId();
        this.nombre = pool.getNombre();
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
    public List<EquipoDto> getListaEquipo() {
        return listaEquipo;
    }
    public void setListaEquipo(List<EquipoDto> listaEquipoDto) {
        this.listaEquipo = listaEquipoDto;
    }
}
