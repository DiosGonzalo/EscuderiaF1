package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.ComponenteRepository;

@Service
public class ComponenteService {

    private ComponenteRepository repo;


    public List<Componente> listaComponentes(){
        return repo.findAll().stream()
                .toList();
    }
    public List<Componente> componentesCoche(Coche coche) {
        List<Componente> componentesCoche = new ArrayList<>();
        if (coche == null) return componentesCoche;
        for (Componente componente : listaComponentes()) {
            Coche c = componente.getCoche();
            if (c != null && c.equals(coche)) {
                componentesCoche.add(componente);
            }
        }
        return componentesCoche;
    }

    public double desgasteComponente(long id){
        Componente componenteDevolver= null;
        for(Componente componente : repo.findAll().stream().toList()){
            if(componente.getId() == id){
                componenteDevolver = componente;
            }
        }
        return componenteDevolver.getEstado();
    }

    public double desgasteMedioCoche(Coche coche){
        double desgasteTotal = 0;
        List<Componente> componentesCoche= new ArrayList<>();
        for( Componente componente : repo.findAll().stream().toList()){
            if(componente.getCoche().equals(coche)){
                desgasteTotal+=componente.getEstado();
                componentesCoche.add(componente);
            }
        }  
        return desgasteTotal/componentesCoche.size();
    }

    public double estadoGeneral(){
        double desgasteGeneral = 0;
        for(Componente componente : repo.findAll().stream().toList()){
            desgasteGeneral+= componente.getEstado();
        }
        return desgasteGeneral/repo.findAll().stream().toList().size();
    }

    public double cantidadComponentes(){
        return repo.findAll().stream()
        .toList()
        .size();
    }
    public List<Componente> buscarPorNombre(String nombre){
        List<Componente> buscados = new ArrayList<>();
        for(Componente componente : repo.findAll().stream().toList()){
            if(componente.getTipo().equals(nombre)){
                buscados.add(componente);
            }
        }
        return buscados;
    }
    public Componente buscarPorId(Long id){
        return repo.findById(id).stream().map();



    }
    
}












}
