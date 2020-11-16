package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Partido;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
import com.sifontes.Qmanagerv2.utils.CustomMessages;
import com.sifontes.Qmanagerv2.utils.EnumAcciones;
import com.sifontes.Qmanagerv2.utils.EnumEntidades;
import com.sifontes.Qmanagerv2.validation.PartidoValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartidoServiceImpl implements CrudInterface<PartidoDto>{

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    EntityConverter entityConverter;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    @Autowired
    PartidoValidationImpl partidoValidation;

    @Autowired
    CustomMessages customMessages;


    public List<PartidoDto> findAllElements(){

        List<Partido> listaPartido = partidoRepository.findAll();
        List<PartidoDto> listaPartidoDto = new ArrayList<>();

        listaPartido.stream().forEach( partido -> listaPartidoDto.add(entityConverter.partidoEntityToDto(partido)));

        return listaPartidoDto;
    }


    public JsonMessage addElement(PartidoDto partidoDto){

        Set<EquipoDto> set = new HashSet<>();
        //valido que exista el equipo
        try {
            for (EquipoDto equipoDto : partidoDto.getListaEquipo()) {

                if(!equipoRepository.existsById(equipoDto.getId())){
                    throw new IllegalArgumentException("Id not found");
                }
            }

            if(!partidoValidation.validateBeforeAdd(partidoDto)){
                throw new IllegalArgumentException("Duplicated teams");
            }

            Partido partido = entityConverter.partidoDtoToEntity(partidoDto);
            partido.setId(sequenceGenerator.generateSequence(partido.SEQUENCE_NAME));
            partidoRepository.insert(partido);

            return customMessages.getActionSuccessMessage(EnumEntidades.PARTIDO, EnumAcciones.SAVE);
        }catch (Exception e){

            return customMessages.getActionErrorMessage(EnumEntidades.PARTIDO, EnumAcciones.SAVE, e);
        }
    }


    @Override
    public PartidoDto findElementById(long id) {

        if(!partidoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe Partido");
        }

        Optional<Partido> partido = partidoRepository.findById(id);

        return entityConverter.partidoEntityToDto(partido.get());
    }

    @Override
    public JsonMessage editElement(PartidoDto elementDto) {//TODO: validar  que no este en evento activo

        try {

            for (EquipoDto equipoDto : elementDto.getListaEquipo()) {

                if(!equipoRepository.existsById(equipoDto.getId())){
                    throw new IllegalArgumentException("Id not found");
                }
            }

            if(!partidoValidation.validateBeforeAdd(elementDto)){
                throw new IllegalArgumentException("Duplicated teams");
            }

            Partido partido = entityConverter.partidoDtoToEntity(elementDto);
            partidoRepository.save(partido);
            return customMessages.getActionSuccessMessage(EnumEntidades.PARTIDO, EnumAcciones.EDIT);
        }catch (Exception e){
            return customMessages.getActionErrorMessage(EnumEntidades.PARTIDO, EnumAcciones.EDIT, e);
        }
    }

    @Override
    public JsonMessage deleteElement(long id) {
        try {

            //TODO: validar  que no este en evento activo

            partidoRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.PARTIDO, EnumAcciones.DELETE);
        }catch (Exception e){
            return customMessages.getActionErrorMessage(EnumEntidades.PARTIDO, EnumAcciones.DELETE, e);
        }
    }
}
