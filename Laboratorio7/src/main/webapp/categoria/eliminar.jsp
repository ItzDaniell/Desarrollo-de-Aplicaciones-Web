<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Categoría</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">Eliminar Categoría</h3>
                </div>
                <div class="card-body">
                    <form action="cController" method="post">

                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombre de Categoría</label>
                            <input class="form-control" type="text" name="txtNombreCategoria" value="${categoria.nombreCategoria}" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Descripción de Categoría</label>
                            <input class="form-control" type="text" name="txtDescripcionCategoria" value="${categoria.descripcionCategoria}" readonly>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input type="hidden" name="action" value="eliminar"/>
                            <input type="hidden" name="txtCodigo" value="${categoria.idCategoria}" readonly/>
                            <button class="btn btn-danger" type="submit">Eliminar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
