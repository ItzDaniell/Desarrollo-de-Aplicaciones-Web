package com.tecsup.laboratorio4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/Ejercicio2")
public class Ejercicio2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nombre, estado;
        double bonificacion = 0, bonificacion_hijos = 0, bonificacion_total = 0, sueldo = 0;
        int hijos = 0;
        nombre = request.getParameter("nombre");
        estado = request.getParameter("estado");
        sueldo = Double.parseDouble(request.getParameter("sueldo"));
        hijos = Integer.parseInt(request.getParameter("hijos"));
        switch (estado) {
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
        if (estado.equals("Soltero")) {
            bonificacion_hijos = 0;
            sueldo = sueldo + sueldo * bonificacion;
        } else {
            if (hijos * 0.015 >= 0.6) {
                bonificacion_hijos = 0.6;
            } else {
                bonificacion_hijos = hijos * 0.015;
            }
            bonificacion_total = (bonificacion_hijos + bonificacion + (bonificacion * bonificacion_hijos) / 2);
            sueldo = sueldo + sueldo * bonificacion_total;
        }
        out.println("<!DOCTYPE html><html><body>");
        out.println("<h1>Nombre: " + nombre + "</h3>");
        out.println("<h3>Estado Civil: " + estado + "</h3>");
        out.println("<h3>Cantidad de Hijos: " + hijos + "</h3>");
        out.println("<h3>Bonificacion: " + bonificacion_total*100 + "%" + "</h3>");
        out.println("<h3>Sueldo final: " + sueldo + "</h3>");
        out.println("</body></html>");
    }
}
