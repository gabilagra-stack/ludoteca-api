package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class JuegoNoEncontradoException extends RuntimeException {
    private final String code = "JUEGO_NO_ENCONTRADO";

    public JuegoNoEncontradoException(String message) {
        super(message);
    }
}
