package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Evento;
import com.sifontes.Qmanagerv2.repository.EventoRepository;
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

    private final EventoRepository eventoRepository;
    private final EntityConverter entityConverter;
    private final SequenceGeneratorService sequenceGenerator;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository, EntityConverter entityConverter, SequenceGeneratorService sequenceGenerator) {
        this.eventoRepository = eventoRepository;
        this.entityConverter = entityConverter;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<EventoDto> findAllElements() {

        List<EventoDto> listaEventosDto = new ArrayList<>();
        eventoRepository.findAll().forEach(evento -> listaEventosDto.add(entityConverter.eventoEntityToDto(evento)));
        return listaEventosDto;
    }

    @Override
    public JsonMessage addElement(EventoDto elementDto) {

        CustomMessages customMessages = new CustomMessages();
        try {
            Evento evento = entityConverter.eventoDtoToEntity(elementDto);
            evento.setId(sequenceGenerator.generateSequence(Evento.SEQUENCE_NAME));
            eventoRepository.insert(evento);

            return customMessages.getActionSuccessMessage(EnumEntidades.EVENTO, EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EVENTO, EnumAcciones.SAVE, e);
        }
    }

    public JsonMessage addMatchToEvent(EventoDto eventoDto,List<PartidoDto> listaPartido){

        CustomMessages customMessages = new CustomMessages();
        try{
            EventoDto evento = findElementById(eventoDto.getId());
            evento.setPartidoList(listaPartido);
            editElement(evento);

            return customMessages.getActionSuccessMessage(EnumEntidades.EVENTO, EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EVENTO, EnumAcciones.SAVE, e);
        }
    }

    @Override
    public EventoDto findElementById(long id) {

        Optional<Evento> evento = eventoRepository.findById(id);
        if (!evento.isPresent()) {
            throw new IllegalArgumentException("No existe Evento");
        }
        return entityConverter.eventoEntityToDto(evento.get());
    }

    @Override
    public JsonMessage editElement(EventoDto elementDto) {

        CustomMessages customMessages = new CustomMessages();
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
        CustomMessages customMessages = new CustomMessages();
        try {
            eventoRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.EVENTO, EnumAcciones.DELETE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EVENTO, EnumAcciones.DELETE, e);
        }
    }
}
