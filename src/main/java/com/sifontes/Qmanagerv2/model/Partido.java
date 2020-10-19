package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.PartidoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Partido {

    @Id
    @NotNull
    private String id;

    private String nombre;

    @DBRef
    private List<Equipo> equipos;

    public Partido() {
    }

    public Partido(PartidoDto partidoDto) {
        this.id = partidoDto.getId();
        this.nombre = partidoDto.getNombre();

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

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
