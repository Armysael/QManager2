package com.sifontes.Qmanagerv2.service.excel;

import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.service.PartidoServiceImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class QuinielaExcelReaderEngine {

    List<PartidoDto> listPartido = new ArrayList<>();
    private static final int INICIO_TABLA=6;
    private static final int INICIO_CELDA=3;

    @Autowired
    PartidoServiceImpl partidoService;


    public void testRead() throws IOException, InvalidFormatException {

        File file = new File("F://QManager2/Evento titulo.xlsx");//TODO: ver como pasarle el path
        XSSFWorkbook workbook = getWorkbook(file);

        readSheet(workbook);
    }

    private XSSFWorkbook getWorkbook(File file) throws IOException, InvalidFormatException {

        return new XSSFWorkbook(file);
    }

    private void readSheet(XSSFWorkbook workbook){

       XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFCell celdaEvento = sheet.getRow(2).getCell(1);
        XSSFCell celdaNombre = sheet.getRow(3).getCell(1);
        XSSFCell celdaApellido = sheet.getRow(4).getCell(1);
        XSSFCell celdaCedula = sheet.getRow(5).getCell(1);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() > INICIO_TABLA) {
                PartidoDto elementById = partidoService.findElementById(((long) row.getCell(INICIO_CELDA).getNumericCellValue()));

                listPartido.add(elementById);
            }
        }

        for (PartidoDto part: listPartido ) {
            System.out.println(part.getNombre()+"  -  "+part.getListaEquipo().get(0).getId());
        }
    }

}
