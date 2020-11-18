package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.utils.JsonMessage;
import com.sifontes.Qmanagerv2.service.EquipoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/equipo")
public class EquipoController {

    private final EquipoServiceImpl equipoService;

    @Autowired
    public EquipoController(EquipoServiceImpl equipoService) {
        this.equipoService = equipoService;
    }

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
    @PostMapping("/agregarEquipoBulk")
    public void agregarEquipoBulk(@RequestBody List<EquipoDto> listEquipoDto){  equipoService.addBulkElements(listEquipoDto);}

    @CrossOrigin
    @PutMapping("/mostrarEquipos")
    public JsonMessage modificarEquipo(@RequestBody EquipoDto equipodto) {
        return equipoService.editElement(equipodto);
    }

    @GetMapping(path = "/mostrarEquipos/{id}")
    public EquipoDto buscarEquipo(@PathVariable long id) {
        return equipoService.findElementById(id);
    }

    @DeleteMapping(path = "/mostrarEquipos/{id}")
    public JsonMessage borrarEquipo(@PathVariable long id) {
        return equipoService.deleteElement(id);
    }
}
