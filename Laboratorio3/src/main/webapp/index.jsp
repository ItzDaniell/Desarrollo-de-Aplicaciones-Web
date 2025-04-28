<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Listado de cursos</h1>
<table border="1">
    <tr>
        <th>Código</th>
        <th>Nombre del Curso</th>
        <th>Crédito</th>
    </tr>
    <%
        String url = "jdbc:mysql://localhost:3306/escuela?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "";
        String query = "SELECT * FROM curso";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(url, username, password);
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    int credito = rs.getInt(3);
    %>
    <tr>
        <td><%= id %></td>
        <td><%= name %></td>
        <td><%= credito %></td>
    </tr>
    <%
                }
            }
        } catch (ClassNotFoundException e1) {
            out.println("<p style='color:red;'>No se encontró el driver</p>");
            e1.printStackTrace();
        } catch (SQLException e2) {
            out.println("<p style='color:red;'>No se conectó a la base de datos</p>");
            e2.printStackTrace();
        }
    %>
</table>
</body>
</html>