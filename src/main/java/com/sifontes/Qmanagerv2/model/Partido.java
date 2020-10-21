package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.PartidoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Partido {

    @Transient
    public static final String SEQUENCE_NAME="partido_sequence";

    @Id
    @NotNull
    private long id;
    private String nombre;
    private List<Equipo> equipos;

    public Partido() {
    }

    public Partido(PartidoDto partidoDto) {
        this.id = partidoDto.getId();
        this.nombre = partidoDto.getNombre();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Equipo> getEquipos() {
        return equipos;
    }
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
