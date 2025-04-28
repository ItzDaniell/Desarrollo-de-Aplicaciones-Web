<%@ page import="java.util.List" %>
<%@ page import="com.tecsup.demo.model.Curso" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de Cursos</title>
</head>
<body>
<%
    List<Curso> listaCursos = (List<Curso>) request.getAttribute("listaCursos");
    if (listaCursos != null && !listaCursos.isEmpty()) {
%>
<div class="container" style="margin-top: 70px;">
    <div class="row mt-5">
        <jsp:include page="master.jsp" />

        <h1>Mantenimiento de Cursos</h1>
        <div style="padding: 10px;">
            <button class="btn btn-danger">
                <a class="nav-link  link-light" aria-current="page" href="agregar.jsp">Nuevo Curso</a>
            </button>
        </div>

        <table class="table table-dark table-hover">
            <thead>
            <tr align="center">
                <th>CODIGO</th>
                <th>NOMBRE</th>
                <th>CREDITOS</th>
                <th>ACCIONES</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Curso curso : listaCursos) {
            %>
            <tr>
                <td><%= curso.getCodigo() %></td>
                <td><%= curso.getNombre() %></td>
                <td><%= curso.getCreditos() %></td>
                <td>
                    <!-- Botón de eliminar -->
                    <a class="btn btn-warning" href="cController?action=mostrarFormularioEliminar&id=<%= curso.getCodigo() %>">
                        <i class="fa-solid fa-trash"></i> Borrar
                    </a>
                    <!-- Botón de actualizar -->
                    <a class="btn btn-danger" href="cController?action=mostrarFormularioActualizar&id=<%= curso.getCodigo() %>">
                        <i class="fa-solid fa-pencil"></i> Actualizar
                    </a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
<%
} else {
%>
<div class="alert alert-warning" role="alert">
    No hay cursos disponibles.
</div>
<%
    }
%>
</body>
</html>
