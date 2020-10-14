package com.sifontes.Qmanagerv2.model;

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

    public Partido(@NotNull String id, String nombre, List<Equipo> equipos) {
        this.id = id;
        this.nombre = nombre;
        this.equipos = equipos;
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

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
