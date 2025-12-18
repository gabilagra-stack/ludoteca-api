package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class ReservaNoEncontradaException extends RuntimeException {
    private final String code = "RESERVA_NO_ENCONTRADA";

    public ReservaNoEncontradaException(String message) {
        super(message);
    }
}
