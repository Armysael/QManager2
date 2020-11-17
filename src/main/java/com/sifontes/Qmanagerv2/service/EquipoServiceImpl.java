package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import com.sifontes.Qmanagerv2.utils.CustomMessages;
import com.sifontes.Qmanagerv2.utils.EnumAcciones;
import com.sifontes.Qmanagerv2.utils.EnumEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements CrudInterface<EquipoDto> {


    private final EquipoRepository equipoRepository;
    private final EntityConverter entityConverter;
    private final SequenceGeneratorService sequenceGenerator;

    @Autowired
    public EquipoServiceImpl(EquipoRepository equipoRepository, EntityConverter entityConverter, SequenceGeneratorService sequenceGenerator) {
        this.equipoRepository = equipoRepository;
        this.entityConverter = entityConverter;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<EquipoDto> findAllElements() {
        List<EquipoDto> listaEquipo = new ArrayList<>();
        equipoRepository.findAll().forEach(equipo1 -> listaEquipo.add(entityConverter.equipoEntityToDto(equipo1)));
        return listaEquipo;
    }

    @Override
    public JsonMessage addElement(EquipoDto equipodto) {

        CustomMessages customMessages = new CustomMessages();
        try {
            if (findelementByName(equipodto.getNombre())) {
                throw new IllegalArgumentException("equipo con nombre :" + equipodto.getNombre() + " ya existe");
            }

            Equipo equipo = entityConverter.equipoDtoToEntity(equipodto);
            equipo.setId(sequenceGenerator.generateSequence(Equipo.SEQUENCE_NAME));
            equipoRepository.insert(equipo);

            return customMessages.getActionSuccessMessage(EnumEntidades.EQUIPO,EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EQUIPO,EnumAcciones.SAVE, e);
        }
    }

    public void addBulkElements(List<EquipoDto> equipoDtoList){

        for (EquipoDto equipoDto: equipoDtoList) {

            addElement(equipoDto);
        }

    }

    private boolean findelementByName(String name) {

        Equipo equipo = equipoRepository.findByName(name);

        return equipo != null;
    }

    @Override
    public EquipoDto findElementById(long id) {

        Optional<Equipo> equipo = equipoRepository.findById(id);

        if(!equipo.isPresent()) {
            throw new IllegalArgumentException("No existe equipo");
        }else {
            return entityConverter.equipoEntityToDto(equipo.get());
        }
    }

    @Override
    public JsonMessage editElement(EquipoDto elementDto) {
        CustomMessages customMessages = new CustomMessages();
        try {
            Equipo equipo = entityConverter.equipoDtoToEntity(elementDto);
            equipoRepository.save(equipo);
            return customMessages.getActionSuccessMessage(EnumEntidades.EQUIPO,EnumAcciones.EDIT);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EQUIPO,EnumAcciones.EDIT, e);
        }
    }

    @Override
    public JsonMessage deleteElement(long id) {
        CustomMessages customMessages = new CustomMessages();
        try {
            equipoRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.EQUIPO,EnumAcciones.DELETE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.EQUIPO,EnumAcciones.DELETE, e);
        }
    }
}
