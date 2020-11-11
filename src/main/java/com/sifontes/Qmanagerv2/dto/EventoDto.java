package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Evento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventoDto implements Serializable {

    private long id;
    private String nombre;
    private List<PartidoDto> partidoList;

    public EventoDto() {
    }

    public EventoDto(Evento evento) {
        this.id = evento.getId();
        this.nombre = evento.getNombre();
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
    public List<PartidoDto> getPartidoList() {
        return partidoList;
    }
    public void setPartidoList(List<PartidoDto> partidoList) {
        this.partidoList = new ArrayList<>(partidoList);
    }
}
