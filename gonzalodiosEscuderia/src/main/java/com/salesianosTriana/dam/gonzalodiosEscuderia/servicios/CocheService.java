package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios;

import java.util.*;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums.TipoComponente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios.CocheRepositorio;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.algoritmos.MejoresCoches;

@Builder
@AllArgsConstructor
@Service
public class CocheService {
    private final CocheRepositorio repo;
    private final MejoresCoches mejoresCoches;

    public List<Coche> listaCompleta() {
        return repo.findAll();
    }

    // Mejor usar findById del repositorio
    public Coche buscarPorId(Long id){
        return repo.findById(id).orElse(null);
    }

    // Alternativa: devolver Optional para forzar al que llama a comprobar existencia
    public Optional<Coche> buscarPorIdOptional(Long id){
        return repo.findById(id);
    }

    public String estadoCoche(Long id){
        Coche coche = buscarPorId(id);
        if (coche == null) {
            // opción A: devolver mensaje controlado
            return "Coche no encontrado";
            // opción B (recomendada en APIs): lanzar excepción y dejar que el controlador la transforme en 404
            // throw new NoSuchElementException("Coche con id " + id + " no existe");
        }

        List<Componente> componentes = Optional.ofNullable(coche.getComponentes()).orElse(Collections.emptyList());
        if (componentes.isEmpty()) {
            return "Sin componentes";
        }

        double desgasteComponentes = componentes.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Componente::getEstado)
                .sum();

        double desgasteTotal = desgasteComponentes / componentes.size();

        return desgasteTotal <=  30 ?  "Bueno"
                : desgasteTotal < 60 ? "Regular"
                : "Mal";
    }

    public void agregarCoche(Coche coche){
        repo.save(coche);
    }

    public double calcularCaballos(Coche coche){
        double caballos = coche.getPotencia();
        for(Componente componente : Optional.ofNullable(coche.getComponentes()).orElse(Collections.emptyList())){
            if (componente != null) {
                caballos += componente.getCaballos();
            }
        }
        return caballos;
    }

    public boolean comprobarRepetirComponentes( Coche coche){
        List<Componente> componentes = Optional.ofNullable(coche.getComponentes()).orElse(Collections.emptyList());

        Set<TipoComponente> tiposUnicos = componentes.stream()
                .filter(Objects::nonNull)
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
        List<Componente> actuales = Optional.ofNullable(coche.getComponentes()).orElse(new ArrayList<>());
        actuales.forEach(c -> { if (c != null) c.setCoche(null); });
        actuales.clear();
        if (nuevosComponentes != null) {
            nuevosComponentes.forEach(c -> {
                c.setCoche(coche);
                actuales.add(c);
            });
            coche.setComponentes(actuales);
        } else {
            coche.setComponentes(new ArrayList<>());
        }
    }

    public List<Componente> comprobarNuevosNulo(List<Componente> componentes){
        if (componentes == null) {
            return new ArrayList<>();
        }
        return componentes.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<List<Componente>> sugerirMejoresComponentes(int topN){
        Map<TipoComponente, List<Componente>> porTipo = repo.findAll().stream()
                .flatMap(coche -> Optional.ofNullable(coche.getComponentes()).orElse(Collections.emptyList()).stream())
                .collect(Collectors.groupingBy(Componente::getTipo));

        return mejoresCoches.optimizarConAlgGenetico(porTipo, 100, 300, topN);
    }
}