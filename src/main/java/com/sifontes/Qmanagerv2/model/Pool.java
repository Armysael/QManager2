package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.PoolDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document
public class Pool {

    @Transient
    public static final String SEQUENCE_NAME="pool_sequence";

    @Id
    @NotNull
    long id;
    String nombre;
    List<Equipo> listaEquipos;

    public Pool() {
    }

    public Pool(PoolDto poolDto) {
        this.id = poolDto.getId();
        this.nombre = poolDto.getNombre();
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
    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }
    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = new ArrayList<>(listaEquipos);
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        append(nombre).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pool))
            return false;
        if (obj == this)
            return true;

        Pool rhs = (Pool) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombre, rhs.nombre).
                        isEquals();
    }
}
