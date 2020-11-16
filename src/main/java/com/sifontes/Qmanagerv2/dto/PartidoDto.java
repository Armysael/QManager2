package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Partido;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PartidoDto implements Serializable {

    private long id;
    private String nombre;
    private List<EquipoDto> listaEquipo;
    private LocalDateTime dateTime;
    private Integer resultado;

    public PartidoDto() {
    }

    public PartidoDto(Partido partido) {
        this.id = partido.getId();
        this.nombre = partido.getNombre();
        this.dateTime = partido.getDateTime();

    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
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
    public void setListaEquipo(List<EquipoDto> listaEquipo) {
        this.listaEquipo = new ArrayList<>(listaEquipo);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        append(nombre).
                        append(dateTime).
                        append(listaEquipo).
                        append(resultado).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PartidoDto))
            return false;
        if (obj == this)
            return true;

        PartidoDto rhs = (PartidoDto) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombre, rhs.nombre).
                        append(dateTime, rhs.dateTime).
                        append(listaEquipo, rhs.listaEquipo).
                        append(resultado, rhs.resultado).
                        isEquals();
    }
}
