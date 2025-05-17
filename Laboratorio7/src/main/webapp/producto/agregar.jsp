<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Producto</title>
</head>
<body>

<jsp:include page="../master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">CREAR PRODUCTO</h3>
                </div>
                <div class="card-body">
                    <form action="pController" method="post">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Código:</label>
                            <input class="form-control" type="text" name="txtCodigo" required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombre:</label>
                            <input class="form-control" type="text" name="txtNombre" required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Descripción:</label>
                            <input class="form-control" type="text" name="txtDescripcion" required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Precio:</label>
                            <input class="form-control" type="number" step="0.01" name="txtPrecio" required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Cantidad:</label>
                            <input class="form-control" type="number" name="txtCantidad" required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Categoría:</label>
                            <select class="form-select" name="categoria_id" required>
                                <option value="">Seleccione una categoría</option>
                                <c:forEach var="c" items="${categorias}">
                                    <option value="${c.idCategoria}">${c.nombreCategoria}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input type="hidden" name="action" value="agregar"/>
                            <input class="btn btn-success" type="submit" value="Insertar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>