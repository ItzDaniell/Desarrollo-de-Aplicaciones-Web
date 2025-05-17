package com.tecsup.demo.daos;

import com.tecsup.demo.model.Categoria;
import com.tecsup.demo.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    private final String url = "jdbc:mysql://localhost:3306/practica";
    private final String user = "root";
    private final String password = "";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }

    public List<Producto> listaProducto() {
        List<Producto> listaProducto = new ArrayList<>();
        String sql = "SELECT p.intCodigo, p.vchNombre, p.vchDescripcion, p.decPrecio, p.intCantidad, " +
                "c.categoria_id, c.vchNombreCategoria " +
                "FROM producto p JOIN categoria c ON p.categoria_id = c.categoria_id";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getInt("intCodigo"));
                p.setNombre(rs.getString("vchNombre"));
                p.setDescripcion(rs.getString("vchDescripcion"));
                p.setPrecio(rs.getDouble("decPrecio"));
                p.setCantidad(rs.getInt("intCantidad"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("categoria_id"));
                categoria.setNombreCategoria(rs.getString("vchNombreCategoria"));
                p.setCategoria(categoria);

                listaProducto.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProducto;
    }

    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO producto (intCodigo, vchNombre, vchDescripcion, decPrecio, intCantidad, categoria_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getCantidad());
            ps.setInt(6, producto.getCategoria().getIdCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET vchNombre = ?, vchDescripcion = ?, decPrecio = ?, intCantidad = ?, categoria_id = ? " +
                "WHERE intCodigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setInt(5, producto.getCategoria().getIdCategoria());
            ps.setInt(6, producto.getCodigo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarProducto(int codigo) {
        String sql = "DELETE FROM producto WHERE intCodigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Producto buscarPorCodigoProducto(int codigo) {
        String sql = "SELECT p.intCodigo, p.vchNombre, p.vchDescripcion, p.decPrecio, p.intCantidad, " +
                "c.categoria_id, c.vchNombreCategoria " +
                "FROM producto p JOIN categoria c ON p.categoria_id = c.categoria_id WHERE p.intCodigo = ?";
        Producto producto = null;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getInt("intCodigo"));
                p.setNombre(rs.getString("vchNombre"));
                p.setDescripcion(rs.getString("vchDescripcion"));
                p.setPrecio(rs.getDouble("decPrecio"));
                p.setCantidad(rs.getInt("intCantidad"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("categoria_id"));
                categoria.setNombreCategoria(rs.getString("vchNombreCategoria"));
                p.setCategoria(categoria);

                producto = p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public List<Producto> buscarPorNombreProducto(String nombre) {
        String sql = "SELECT p.intCodigo, p.vchNombre, p.vchDescripcion, p.decPrecio, p.intCantidad, " +
                "c.categoria_id, c.vchNombreCategoria " +
                "FROM producto p JOIN categoria c ON p.categoria_id = c.categoria_id WHERE p.vchNombre LIKE ?";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getInt("intCodigo"));
                p.setNombre(rs.getString("vchNombre"));
                p.setDescripcion(rs.getString("vchDescripcion"));
                p.setPrecio(rs.getDouble("decPrecio"));
                p.setCantidad(rs.getInt("intCantidad"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("categoria_id"));
                categoria.setNombreCategoria(rs.getString("vchNombreCategoria"));
                p.setCategoria(categoria);

                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
