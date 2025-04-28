<%--
  Created by IntelliJ IDEA.
  User: ItzDaniel
  Date: 27/03/2025
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.DecimalFormat" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    String nombre, categoria, prenda;
    int cantidad = 0, bonificacion = 0;
    double total = 0, descuento_impuesto = 0, descuento_seguro = 0, descuento_solidaridad = 0, descuento_total = 0;
    nombre = request.getParameter("nombre");
    categoria = request.getParameter("categoria");
    prenda = request.getParameter("prenda");
    cantidad = Integer.parseInt(request.getParameter("cantidad"));
    switch (prenda){
        case "polo":
            total = cantidad * 0.5;
            break;
        case "camisa":
            total = cantidad * 1;
            break;
        case "pantalon":
            total = cantidad * 1.5;
            break;
    }
    if (cantidad >= 700){
        switch (categoria) {
            case "A":
                bonificacion = 250;
                break;
            case "B":
                bonificacion = 150;
                break;
            case "C":
                bonificacion = 100;
                break;
            case "D":
                bonificacion = 50;
                break;
        }
        total = total + bonificacion;
    }
    descuento_impuesto = total * 0.09;
    descuento_seguro = (total - descuento_impuesto) * 0.02;
    descuento_solidaridad = (total - descuento_impuesto - descuento_seguro) * 0.01;
    descuento_total = descuento_solidaridad + descuento_seguro +descuento_impuesto;
%>
<section class="card">
    <div class="title">
        <h1 class="title">Ejercicio 3</h1>
    </div>
    <div class="response">
        <%
            if (descuento_total >= 20 ){
                out.println("Nombre del Trabajador: "+nombre+"<br>");
                if (bonificacion != 0){
                    out.println("Usted tiene una bonificación de: "+bonificacion+" soles <br>");
                }
                out.println("Su sueldo total es de: "+(total-20)+" soles");
            }else{
                out.println("Nombre del Trabajador: "+nombre+"<br>");
                if (bonificacion != 0){
                    out.println("Usted tiene una bonificación de: "+bonificacion+" soles <br>");
                }
                out.println("Descuento por impuesto: "+descuento_impuesto+" soles <br>");
                out.println("Descuento por seguro: "+descuento_seguro+" soles <br>");
                out.println("Descuento por solidaridad: "+descuento_solidaridad+" soles <br>");
                DecimalFormat df = new DecimalFormat("#.00");
                String numeroFormateado = df.format(total-descuento_total);
                out.println("Su sueldo total es de: "+(numeroFormateado)+" soles");
            }
        %>
    </div>
</section>
</body>
</html>
