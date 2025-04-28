<%--
  Created by IntelliJ IDEA.
  User: ItzDaniel
  Date: 26/03/2025
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Title</title>
</head>
<body>
<%
    String nombre, estado;
    double bonificacion = 0, bonificacion_hijos = 0, bonificacion_total = 0, sueldo = 0;
    int hijos = 0;
    nombre = request.getParameter("nombre");
    estado = request.getParameter("estado");
    sueldo = Double.parseDouble(request.getParameter("sueldo"));
    hijos = Integer.parseInt(request.getParameter("hijos"));
    switch (estado){
        case "Casado":
            bonificacion = 0.13;
            break;
        case "Viudo":
            bonificacion = 0.15;
            break;
        case "Soltero":
            bonificacion = 0.05;
            break;
    }
    if(estado.equals("Soltero")){
        bonificacion_hijos = 0;
        sueldo = sueldo + sueldo * bonificacion;
    }else{
        if (hijos * 0.015 >= 0.6){
            bonificacion_hijos = 0.6;
        }else{
            bonificacion_hijos = hijos * 0.015;
        }
        bonificacion_total = (bonificacion_hijos + bonificacion + (bonificacion * bonificacion_hijos)/2);
        sueldo = sueldo + sueldo*bonificacion_total;
    }

%>
<section class="card">
    <div class="title">
        <h1 class="title">Ejercicio 2</h1>
    </div>
    <div class="response">
        <%
            out.println("Nombre: "+nombre+"<br>");
            out.println("Numero de hijos: "+hijos+"<br>");
            out.println("Estado Civil: "+estado+"<br>");
            out.println("Sueldo total: "+sueldo+"<br>");
        %>
    </div>
</section>
</body>
</html>
