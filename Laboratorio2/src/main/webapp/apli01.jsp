<%--
  Created by IntelliJ IDEA.
  User: ItzDaniel
  Date: 26/03/2025
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Soy una página</h1>
    <h1>Expresiones</h1>
        <%= new java.util.Date() %>
    <br>
    <br>
    <%= Math.PI * 2 %>
    <h1>Scriptlets</h1>
    <%
        int i;
        for (i=1; i<=10; i++){
            out.println("Numero :"+i+"<br>");
        }
    %>
    <h1>Declaraciones</h1>
    <%! String ciclo ="4to"; String turno = "Mañana"; %>
    <%
        if(ciclo.equals("4to") && (turno.equals("Mañana"))){
            out.println("Ganó una laptop");
        }else{
            out.println("No ganaste nada baoso");
        }
    %>

</body>
</html>
