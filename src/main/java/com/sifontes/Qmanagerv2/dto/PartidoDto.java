package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.model.Partido;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.List;

public class PartidoDto implements Serializable {

    private String id;
    private String nombre;

    @DBRef
    private List<Equipo> listaEquipo;

    public PartidoDto() {
    }


    public PartidoDto(Partido partido) {
        this.id = partido.getId();
        this.nombre = partido.getNombre();
        this.listaEquipo = partido.getEquipos();
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

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }
}
