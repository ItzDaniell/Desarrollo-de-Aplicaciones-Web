<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">Eliminar Producto</h3>
                </div>
                <div class="card-body">
                    <form action="pController" method="post">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Código</label>
                            <input class="form-control" type="text" name="txtCodigo" value="${producto.codigo}" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombre</label>
                            <input class="form-control" type="text" name="txtNombre" value="${producto.nombre}" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Descripción</label>
                            <input class="form-control" type="text" name="txtDescripcion" value="${producto.descripcion}" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Precio</label>
                            <input class="form-control" type="text" name="txtPrecio" value="${producto.precio}" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Cantidad</label>
                            <input class="form-control" type="text" name="txtCantidad" value="${producto.cantidad}" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Categoría</label>
                            <input class="form-control" type="text" name="txtCategoria" value="${producto.categoria.nombreCategoria}" readonly>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input type="hidden" name="action" value="eliminar"/>
                            <input class="btn btn-danger" type="submit" value="Eliminar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
