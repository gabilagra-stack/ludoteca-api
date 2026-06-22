package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class EventoNoEncontradoException extends RuntimeException {
    private final String code = "EVENTO_NO_ENCONTRADO";

    public EventoNoEncontradoException(String message) {
        super(message);
    }
}
