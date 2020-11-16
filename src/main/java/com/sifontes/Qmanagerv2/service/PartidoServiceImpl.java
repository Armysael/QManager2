package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.model.Partido;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
import com.sifontes.Qmanagerv2.validation.PartidoValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public List<PartidoDto> findAllElements(){

        List<Partido> listaPartido = partidoRepository.findAll();
        List<PartidoDto> listaPartidoDto = new ArrayList<>();

        listaPartido.stream().forEach( partido -> listaPartidoDto.add(entityConverter.partidoEntityToDto(partido)));

        return listaPartidoDto;
    }


    public JsonMessage addElement(PartidoDto partidoDto){

     //   List<EquipoDto> listaEquipo = new ArrayList<>();
        //TODO:Validar que no juegue el mismo equipo 2 veces

        //valido que exista el equipo
        try {
            for (EquipoDto equipoDto : partidoDto.getListaEquipo()) {

                if(!equipoRepository.existsById(equipoDto.getId())){
                    throw new IllegalArgumentException("Id not found");
                }/*else{
                    Optional<Equipo> byId = equipoRepository.findById(equipoDto.getId());
                    EquipoDto dto = entityConverter.equipoEntityToDto(byId.get());
                    listaEquipo.add(dto);
                }*/
            }

            if(!partidoValidation.validateBeforeAdd(partidoDto)){
                throw new IllegalArgumentException("Duplicated teams");
            }

        //    partidoDto.setListaEquipo(listaEquipo);
            partidoDto.setDateTime(java.time.LocalDateTime.now());//TODO: esto se va a pasar por parametro eventualmente
            Partido partido = entityConverter.partidoDtoToEntity(partidoDto);
            partido.setId(sequenceGenerator.generateSequence(partido.SEQUENCE_NAME));
            partidoRepository.insert(partido);

            return new JsonMessage(true,"Partido guardada con éxito");
        }catch (Exception e){

            return new JsonMessage("Error guardando Partido:",e);
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
            return new JsonMessage(true,"Partido editado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error editando  Partido:",e);
        }
    }

    @Override
    public JsonMessage deleteElement(long id) {
        try {

            //TODO: validar  que no este en evento activo

            partidoRepository.deleteById(id);
            return new JsonMessage(true,"Partido borrado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error borrando Partido:",e);
        }
    }
}
