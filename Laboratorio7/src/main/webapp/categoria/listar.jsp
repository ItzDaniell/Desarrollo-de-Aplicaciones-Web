<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Categorías</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../master.jsp" />
<br>
<br>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h3 class="mb-0">Listado de Categorías</h3>
        <a href="cController?action=formularioAgregar" class="btn btn-primary">Agregar Producto</a>
    </div>

    <form class="row g-3 mb-4" action="cController" method="get">
        <input type="hidden" name="action" value="buscar">
        <div class="col-auto">
            <input type="text" name="txtNombreCategoria" class="form-control" placeholder="Buscar categoría..." required>
        </div>
        <div class="col-auto">
            <input type="hidden" name="action" value="buscar"/>
            <button type="submit" class="btn btn-outline-secondary">Buscar</button>
        </div>
    </form>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="table-primary text-center">
            <tr>
                <th>Nombre de Categoría</th>
                <th>Descripción de Categoría</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty categorias}">
                    <c:forEach var="cat" items="${categorias}">
                        <tr>
                            <td>${cat.nombreCategoria}</td>
                            <td>${cat.descripcionCategoria}</td>
                            <td class="text-center">
                                <a href="cController?action=formularioEditar&txtCodigo=${cat.idCategoria}" class="btn btn-warning btn-sm">Editar</a>
                                <a href="cController?action=formularioEliminar&txtCodigo=${cat.idCategoria}" class="btn btn-danger btn-sm">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="3" class="text-center text-muted">No hay categorías registradas.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
