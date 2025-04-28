<%@ page import="com.tecsup.demo.model.Curso" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Curso Eliminar</title>
</head>
<%
  Curso curso =  (Curso) request.getAttribute("curso");
%>
<body>
<jsp:include page="master.jsp" />

<div class="container p-4" style="margin-top:70px ;">
  <div class="row">
    <div class="col-md-4 mx-auto">
      <div class="card text-center">
        <div class="card-header">
          <h3 class="text-uppercase">ELIMINAR CLIENTE</h3>
        </div>
        <div class="card-body">
          <form action="cController?action=eliminar" method="post">
            <div class="input-group mt-2">
              <label class="input-group-text">Còdigo</label>
              <input class="form-control" type="text" name="txtCodigo"
                     value="<%=curso.getCodigo() %>" readonly>
            </div>

            <div class="input-group mt-2">
              <label  class="input-group-text">Nombre</label>
              <input class="form-control" type="text" name="txtNombre"
                     value="<%=curso.getNombre() %>" readonly>
            </div>

            <div class="input-group mt-2">
              <label class="input-group-text">Credito</label>
              <input class="form-control" type="text" name="txtCreditos" id="txtCreditos"
                     value="<%=curso.getCreditos() %>" readonly>
            </div>

            <div class="form-group mt-4 d-grid gap-2">
              <input name="accion" type="hidden" value="eliminar"/><br/>
              <input class="btn btn-success" type="submit" value="eliminar"/>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>