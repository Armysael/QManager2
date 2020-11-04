package com.sifontes.Qmanagerv2.dto;

import com.sifontes.Qmanagerv2.model.Quiniela;

import java.util.List;

public class QuinielaDataDTO {

    private long id;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String cedulaUsuario;
    private long eventId;
    private List<InfoQuinielaPartidoDto> infoQuinielaPartidoDtos;

    public QuinielaDataDTO() {
    }

    public QuinielaDataDTO(long id, String nombreUsuario, String apellidoUsuario, String cedulaUsuario, long eventId) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.cedulaUsuario = cedulaUsuario;
        this.eventId = eventId;
    }

    public QuinielaDataDTO(Quiniela quiniela) {
        this.id = quiniela.getId();
        this.nombreUsuario = quiniela.getNombreUsuario();
        this.apellidoUsuario = quiniela.getApellidoUsuario();
        this.cedulaUsuario = quiniela.getCedulaUsuario();
        this.eventId = quiniela.getEventId();
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
        this.infoQuinielaPartidoDtos = infoQuinielaPartidoDtos;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
