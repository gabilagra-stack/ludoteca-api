package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class TurnoNoEncontradoException extends RuntimeException {
    private final String code = "TURNO_NO_ENCONTRADO";

    public TurnoNoEncontradoException(String message) {
        super(message);
    }
}
