package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Carrera;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.TipoComponente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CarreraService;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.CocheService;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;
import org.springframework.web.bind.annotation.RequestBody;






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

        List<String> tipos = List.of(
        "Motor", "Turbo", "Bateria", "Caja de Cambios", "Neumaticos", 
        "Aleron", "Paragolpes", "Suspension", "Direccion");

        model.addAttribute("tipos", tipos);
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

        List<Componente> todosComponentes = componenteService.listaComponentes();
        model.addAttribute("componente", todosComponentes);  

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
    
    @GetMapping("/garaje/nuevo")
    public String crearCoche(Model model) {
        
        model.addAttribute("tipos", TipoComponente.values());


        List<Componente> componentes = componenteService.componentesSinCoche();
        model.addAttribute("componentes",componentes );
        model.addAttribute("coche", new Coche());
        return "agregarCoche";
    }
    
    @PostMapping("/garaje/nuevo/crear")
    public String crearCoche(@Validated @ModelAttribute("coche")  Coche  coche,BindingResult bindingResult,@RequestParam("componenteIds")List<Long> componenteIds,RedirectAttributes redirectAttributes, Model model) {
        


        List<Componente>componenteSeleccionado = componenteIds.stream()
        .map(id -> componenteService.findById(id))
        .filter(n -> n != null)
        .collect(Collectors.toList());
        coche.setComponentes(componenteSeleccionado);
        componenteSeleccionado.forEach(c -> c.setCoche(coche));


        if(cocheService.comprobarRepetirComponentes(coche)){
            bindingResult.rejectValue("componentes", "Error componentes", "No se pueden repetir componentes de un mismo tipo");

        }
        if(bindingResult.hasErrors()){
            return "agregarCoche";

        }

        try{
            coche.setPotencia(cocheService.calcularCaballos(coche));
            cocheService.guardar(coche);
            redirectAttributes.addFlashAttribute("Bien", "Coche creado con exito");
            return "redirect:/garaje";

        } catch( Exception e){
            redirectAttributes.addFlashAttribute("Error",  "Error al crear el coche" + e.getMessage());
            return "redirect:/garaje/nuevo";
        }

        
    }
    
        @GetMapping("/garaje/editar/{id}")
        public String editarCoche(@PathVariable Long id, Model model){
            Coche coche = cocheService.buscarPorId(id);
            if (coche == null){
                return ("redirect:/garaje");
            }
            
            model.addAttribute( "coche", coche);

            return "coche/editar/{id}";
        }
        
        @PostMapping("/coche/editar/{id}/guardar")
        public String editarCoche(@ModelAttribute("coche") Coche coche) {
            
            cocheService.agregarCoche(coche);

            return "redirect:/garaje/{id}";
        }

        @GetMapping("/error")
        public String error(@RequestParam String param) {
            return new String();
        }
        

        @GetMapping("/coche/componentes/editar/{id}")
        public String gestionarComponentes(@PathVariable Long id, Model model) {
            Coche coche = cocheService.buscarPorId(id);
            model.addAttribute("coche", coche);
            List<Componente> componentes = componenteService.listaComponentes();
            model.addAttribute("componentes", componentes);
            model.addAttribute("showComponentModal", true); 
                return "garaje/detalle";
        }

        @PostMapping("/componente/gestionar/{id}/guardar")
        public String gestionarComponentes(@PathVariable Long id, @RequestParam(value = "componenteIds", required = false) List<Long> componenteIds, 
        RedirectAttributes redirectAttributes ) {
        
          try{
            Coche coche = cocheService.buscarPorId(id);
            if(coche == null){
                redirectAttributes.addFlashAttribute("errorComponentes","No se ha encontrado el coche");
                return "redirect:/garaje/{id}";
            }

            

            List<Componente> nuevosComponentes = new ArrayList<>();
            if( componenteIds != null && !componenteIds.isEmpty()){
                nuevosComponentes = componenteService.crearListaConIds(componenteIds);
                nuevosComponentes = cocheService.comprobarNuevosNulo(nuevosComponentes);
            }


            if(cocheService.comprobarRepetirComponentes(coche)){
                redirectAttributes.addFlashAttribute("errorComponentes", "No se puede repetir un componente");
                redirectAttributes.addFlashAttribute("showComponentModal",true);
                return "redirect:/garaje/{id}";
            }

            cocheService.reemplazarComponentes(coche, nuevosComponentes);

            coche.setPotencia(cocheService.calcularCaballos(coche));
            cocheService.guardar(coche);

            redirectAttributes.addFlashAttribute("exitoComponentes","Componente actuzalizados con exito");
            }catch(Exception e){
                redirectAttributes.addFlashAttribute("errorComponentes", "Error al actualizar los componentes" + e.getMessage());
                redirectAttributes.addFlashAttribute("showComponentModal", true);
             }
            return "redirect:/garaje/{id}";
        }
        
        
        







  }

