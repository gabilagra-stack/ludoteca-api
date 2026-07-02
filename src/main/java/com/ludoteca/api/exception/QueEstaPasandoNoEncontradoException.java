package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class QueEstaPasandoNoEncontradoException extends RuntimeException {
    private final String code = "QUE_ESTA_PASANDO_NO_ENCONTRADO";

    public QueEstaPasandoNoEncontradoException(String message) {
        super(message);
    }
}
