package com.tecsup.demo.controller;

import com.tecsup.demo.dao.CursoDAO;
import com.tecsup.demo.model.Curso;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CursoController", urlPatterns = {"/", "/cController", "/sCurso", "/cursoController"})
public class CursoServlet extends HttpServlet {
    private CursoDAO cursoDAO;

    @Override
    public void init() throws ServletException {
        cursoDAO = new CursoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                listarCursos(request, response);
                break;
            case "buscar":
                buscarCurso(request, response);
                break;
            case "mostrarFormularioActualizar":
                mostrarFormularioActualizar(request, response);
                break;
            case "mostrarFormularioEliminar":
                mostrarFormularioEliminar(request, response);
                break;
            default:
                listarCursos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "insertar":
                insertarCurso(request, response);
                break;
            case "actualizar":
                actualizarCurso(request, response);
                break;
            case "eliminar":
                eliminarCurso(request, response);
                break;
            default:
                listarCursos(request, response);
                break;
        }
    }

    private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Curso> listaCurso = cursoDAO.listar();
        request.setAttribute("listaCursos", listaCurso);
        request.getRequestDispatcher("/listar.jsp").forward(request, response);
    }

    private void buscarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Curso curso = cursoDAO.buscarPorCodigo(codigo);
        request.setAttribute("curso", curso);
        request.getRequestDispatcher("/editar.jsp").forward(request, response);
    }

    private void insertarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("txtCodigo");
        String nombre = request.getParameter("txtNombre");
        int creditos = Integer.parseInt(request.getParameter("txtCreditos"));

        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setNombre(nombre);
        curso.setCreditos(creditos);

        cursoDAO.insertar(curso);
        response.sendRedirect("cController?action=listar");
    }

    private void actualizarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        int creditos = Integer.parseInt(request.getParameter("creditos"));

        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setNombre(nombre);
        curso.setCreditos(creditos);

        cursoDAO.actualizar(curso);
        response.sendRedirect("cController?action=listar");
    }

    private void eliminarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("txtCodigo");
        cursoDAO.eliminar(codigo);
        response.sendRedirect("cController?action=listar");
    }

    private void mostrarFormularioActualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("id");
        Curso curso = cursoDAO.buscarPorCodigo(codigo);
        if (curso != null) {
            request.setAttribute("curso", curso);
            request.getRequestDispatcher("/editar.jsp").forward(request, response);
        } else {
            response.sendRedirect("cController?action=listar");
        }
    }
    private void mostrarFormularioEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("id");
        Curso curso = cursoDAO.buscarPorCodigo(codigo);
        if (curso != null) {
            request.setAttribute("curso", curso);
            request.getRequestDispatcher("/eliminar.jsp").forward(request, response);
        } else {
            response.sendRedirect("cController?action=listar");
        }
    }
}
