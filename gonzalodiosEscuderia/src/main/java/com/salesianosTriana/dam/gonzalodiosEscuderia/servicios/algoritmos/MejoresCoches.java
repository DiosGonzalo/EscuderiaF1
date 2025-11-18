package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.algoritmos;

import java.util.*;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums.TipoComponente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Service
public class MejoresCoches {
    Random random = new Random();
    private final ComponenteService componenteService;

public List<Componente> generarConfiguracionAleatoria(Map<TipoComponente, List<Componente>> porTipo) {

    //Creo una lista de componentes aleatorios, par aun coche, como no se pueden repetir cojo un componente de cada tipo, el map seria asi motor = [motor 1, y asi]
    List<Componente> configuracion = new ArrayList<>();

    for (List<Componente> componentes : porTipo.values()) {
        //El rnadom coje un indice random de la lista y ese componente es el que metera
        Componente elegido = componentes.get(random.nextInt(componentes.size()));
        configuracion.add(elegido);
    }

    return configuracion;
}

public double evaluarConfiguraciones(List<Componente> componentes) {
    double potencia = 0, peso = 0, downforce = 0, drag = 0, estado = 0;

    for (Componente c : componentes) {
        potencia += c.getCaballos();
        peso += c.getPeso();
        downforce += c.getDownforce();
        drag += c.getDrag();
        estado += c.getEstado();
    }
    //cojemos los atributos de la lista de componentes que nos ha dado el metodo de antes y le damos una puntuacion, que sera la puntuacion de esa build
    estado /= componentes.size(); 

    return (potencia * 0.6) + (downforce * 0.3) + (estado * 0.2)
         - (peso * 0.4) - (drag * 0.3);
}

    //Aqui cogemos la lista de elementos que hemso creado, de manera aleatoria elegimos un componente a cambiar y dentro de ese tipo uno aleatorio y se lo metemos
    public List<Componente> variacionesConfiguracion(List<Componente> original, Map<TipoComponente,List<Componente>> porTipo){
       
        List<Componente> copia = new ArrayList<>(original);

        int indexCambiado = (int) (Math.random() * copia.size());
        TipoComponente tipo = copia.get(indexCambiado).getTipo();


        List<Componente> posibles = porTipo.get(tipo);
        Componente nuevo = posibles.get(random.nextInt(posibles.size()));

        copia.set(indexCambiado, nuevo);


        return copia;
    }


    public List<List<Componente>> copiarElite(List<List<Componente>> poblacion, int cantidadElite) {
        List<List<Componente>> elite = new ArrayList<>();

        for (int i = 0; i < cantidadElite; i++) {
            // Copiamos cada configuración
            elite.add(new ArrayList<>(poblacion.get(i)));
        }

        return elite;
    }

    private List<List<Componente>> generarHijos(
            List<List<Componente>> elite,
            Map<TipoComponente,List<Componente>> porTipo,
            int cantidad) {

        List<List<Componente>> hijos = new ArrayList<>();

        while (hijos.size() < cantidad) {
            // Elegir padre aleatorio de la élite
            List<Componente> padre = elite.get(random.nextInt(elite.size()));

            // Crear hijo con variación
            hijos.add(variacionesConfiguracion(padre, porTipo));
        }

        return hijos;
    }
    //Creamos muchos coches con el primer metodo, filtro los mejores, a los mejores les hace cambios y los vuelve a filtrar y despues de muchos cambios te da los mejores
    public List<List<Componente>> optimizarConAlgGenetico(
            Map<TipoComponente,List<Componente>> porTipo,
            int generaciones,
            int tamPoblacion,
            int cantidadCoches) {

        // 1. Crear población inicial aleatoria
        List<List<Componente>> poblacion = new ArrayList<>();
        for (int i = 0; i < tamPoblacion; i++) {
            poblacion.add(generarConfiguracionAleatoria(porTipo));
        }

        // 2. Evolucionar durante N generaciones
        for(int gen = 0; gen < generaciones; gen++) {

            // 2.1 Ordenar por fitness (mejor primero)
            poblacion.sort(Comparator.comparingDouble(this::evaluarConfiguraciones).reversed());

            // 2.2 Seleccionar los mejores (élite = 20%)
            int cantidadElite = Math.max(2, tamPoblacion / 5);
            List<List<Componente>> elite = copiarElite(poblacion, cantidadElite);

            // 2.3 Crear nueva población: élite + hijos
            List<List<Componente>> nuevaPoblacion = new ArrayList<>(elite);
            int hijosNecesarios = tamPoblacion - cantidadElite;
            nuevaPoblacion.addAll(generarHijos(elite, porTipo, hijosNecesarios));

            poblacion = nuevaPoblacion;
        }

        // 3. Ordenar resultado final y retornar los mejores
        poblacion.sort(Comparator.comparingDouble(this::evaluarConfiguraciones).reversed());

        return poblacion.subList(0, Math.min(cantidadCoches, poblacion.size()));
    }
}
