package com.ludoteca.api.enums;

public enum Dificultad {
    FACIL,
    MEDIO,
    DIFICIL,
    EXPERTO;

    public static Dificultad contieneEnum(final String estado) {
        for (Dificultad valor : Dificultad.values()) {
            if (valor.name().equalsIgnoreCase(estado)) {
                return valor;
            }
        }
        return null;
    }
}
