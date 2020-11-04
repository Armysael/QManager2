package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.service.excel.QuinielaExcelCreatorEngine;
import com.sifontes.Qmanagerv2.service.excel.QuinielaExcelReaderEngine;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/excel")
public class ExcelController {

    @Autowired
    QuinielaExcelCreatorEngine quinielaExcelCreatorEngine;

    @Autowired
    QuinielaExcelReaderEngine quinielaExcelReaderEngine;

    @GetMapping("/crearExcel")
    public void createExcel(){

        quinielaExcelCreatorEngine.generarLibro("","Evento titulo");

    }

    @GetMapping("/leerExcel")
    public void leerExcel() throws IOException, InvalidFormatException {

        quinielaExcelReaderEngine.testRead();
    }
}
