<%--
  Created by IntelliJ IDEA.
  User: ItzDaniel
  Date: 2/04/2025
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>Listado de Estudiantes</title>
</head>
<body>
<h1>Listado de Estudiantes con PreparedStatement</h1>
<table border="1">
    <tr>
        <th>Código</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Fecha de Nacimiento</th>
        <th>Sexo</th>
    </tr>
    <%
        String url = "jdbc:mysql://localhost/escuela?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "" ;
        String query = "SELECT * FROM alumno";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    String id = rs.getString(1);
                    String nombre = rs.getString(2);
                    String apellido = rs.getString(3);
                    Date fecha_nac = rs.getDate(4);
                    String sexo = rs.getString(5);
    %>
    <tr>
        <td><%= id %></td>
        <td><%= nombre %></td>
        <td><%= apellido %></td>
        <td><%= fecha_nac %></td>
        <td><%= sexo %></td>
    </tr>
    <%
                }
            }
        }catch (ClassNotFoundException e1){
            out.println("<p style='color:red;'>No se encontró el driver</p>");
            e1.printStackTrace();
        }catch (SQLException e2){
            out.println("<p style='color:red;'>No se conectó a la base de datos</p>");
            e2.printStackTrace();
        }
    %>
</body>
</html>
