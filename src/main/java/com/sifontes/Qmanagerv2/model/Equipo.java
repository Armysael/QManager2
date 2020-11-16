package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
        if (!(obj instanceof Equipo))
            return false;
        if (obj == this)
            return true;

        Equipo rhs = (Equipo) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombre, rhs.nombre).
                        isEquals();
    }
}
