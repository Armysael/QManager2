package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.QuinielaDTO;
import com.sifontes.Qmanagerv2.service.QuinielaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quiniela")
public class QuinielaController {

    @Autowired
    QuinielaService quinielaService;

    @CrossOrigin
    @PostMapping("/addQuiniela")
    public JsonMessage agregarQuiniela(@RequestBody QuinielaDTO quinielaDto) {
        return quinielaService.addElement(quinielaDto);
    }

    @GetMapping("/getQuinielaEvent/{id}")
    public List<QuinielaDTO> buscarQuinielaPorEvento(@PathVariable(required = true) long id ){
        return quinielaService.findByEvent(id);
    }

    @GetMapping("/getQuiniela/{id}")
    public QuinielaDTO buscarQuiniela(@PathVariable(required = true) long id ){
        return quinielaService.findElementById(id);
    }

    @CrossOrigin
    @DeleteMapping("/getQuiniela/{id}")
    public JsonMessage borrarQuiniela(@PathVariable(required = true) long id ){
        return quinielaService.deleteElement(id);
    }
}
