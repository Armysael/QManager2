package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements CrudInterface<EquipoDto>{


    @Autowired
    EquipoRepository equipoRepository;



    public List<EquipoDto> findAllElements(){

        List<EquipoDto> listaEquipo = new ArrayList<>();
        List<Equipo> equipo =   equipoRepository.findAll();

        equipo.stream().forEach(equipo1 -> listaEquipo.add(new EquipoDto(equipo1)));
        return listaEquipo;
    }


    public JsonMessage addElement( EquipoDto equipodto){//TODO: crear engine de auto Id

        try {
            Equipo equipo = new Equipo();

            if(findelementByName(equipodto.getNombre())){
                throw new IllegalArgumentException("equipo con nombre :"+equipodto.getNombre()+" ya existe");
            }
            equipo.setNombre(equipodto.getNombre());
            equipoRepository.insert(equipo);
            return new JsonMessage(true,"Equipo guardado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error guardando  equipo:",e);
        }
    }

    private boolean findelementByName(String name){

        Equipo equipo = equipoRepository.findByName(name);

        if(equipo != null){
            return true;
        }

        return false;
    }

    public EquipoDto findElementById(String id){

        if(!equipoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe partido");
        }

        Optional<Equipo> equipo = equipoRepository.findById(id);

        return new EquipoDto(equipo.get());

    }

    public JsonMessage editElement( EquipoDto elementDto){

        try {

            EquipoDto equipodt = findElementById(elementDto.getId());
            Equipo equipo =  new Equipo(equipodt);
            equipo.setNombre(elementDto.getNombre());
            equipoRepository.save(equipo);
            return new JsonMessage(true,"Equipo editado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error editando  equipo:",e);
        }

    }

    public JsonMessage deleteElement(String id){
        try {
            equipoRepository.deleteById(id);
            return new JsonMessage(true,"Equipo borrado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error borrando equipo:",e);
        }
    }
}
