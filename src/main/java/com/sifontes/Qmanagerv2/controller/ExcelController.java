package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.service.excel.QuinielaExcelCreatorEngine;
import com.sifontes.Qmanagerv2.service.excel.QuinielaExcelReaderEngine;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/excel")
public class ExcelController {

    @Autowired
    QuinielaExcelCreatorEngine quinielaExcelCreatorEngine;

    @Autowired
    QuinielaExcelReaderEngine quinielaExcelReaderEngine;

    @GetMapping("/crearExcel")
    public void createExcel(@RequestBody EventoDto eventoDto){

        quinielaExcelCreatorEngine.constructExcel("", eventoDto);

    }

    @CrossOrigin
    @GetMapping("/leerExcel/{nombre}")
    public JsonMessage leerExcel(@PathVariable(required = true) String nombre)  {

        return quinielaExcelReaderEngine.testRead(nombre);
    }
}
