package com.tecsup.demo.dao;
import com.tecsup.demo.model.Curso;
import java.sql.*;
import java.util.*;
public class CursoDAO {
    private final String url = "jdbc:mysql://localhost:3306/escuela";
    private final String user = "root";
    private final String pass = "";
    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, pass);
    }
    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Curso c = new Curso();
                c.setCodigo(rs.getString("chrCurCodigo"));
                c.setNombre(rs.getString("vchCurNombre"));
                c.setCreditos(rs.getInt("intCurCreditos"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public boolean insertar(Curso curso) {
        String sql = "INSERT INTO curso (chrCurCodigo, vchCurNombre, intCurCreditos) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, curso.getCodigo());
            ps.setString(2, curso.getNombre());
            ps.setInt(3, curso.getCreditos());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean actualizar(Curso curso) {
        String sql = "UPDATE curso SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.setString(3, curso.getCodigo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM curso WHERE chrCurCodigo = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Curso buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM curso WHERE chrCurCodigo = ?";
        Curso curso = null;
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                curso = new Curso();
                curso.setCodigo(rs.getString("chrCurCodigo"));
                curso.setNombre(rs.getString("vchCurNombre"));
                curso.setCreditos(rs.getInt("intCurCreditos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    public List<Curso> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM curso WHERE vchCurNombre LIKE ?";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getString("chrCurCodigo"));
                curso.setNombre(rs.getString("vchCurNombre"));
                curso.setCreditos(rs.getInt("intCurCreditos"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}