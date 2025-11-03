package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.ComponenteRepository;

@Service
public class ComponenteService {

    private final ComponenteRepository repo;
    
    public ComponenteService(ComponenteRepository repo) {
        this.repo = repo;
    }

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

    public void necesitaCambio(Coche coche){
        for (Componente componente : componentesCoche(coche)){
            if (componente.getEstado() < 40){
                System.out.println(componente + "Necesita cambio");
            }else{
                System.out.println(componente);
            }
        }
    }
    public Componente buscarNombreComponenteCoche( String nombre, Coche coche){
        return repo.findAll().stream()
        .filter(n -> n.getCoche().equals(coche))
        .filter(n-> n.getNombre().equals(nombre))
        .toList()
        .getFirst();
    }

}













