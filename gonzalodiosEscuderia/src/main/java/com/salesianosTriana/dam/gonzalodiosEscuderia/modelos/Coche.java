package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Coche {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String modelo;
    private String piloto;
    @ManyToMany (mappedBy = "coches")
    private List<Carrera> carreras = new ArrayList<>();

    @OneToMany( mappedBy = "coche", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<Componente> componentes = new ArrayList<>();





}
