package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Carrera;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.CarreraRepository;

@Service
public class CarreraService {
    private CarreraRepository repo;

     public CarreraService(CarreraRepository repo) {
        this.repo = repo;
    }
    public List<Carrera> todasCarreras(){
        return repo.findAll().stream()
        .collect(Collectors.toList());
    }
   
public List<Carrera> carrerasCoche(Coche coche){
    return repo.findAll().stream()
    .filter(n->n.getCoches().stream()
    .filter(c->c.getId() == coche.getId())
    .count()> 0)
    .toList();
}

}
