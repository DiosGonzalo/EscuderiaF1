package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;


import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.ComponenteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public String desgasteComponente(long id){
        Componente componenteDevolver= null;
        for(Componente componente : repo.findAll().stream().toList()){
            if(componente.getId() == id){
                componenteDevolver = componente;
            }
        }
        return componenteDevolver.getEstado();
    }











}
