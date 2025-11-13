package com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.algoritmos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums.TipoComponente;
import com.salesianosTriana.dam.gonzalodiosEscuderia.servicios.ComponenteService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MejoresCoches {
    Random random = new Random;
    private final ComponenteService componenteService;

public List<Componente> generarConfiguracionAleatoria(Map<TipoComponente, List<Componente>> porTipo) {

    //Creo una lista de componentes aleatorios, par aun coche, como no se pueden repetir cojo un componente de cada tipo, el map seria asi motor = [motor 1, y asi]
    List<Componente> configuracion = new ArrayList<>();

    for (List<Componente> componentes : porTipo.values()) {
        //El rnadom coje un indice random de la lista y ese componente es el que metera
        Componente elegido = componentes.get((int) (Math.random() * componentes.size()));
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
    public List<Componente> variacionesConfiguracion(List<Componente> original, Map<TipoComponente,List<Componente> porTipo){
       
        List<Componente> copia = List<>.of(original);

        int indexCambiado = (int) (Math.random() * copia.size());
        TipoComponente tipo = copia.get(indexCambiado).getTipo();


        List<Componente> posibles = porTipo.get(tipo);
        Componente nuevo = posibles.get((int) (Math.random() * posibles.size()));

        copia.set(indexCambiado, nuevo);


        return copia;
    }


    //Creamos muchos coches con el primer metodo, filtro los mejores, a los mejores les hace cambios y los vuelve a filtrar y despues de muchos cambios te da los mejores
    public List<Lista<Componente>> optimizarConAlgGenetico(Map<TipoComponente,List<Componente> porTipo,int generaciones, int tamPoblacion, int cantidadCoches){
        List<List<Componente>> poblacion = new ArrayList<>();
        for (int i = 0; i < tamPoblacion; i++) {
            poblacion.add(generarConfiguracionAleatoria(porTipo));
        }
        //Creo la cantidad de coches que paso por atributo ptamPoblacion, porque es un estuidio de la pobnlacion de coches
        for(int i = 0; i < generaciones; i++){


            poblacion.sort(Comparator<T>.comparingDouble(this::evaluarConfiguraciones).reversed())
            int elite = tamPoblacion/5;
            List<List<Componente>> nuevaPoblacion = new ArrayList<>(poblacion.subList(0, elite));

            while(nuevaPoblacion.size() < tamPoblacion){

                List<Componente> padre = poblacion.get(random.nextIn(elite));
                nuevaPoblacion.add(variacionesConfiguracion(padre, porTipo));
            }

            poblacion = nuevaPoblacion;


        }


        poblacion.sort(Comparator.comparingDouble(this::evaluarConfiguraciones).reversed())

        return poblacion.subList(0,Math.min(cantidadCoches,poblacion.size()));


    }

















    
}
