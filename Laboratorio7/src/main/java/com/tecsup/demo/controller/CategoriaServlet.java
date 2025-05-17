package com.tecsup.demo.controller;

import com.tecsup.demo.daos.CategoriaDao;
import com.tecsup.demo.daos.ProductoDao;
import com.tecsup.demo.model.Categoria;
import com.tecsup.demo.model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoriaController", urlPatterns = {"/cController"})
public class CategoriaServlet extends HttpServlet {
    private CategoriaDao categoriaDao;
    @Override
    public void init() throws ServletException {
        categoriaDao = new CategoriaDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }
        switch (action) {
            case "listar":
                listarCategorias(request, response);
                break;
            case "buscar":
                buscarCategoria(request, response);
                break;
            case "formularioAgregar":
                formularioAgregar(request, response);
                break;
            case "formularioEditar":
                formularioEditar(request, response);
                break;
            case "formularioEliminar":
                formularioEliminar(request, response);
                break;
            default:
                listarCategorias(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "agregar":
                agregarCategoria(request, response);
                break;
            case "actualizar":
                actualizarCategoria(request, response);
                break;
            case "eliminar":
                eliminarCategoria(request, response);
                break;
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> lista = categoriaDao.listarCategoria();
        request.setAttribute("categorias", lista);
        request.getRequestDispatcher("/categoria/listar.jsp").forward(request, response);
    }

    private void buscarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("txtNombreCategoria");
        List<Categoria> lista = categoriaDao.buscarPorNombre(nombre);
        request.setAttribute("categorias", lista);
        request.getRequestDispatcher("/categoria/listar.jsp").forward(request, response);
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String nombre = request.getParameter("txtNombreCategoria");
        String descripcion = request.getParameter("txtDescripcionCategoria");
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(nombre);
        categoria.setDescripcionCategoria(descripcion);
        categoriaDao.insertarCategoria(categoria);
        response.sendRedirect("cController?action=listar");
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("txtCodigo"));
        String nombre = request.getParameter("txtNombreCategoria");
        String descripcion = request.getParameter("txtDescripcionCategoria");
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(nombre);
        categoria.setDescripcionCategoria(descripcion);
        categoria.setIdCategoria(id);
        categoriaDao.actualizarCategoria(categoria);
        response.sendRedirect("cController?action=listar");
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        categoriaDao.eliminarCategoria(codigo);
        response.sendRedirect("cController?action=listar");
    }

    private void formularioAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/categoria/agregar.jsp").forward(request, response);
    }

    private void formularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        Categoria categoria = categoriaDao.buscarPorCodigo(codigo);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("/categoria/editar.jsp").forward(request, response);
    }

    private void formularioEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        Categoria categoria = categoriaDao.buscarPorCodigo(codigo);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("/categoria/eliminar.jsp").forward(request, response);
    }
}
