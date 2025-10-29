package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CarreraService;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CocheService;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;


@Controller
public class CocheController {
    private CocheService cocheService;
    private ComponenteService componenteService;
    private CarreraService carreraService;

    @GetMapping("/")
    public String heroSection(Model model){
        return "heroSection";
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Coche> coches = CocheService.listaCompleta();
        model.addAttribute("Coche",coches);
        List<Componente> componentes = componenteService.listaComponentes();
        model.addAttribute("Componente",componentes);
        Double cantidadComponentes= componenteService.cantidadComponentes();
        return "dashboard";
    }


    
    

}
