package com.ludoteca.api.exception;

import lombok.Data;

@Data
public class UsuarioNoEncontradoException extends RuntimeException{
    private final String code = "USUARIO_NO_ENCONTRADO";

    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
}
