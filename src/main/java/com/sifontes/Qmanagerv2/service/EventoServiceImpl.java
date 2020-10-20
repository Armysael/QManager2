package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Evento;
import com.sifontes.Qmanagerv2.repository.EventoRepository;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements CrudInterface<EventoDto>{

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EntityConverter entityConverter;

    @Autowired
    PartidoRepository partidoRepository;

    @Override
    public List<EventoDto> findAllElements() {

        List<Evento> listaEventos = eventoRepository.findAll();
        List<EventoDto> listaEventosDto = new ArrayList<>();

        listaEventos.stream().forEach(evento -> listaEventosDto.add(entityConverter.eventoEntityToDto(evento)));
        return listaEventosDto;
    }

    @Override
    public JsonMessage addElement(EventoDto elementDto){

        List<PartidoDto> listaPartidoDto = new ArrayList<>();

        //TODO:Validar que no juegue el mismo equipo 2 veces


        //valido que exista los partidos
        for (PartidoDto partidoDto : elementDto.getPartidoList()) {

            if(!partidoRepository.existsById(partidoDto.getId())){
                throw new IllegalArgumentException("Id not found");
            }
        }

        try {

            Evento evento = entityConverter.eventoDtoToEntity(elementDto);

            eventoRepository.insert(evento);

            return new JsonMessage(true,"Evento guardado con éxito");
        }catch (Exception e){

            return new JsonMessage("Error guardando Evento:",e);
        }
    }

    @Override
    public EventoDto findElementById(String id) {

        if(!eventoRepository.existsById(id)){
            throw new IllegalArgumentException("No existe Evento");
        }
        Optional<Evento> evento = eventoRepository.findById(id);

        return entityConverter.eventoEntityToDto(evento.get());
    }

    @Override
    public JsonMessage editElement(EventoDto elementDto) {

        try {
            Evento evento = entityConverter.eventoDtoToEntity(elementDto);

            eventoRepository.save(evento);

            return new JsonMessage(true,"Evento editado con éxito");
        }catch (Exception e){

            return new JsonMessage("Error editando Evento:",e);
        }
    }

    @Override
    public JsonMessage deleteElement(String id) {
        try {
        eventoRepository.deleteById(id);
            return new JsonMessage(true,"Evento borrado con éxito");
        }catch (Exception e){

            return new JsonMessage("Error borrando Evento:",e);
        }
    }
}
