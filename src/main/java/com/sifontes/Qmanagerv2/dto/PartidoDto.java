package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Partido;


import java.io.Serializable;
import java.util.List;

public class PartidoDto implements Serializable {

    private long id;
    private String nombre;
    private List<EquipoDto> listaEquipo;
    private Integer resultado;

    public PartidoDto() {
    }

    public PartidoDto(Partido partido) {
        this.id = partido.getId();
        this.nombre = partido.getNombre();
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
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
    public void setListaEquipo(List<EquipoDto> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }
}
