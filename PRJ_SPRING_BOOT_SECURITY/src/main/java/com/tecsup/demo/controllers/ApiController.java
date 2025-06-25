package com.tecsup.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @CrossOrigin(origins = "https://miapp.com")
    @GetMapping("/datos")
    public String obtenerDatos() {
        return "¡Hola desde la API con CORS!";
    }
}
