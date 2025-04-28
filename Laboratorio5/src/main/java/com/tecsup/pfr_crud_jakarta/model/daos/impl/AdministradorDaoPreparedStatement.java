package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AdministradorDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Administrador;
import com.tecsup.pfr_crud_jakarta.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministradorDaoPreparedStatement implements AdministradorDao {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public Administrador validar(String user, String password) {
        Administrador administrador = null;
        try(Connection con = DBConn.getConnection()) {
            pst = con.prepareStatement("SELECT * FROM administrador WHERE chrAdmLogin = ? AND chrAdmPassword = ?");
            pst.setString(1, user);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if(rs.next()) {
                administrador = new Administrador();
                administrador.setCodigo(rs.getString(1));
                administrador.setLogin(rs.getString(2));
                administrador.setPassword(rs.getString(3));
                administrador.setNombres(rs.getString(4));
                administrador.setApellidos(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return administrador;
    }
}

