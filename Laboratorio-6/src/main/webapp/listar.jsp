<%@ page import="java.util.List" %>
<%@ page import="com.tecsup.demo.model.Curso" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de Cursos</title>
</head>
<body>

<h1>Listado de Cursos</h1>

<!-- Botón para ir a agregar nuevo curso -->
<a href="agregar.jsp">Nuevo Curso</a>

<table border="1">
    <tr>
        <th>CODIGO</th>
        <th>NOMBRE</th>
        <th>CREDITOS</th>
        <th>ACCIONES</th>
    </tr>
    <%
        List<Curso> listaCursos = (List<Curso>) request.getAttribute("listaCursos");

        if (listaCursos != null && !listaCursos.isEmpty()) {
            for (Curso curso : listaCursos) {
    %>
    <tr>
        <td><%= curso.getCodigo() %></td>
        <td><%= curso.getNombre() %></td>
        <td><%= curso.getCreditos() %></td>
        <td>
            <a href="cController?action=eliminar&codigo=<%= curso.getCodigo() %>">Borrar</a>
            |
            <a href="cController?action=editar&codigo=<%= curso.getCodigo() %>">Actualizar</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">No hay cursos registrados.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
