<%@ page import="com.tecsup.pfr_crud_jakarta.model.entities.Administrador" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.model.entities.Curso" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.services.CursoService" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.services.impl.CursoServiceImpl" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.services.AlumnoService" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.services.impl.AlumnoServiceImpl" %>
<%@ page import="com.tecsup.pfr_crud_jakarta.model.entities.Alumno" %>
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
        String sid= request.getParameter("id");
        AlumnoService servicio = new AlumnoServiceImpl();
        Alumno alumno = servicio.buscar(sid);
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alumno Actualizar - <%=nombre %></title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container p-4" style="margin-top:70px ;">
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">ACTUALIZAR ALUMNO</h3>
                </div>
                <div class="card-body">
                    <form action="aController">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Còdigo</label>
                            <input class="form-control" type="text" name="txtCodigo" id="Código" placeholder="Código"
                                   value="<%=alumno.getCodigo() %>" readonly>
                        </div>

                        <div class="input-group mt-2">
                            <label  class="input-group-text">Nombre</label>
                            <input class="form-control" type="text" name="txtNombre" id="Nombre" placeholder="Nombre"
                                   value="<%=alumno.getNombre() %>" autofocus required>
                        </div>

                        <div class="input-group mt-2">
                            <label  class="input-group-text">Apellidos</label>
                            <input class="form-control" type="text" name="txtApellido" id="Apellidos" placeholder="Apellidos"
                                   value="<%=alumno.getApellido() %>" autofocus required>
                        </div>

                        <div class="input-group mt-2">
                            <label  class="input-group-text">Fecha de Nacimiento</label>
                            <input class="form-control" type="date" name="txtFechaNac" id="FechaNac" placeholder="Fecha de Nacimiento"
                                   value="<%=alumno.getFecha_nacimiento() %>" autofocus required>
                        </div>

                        <div class="input-group mt-2">
                            <label class="input-group-text" for="sexo">Sexo</label>
                            <select class="form-select" name="txtSexo" id="sexo" required>
                                <option value="">Seleccione...</option>
                                <option value="M" <%= alumno.getSexo().equals("M") ? "selected" : "" %>>Masculino</option>
                                <option value="F" <%= alumno.getSexo().equals("F") ? "selected" : "" %>>Femenino</option>
                            </select>
                        </div>

                        <div class="form-group mt-4 d-grid gap-2">
                            <input name="accion" type="hidden" value="actualizar"/><br/>
                            <input class="btn btn-success" type="submit" value="actualizar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<% } %>
</html>



