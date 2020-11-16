package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Pool;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PoolDto implements Serializable {

    private long id;
    private String nombre;
    private List<EquipoDto> listaEquipo;

    public PoolDto() {
    }

    public PoolDto(Pool pool) {
        this.id = pool.getId();
        this.nombre = pool.getNombre();
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
    public List<EquipoDto> getListaEquipo() {
        return listaEquipo;
    }
    public void setListaEquipo(List<EquipoDto> listaEquipoDto) {
        this.listaEquipo = new ArrayList<>(listaEquipoDto);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        append(nombre).
                        append(listaEquipo).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PoolDto))
            return false;
        if (obj == this)
            return true;

        PoolDto rhs = (PoolDto) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombre, rhs.nombre).
                        append(listaEquipo, rhs.listaEquipo).
                        isEquals();
    }
}
