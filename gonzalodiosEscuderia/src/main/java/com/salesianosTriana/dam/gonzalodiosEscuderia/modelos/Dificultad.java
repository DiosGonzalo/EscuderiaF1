package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos;

public enum Dificultad {
    FACIL,
    MEDIA,
    DIFICIL;

    public String getNombreDisplay() {
        return this.name().replaceAll("_", " ");
    }


}
