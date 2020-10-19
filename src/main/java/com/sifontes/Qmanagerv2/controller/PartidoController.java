package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.service.PartidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/partido")
public class PartidoController {

    @Autowired
    PartidoServiceImpl partidoService;

    @GetMapping("/mostrarPartidos")
    public List<PartidoDto> getPartidos(){
        return partidoService.findAllElements();
    }

    @CrossOrigin
    @PostMapping("/agregarPartido")
    public JsonMessage agregarPartido(@RequestBody PartidoDto partidoDto){

        return partidoService.addElement(partidoDto);
    }

    @CrossOrigin
    @PutMapping("/mostrarPartidos")
    public JsonMessage editPartido(@RequestBody PartidoDto partidoDto){

        return partidoService.editElement(partidoDto);
    }

    @GetMapping("/mostrarPartidos/{id}")
    public PartidoDto getPartidosById(@PathVariable(required = true) String id ){
        return partidoService.findElementById(id);
    }

    @DeleteMapping("/mostrarPartidos/{id}")
    public JsonMessage deletePartido(@PathVariable(required = true) String id ){
        return partidoService.deleteElement(id);
    }
}
