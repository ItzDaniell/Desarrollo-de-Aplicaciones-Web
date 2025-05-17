package com.tecsup.demo.controller;

import com.tecsup.demo.daos.CategoriaDao;
import com.tecsup.demo.daos.ProductoDao;
import com.tecsup.demo.model.Categoria;
import com.tecsup.demo.model.Producto;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.List;

@WebServlet(name = "ProductoController", urlPatterns = {"/pController"})
public class ProductoServlet extends HttpServlet {
    private ProductoDao productoDao;
    private CategoriaDao categoriaDao;
    @Override
    public void init() throws ServletException {
        productoDao = new ProductoDao();
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
                listarProductos(request, response);
                break;
            case "buscar":
                buscarProductos(request, response);
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
                listarProductos(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "agregar":
                insertarProducto(request, response);
                break;
            case "actualizar":
                actualizarProducto(request, response);
                break;
            case "eliminar":
                eliminarProducto(request, response);
                break;
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> lista = productoDao.listaProducto();
        request.setAttribute("productos", lista);
        request.getRequestDispatcher("/producto/listar.jsp").forward(request, response);
    }

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("txtNombre");
        List<Producto> lista = productoDao.buscarPorNombreProducto(nombre);
        request.setAttribute("productos", lista);
        request.getRequestDispatcher("/producto/listar.jsp").forward(request, response);
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producto p = new Producto();
        p.setCodigo(Integer.parseInt(request.getParameter("txtCodigo")));
        p.setNombre(request.getParameter("txtNombre"));
        p.setDescripcion(request.getParameter("txtDescripcion"));
        p.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
        p.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));

        Categoria c = new Categoria();
        c.setIdCategoria(Integer.parseInt(request.getParameter("categoria_id")));
        p.setCategoria(c);

        productoDao.insertarProducto(p);
        response.sendRedirect("pController?action=listar");
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        String nombre = request.getParameter("txtNombre");
        String descripcion = request.getParameter("txtDescripcion");
        double precio = Double.parseDouble(request.getParameter("txtPrecio"));
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        producto.setCategoria(categoria);

        productoDao.actualizarProducto(producto);

        response.sendRedirect("pController?action=listar");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        productoDao.eliminarProducto(codigo);
        response.sendRedirect("pController?action=listar");
    }

    private void formularioAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> categorias = categoriaDao.listarCategoria();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/producto/agregar.jsp").forward(request, response);
    }
    private void formularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        Producto producto = productoDao.buscarPorCodigoProducto(codigo);
        List<Categoria> categorias = categoriaDao.listarCategoria();
        request.setAttribute("producto", producto);
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/producto/editar.jsp").forward(request, response);
    }
    private void formularioEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        Producto producto = productoDao.buscarPorCodigoProducto(codigo);
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("/producto/eliminar.jsp").forward(request, response);
    }
}