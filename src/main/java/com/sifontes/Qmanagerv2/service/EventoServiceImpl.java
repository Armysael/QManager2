package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Evento;
import com.sifontes.Qmanagerv2.repository.EventoRepository;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
import com.sifontes.Qmanagerv2.utils.CustomMessages;
import com.sifontes.Qmanagerv2.utils.EnumAcciones;
import com.sifontes.Qmanagerv2.utils.EnumEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements CrudInterface<EventoDto> {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EntityConverter entityConverter;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    @Autowired
    CustomMessages customMessages;

    @Override
    public List<EventoDto> findAllElements() {

        List<Evento> listaEventos = eventoRepository.findAll();
        List<EventoDto> listaEventosDto = new ArrayList<>();

        listaEventos.stream().forEach(evento -> listaEventosDto.add(entityConverter.eventoEntityToDto(evento)));
        return listaEventosDto;
    }

    @Override
    public JsonMessage addElement(EventoDto elementDto) {

        try {
            //valido que exista los partidos
            for (PartidoDto partidoDto : elementDto.getPartidoList()) {

                if (!partidoRepository.existsById(partidoDto.getId())) {
                    throw new IllegalArgumentException("Id not found");
                }
            }

            Evento evento = entityConverter.eventoDtoToEntity(elementDto);
            evento.setId(sequenceGenerator.generateSequence(evento.SEQUENCE_NAME));
            eventoRepository.insert(evento);

            return customMessages.getActionSuccessMessage(EnumEntidades.EVENTO, EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EVENTO, EnumAcciones.SAVE, e);
        }
    }

    @Override
    public EventoDto findElementById(long id) {

        if (!eventoRepository.existsById(id)) {
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

            return customMessages.getActionSuccessMessage(EnumEntidades.EVENTO, EnumAcciones.EDIT);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EVENTO, EnumAcciones.EDIT, e);
        }
    }

    @Override
    public JsonMessage deleteElement(long id) {
        try {
            eventoRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.EVENTO, EnumAcciones.DELETE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EVENTO, EnumAcciones.DELETE, e);
        }
    }
}
