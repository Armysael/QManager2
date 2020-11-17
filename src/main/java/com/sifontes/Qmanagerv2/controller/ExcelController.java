package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.dto.EventoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.service.excel.QuinielaExcelCreatorEngine;
import com.sifontes.Qmanagerv2.service.excel.QuinielaExcelReaderEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/excel")
public class ExcelController {


    private final QuinielaExcelCreatorEngine quinielaExcelCreatorEngine;
    private final QuinielaExcelReaderEngine quinielaExcelReaderEngine;

    @Autowired
    public ExcelController(QuinielaExcelCreatorEngine quinielaExcelCreatorEngine, QuinielaExcelReaderEngine quinielaExcelReaderEngine) {
        this.quinielaExcelCreatorEngine = quinielaExcelCreatorEngine;
        this.quinielaExcelReaderEngine = quinielaExcelReaderEngine;
    }

    @GetMapping("/crearExcel")
    public void createExcel(@RequestBody EventoDto eventoDto){

        quinielaExcelCreatorEngine.constructExcel("", eventoDto);

    }

    @CrossOrigin
    @GetMapping("/leerExcel/{nombre}")
    public JsonMessage leerExcel(@PathVariable String nombre)  {

        return quinielaExcelReaderEngine.testRead(nombre);
    }
}
