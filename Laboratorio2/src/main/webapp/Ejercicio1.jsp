<%--
  Created by IntelliJ IDEA.
  User: ItzDaniel
  Date: 26/03/2025
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        String nombre = request.getParameter("nombre");
        String colegio = request.getParameter("colegio");
        String categoria = request.getParameter("categoria");
        double pension = Double.parseDouble(request.getParameter("pension"));
        double porcentaje = 0, total = 0 , descuento=0;

        if (colegio != null){
            if (colegio.equals("Nacional")){
                switch (categoria){
                    case("A"):
                        porcentaje = 0.5;
                        break;
                    case("B"):
                        porcentaje = 0.4;
                        break;
                    case("C"):
                        porcentaje = 0.3;
                        break;
                }
            }else{
                switch (categoria){
                    case("A"):
                        porcentaje = 0.25;
                        break;
                    case("B"):
                        porcentaje = 0.2;
                        break;
                    case("C"):
                        porcentaje = 0.15;
                        break;
                }
            }
        }
        descuento = pension * porcentaje;
        total = pension - descuento;
    %>
<section class="card">
    <div class="title">
        <h1 class="title">Ejercicio 1</h1>
    </div>
    <div class="response">
        <%
            out.println("Nombre: " + nombre + "<br>");
            out.println("Colegio: " + colegio + "<br>");
            out.println("Categoria: " + categoria + "<br>");
            out.println("Pension: " + pension + "<br>");
            out.println("Descuento: " + descuento + "<br>");
            out.println("Total: " + total);
        %>
    </div>
</section>
</body>
</html>
