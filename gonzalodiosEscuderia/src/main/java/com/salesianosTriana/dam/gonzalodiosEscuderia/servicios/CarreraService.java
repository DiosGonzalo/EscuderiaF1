package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Carrera;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.CarreraRepository;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.ComponenteRepository;

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
   
    

}
