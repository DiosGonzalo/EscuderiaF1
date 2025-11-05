package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComponenteController {

    @GetMapping ("/almacen")
    public String inventario(Model model){
        return "almacen";
    }
}
