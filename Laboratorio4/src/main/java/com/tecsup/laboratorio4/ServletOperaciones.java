package com.tecsup.laboratorio4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/operaciones")
public class ServletOperaciones extends HttpServlet  {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        double num1 = Double.parseDouble(request.getParameter("num1"));
        double num2 = Double.parseDouble(request.getParameter("num2"));
        String op = request.getParameter("op");

        double resultado = 0;
        String operacion = "";

        switch (op) {
            case "suma":
                resultado = num1 + num2;
                operacion = "Suma";
                break;
            case "resta":
                resultado = num1 - num2;
                operacion = "Resta";
                break;
            case "multiplicacion":
                resultado = num1 * num2;
                operacion = "Multiplicación";
                break;
            case "division":
                if (num2 == 0) {
                    out.println("<h1>Error: División por cero</h1>");
                    return;
                }
                resultado = num1 / num2;
                operacion = "División";
                break;
            default:
                out.println("<h1>Error de operación</h1>");
                return;
        }

        out.println("<!DOCTYPE html><html><body>");
        out.println("<h1>Resultado de la " + operacion + "</h1>");
        out.println("<p>" + num1 + " " + simboloOperacion(op) + " " + num2 + " = " + resultado + "</p>");
        out.println("</body></html>");
    }

    private String simboloOperacion(String op) {
        switch (op) {
            case "suma": return "+";
            case "resta": return "-";
            case "multiplicacion": return "*";
            case "division": return "/";
            default: return "?";
        }
    }
}
