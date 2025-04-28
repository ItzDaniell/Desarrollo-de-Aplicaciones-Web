package com.tecsup.pfr_crud_jakarta.model.entities;

public class Administrador {
    private String codigo;
    private String login;
    private String password;
    private String nombres;
    private String apellidos;

    public Administrador(String codigo, String login, String password, String nombres, String apellidos) {
        this.codigo = codigo;
        this.login = login;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    public Administrador() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    @Override
    public String toString() {
        return "Administrador{" +
                "codigo='" + codigo + '\'' +
                ", login='" + login + '\'' +
                ", passwprd='" + password + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
