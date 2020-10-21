package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Document
public class Equipo {

    @Transient
    public static final String SEQUENCE_NAME="equipo_sequence";

    @Id
    @NotNull
    private long id;
    private String nombre;

    public Equipo() {
    }

    public Equipo(EquipoDto equipoDto) {
        this.id = equipoDto.getId();
        this.nombre = equipoDto.getNombre();
    }

    public void setId(long id) { this.id = id; }
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
