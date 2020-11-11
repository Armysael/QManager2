package com.sifontes.Qmanagerv2.configuration;

import com.sifontes.Qmanagerv2.dto.*;
import com.sifontes.Qmanagerv2.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityConverter {

    //@Autowired
    // SequenceGeneratorService sequenceGenerator;


    public EquipoDto equipoEntityToDto(Equipo equipo) {

        return new EquipoDto(equipo);
    }

    public Equipo equipoDtoToEntity(EquipoDto equipoDto) {

        Equipo equipo = new Equipo(equipoDto);

        //    equipo.setId(sequenceGenerator.generateSequence(equipo.SEQUENCE_NAME));

        return equipo;
    }

    public PartidoDto partidoEntityToDto(Partido partido) {

        PartidoDto partidoDto = new PartidoDto(partido);
        List<EquipoDto> listaEquipoDto = new ArrayList<>();
        List<Equipo> listaEquipos = partido.getEquipos();

        listaEquipos.stream().forEach(equipo -> listaEquipoDto.add(new EquipoDto(equipo)));

        partidoDto.setListaEquipo(listaEquipoDto);

        return partidoDto;
    }

    public Partido partidoDtoToEntity(PartidoDto partidoDto) {

        Partido partido = new Partido(partidoDto);
        List<EquipoDto> listaEquipoDto = partidoDto.getListaEquipo();
        List<Equipo> listaEquipos = new ArrayList<>();

        listaEquipoDto.stream().forEach(equipoDto -> listaEquipos.add(new Equipo(equipoDto)));

        //  partido.setId(sequenceGenerator.generateSequence(partido.SEQUENCE_NAME));
        partido.setEquipos(listaEquipos);

        return partido;
    }

    public Pool poolDtoToEntity(PoolDto poolDto) {

        Pool pool = new Pool(poolDto);

        List<EquipoDto> listaEquipoDto = poolDto.getListaEquipo();
        List<Equipo> listaEquipo = new ArrayList<>();

        listaEquipoDto.stream().forEach(equipoDto -> listaEquipo.add(new Equipo(equipoDto)));

        //   pool.setId(sequenceGenerator.generateSequence(Pool.SEQUENCE_NAME));
        pool.setListaEquipos(listaEquipo);

        return pool;
    }

    public PoolDto poolEntitytoDto(Pool pool) {

        PoolDto poolDto = new PoolDto(pool);
        List<EquipoDto> listaEquipoDto = new ArrayList<>();
        List<Equipo> listaEquipos = pool.getListaEquipos();

        listaEquipos.stream().forEach(equipo -> listaEquipoDto.add(new EquipoDto(equipo)));

        poolDto.setListaEquipo(listaEquipoDto);

        return poolDto;

    }

    //TODO: Agregar parte de Quiniela
    public Evento eventoDtoToEntity(EventoDto eventoDto) {

        Evento evento = new Evento(eventoDto);
        List<Partido> listaPartido = new ArrayList<>();
        List<PartidoDto> listaPartidoDto = eventoDto.getPartidoList();

        listaPartidoDto.stream().forEach(partidoDto -> listaPartido.add(new Partido(partidoDto)));

        //  evento.setId(sequenceGenerator.generateSequence(evento.SEQUENCE_NAME));
        evento.setPartidosList(listaPartido);

        return evento;
    }

    //TODO: Agregar parte de Quiniela
    public EventoDto eventoEntityToDto(Evento evento) {
        EventoDto eventoDto = new EventoDto(evento);
        List<PartidoDto> listaPartidoDto = new ArrayList<>();
        List<Partido> listaPartido = evento.getPartidosList();

        listaPartido.stream().forEach(partido -> listaPartidoDto.add(new PartidoDto(partido)));

        eventoDto.setPartidoList(listaPartidoDto);

        return eventoDto;
    }

    //TODO: Agregar parte de Quiniela
    public Quiniela quinielaDtoToEntity(QuinielaDTO quinielaDTO) {

        Quiniela quiniela = new Quiniela(quinielaDTO);

        quiniela.setDatosPartidoList(quinielaDTO.getInfoQuinielaPartidoDtos() );

        return quiniela;
    }

    //TODO: desarmar las clases internas que sean lista
    public QuinielaDTO quinielaEntityToDto(Quiniela quiniela) {

        QuinielaDTO quinielaDTO = new QuinielaDTO(quiniela);

        quinielaDTO.setInfoQuinielaPartidoDtos(quiniela.getDatosPartidoList());

        return quinielaDTO;
    }


}
