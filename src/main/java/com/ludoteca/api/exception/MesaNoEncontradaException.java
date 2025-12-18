package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class MesaNoEncontradaException extends RuntimeException {
    private final String code = "MESA_NO_ENCONTRADA";

    public MesaNoEncontradaException(String message) {
        super(message);
    }
}
