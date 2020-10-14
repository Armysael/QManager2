package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/equipo")
public class EquipoService {

    @Autowired
    EquipoRepository equipoRepository;


    @GetMapping(path = "/mostrarEquipos")
    public List<EquipoDto> getEquipos(){

        List<EquipoDto> listaEquipo = new ArrayList<>();
        List<Equipo> equipo =   equipoRepository.findAll();

        equipo.stream().forEach(equipo1 -> listaEquipo.add(new EquipoDto(equipo1)));
            return listaEquipo;
    }

    @CrossOrigin
    @PostMapping("/agregarEquipo")
    public JsonMessage agregarEquipo(@RequestBody EquipoDto equipodto){

        try {
            Equipo equipo = new Equipo();

            equipo.setId(equipodto.getId());
            equipo.setNombre(equipodto.getNombre());
            equipoRepository.save(equipo);
            return new JsonMessage(true,"Respuesta guardada con éxito");
        }catch (Exception e){

            return new JsonMessage("Error guardando la respuesta:",e);
        }

    }

    @GetMapping(path = "/mostrarEquipos/{id}")
    public EquipoDto buscarEquipo(@PathVariable(required = true) String id){
        List<EquipoDto> listaEquipo = new ArrayList<>();
        List<Equipo> equipo =   equipoRepository.findAll();
        equipo.stream().forEach(equipo1 -> listaEquipo.add(new EquipoDto(equipo1)));

        List<EquipoDto> listaEquipo2 =   listaEquipo.stream().filter(equipoDto -> equipoDto.getId().equals(id)).collect(Collectors.toList());

       return listaEquipo2.get(0);
    }
}
