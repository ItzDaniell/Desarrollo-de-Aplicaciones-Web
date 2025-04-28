package com.tecsup.laboratorio4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/ListaDeCursos")
public class ConexionServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/escuela";
    private static final String user="root";
    private static final String password="";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                    Connection conn = DriverManager.getConnection(URL, user, password);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("Select * from curso");
            ) {
                out.println("<html>");
                out.println("<body>");
                out.println("<h1>Lista de Cursos</h1>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Código</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Credito</th>");
                out.println("</tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + "</td>");
                    out.println("<td>" + rs.getInt(3) + "</td>");
                }
                out.println("</table");
                out.println("</body>");
                out.println("</html>");
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}