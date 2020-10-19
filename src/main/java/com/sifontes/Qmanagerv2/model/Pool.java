package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.PoolDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Pool {

    @Id
    @NotNull
    String id;

    String nombre;

    @DBRef
    List<Equipo> listaEquipos;

    public Pool(PoolDto poolDto) {
        this.id = poolDto.getId();
        this.nombre = poolDto.getNombre();

    }

    public Pool() {
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

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }
}
