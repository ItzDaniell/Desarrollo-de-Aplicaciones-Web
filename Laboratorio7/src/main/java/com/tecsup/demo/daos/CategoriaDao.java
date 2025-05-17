package com.tecsup.demo.daos;
import com.tecsup.demo.model.Categoria;
import java.sql.*;
import java.util.*;

public class CategoriaDao {
    private final String url = "jdbc:mysql://localhost:3306/practica";
    private final String user = "root";
    private final String password = "";

    public Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }

    public List<Categoria> listarCategoria(){
        List<Categoria> listaCategoria = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("categoria_id"));
                c.setNombreCategoria(rs.getString("vchNombreCategoria"));
                c.setDescripcionCategoria(rs.getString("vchDescripcionCategoria"));
                listaCategoria.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCategoria;
    }
    public boolean insertarCategoria(Categoria c) {
        String sql = "INSERT INTO categoria (vchNombreCategoria, vchDescripcionCategoria) VALUES (?, ?)";
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, c.getNombreCategoria());
            ps.setString(2, c.getDescripcionCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarCategoria(Categoria c) {
        String sql = "UPDATE categoria SET vchNombreCategoria = ?, vchDescripcionCategoria = ? WHERE categoria_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCategoria());
            ps.setString(2, c.getDescripcionCategoria());
            ps.setInt(3, c.getIdCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean eliminarCategoria(int codigo) {
        String sql = "DELETE FROM categoria WHERE categoria_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Categoria buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM categoria WHERE categoria_id = ?";
        Categoria categoria = null;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("categoria_id"));
                c.setNombreCategoria(rs.getString("vchNombreCategoria"));
                c.setDescripcionCategoria(rs.getString("vchDescripcionCategoria"));
                categoria = c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public List<Categoria> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM categoria WHERE vchNombreCategoria LIKE ?";
        List<Categoria> categorias = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("categoria_id"));
                c.setNombreCategoria(rs.getString("vchNombreCategoria"));
                c.setDescripcionCategoria(rs.getString("vchDescripcionCategoria"));
                categorias.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
