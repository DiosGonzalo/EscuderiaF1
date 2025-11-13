package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos;


import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums.TipoComponente;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Componente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    @Enumerated(EnumType.STRING)
    private TipoComponente tipo;
    private long limiteUsos;
    private long vecesUsado;
    private double estado;
    private double caballos;

    private double peso;         
    private double downforce;    
    private double drag;         
    private double gripSeco;     
    private double gripLluvia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coche_id")
    @ToString.Exclude
    private Coche coche;

}
