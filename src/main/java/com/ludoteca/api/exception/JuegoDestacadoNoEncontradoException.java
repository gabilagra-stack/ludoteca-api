package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class JuegoDestacadoNoEncontradoException extends RuntimeException {
    private final String code = "JUEGO_DESTACADO_NO_ENCONTRADO";

    public JuegoDestacadoNoEncontradoException(String message) {
        super(message);
    }
}
