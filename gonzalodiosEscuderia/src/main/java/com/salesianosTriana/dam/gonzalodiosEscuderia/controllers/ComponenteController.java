package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.TipoComponente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;





@Controller
public class ComponenteController {
    ComponenteService componenteService;
    public ComponenteController(ComponenteService componenteService) {
        this.componenteService = componenteService;
    }


    @GetMapping("/almacen")
    public String getMethodName(Model model) {
        List<Componente> componentes = componenteService.listaComponentes();
        model.addAttribute("componentes", componentes);
        return "almacen";
    }


    @PostMapping("/almacen/buscarNombre")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {

       List<Componente> componentes = componenteService.buscarPorNombre(nombre);
        model.addAttribute("componentes", componentes);
        return "almacen";
    }
    
    @PostMapping("almacen/filtrar/tipo")
    public String filtrarTipo(@RequestParam("tipo") TipoComponente tipo, Model model) {
        List<Componente> componentesFiltrados = componenteService.filtrarPorTipo(tipo);
        model.addAttribute("componentes", componentesFiltrados);
        return "almacen";
    }
    
    @PostMapping("almacen/ordenarUsosMayor")
    public String ordenarMayor(Model model) {
        List<Componente> componentes = componenteService.ordenarUsosMasMenos();
        model.addAttribute("componentes", componentes);

        return "almacen";
    }

    @PostMapping("/almacen/ordenarUsosMenor")
    public String ordenarMenor(Model model) {
       List<Componente> componentes =  componenteService.ordenarUsosMenosMas();
       model.addAttribute("componentes", componentes);

        return "almacen";
    }        

    @PostMapping("/almacen/mejores")
    public String mejores(Model model) {
        List<Componente> componentes = componenteService.ordenarMejores();
        model.addAttribute("componentes", componentes);
        return "almacen";
    }
    
    @GetMapping("/almacen/componente/{id}")
    public String getComponenteDetalle(@PathVariable Long id, Model model) {
        Componente componente = componenteService.buscarPorId(id);
        
        if (componente == null) {
            // Redirigir al almacén si no se encuentra el componente
            return "redirect:/almacen";
        }
        
        model.addAttribute("componente", componente);
        return "infoComponente";
    }
    

    





    
}
