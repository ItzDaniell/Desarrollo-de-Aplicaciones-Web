<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Categoría</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h3 class="text-center mb-4">Editar Categoría</h3>
            <form action="cController" method="post">

                <div class="input-group mt-2">
                    <label for="txtNombreCategoria" class="input-group-text">Nombre de la Categoría</label>
                    <input type="text" id="txtNombreCategoria" name="txtNombreCategoria" class="form-control"
                           value="${categoria.nombreCategoria}" required>
                </div>

                <div class="input-group mt-2">
                    <label for="txtDescripcionCategoria" class="input-group-text">Descripción</label>
                    <input type="text" id="txtDescripcionCategoria" name="txtDescripcionCategoria" class="form-control"
                           value="${categoria.descripcionCategoria}" required>
                </div>

                <div class="form-group mt-4 d-grid gap-2">
                    <input type="hidden" name="action" value="actualizar"/>
                    <input type="hidden" name="txtCodigo" value="${categoria.idCategoria}"/>
                    <button type="submit" class="btn btn-warning">Actualizar</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
