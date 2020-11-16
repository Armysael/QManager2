package com.sifontes.Qmanagerv2.service.excel;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExcelStyles {//TODO: mejorar con patron decorator

    public ExcelStyles(){}

    public  Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<>();

        styles.put("title", setTitleStyle(wb));
        styles.put("header", setHeaderStyle(wb));
        styles.put("cell", setCellStyle(wb));
        styles.put("cellUnlocked", setCellStyleUnlocked(wb));
        styles.put("middleCell", setMiddleCellStyle(wb));
        styles.put("formula",setFormulaStyle(wb));
        styles.put("formula_2",setFormula2Style(wb));
        styles.put("customTitle",setCustomTitleStyle(wb));
        styles.put("customInfo",setCustomInfoStyle(wb));
        styles.put("locked",setLockStyle(wb));

        return styles;
    }

    private CellStyle  setCustomTitleStyle( Workbook wb){

        CellStyle style;
        Font customTitleFont = wb.createFont();
        customTitleFont.setFontHeightInPoints((short)20);
        customTitleFont.setBold(true);
        style = wb.createCellStyle();


        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(customTitleFont);
        style.setWrapText(true);

        return style;
    }

    private CellStyle  setCustomInfoStyle( Workbook wb){

        CellStyle style;
        Font customInfoStyle = wb.createFont();
        customInfoStyle.setFontHeightInPoints((short)11);
        customInfoStyle.setBold(true);

        style = wb.createCellStyle();
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());


        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(customInfoStyle);
        style.setWrapText(true);

        return style;
    }

    private CellStyle setTitleStyle(Workbook wb){

        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBold(true);
        style = wb.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);

        return style;
    }


    private CellStyle setLockStyle(Workbook wb){
        CellStyle style;
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setLocked(true);

        return style;
    }
    private CellStyle setHeaderStyle(Workbook wb){

        CellStyle style;
        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);

        return style;
    }

    private CellStyle setMiddleCellStyle(Workbook wb){

        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        return style;
    }


    private CellStyle setCellStyle(Workbook wb){

        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        return style;
    }

    private CellStyle setCellStyleUnlocked(Workbook wb){

        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLocked(false);

        return style;
    }

    private CellStyle setFormulaStyle(Workbook wb){

        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));

        return style;
    }

    private CellStyle setFormula2Style(Workbook wb){

        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));

        return style;
    }

}
