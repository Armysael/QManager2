package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.model.Partido;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/partido")
public class PartidoService {

    @Autowired
    PartidoRepository partidoRepository;



    @GetMapping("/mostrarPartidos")
    public List<PartidoDto> getPartidos(){

        List<Partido> listaPartido = partidoRepository.findAll();
        List<PartidoDto> listaPartidoDto = new ArrayList<>();


        listaPartido.stream().forEach( partido -> listaPartidoDto.add(new PartidoDto(partido)));

        return listaPartidoDto;
    }


    @CrossOrigin
    @PostMapping("/agregarPartido")
    public JsonMessage agregarPartido(@RequestBody PartidoDto partidoDto){


        try {
            Partido partido = new Partido();
            partido.setId(partidoDto.getId());
            partido.setNombre(partidoDto.getNombre());
            partido.setEquipos(partidoDto.getListaEquipo());

            partidoRepository.save(partido);

            return new JsonMessage(true,"Respuesta guardada con éxito");
        }catch (Exception e){

            return new JsonMessage("Error guardando la respuesta:",e);
        }
    }
}
