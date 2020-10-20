package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Partido;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
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
    EntityConverter entityConverter;



    public List<PartidoDto> findAllElements(){

        List<Partido> listaPartido = partidoRepository.findAll();
        List<PartidoDto> listaPartidoDto = new ArrayList<>();

        listaPartido.stream().forEach( partido -> listaPartidoDto.add(entityConverter.partidoEntityToDto(partido)));

        return listaPartidoDto;
    }


    public JsonMessage addElement(PartidoDto partidoDto){

        //List<EquipoDto> listaEquiposDto = new ArrayList<>();

        //TODO:Validar que no juegue el mismo equipo 2 veces


        //valido que exista el equipo
     /*   for (EquipoDto equipoDto : partidoDto.getListaEquipo()) {

            if(!equipoRepository.existsById(equipoDto.getId())){
                throw new IllegalArgumentException("Id not found");
            }
            listaEquiposDto.add(equipoDto);
        }
*/
        try {
            Partido partido = entityConverter.partidoDtoToEntity(partidoDto);

            partidoRepository.insert(partido);

            return new JsonMessage(true,"Partido guardada con éxito");
        }catch (Exception e){

            return new JsonMessage("Error guardando Partido:",e);
        }
    }


    @Override
    public PartidoDto findElementById(String id) {

        if(!partidoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe partido");
        }

        Optional<Partido> partido = partidoRepository.findById(id);

        return entityConverter.partidoEntityToDto(partido.get());
    }

    //TODO: seguro se puede simplificar edit y add en una sola funcion
    @Override
    public JsonMessage editElement(PartidoDto elementDto) {

        try {

            Partido partido = entityConverter.partidoDtoToEntity(elementDto);
                    /*
            List<Equipo> listaEquipo = new ArrayList<>();

            partido.setNombre(elementDto.getNombre());//TODO: validar lista equipos
            listaEquipo.addAll(elementDto.getListaEquipo());
            partido.setEquipos(listaEquipo);*/

            partidoRepository.save(partido);
            return new JsonMessage(true,"Equipo editado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error editando  equipo:",e);
        }
    }

    @Override
    public JsonMessage deleteElement(String id) {
        try {
            partidoRepository.deleteById(id);
            return new JsonMessage(true,"Partido borrado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error borrando equipo:",e);
        }
    }
}
