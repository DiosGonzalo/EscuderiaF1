package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.TipoComponente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.CocheRepositorio;

@Service
public class CocheService {
   private final CocheRepositorio repo;
   public CocheService(CocheRepositorio repo) {
       this.repo = repo;
   }

    public List<Coche> listaCompleta() {
        return repo.findAll();
    }

    public Coche buscarPorId(Long id){
        return repo.findAll().stream()
        .filter(n -> Objects.equals(n.getId(), id))
        .findFirst()
        .orElse(null);
    }
    

    public String estadoCoche(Long id){
        double desgasteComponentes = 0;
        double desgasteTotal;
        String estado;
        List<Componente> componentes  = buscarPorId(id).getComponentes();
        for(Componente componente : componentes){
            desgasteComponentes+= componente.getEstado();
        }
        desgasteTotal = desgasteComponentes/componentes.size();
        return desgasteTotal <=  30 ?  "Bueno"
        : desgasteTotal > 30 && desgasteTotal < 60 ? "Regular"
        : "Mal";

        
    }
    public void agregarCoche(Coche coche){
        repo.save(coche);
    }

    public double calcularCaballos(Coche coche){
        double caballos = coche.getPotencia();
        for(Componente componente : coche.getComponentes()){
            caballos += componente.getCaballos();
        }
        return caballos;
    }
    public boolean comprobarRepetirComponentes( Coche coche){
        List<Componente> componentes = coche.getComponentes();
    
        Set<TipoComponente> tiposUnicos = componentes.stream()
        .map(Componente::getTipo) 
        .collect(Collectors.toSet()); 

        return tiposUnicos.size() != componentes.size();
    }
    
    

    public void guardar(Coche coche){
        repo.save(coche);
    }

    public void guardarComponentes(List<Componente> componentes, Coche coche){
        coche.setComponentes(componentes);
    }   
    public void reemplazarComponentes(Coche coche, List<Componente> nuevosComponentes){
        if(coche.getComponentes() != null && !coche.getComponentes().isEmpty()){

            List<Componente> copia = new ArrayList<>(coche.getComponentes());
            copia.forEach(c -> c.setCoche(null));
            coche.getComponentes().clear();
        }

         if(nuevosComponentes != null && !nuevosComponentes.isEmpty()){
            nuevosComponentes.forEach(n ->{
            n.setCoche(coche);
            coche.getComponentes().add(n);
            
            });
         }
    }


    

    public List<Componente> comprobarNuevosNulo(List<Componente> componentes){
       
       
        return componentes.stream()
        .filter(n -> n != null)
        .collect(Collectors.toList());


    }

    public void actualizarComponentes(Coche coche, List<Componente> nuevosComponentes){
        nuevosComponentes.stream()
        .forEach(n ->{
            if(!coche.getComponentes().contains(n)){
            n.setCoche(coche);
            coche.getComponentes().add(n);
        }
        });

    }
}

