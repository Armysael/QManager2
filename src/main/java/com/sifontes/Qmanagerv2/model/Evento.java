package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.EventoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Evento {

    @Id
    @NotNull
    private String id;
    private String nombre;

    @DBRef
    private List<Partido> partidoList;

    public Evento() {
    }

    public Evento(EventoDto eventoDto) {
        this.id = eventoDto.getId();
        this.nombre = eventoDto.getNombre();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Partido> getPartidosList() {
        return partidoList;
    }

    public void setPartidosList(List<Partido> partidosList) {
        this.partidoList = partidosList;
    }
}
