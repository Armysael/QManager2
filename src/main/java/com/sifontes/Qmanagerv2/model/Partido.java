package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.PartidoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Partido {

    @Transient
    public static final String SEQUENCE_NAME="partido_sequence";

    @Id
    @NotNull
    private long id;
    private String nombre;
    @DateTimeFormat
    private LocalDateTime dateTime;
    private List<Equipo> equipos;
    private Integer resultado;

    public Partido() {
    }

    public Partido(PartidoDto partidoDto) {
        this.id = partidoDto.getId();
        this.nombre = partidoDto.getNombre();
        this.dateTime = partidoDto.getDateTime();
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
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
        this.equipos = new ArrayList<>(equipos);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getResultado() {  return resultado; }
    public void setResultado(Integer resultado) { this.resultado = resultado;}

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        append(nombre).
                        append(dateTime).
                        append(equipos).
                        append(resultado).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Partido))
            return false;
        if (obj == this)
            return true;

        Partido rhs = (Partido) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombre, rhs.nombre).
                        append(dateTime, rhs.dateTime).
                        append(equipos, rhs.equipos).
                        append(resultado, rhs.resultado).
                        isEquals();
    }
}
