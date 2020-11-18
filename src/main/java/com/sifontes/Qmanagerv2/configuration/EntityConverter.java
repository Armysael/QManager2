package com.sifontes.Qmanagerv2.configuration;

import com.sifontes.Qmanagerv2.dto.*;
import com.sifontes.Qmanagerv2.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityConverter {

    public EquipoDto equipoEntityToDto(Equipo equipo) {

        return new EquipoDto(equipo);
    }

    public Equipo equipoDtoToEntity(EquipoDto equipoDto) {

        return  new Equipo(equipoDto);
    }

    public PartidoDto partidoEntityToDto(Partido partido) {

        PartidoDto partidoDto = new PartidoDto(partido);
        List<EquipoDto> listaEquipoDto = new ArrayList<>();
        List<Equipo> listaEquipos = partido.getEquipos();

        listaEquipos.forEach(equipo -> listaEquipoDto.add(new EquipoDto(equipo)));

        partidoDto.setListaEquipo(listaEquipoDto);

        return partidoDto;
    }

    public Partido partidoDtoToEntity(PartidoDto partidoDto) {

        Partido partido = new Partido(partidoDto);
        List<EquipoDto> listaEquipoDto = partidoDto.getListaEquipo();
        List<Equipo> listaEquipos = new ArrayList<>();

        listaEquipoDto.forEach(equipoDto -> listaEquipos.add(new Equipo(equipoDto)));

        partido.setEquipos(listaEquipos);

        return partido;
    }

    public Pool poolDtoToEntity(PoolDto poolDto) {

        Pool pool = new Pool(poolDto);

        List<EquipoDto> listaEquipoDto = poolDto.getListaEquipo();
        List<Equipo> listaEquipo = new ArrayList<>();

        listaEquipoDto.forEach(equipoDto -> listaEquipo.add(new Equipo(equipoDto)));

        pool.setListaEquipos(listaEquipo);

        return pool;
    }

    public PoolDto poolEntitytoDto(Pool pool) {

        PoolDto poolDto = new PoolDto(pool);
        List<EquipoDto> listaEquipoDto = new ArrayList<>();
        List<Equipo> listaEquipos = pool.getListaEquipos();

        listaEquipos.forEach(equipo -> listaEquipoDto.add(new EquipoDto(equipo)));

        poolDto.setListaEquipo(listaEquipoDto);

        return poolDto;
    }

    public Evento eventoDtoToEntity(EventoDto eventoDto) {

        Evento evento = new Evento(eventoDto);
        List<Partido> listaPartido = new ArrayList<>();
        List<PartidoDto> listaPartidoDto = eventoDto.getPartidoList();

        listaPartidoDto.forEach(partidoDto -> listaPartido.add(new Partido(partidoDto)));

        evento.setPartidosList(listaPartido);

        return evento;
    }

    public EventoDto eventoEntityToDto(Evento evento) {
        EventoDto eventoDto = new EventoDto(evento);
        List<PartidoDto> listaPartidoDto = new ArrayList<>();
        List<Partido> listaPartido = evento.getPartidosList();

        listaPartido.forEach(partido -> listaPartidoDto.add(new PartidoDto(partido)));

        eventoDto.setPartidoList(listaPartidoDto);

        return eventoDto;
    }

    public Quiniela quinielaDtoToEntity(QuinielaDTO quinielaDTO) {

        Quiniela quiniela = new Quiniela(quinielaDTO);

        quiniela.setDatosPartidoList(quinielaDTO.getInfoQuinielaPartidoDtos() );

        return quiniela;
    }

    public QuinielaDTO quinielaEntityToDto(Quiniela quiniela) {

        QuinielaDTO quinielaDTO = new QuinielaDTO(quiniela);

        quinielaDTO.setInfoQuinielaPartidoDtos(quiniela.getDatosPartidoList());

        return quinielaDTO;
    }


}
