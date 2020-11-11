package com.sifontes.Qmanagerv2.service.excel;

import com.sifontes.Qmanagerv2.dto.*;
import com.sifontes.Qmanagerv2.model.Partido;
import com.sifontes.Qmanagerv2.service.EquipoServiceImpl;
import com.sifontes.Qmanagerv2.service.EventoServiceImpl;
import com.sifontes.Qmanagerv2.service.PartidoServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QuinielaExcelCreatorEngine {

    @Autowired
    ExcelStyles excelStyles;

    @Autowired
    PartidoServiceImpl partidoService;

    @Autowired
    EquipoServiceImpl equipoService;

    @Autowired
    EventoServiceImpl eventoService;

    private final static String[] titulosTest ={"Id","Fecha","Grupo","Partido","Resultado"};

    private  List<PartidoDto> initData(){

        EquipoDto equipoDto1 = new EquipoDto();
        equipoDto1.setId(1);
        equipoDto1.setNombre("Argentina");

        EquipoDto equipoDto2 = new EquipoDto();
        equipoDto2.setId(2);
        equipoDto2.setNombre("Brasil");

        EquipoDto equipoDto3 = new EquipoDto();
        equipoDto3.setId(3);
        equipoDto3.setNombre("Ecuador");

        EquipoDto equipoDto4 = new EquipoDto();
        equipoDto4.setId(4);
        equipoDto4.setNombre("Venezuela");

        List<EquipoDto> lista1 = new ArrayList<>();
        lista1.add(equipoDto1);
        lista1.add(equipoDto2);

        List<EquipoDto> lista2 = new ArrayList<>();
        lista2.add(equipoDto3);
        lista2.add(equipoDto4);

        PartidoDto partidoDto= new PartidoDto();
        partidoDto.setId(1);
        partidoDto.setNombre("ARG-BRA");
        partidoDto.setListaEquipo(lista1);

        PartidoDto partidoDto2= new PartidoDto();
        partidoDto2.setId(2);
        partidoDto2.setNombre("ECU-VZA");
        partidoDto2.setListaEquipo(lista2);

        List<PartidoDto> partidoList = new ArrayList<>();

        partidoList.add(partidoDto);
        partidoList.add(partidoDto2);

        return partidoList;
    }

    public void constructExcel(String type,EventoDto eventoDto){


        List<PartidoDto> partidoDtoListCompleto = new ArrayList<>();
        List<EquipoDto> listaEquipoCompleto = new ArrayList<>();

        EventoDto elementById = eventoService.findElementById(eventoDto.getId());

        List<PartidoDto> partidoList = elementById.getPartidoList();
        for (PartidoDto ptDto: partidoList ) {
            PartidoDto partido = partidoService.findElementById(ptDto.getId());
            for (EquipoDto equipoParcial : partido.getListaEquipo()) {
                listaEquipoCompleto.add( equipoService.findElementById(equipoParcial.getId()));
            }
            partido.setListaEquipo(listaEquipoCompleto);
            partidoDtoListCompleto.add(partido);

            listaEquipoCompleto.clear();
        }
        elementById.setPartidoList(partidoDtoListCompleto);

        generarLibro(type,elementById);
    }

    public void generarLibro(String type, EventoDto eventoDto){
        Workbook book = createBook(type);

        Map<String, CellStyle> styles = excelStyles.createStyles(book);
        Sheet sheet = createSheet(book, eventoDto.getNombre());

        createTitleRow(sheet, styles, 0, 50, 0, eventoDto.getNombre(), "$A$1:$L$1");

        styles = excelStyles.createStyles(book);
        createInformationCells(sheet,styles);
        createGameHeaderTable(sheet,styles);
        createInfoTableCells(sheet,styles, eventoDto.getPartidoList());
        try {
            createExcelFile(book,eventoDto.getNombre());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Workbook createBook(String type) {
        Workbook wb;

        if (type.equals("-xls")) wb = new HSSFWorkbook();
        else wb = new XSSFWorkbook();

        return wb;
    }

    private Sheet createSheet(Workbook wb, String name) {

        //nombre de la hoja
        Sheet sheet = wb.createSheet(name);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        sheet.protectSheet("QManagerv2"); //se bloquea con excepcion de lo que el usuario puede usar
        return sheet;
    }

    private void createTitleRow(Sheet sheet, Map<String, CellStyle> styles, int rowToWork, int rowHeight,
                                 int cellToWork, String titleText, String mergeRange) {
        //title row
        Row titleRow = sheet.createRow(rowToWork); //fila en la que va a trabajar 0 = 1
        titleRow.setHeightInPoints(rowHeight); //altura de la fila
        Cell titleCell = titleRow.createCell(cellToWork); //celda en la que va a trabajar (0,0) = A1
        titleCell.setCellValue(titleText); //valor que tiene la celda
        titleCell.setCellStyle(styles.get("customTitle")); //llamando style se da color, font y fondo
        sheet.addMergedRegion(CellRangeAddress.valueOf(mergeRange)); //metodo de hoja para fusionar celdas


    }

    private void createExcelFile(Workbook wb, String fileName) throws FileNotFoundException, IOException {
        // Write the output to a file
        String file = fileName+".xls";
        if (wb instanceof XSSFWorkbook) file += "x";
        FileOutputStream out = null;
        out = new FileOutputStream(file);
        wb.write(out);
        out.close();
    }

    private void createGameHeaderTable(Sheet sheet, Map<String, CellStyle> styles) {

        sheet.addMergedRegion(CellRangeAddress.valueOf("$G$7:$I$7"));

        Row row = sheet.createRow(6);
        row.setHeightInPoints(15);
        Cell cell;
        int cellpos = 3;
        for (int j = 0; j < titulosTest.length; j++) {

                cell = row.createCell(cellpos++);
                cell.setCellValue(titulosTest[j]);

            cell.setCellStyle(styles.get("customInfo"));
        }
        cell = row.createCell(cellpos);
        cell.setCellStyle(styles.get("middleCell"));
        cell = row.createCell(++cellpos);
        cell.setCellValue(titulosTest[titulosTest.length-1]);
        cell.setCellStyle(styles.get("customInfo"));

    }

    private void createInfoTableCells(Sheet sheet, Map<String, CellStyle> styles, List<PartidoDto> listaPartido){
    //    List<PartidoDto> listaPartido = initData();


        int rownum = 7;// a partir de que fila va a crear las celdas de uso
        for (int i = 0; i < listaPartido.size(); i++) {
            int posCell=3;
            Row row = sheet.createRow(rownum++);
            row.setHeightInPoints(15);
            for (int j = 0; j < 7; j++) {
                Cell cell = row.createCell(posCell++);
                cell.setCellStyle(styles.get("cell"));

                switch (j){
                    case 0:
                        cell.setCellValue(listaPartido.get(i).getId());
                        break;
                    case 1:
                        cell.setCellValue(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(listaPartido.get(i).getDateTime()));
                        break;
                    case 2:
                        cell.setCellValue("none");
                        break;
                    case 3:
                        cell.setCellValue(listaPartido.get(i).getListaEquipo().get(0).getNombre());
                        break;
                    case 4:
                        cell.setCellValue("vs");
                        break;
                    case 5:
                        cell.setCellValue(listaPartido.get(i).getListaEquipo().get(1).getNombre());
                        break;

                    default:
                        cell.setCellValue("");
                        cell.setCellStyle(styles.get("cellUnlocked"));
                        break;
                }
            }
        }
    }

    private void createInformationCells(Sheet sheet, Map<String, CellStyle> styles){
        Row eventoRow = sheet.createRow(2);
        eventoRow.setHeightInPoints(15); //por alguna razon esta fila se desajusta y hay qu reajustarla
        Row nombreRow = sheet.createRow(3);
        Row apellidoRow = sheet.createRow(4);
        Row cedulaRow = sheet.createRow(5);

        Cell eventoCell = eventoRow.createCell(0);
        Cell nombreCell = nombreRow.createCell(0);
        Cell apellidoCell = apellidoRow.createCell(0);
        Cell cedulaCell = cedulaRow.createCell(0);

        Cell eventoCellValue = eventoRow.createCell(1);
        Cell nombreCellValue = nombreRow.createCell(1);
        Cell apellidoCellValue = apellidoRow.createCell(1);
        Cell cedulaCellValue = cedulaRow.createCell(1);

        eventoCell.setCellStyle(styles.get("customInfo"));
        nombreCell.setCellStyle(styles.get("customInfo"));
        apellidoCell.setCellStyle(styles.get("customInfo"));
        cedulaCell.setCellStyle(styles.get("customInfo"));

        eventoCellValue.setCellStyle(styles.get("cell"));
        nombreCellValue.setCellStyle(styles.get("cellUnlocked"));
        apellidoCellValue.setCellStyle(styles.get("cellUnlocked"));
        cedulaCellValue.setCellStyle(styles.get("cellUnlocked"));

        eventoCell.setCellValue("Evento ID");
        nombreCell.setCellValue("Nombre");
        apellidoCell.setCellValue("Apellido");
        cedulaCell.setCellValue("Cedula");
    }
}
