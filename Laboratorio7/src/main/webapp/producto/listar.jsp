<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Productos</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../master.jsp" />
<br>
<br>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h3 class="mb-0">Listado de Productos</h3>
        <a href="pController?action=formularioAgregar" class="btn btn-primary">Agregar Producto</a>
    </div>

    <!-- Formulario de búsqueda -->
    <form class="row g-3 mb-4" action="pController" method="get">
        <input type="hidden" name="action" value="buscar">
        <div class="col-auto">
            <input type="text" name="txtNombre" class="form-control" placeholder="Buscar por nombre..." required>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-outline-secondary">Buscar</button>
        </div>
    </form>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="table-primary text-center">
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Categoría</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty productos}">
                    <c:forEach var="p" items="${productos}">
                        <tr>
                            <td>${p.codigo}</td>
                            <td>${p.nombre}</td>
                            <td>${p.descripcion}</td>
                            <td>${p.precio}</td>
                            <td>${p.cantidad}</td>
                            <td>${p.categoria.nombreCategoria}</td>
                            <td class="text-center">
                                <a href="pController?action=formularioEditar&txtCodigo=${p.codigo}" class="btn btn-warning btn-sm">Editar</a>
                                <a href="pController?action=formularioEliminar&txtCodigo=${p.codigo}" class="btn btn-danger btn-sm">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7" class="text-center text-muted">No hay productos registrados.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
