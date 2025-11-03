package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;

import java.util.List;

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

   
    public Coche buscarPorId(Long id){
        return repo.findAll().stream()
        .filter(n -> n.getId() == id)
        .toList()
        .getFirst();
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
}
