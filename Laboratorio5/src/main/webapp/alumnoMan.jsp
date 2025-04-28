<%@ page import="com.tecsup.pfr_crud_jakarta.model.entities.Administrador" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.model.entities.Alumno" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.services.AlumnoService" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.services.impl.AlumnoServiceImpl" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    HttpSession misesion = request.getSession();
    if(misesion.getAttribute("eladministrador")==null){
        response.sendRedirect("error.jsp");
    }else{
        Administrador adm = (Administrador)misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();
        AlumnoService servicio = new AlumnoServiceImpl();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mantenimiento de Alumnos! <%=nombre %></title>
</head>
<body>

<div class="container" style="margin-top:70px ;">
    <div class="row mt-5">

        <jsp:include page="master.jsp" />

        <h1>Mantenimiento de Alumnos!</h1>
        <div style="padding: 10px;">
            <button class="btn btn-danger"><a class="nav-link  link-light" aria-current="page"
                                              href="alumnoInsertar.jsp"> Nuevo Alumno</a></button>
        </div>

        <table  class="table table-dark table-hover">
            <tr align="center">
                <th>CODIGO</th>
                <th>NOMBRES</th>
                <th>APELLIDOS</th>
                <th>FECHA DE NACIEMIENTO</th>
                <th>SEXO</th>
                <th>ACCIONES</th>
            </tr>
            <% for (Alumno alumno : servicio.listar()) { %>
            <tr>
                <td><%=alumno.getCodigo() %></td>
                <td><%=alumno.getNombre() %></td>
                <td><%=alumno.getApellido() %></td>
                <td><%=alumno.getFecha_nacimiento() %></td>
                <td><%=alumno.getSexo() %></td>
                <td><a class="btn btn-warning" href="alumnoEliminar.jsp?id=<%=alumno.getCodigo() %>">
                    <i class="fa-solid fa-trash"></i>Borrar</a>
                    <a class="btn btn-danger" href="alumnoActualizar.jsp?id=<%=alumno.getCodigo() %>">
                        <i class="fa-solid fa-pencil"></i>Actualizar</a></td>
            </tr>
            <% } %>
        </table>
    </div></div></body>
<% } %>
</html>

