package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.utils.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.service.EventoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/evento")
public class EventoController {

    private final EventoServiceImpl eventoService;

    @Autowired
    public EventoController(EventoServiceImpl eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/mostrarEventos")
    public List<EventoDto> getAllEvents() {
        return eventoService.findAllElements();
    }

    @GetMapping("/mostrarEventos/{id}")
    public EventoDto getEventById(@PathVariable long id) {
        return eventoService.findElementById(id);
    }

    @CrossOrigin
    @PostMapping("/agregarEvento")
    public JsonMessage agregarEvento(@RequestBody EventoDto eventoDto) {
        return eventoService.addElement(eventoDto);
    }

    @CrossOrigin
    @PostMapping("/agregarPartidosEvento")
    public JsonMessage agregarPartidoEvento(@RequestBody EventoDto eventoDto, @RequestBody List<PartidoDto> listPartido) {
        return eventoService.addMatchToEvent(eventoDto,listPartido);
    }


    @CrossOrigin
    @PutMapping("/mostrarEventos")
    public JsonMessage editEvento(@RequestBody EventoDto eventoDto) {
        return eventoService.editElement(eventoDto);
    }

    @DeleteMapping("/mostrarEventos/{id}")
    public JsonMessage deleteEvento(@PathVariable long id) {
        return eventoService.deleteElement(id);
    }
}
