package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Quiniela;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class QuinielaDTO {

    private long id;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String cedulaUsuario;
    private long eventoId;
    private List<InfoQuinielaPartidoDto> infoQuinielaPartidoDtos;

    public QuinielaDTO() {
    }

    public QuinielaDTO(long id, String nombreUsuario, String apellidoUsuario, String cedulaUsuario, long eventoId) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.cedulaUsuario = cedulaUsuario;
        this.eventoId = eventoId;
    }

    public QuinielaDTO(Quiniela quiniela) {
        this.id = quiniela.getId();
        this.nombreUsuario = quiniela.getNombreUsuario();
        this.apellidoUsuario = quiniela.getApellidoUsuario();
        this.cedulaUsuario = quiniela.getCedulaUsuario();
        this.eventoId = quiniela.getEventoId();
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

    public List<InfoQuinielaPartidoDto> getInfoQuinielaPartidoDtos() {
        return infoQuinielaPartidoDtos;
    }

    public void setInfoQuinielaPartidoDtos(List<InfoQuinielaPartidoDto> infoQuinielaPartidoDtos) {
        this.infoQuinielaPartidoDtos =  new ArrayList<>(infoQuinielaPartidoDtos);
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
                        append(apellidoUsuario).
                        append(eventoId).
                        append(infoQuinielaPartidoDtos).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuinielaDTO))
            return false;
        if (obj == this)
            return true;

        QuinielaDTO rhs = (QuinielaDTO) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(id, rhs.id).
                        append(nombreUsuario, rhs.nombreUsuario).
                        append(apellidoUsuario, rhs.apellidoUsuario).
                        append(eventoId, rhs.eventoId).
                        append(infoQuinielaPartidoDtos, rhs.infoQuinielaPartidoDtos).
                        isEquals();
    }
}
