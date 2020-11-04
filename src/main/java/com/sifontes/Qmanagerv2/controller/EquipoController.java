package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.service.EquipoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/equipo")
public class EquipoController {

    @Autowired
    EquipoServiceImpl equipoService;

    @GetMapping(path = "/mostrarEquipos")
    public List<EquipoDto> getEquipos() {
        return equipoService.findAllElements();
    }

    @CrossOrigin
    @PostMapping("/agregarEquipo")
    public JsonMessage agregarEquipo(@RequestBody EquipoDto equipodto) {
        return equipoService.addElement(equipodto);
    }

    @CrossOrigin
    @PutMapping("/mostrarEquipos")
    public JsonMessage modificarEquipo(@RequestBody EquipoDto equipodto) {
        return equipoService.editElement(equipodto);
    }

    @GetMapping(path = "/mostrarEquipos/{id}")
    public EquipoDto buscarEquipo(@PathVariable(required = true) long id) {
        return equipoService.findElementById(id);
    }

    @DeleteMapping(path = "/mostrarEquipos/{id}")
    public JsonMessage borrarEquipo(@PathVariable(required = true) long id) {
        return equipoService.deleteElement(id);
    }
}
