<%@ page import="com.tecsup.demo.model.Curso" %>
<%@ page import="com.tecsup.demo.controller.CursoServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Cu servicio = new CursoServlet();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mantenimiento de Cursos></title>
</head>
<body>

<div class="container" style="margin-top:70px ;">
    <div class="row mt-5">
        <jsp:include page="header.jsp" />

        <h1>Mantenimiento de Cursos!</h1>
        <div style="padding: 10px;">
            <button class="btn btn-danger"><a class="nav-link  link-light" aria-current="page" href="agregar.jsp"> Nuevo Curso</a></button>
        </div>

        <table  class="table table-dark table-hover">
            <tr align="center">
                <th>CODIGO</th>
                <th>NOMBRE</th>
                <th>CREDITOS</th>
                <th>ACCIONES</th>
            </tr>
            <% for (Curso curso : servicio.listar()) { %>
            <tr>
                <td><%=curso.getCodigo() %></td>
                <td><%=curso.getNombre() %></td>
                <td><%=curso.getCreditos() %></td>
                <td><a class="btn btn-warning" href="cursoEliminar.jsp?id=<%=curso.getCodigo() %>">
                    <i class="fa-solid fa-trash"></i>Borrar</a>
                    <a class="btn btn-danger" href="cursoActualizar.jsp?id=<%=curso.getCodigo() %>">
                        <i class="fa-solid fa-pencil"></i>Actualizar</a></td>
            </tr>
            <% } %>
        </table>
    </div></div></body>
</html>