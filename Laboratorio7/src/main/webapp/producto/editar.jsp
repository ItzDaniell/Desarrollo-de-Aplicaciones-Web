<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Actualizar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h3 class="text-center mb-4">Actualizar Producto</h3>
            <form action="pController" method="post">

                <div class="input-group mt-2">
                    <label class="input-group-text">Código</label>
                    <input class="form-control" type="text" name="txtCodigo" value="${producto.codigo}" readonly>
                </div>

                <div class="input-group mt-2">
                    <label class="input-group-text">Nombre</label>
                    <input class="form-control" type="text" name="txtNombre" value="${producto.nombre}" required>
                </div>

                <div class="input-group mt-2">
                    <label class="input-group-text">Descripción</label>
                    <input class="form-control" type="text" name="txtDescripcion" value="${producto.descripcion}">
                </div>

                <div class="input-group mt-2">
                    <label class="input-group-text">Precio</label>
                    <input class="form-control" type="number" step="0.01" name="txtPrecio" value="${producto.precio}" required>
                </div>

                <div class="input-group mt-2">
                    <label class="input-group-text">Cantidad</label>
                    <input class="form-control" type="number" name="txtCantidad" value="${producto.cantidad}" required>
                </div>

                <div class="input-group mt-2">
                    <label class="input-group-text">Categoría</label>
                    <select name="idCategoria" class="form-select" required>
                        <c:forEach var="cat" items="${categorias}">
                            <option value="${cat.idCategoria}" ${cat.idCategoria == producto.categoria.idCategoria ? 'selected' : ''}>
                                    ${cat.nombreCategoria}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mt-4 d-grid gap-2">
                    <input type="hidden" name="action" value="actualizar"/>
                    <button type="submit" class="btn btn-warning">Actualizar</button>
                </div>

            </form>
        </div>
    </div>
</div>

<br><br><br>
</body>
</html>
