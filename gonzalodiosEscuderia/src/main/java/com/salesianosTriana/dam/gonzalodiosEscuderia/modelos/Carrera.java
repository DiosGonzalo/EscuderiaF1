package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private LocalDate fecha;

    @ManyToMany (cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "carrera_coche",
            joinColumns = @JoinColumn(name = "carreras_id"),
            inverseJoinColumns = @JoinColumn( name = "coche_id")
    )
    private List<Coche> coches = new ArrayList<>();








}
