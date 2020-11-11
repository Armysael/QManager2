package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.EventoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document
public class Evento {

    @Transient
    public static final String SEQUENCE_NAME="evento_sequence";//TODO: agregarle un campo booleano para definir si el evento esta activo o termino

    @Id
    @NotNull
    private long id;
    private String nombre;
    private List<Partido> partidoList;

    public Evento() {
    }

    public Evento(EventoDto eventoDto) {
        this.id = eventoDto.getId();
        this.nombre = eventoDto.getNombre();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) { this.id = id; }
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
        this.partidoList = new ArrayList<>(partidosList);
    }
}
