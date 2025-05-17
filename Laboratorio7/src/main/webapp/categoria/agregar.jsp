<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Categoría</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h3 class="text-center mb-4">Registrar Nueva Categoría</h3>
            <form action="cController" method="post">
                <div class="input-group mb-3">
                    <label class="input-group-text">Nombre Categoría</label>
                    <input type="text" name="txtNombreCategoria" class="form-control" required>
                </div>
                <div class="input-group mb-3">
                    <label class="input-group-text">Descripción de la categoría</label>
                    <input type="text" name="txtDescripcionCategoria" class="form-control" required>
                </div>
                <div class="form-group mt-4 d-grid gap-2">
                    <input type="hidden" name="action" value="agregar" />
                    <button type="submit" class="btn btn-success">Registrar</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
