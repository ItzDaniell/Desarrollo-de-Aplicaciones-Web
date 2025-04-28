package com.tecsup.pfr_crud_jakarta.controllers;

import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;
import com.tecsup.pfr_crud_jakarta.services.AlumnoService;
import com.tecsup.pfr_crud_jakarta.services.impl.AlumnoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AlumnoController", urlPatterns = {"/aController", "/sAlumno", "/alumnoController"})
public class AlumnoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Alumno alumno = new Alumno();

        alumno.setCodigo(request.getParameter("txtCodigo"));
        alumno.setNombre(request.getParameter("txtNombre"));
        alumno.setApellido(request.getParameter("txtApellido"));

        String fechaStr = request.getParameter("txtFechaNac");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date fechaNacimiento = sdf.parse(fechaStr);
            alumno.setFecha_nacimiento(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        alumno.setSexo(request.getParameter("txtSexo"));

        System.out.println(alumno);

        AlumnoService servicio = new AlumnoServiceImpl();

        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar":
                servicio.grabar(alumno);
                break;
            case "actualizar":
                servicio.actualizar(alumno);
                break;
            case "eliminar":
                servicio.borrar(alumno.getCodigo());
                break;
        }

        response.sendRedirect("alumnoMan.jsp");
    }
}