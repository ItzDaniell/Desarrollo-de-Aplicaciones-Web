<%@ page import="com.tecsup.demo.model.Curso" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Curso Actualizar</title>
</head>
<body>
<%
    // Asegúrate de que el objeto 'curso' se obtiene correctamente desde el request
    Curso curso =  (Curso) request.getAttribute("curso");
%>
<jsp:include page="master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">ACTUALIZAR CURSO</h3>
                </div>
                <div class="card-body">
                    <form action="cController?action=actualizar" method="post">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Código</label>
                            <input class="form-control" type="text" name="codigo" id="nomcli" placeholder="Código"
                                   value="<%=curso.getCodigo() %>" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombre</label>
                            <input class="form-control" type="text" name="nombre" id="apecli" placeholder="Nombre"
                                   value="<%=curso.getNombre() %>" autofocus required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text">Créditos</label>
                            <input class="form-control" type="text" name="creditos" id="txtCreditos"
                                   value="<%=curso.getCreditos() %>" placeholder="Créditos" required>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input name="accion" type="hidden" value="actualizar"/>
                            <input class="btn btn-success" type="submit" value="Actualizar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
