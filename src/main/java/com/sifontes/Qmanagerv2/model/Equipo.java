package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Document
public class Equipo {

    @Id
    @NotNull
    private String id;

    private String nombre;

    public Equipo() {
    }

    public Equipo(EquipoDto equipoDto) {
        this.id = equipoDto.getId();
        this.nombre = equipoDto.getNombre();
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
}
