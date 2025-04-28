package com.tecsup.laboratorio4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/Ejercicio1")
public class Ejercicio1Servlet extends HttpServlet  {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
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
        out.println("<!DOCTYPE html><html><body>");
        out.println("<h1>Nombre: " + nombre + "</h3>");
        out.println("<h3>Tipo de Colegio: " + colegio + "</h3>");
        out.println("<h3>Categoria Peteneciente: " + categoria + "</h3>");
        out.println("<h3>Pensión Actual: " + pension + "</h3>");
        out.println("<h3>Descuento: " + descuento + "</h3>");
        out.println("<h3>Pensión Final: " + total + "</h3>");
        out.println("</body></html>");
    }
}