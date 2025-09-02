package com.ludoteca.api.exception;

public class UsuarioYaExisteException extends RuntimeException{
    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}
