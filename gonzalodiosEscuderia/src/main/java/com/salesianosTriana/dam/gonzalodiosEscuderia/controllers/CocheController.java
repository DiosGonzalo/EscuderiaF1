package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Carrera;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CarreraService;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CocheService;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;



@Controller
public class CocheController {
    private final CocheService cocheService;
    private final ComponenteService componenteService;
    private final CarreraService carreraService;

    public CocheController(CocheService cocheService, ComponenteService componenteService, CarreraService carreraService) {
        this.cocheService = cocheService;
        this.componenteService = componenteService;
        this.carreraService = carreraService;
    }

    @GetMapping("/")
    public String heroSection(Model model){
        return "heroSection";
    }

    @GetMapping("/dashboard") 
    public String index(Model model){
        List<Coche> coches = cocheService.listaCompleta();
        model.addAttribute("coches", coches); 

       List<Componente> componentes = componenteService.listaComponentes();
        model.addAttribute("componente", componentes);

        List<Carrera> carreras = carreraService.todasCarreras();
        model.addAttribute("carrera", carreras);

        return "dashboard";
    }
    @GetMapping("/garaje")
    public String GarajeDefault() {
        return "redirect:/garaje/1";
    }
    
    @GetMapping("/garaje/{id}")
    public String Garaje(@PathVariable Long id, Model model) {

        List<Coche> coches = cocheService.listaCompleta();
        model.addAttribute("coches", coches);

        List<Componente> componentes = componenteService.componentesCoche(cocheService.buscarPorId(id));
        model.addAttribute("componentesCoche",componentes);
        
        Coche coche = cocheService.buscarPorId(id);
        model.addAttribute("coche", coche);

        List<Carrera> carreras = carreraService.carrerasCoche(cocheService.buscarPorId(id));
        model.addAttribute("carreras", carreras);

        String estadoCoche = cocheService.estadoCoche(id);
        model.addAttribute("estadoCoche", estadoCoche);


        return "garaje";
    }
    
  }

