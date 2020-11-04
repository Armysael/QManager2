package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.dto.QuinielaDataDTO;
import com.sifontes.Qmanagerv2.service.PartidoServiceImpl;
import com.sifontes.Qmanagerv2.service.QuinielaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/quiniela")
public class QuinielaController {

    @Autowired
    QuinielaService quinielaService;


    @CrossOrigin
    @PostMapping("/addQuiniela")
    public JsonMessage agregarPartido(@RequestBody QuinielaDataDTO quinielaDto) {
        return quinielaService.addElement(quinielaDto);
    }

}
