package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Carrera;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CarreraService;


@Controller
public class CarreraController {
    CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping("/carreras")
    public String getMethodName(Model model) {
        List<Carrera> carreras = carreraService.todasCarreras();
        model.addAttribute("carreras", carreras);
        return "carreras";
    }
    



}
