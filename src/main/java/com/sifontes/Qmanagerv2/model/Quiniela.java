package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.InfoQuinielaPartidoDto;
import com.sifontes.Qmanagerv2.dto.QuinielaDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document
public class Quiniela {

    @Transient
    public static final String SEQUENCE_NAME="quiniela_sequence";

    @Id
    @NotNull
    private long id;

    private String nombreUsuario;
    private String apellidoUsuario;
    private String cedulaUsuario;
    private long eventoId;

    private List<InfoQuinielaPartidoDto> datosPartidoList;

    public Quiniela() {
    }

    public Quiniela(QuinielaDTO quinielaDTO) {
        this.id = quinielaDTO.getId();
        this.nombreUsuario = quinielaDTO.getNombreUsuario();
        this.apellidoUsuario = quinielaDTO.getApellidoUsuario();
        this.cedulaUsuario = quinielaDTO.getCedulaUsuario();
        this.eventoId = quinielaDTO.getEventoId();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public List<InfoQuinielaPartidoDto> getDatosPartidoList() {
        return datosPartidoList;
    }

    public void setDatosPartidoList(List<InfoQuinielaPartidoDto> datosPartidoList) {
        this.datosPartidoList = new ArrayList<>(datosPartidoList);
    }

    public long getEventoId() {
        return eventoId;
    }

    public void setEventoId(long eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        append(nombreUsuario).
                        append(apellidoUsuario).
                        append(cedulaUsuario).
                        append(eventoId).
                        append(datosPartidoList).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quiniela))
            return false;
        if (obj == this)
            return true;

        Quiniela rhs = (Quiniela) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombreUsuario, rhs.nombreUsuario).
                        append(apellidoUsuario, rhs.apellidoUsuario).
                        append(cedulaUsuario, rhs.cedulaUsuario).
                        append(eventoId, rhs.eventoId).
                        append(datosPartidoList, rhs.datosPartidoList).
                        isEquals();
    }
}
