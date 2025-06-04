package com.tecsup.demo.views;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.domain.entities.Alumno;
import com.tecsup.demo.domain.entities.Curso;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("alumno/ver.xlsx")
public class AlumnoXlsView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"alumno_view.xlsx\"");

        List<Alumno> alumnos = (List<Alumno>)model.get("alumnos");
        Sheet sheet = workbook.createSheet("Lista de Alumnos");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Alumnos");

        row = sheet.createRow(1);
        cell = row.createCell(0);

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(4);
        String[] columnas = {"ID", "Nombre", "Apellido", "Fecha Nacimiento", "Género", "Email"};
        for (int i = 0; i < columnas.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(columnas[i]);
            headerCell.setCellStyle(theaderStyle);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int rownum = 5;

        for(Alumno alumno: alumnos) {
            Row fila = sheet.createRow(rownum ++);
            cell = fila.createCell(0);
            cell.setCellValue(alumno.getId());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(alumno.getNombre());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(alumno.getApellido());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(alumno.getFechaNacimiento() != null ?
                    alumno.getFechaNacimiento().format(formatter) : "N/A");
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(alumno.getGenero());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(5);
            cell.setCellValue(alumno.getEmail());
            cell.setCellStyle(tbodyStyle);
        }
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
