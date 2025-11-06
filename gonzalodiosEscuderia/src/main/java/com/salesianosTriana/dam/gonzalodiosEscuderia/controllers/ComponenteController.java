package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;


@Controller
public class ComponenteController {
    ComponenteService componenteService;



    @GetMapping("/almacen")
    public String getMethodName(Model model) {
        List<Componente> componentes = componenteService.listaComponentes();
        model.addAttribute("componentes", componentes);
        return "almacen";
    }
    
}
