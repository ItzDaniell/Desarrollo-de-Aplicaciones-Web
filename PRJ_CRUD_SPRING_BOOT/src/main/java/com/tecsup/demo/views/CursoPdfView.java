package com.tecsup.demo.views;
import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.domain.entities.Curso;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component("curso/ver")
public class CursoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Curso> cursos = (List<Curso>)model.get("cursos");

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = null;

        cell = new PdfPCell(new Phrase("Lista de Cursos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(3);
        //tabla2.setWidths(new float [] {3.5f, 1, 1, 1});
        tabla2.addCell("id");
        tabla2.addCell("nombre");
        tabla2.addCell("creditos");

        for(Curso curso: cursos) {
            tabla2.addCell(""+curso.getId());
            tabla2.addCell(curso.getNombre());
            tabla2.addCell(""+curso.getCreditos());
        }
        document.add(tabla);
        document.add(tabla2);
    }
}
