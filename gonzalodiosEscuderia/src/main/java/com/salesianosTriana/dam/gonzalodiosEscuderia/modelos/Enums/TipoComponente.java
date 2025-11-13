package com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Enums;

public enum TipoComponente {
    MOTOR,
    TURBO,
    BATERIA,
    CAJA_DE_CAMBIOS,
    NEUMATICOS,
    ALERON,
    PARAGOLPES,
    SUSPENSION,
    DIRECCION;


    public String getNombreDisplay() {
        return this.name().replaceAll("_", " ");
    }
}
