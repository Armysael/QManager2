package com.sifontes.Qmanagerv2.configuration;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.dto.PoolDto;
import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.model.Evento;
import com.sifontes.Qmanagerv2.model.Partido;
import com.sifontes.Qmanagerv2.model.Pool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityConverter {


    public PartidoDto partidoEntityToDto(Partido partido){

        PartidoDto partidoDto = new PartidoDto(partido);
        List<EquipoDto> listaEquipoDto = new ArrayList<>();
        List<Equipo> listaEquipos = partido.getEquipos();

        listaEquipos.stream().forEach(equipo -> listaEquipoDto.add( new EquipoDto(equipo)));

        partidoDto.setListaEquipo(listaEquipoDto);

        return partidoDto;
    }

    public Partido partidoDtoToEntity(PartidoDto partidoDto){

        Partido partido = new Partido(partidoDto);
        List<EquipoDto> listaEquipoDto = partidoDto.getListaEquipo();
        List<Equipo> listaEquipos = new ArrayList<>();

        listaEquipoDto.stream().forEach(equipoDto -> listaEquipos.add(new Equipo(equipoDto)));

        partido.setEquipos(listaEquipos);

        return partido;
    }

    public Pool poolDtoToEntity(PoolDto poolDto){

        Pool pool = new Pool(poolDto);

        List<EquipoDto> listaEquipoDto = poolDto.getListaEquipo();
        List<Equipo> listaEquipo = new ArrayList<>();

        listaEquipoDto.stream().forEach(equipoDto -> listaEquipo.add(new Equipo(equipoDto)));

        pool.setListaEquipos(listaEquipo);

        return pool;
    }

    public PoolDto poolEntitytoDto(Pool pool){

        PoolDto poolDto = new PoolDto(pool);
        List<EquipoDto> listaEquipoDto = new ArrayList<>();
        List<Equipo> listaEquipos = pool.getListaEquipos();

        listaEquipos.stream().forEach(equipo -> listaEquipoDto.add( new EquipoDto(equipo)));

        poolDto.setListaEquipo(listaEquipoDto);

        return poolDto;

    }
    //TODO: Agregar parte de Quiniela
    public Evento eventoDtoToEntity(EventoDto eventoDto){

        Evento evento = new Evento(eventoDto);
        List<Partido> listaPartido = new ArrayList<>();
        List<PartidoDto> listaPartidoDto = eventoDto.getPartidoList();

        listaPartidoDto.stream().forEach(partidoDto -> listaPartido.add(new Partido(partidoDto)));

        evento.setPartidosList(listaPartido);

        return evento;
    }
    //TODO: Agregar parte de Quiniela
    public EventoDto eventoEntityToDto(Evento evento){
        EventoDto eventoDto = new EventoDto(evento);
        List<PartidoDto> listaPartidoDto = new ArrayList<>();
        List<Partido> listaPartido = evento.getPartidosList();

        listaPartido.stream().forEach(partido -> listaPartidoDto.add(new PartidoDto(partido)));

        eventoDto.setPartidoList(listaPartidoDto);

        return eventoDto;
    }


}
