package com.sifontes.Qmanagerv2.service.excel;

import com.sifontes.Qmanagerv2.dto.InfoQuinielaPartidoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import com.sifontes.Qmanagerv2.dto.QuinielaDTO;
import com.sifontes.Qmanagerv2.service.PartidoServiceImpl;
import com.sifontes.Qmanagerv2.service.QuinielaService;
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
    private static final int FIN_CELDA=9;

    @Autowired
    PartidoServiceImpl partidoService;

    @Autowired
    QuinielaService quinielaService;


    public JsonMessage testRead(String nombreArchivo)  {


        File file = new File("../QManager2/"+nombreArchivo+".xlsx");//TODO: ver como pasarle el path
        XSSFWorkbook workbook = null;
        try {
            workbook = getWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonMessage(false,"Error IO");
        } catch (InvalidFormatException e) {

            e.printStackTrace();
            return new JsonMessage(false,"Error Format");
        }

       return readSheet(workbook);
    }

    private XSSFWorkbook getWorkbook(File file) throws IOException, InvalidFormatException {

        return new XSSFWorkbook(file);
    }

    private JsonMessage readSheet(XSSFWorkbook workbook){

       XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFCell celdaEvento = sheet.getRow(2).getCell(1);
        XSSFCell celdaNombre = sheet.getRow(3).getCell(1);
        XSSFCell celdaApellido = sheet.getRow(4).getCell(1);
        XSSFCell celdaCedula = sheet.getRow(5).getCell(1);

        Iterator<Row> rowIterator = sheet.iterator();




        String nombreSave=  celdaNombre.getStringCellValue();
        String apellidoSave=  celdaApellido.getStringCellValue();
        Long cedulaSave=  (long) celdaCedula.getNumericCellValue();
        String eventoSave = celdaEvento.getStringCellValue();
        //long eventoSave=  (long) celdaEvento.getNumericCellValue();

        QuinielaDTO newDto = new QuinielaDTO(0, nombreSave,apellidoSave,cedulaSave.toString(),Long.parseLong(eventoSave));

        List<InfoQuinielaPartidoDto> listaInfo = new ArrayList<>();


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() > INICIO_TABLA) {
                PartidoDto elementById = partidoService.findElementById(((long) row.getCell(INICIO_CELDA).getNumericCellValue()));

                listaInfo.add( new InfoQuinielaPartidoDto(elementById.getId(), (int) row.getCell(FIN_CELDA).getNumericCellValue()));
                listPartido.add(elementById);
            }
        }
        newDto.setInfoQuinielaPartidoDtos(listaInfo);
       /* for (PartidoDto part: listPartido ) {
            System.out.println(part.getNombre()+"  -  "+part.getListaEquipo().get(0).getId());
        }*/

       return saveQuiniela(newDto);
    }


    private JsonMessage saveQuiniela(QuinielaDTO newDto){

       return quinielaService.addElement(newDto);
    }

}
