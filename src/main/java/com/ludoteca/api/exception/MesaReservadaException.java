package com.ludoteca.api.exception;

import lombok.Getter;

@Getter
public class MesaReservadaException extends RuntimeException {
    private final String code = "MESA_RESERVADA";

    public MesaReservadaException(String message) {
        super(message);
    }
}
