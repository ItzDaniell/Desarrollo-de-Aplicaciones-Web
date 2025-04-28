package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AdministradorDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Administrador;
import com.tecsup.pfr_crud_jakarta.util.DBConn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDaoCallableStatement implements AdministradorDao {

    private Connection con;
    private ResultSet rs;
    private CallableStatement cst;

    @Override
    public Administrador validar(String user, String password) {
        Administrador administrador = null;

        try(Connection con = DBConn.getConnection()) {
            cst = con.prepareCall("{call sp_login(?,?)}");
            cst.setString(1, user);
            cst.setString(2, password);
            rs = cst.executeQuery();

            if(rs.next()) {
                administrador = new Administrador();
                administrador.setCodigo(rs.getString(1));
                administrador.setLogin(rs.getString(2));
                administrador.setPassword(rs.getString(3));
                administrador.setNombres(rs.getString(4));
                administrador.setApellidos(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return administrador;
    }
}