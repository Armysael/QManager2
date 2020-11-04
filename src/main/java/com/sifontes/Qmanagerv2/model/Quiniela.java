package com.sifontes.Qmanagerv2.model;

import com.sifontes.Qmanagerv2.dto.InfoQuinielaPartidoDto;
import com.sifontes.Qmanagerv2.dto.QuinielaDataDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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
    private long eventId;

    private List<InfoQuinielaPartidoDto> datosPartidoList;

    public Quiniela() {
    }

    public Quiniela(QuinielaDataDTO quinielaDataDTO) {
        this.id = quinielaDataDTO.getId();
        this.nombreUsuario = quinielaDataDTO.getNombreUsuario();
        this.apellidoUsuario = quinielaDataDTO.getApellidoUsuario();
        this.cedulaUsuario = quinielaDataDTO.getCedulaUsuario();
        this.eventId = quinielaDataDTO.getEventId();
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
        this.datosPartidoList = datosPartidoList;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
