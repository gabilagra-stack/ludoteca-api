package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class FechaInvalidaException extends RuntimeException {
    private final String code = "FECHA_INVALIDA";

    public FechaInvalidaException(String message) {
        super(message);
    }
}
