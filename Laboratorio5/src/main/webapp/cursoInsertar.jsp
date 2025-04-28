<%@ page import="com.tecsup.pfr_crud_jakarta.model.entities.Administrador" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%
    HttpSession misesion = request.getSession();
    if(misesion.getAttribute("eladministrador")==null){
        response.sendRedirect("error.jsp");
    }else{
        Administrador adm = (Administrador)misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nuevo Curso</title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container p-4" style="margin-top:70px ;">
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">CREAR CURSO</h3>
                </div>
                <div class="card-body">
                    <form action="cController">
                        <div class="input-group mt-2">
                            <label  class="input-group-text">Código:</label>
                            <input class="form-control" type="text" name="txtCodigo" id="nomcli" placeholder="Código"
                                   autofocus required>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombres:</label>
                            <input class="form-control" type="text" name="txtNombre" id="apecli" placeholder="Nombres"
                                   required>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Creditos</label>
                            <input class="form-control" type="text" name="txtCreditos" id="nrodnicli"
                                   placeholder="Creditos" required>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input name="accion" type="hidden" value="insertar"/><br/>
                            <input class="btn btn-success" type="submit" value="Insertar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<% } %>
</body>
</html>




