package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Evento;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventoDto implements Serializable {

    private long id;
    private String nombre;
    private List<PartidoDto> partidoList;

    public EventoDto() {
    }

    public EventoDto(Evento evento) {
        this.id = evento.getId();
        this.nombre = evento.getNombre();
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
    public List<PartidoDto> getPartidoList() {
        return partidoList;
    }
    public void setPartidoList(List<PartidoDto> partidoList) {
        this.partidoList = new ArrayList<>(partidoList);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        append(nombre).
                        append(partidoList).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventoDto))
            return false;
        if (obj == this)
            return true;

        EventoDto rhs = (EventoDto) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombre, rhs.nombre).
                        append(partidoList, rhs.partidoList).
                        isEquals();
    }
}
