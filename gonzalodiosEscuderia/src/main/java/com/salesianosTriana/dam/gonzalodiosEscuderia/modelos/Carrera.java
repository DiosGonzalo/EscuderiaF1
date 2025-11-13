package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums.Clima;
import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums.Dificultad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Date fecha;
    private String imagen;
    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;
    private Long numeroVueltas;
    @Enumerated (EnumType.STRING)
    private Clima clima;
    @ManyToMany (cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "carrera_coche",
            joinColumns = @JoinColumn(name = "carreras_id"),
            inverseJoinColumns = @JoinColumn( name = "coche_id")
    )
    private List<Coche> coches = new ArrayList<>();

    








}
