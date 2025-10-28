package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Componente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tipo;
    private long limiteUsos;
    private long vecesUsado;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coche_id")
    @ToString.Exclude
    private Coche coche;

}
