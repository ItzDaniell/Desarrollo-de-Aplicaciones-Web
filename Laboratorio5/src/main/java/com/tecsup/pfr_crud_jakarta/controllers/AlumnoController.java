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


package com.tecsup.laboratorio4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/curso")
public class CursoServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/escuela";
    private static final String user = "root";
    private static final String password = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");

        if (action == null) {
            listCursos(out, req);
        } else if (action.equals("delete")) {
            deleteCurso(req, res);
        } else if (action.equals("edit")) {
            editCursoForm(out, req);
        }
    }

    private void listCursos(PrintWriter out, HttpServletRequest req) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, user, password)) {
                String sql = "SELECT * FROM curso";
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery(sql);

                    out.println("<h2>Lista de Cursos</h2>");
                    out.println("<a href='index.html'>Agregar Nuevo Curso</a><br><br>");
                    out.println("<table border='1'>");
                    out.println("<tr><th>ID</th><th>Nombre</th><th>Créditos</th><th>Opciones</th></tr>");

                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getString(1) + "</td>");
                        out.println("<td>" + rs.getString(2) + "</td>");
                        out.println("<td>" + rs.getInt(3) + "</td>");
                        out.println("<td>");
                        out.println("<a href='curso?action=edit&id=" + rs.getString(1) + "'>Editar</a> ");
                        out.println("<a href='curso?action=delete&id=" + rs.getString(1) + "'>Eliminar</a>");
                        out.println("</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }

    private void deleteCurso(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, user, password)) {
                String sql = "DELETE FROM curso WHERE chrCurCodigo = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("curso");
    }

    private void editCursoForm(PrintWriter out, HttpServletRequest req) {
        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, user, password)) {
                String sql = "SELECT * FROM curso WHERE chrCurCodigo = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, id);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        out.println("<h2>Editar Curso</h2>");
                        out.println("<form method='post' action='curso'>");
                        out.println("<input type='hidden' name='id' value='" + rs.getString(1) + "'>");
                        out.println("Nombre: <input type='text' name='nombre' value='" + rs.getString(2) + "' required><br>");
                        out.println("Créditos: <input type='number' name='creditos' value='" + rs.getInt(3) + "' required><br>");
                        out.println("<input type='submit' value='Actualizar'>");
                        out.println("</form>");
                    } else {
                        out.println("Curso no encontrado.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        int creditos = Integer.parseInt(req.getParameter("creditos"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, user, password)) {

                if (idParam == null || idParam.isEmpty()) {
                    String sql = "INSERT INTO curso (vchCurNombre, intCurCreditos) VALUES (?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nombre);
                        pstmt.setInt(2, creditos);
                        pstmt.executeUpdate();
                    }
                } else {
                    String id = req.getParameter("id");
                    String sql = "UPDATE curso SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nombre);
                        pstmt.setInt(2, creditos);
                        pstmt.setString(3, id);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("curso");
    }
}
