package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
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

    //Metodo para ver si un coche esta listo, si mas de el 75 de los componentes esta por debajo del 50% no lo esta
    public boolean  estadoCoches(Long id){
        Coche coche = repo.findById(id).orElse(null);
        if (coche == null || coche.getComponentes() == null || coche.getComponentes().isEmpty()) {
            return false;
        }

        double componentesMalos = 0;
        componentesMalos =    coche.getComponentes().stream()
        .filter(n -> n.getEstado()<50)
        .collect(Collectors.toList())
        .size();

        double porcentajeMalos = (componentesMalos * 100.0) / coche.getComponentes().size();
        if (porcentajeMalos > 75){
            return false;
        } else {
            return true;
        }
    }
}
