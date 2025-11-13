package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums;

public enum Dificultad {
    FACIL,
    MEDIA,
    DIFICIL;

    public String getNombreDisplay() {
        return this.name().replaceAll("_", " ");
    }


}
