package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AlumnoDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;
import com.tecsup.pfr_crud_jakarta.util.DBConn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoCallableStatement implements AlumnoDao {
    private Connection con;
    private ResultSet rs;
    private CallableStatement cst;

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = new ArrayList<>();
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_findAll_alumno()}")){
            rs = cst.executeQuery();
            while(rs.next()){
                alumnos.add(new Alumno(rs.getString("chrAluCodigo"), rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"), rs.getDate("dtmAluFechaNac"), rs.getString("chrAluSexo")));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return alumnos;
    }
    @Override
    public void create(Alumno alumno) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_ins_alumno(?,?,?,?,?)}")) {

            cst.setString(1, alumno.getCodigo());
            cst.setString(2, alumno.getNombre());
            cst.setString(3, alumno.getApellido());
            cst.setTimestamp(4, new java.sql.Timestamp(alumno.getFecha_nacimiento().getTime()));
            cst.setString(5, alumno.getSexo());

            cst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Alumno alumno) {
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_upd_alumno(?,?,?,?,?)}")){
            cst.setString(1, alumno.getCodigo());
            cst.setString(2, alumno.getNombre());
            cst.setString(3, alumno.getApellido());
            cst.setTimestamp(4, new java.sql.Timestamp(alumno.getFecha_nacimiento().getTime()));
            cst.setString(5, alumno.getSexo());
            cst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la actualización");
        }
    }

    @Override
    public void delete(String id) {
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_del_alumno(?)}")){
            cst.setString(1, id);
            cst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en la eliminación");
        }
    }

    @Override
    public Alumno find(String id) {
        Alumno alumno = null;
        try(Connection con = DBConn.getConnection();
            CallableStatement cst = con.prepareCall("{call sp_find_alumno(?)}")){
            cst.setString(1, id);
            rs = cst.executeQuery();
            if(rs.next()){
                alumno = new Alumno(rs.getString("chrAluCodigo"), rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"), rs.getDate("dtmAluFechaNac"), rs.getString("chrAluSexo"));
            }
        }catch(SQLException e){
            System.out.println("Error en la consulta");
        }
        return alumno;
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        return List.of();
    }
}
